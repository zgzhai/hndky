package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.tools.Vector;

/**
 * 正常周期性负荷能力评估程序
 * 长期急救负荷能力评估程度
 * 实现load1.m, load2.m程序
 */
public class HSLoad extends HS {
    /**
     * 在线数据
     */
    private class ConLoad {
        private Vector T_amb;       //读取环境温度
        private Vector I_H_current;   //读取负载电流
        private Vector p_sun;       //读取太阳日辐射功率

        public void init(IFhnlpgLimit iFhnlpgLimit) {
            double in_T_amb = iFhnlpgLimit.getT_amb();
            double in_I_H_current = iFhnlpgLimit.getI_H_current();
            double in_p_sun = 0;

            T_amb = new Vector(n_iters, 0);
            I_H_current = new Vector(n_iters, 0);
            p_sun = new Vector(n_iters, 0);
            for (int i = 0; i < n_iters; i++) {
                T_amb.set(i, in_T_amb + iFhnlpgLimit.getT_amb_offset());
                I_H_current.set(i, iFhnlpgLimit.getI_H_current_coef() * in_I_H_current);
                p_sun.set(i, in_p_sun);
            }

        }
    }

    private int n_iters;                  //迭代次数，一天24小时，每小时采4个点 //隐藏参数
    private double Klimit_fu;             //辅助设备容量等级约束              //隐藏参数
    private double Tlimit_top;            //顶层油温约束                     //隐藏参数
    private double Tlimit_hs;             //热点温度约束                     //隐藏参数
    private double Llimit_L;              //相对寿命损失约束                  //隐藏参数
    private double Flimit_IC;             //故障模式约束                     //隐藏参数

    private double K1;
    private double K;

    private ConLoad conLoad = new ConLoad();

    private String YXTJ;

    public static void main(String[] args) {
        // write your code here
        /*
        HSLoad hs = new HSLoad();
        //load1.m
        //hs.init(3, 3, 17, 100, 0.9, 1.2, 105, 120, 1);
        //load2.m
        //hs.init(3, 3, 17, 100, 1, 1.2, 105, 120, 10);
        hs.solve();
        hs.print();
        */
    }

    /**
     *
     */
    public void init(IFhnlpgBase iFhnlpgBase,
                     IFhnlpgInitial iFhnlpgInitial,
                     IFhnlpgResistance iFhnlpgResistance,
                     IFhnlpgTRise iFhnlpgTRise,
                     IFhnlpgRatio iFhnlpgRatio,
                     IFhnlpgOnLoad iFhnlpgOnLoad,
                     IFhnlpgLimit iFhnlpgLimit) {
        super.init(iFhnlpgBase, iFhnlpgInitial, iFhnlpgResistance, iFhnlpgTRise, iFhnlpgRatio, iFhnlpgOnLoad);
        n_iters = iFhnlpgLimit.getN_iters();
        Klimit_fu = iFhnlpgLimit.getKlimit_fu();
        Tlimit_top = iFhnlpgLimit.getTlimit_top();
        Tlimit_hs = iFhnlpgLimit.getTlimit_hs();
        Llimit_L = 86400 * iFhnlpgLimit.getLlimit_L_coef();

        GZZT gzzt = new GZZT();
        gzzt.init();
        Flimit_IC = gzzt.solve(iFhnlpgLimit.getIC());

        conLoad.init(iFhnlpgLimit);
        conLoad.p_sun.times(a * b * m_size.S);
        K1 = m_trise.I_H_DC;
        interval = 15;
        num = conLoad.T_amb.getSize();

    }


    public void solve() {
        Vector T_top_G_tmp = new Vector(num, 0.0d);
        Vector T_oil_G_tmp = new Vector(num, 0.0d);
        Vector T_wnd_G_tmp = new Vector(num, 0.0d);
        Vector T_hs_G_tmp = new Vector(num, 0.0d);
        Vector V_G_tmp = new Vector(num, 0.0d);

        T_top_G = new Vector(num, 0.0d);
        T_oil_G = new Vector(num, 0.0d);
        T_wnd_G = new Vector(num, 0.0d);
        T_hs_G = new Vector(num, 0.0d);
        V_G = new Vector(num, 0.0d);

        double L, L1;
        int seconds = 5;
        Vector t = new Vector(1.0, 1.0, seconds);
        while (true) {

            T_top_now = m_initial.T_top_0;
            T_oil_now = m_initial.T_oil_0;
            T_wnd_now = m_initial.T_wnd_0;
            T_hs_now = m_initial.T_hs_0;

            T_top_rate = m_trise.T_amb_r + m_trise.T_top_r;
            T_oil_rate = m_trise.T_amb_r + m_trise.T_oil_r;
            T_wnd_rate = m_trise.T_amb_r + m_trise.T_wnd_r;
            L = 0;
            L1 = 0;

            for (int i = 1; i < num; i++) {
                double T_amb_now = conLoad.T_amb.get(i - 1);
                double I_current_now = conLoad.I_H_current.get(i - 1);
                double P_sun_now = conLoad.p_sun.get(i - 1);

                for (int k = 0; k < 60 * interval / seconds; k++) {
                    K = I_current_now / m_trise.I_H_DC;
                    double P_cu_now = (m_trise.P_dc_r / m_trise.P_cu_r) * ((T_wnd_now + 235) / (T_wnd_rate + 235)) + (m_trise.P_fj_r / m_trise.P_cu_r) * ((T_wnd_rate + 235) / (T_wnd_now + 235));
                    //基于环境温度计算平均油温
                    double u_p = Math.exp(2797.3 / (T_oil_now + 273)) / Math.exp(2797.3 / (T_oil_rate + 273));

                    RungeKutta rk = new RungeKutta(t.getArray(), T_oil_now);
                    rk.Init(m_trise.R, K, P_cu_now, P_sun_now, m_trise.P_fe_r, m_trise.T_top_r, m_trise.T_oil_r,
                            m_trise.T_wnd_r, T_amb_now, n, n1, u_p, t_oil, t_top, t_wnd, T_oil_now, T_top_now, T_wnd_now);
                    rk.solve_oil();
                    T_oil_now = rk.getY(seconds - 1);

                    //基于平均油温计算顶层油温
                    //u_p = Math.exp(2797.3 / (T_top_now + 273)) / Math.exp(2797.3 / (T_top_rate + 273));
                    rk.setY0(T_top_now);
                    rk.solve_top();
                    T_top_now = rk.getY(seconds - 1);

                    //基于平均油温计算绕组平均温度
                    //u_p = Math.exp(2797.3 / (T_wnd_now + 273)) / Math.exp(2797.3 / (T_wnd_rate + 273));
                    rk.setY0(T_wnd_now);
                    rk.solve_wnd();
                    T_wnd_now = rk.getY(seconds - 1);

                    //热点温度估算值
                    T_hs_now = m_trise.H * (T_wnd_now - T_oil_now) + T_top_now;
                    //相对老化速率
                    if (Y_paper == 0) {
                        V_now = Math.pow(2, (T_hs_now - 98) / 6);
                    } else {
                        V_now = Math.exp(15000 / (110 + 273) - 15000 / (T_hs_now + 273));
                    }

                    L1 += V_now * seconds;
                } //for(k<interal) end

                T_top_G_tmp.set(i, T_top_now);
                T_oil_G_tmp.set(i, T_oil_now);
                T_wnd_G_tmp.set(i, T_wnd_now);
                T_hs_G_tmp.set(i, T_hs_now);
                V_G_tmp.set(i, V_now);
                L += L1;
                //System.out.println("i=" + i + ",L1=" + L1 + ",L=" + L+",T_top_now="+T_top_now+",T_oil_now="+T_oil_now+",T_wnd_now="+T_wnd_now);
                L1 = 0;

                //判断负荷是否超出范围
                if (T_hs_now >= Tlimit_hs | T_top_now >= Tlimit_top | K >= Klimit_fu | L >= Llimit_L) {
                    break;
                }
            }//for(i<num) end

            //输出越限条件                    //输出参数，YXTJ=
            if (T_hs_now >= Tlimit_hs) {
                //System.out.println("热点温度越限");
                YXTJ = "热点温度越限";
            } else if (T_top_now >= Tlimit_top) {
                //System.out.println("顶层油温越限");
                YXTJ = "顶层油温越限";
            } else if (K >= Klimit_fu) {
                //System.out.println("负荷率越限");
                YXTJ = "负荷率越限";
            } else if (L >= Llimit_L) {
                //System.out.println("相对老化速率越限");
                YXTJ = "相对老化速率越限";
            }

            //判断负荷是否超出约束范围
            if (T_hs_now >= Tlimit_hs | T_top_now >= Tlimit_top | K >= Klimit_fu | L >= Llimit_L) {
                break;
            }

            K = K * m_trise.I_H_DC + 0.01 * K1;
            for (int i = 0; i < num; i++) {
                conLoad.I_H_current.set(i, K);
            }

            for (int i = 0; i < num; i++) {
                T_top_G.set(i, T_top_G_tmp.get(i));
                T_oil_G.set(i, T_oil_G_tmp.get(i));
                T_wnd_G.set(i, T_wnd_G_tmp.get(i));
                T_hs_G.set(i, T_hs_G_tmp.get(i));
                V_G.set(i, V_G_tmp.get(i));
            }

            T_hs_G.set(0, m_initial.T_hs_0);
            T_top_G.set(0, m_initial.T_top_0);
            V_G.set(0, m_initial.V_0);
        } //while end
    }

    private void calcK() {
        if (Flimit_IC > 1) {
            System.out.println("存在潜伏性故障，变压器过负荷运行有风险，建议尽快检修。");
            K = (Flimit_IC - 1) * (K - 0.01);
        } else {
            K = Flimit_IC * (K - 0.01);
        }
    }

    public void print() {
        super.print();
        calcK();
        System.out.println(String.format("K= %-8.4f", K));
    }

    public OZcfhnlpg output() {
        calcK();
        OZcfhnlpg oZcfhnlpg = new OZcfhnlpg();
        oZcfhnlpg.T_hs_G = T_hs_G.toArrayList();
        oZcfhnlpg.T_top_C = m_onload.T_top_C.toArrayList();
        oZcfhnlpg.HST = T_hs_G.get(T_hs_G.getSize() - 1);
        oZcfhnlpg.TOPT = m_onload.T_top_C.get(m_onload.T_top_C.getSize() - 1);
        oZcfhnlpg.YXTJ = YXTJ;
        oZcfhnlpg.Flimit_IC = Flimit_IC;
        oZcfhnlpg.K = K;
        oZcfhnlpg.V_G = V_G.toArrayList();
        oZcfhnlpg.XDLHSL = V_G.get(V_G.getSize() - 1);
        return oZcfhnlpg;
    }
}
