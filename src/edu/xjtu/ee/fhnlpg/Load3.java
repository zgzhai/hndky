package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.tools.Vector;

public class Load3 extends HS {
    /**
     * 在线数据
     */
    private class ConLoad {
        private Vector T_amb;       //读取环境温度
        private Vector I_H_current;   //读取负载电流
        private Vector p_sun;       //读取太阳日辐射功率

        public void init(double I_H_current_coef) {
            double in_T_amb = 34;
            double in_I_H_current = m_trise.I_H_DC;
            double in_p_sun = 0;

            T_amb = new Vector(n_iters, 0);
            I_H_current = new Vector(n_load, 0);
            p_sun = new Vector(n_iters, 0);
            for (int i = 0; i < n_iters; i++) {
                T_amb.set(i, in_T_amb);
                p_sun.set(i, in_p_sun);
            }

            for (int i = 0; i < n_load; i++) {
                //I_H_current_2(i1)=I_H_current*(1.2+0.012*i1);
                I_H_current.set(i, in_I_H_current * (1.2 + 0.012 * (i + 1)));
            }

        }
    }

    private int n_iters;                  //迭代次数，一天24小时，每小时采4个点
    private double Klimit_fu;             //辅助设备容量等级约束
    private double Tlimit_top;            //顶层油温约束
    private double Tlimit_hs;             //热点温度约束
    private double Llimit_L;              //相对寿命损失约束
    private double Flimit_IC;             //故障模式约束

    private double K1;
    private double K;

    private ConLoad conLoad = new ConLoad();

    private Vector K2;
    private Vector T2;
    private int n_load;
    private double Time;


    public static void main(String[] args) {
        // write your code here
        Load3 hs = new Load3();
        //load3.m
        hs.init(3, 3, 9, 100, 1, 2, 115, 140, 100);
        hs.solve();
        hs.print();
    }

    /**
     * @param in_type
     * @param in_kind
     * @param in_Tap
     * @param IC
     * @param I_H_current_coef
     * @param in_Klimit_fu
     * @param in_Tlimit_top
     * @param in_Tlimit_hs
     * @param Llimit_L_coef
     */
    public void init(int in_type, int in_kind, int in_Tap, double IC, double I_H_current_coef, double in_Klimit_fu, double in_Tlimit_top, double in_Tlimit_hs, double Llimit_L_coef) {
        super.init(in_type, in_kind, in_Tap);
        n_load = 50;
        n_iters = 96;
        Klimit_fu = in_Klimit_fu;
        Tlimit_top = in_Tlimit_top;
        Tlimit_hs = in_Tlimit_hs;
        Llimit_L = 86400 * Llimit_L_coef;

        GZZT gzzt = new GZZT();
        gzzt.init();
        Flimit_IC = gzzt.solve(IC);

        conLoad.init(I_H_current_coef);
        conLoad.p_sun.times(a * b * m_size.S);
        K1 = m_trise.I_H_DC;
        interval = 15;
        num = conLoad.T_amb.getSize();

        K2 = new Vector(n_load);
        T2 = new Vector(n_load);
    }


    public void solve() {
        T_top_G = new Vector(num, 0.0d);
        T_oil_G = new Vector(num, 0.0d);
        T_wnd_G = new Vector(num, 0.0d);
        T_hs_G = new Vector(num, 0.0d);
        V_G = new Vector(num, 0.0d);

        double L, L1;
        double Time1;
        int seconds = 5;
        Vector t = new Vector(1.0, 1.0, seconds);
        for (int r = 0; r < n_load; r++) {

            T_top_now = m_initial.T_top_0;
            T_oil_now = m_initial.T_oil_0;
            T_wnd_now = m_initial.T_wnd_0;
            T_hs_now = m_initial.T_hs_0;

            T_top_rate = m_trise.T_amb_r + m_trise.T_top_r;
            T_oil_rate = m_trise.T_amb_r + m_trise.T_oil_r;
            T_wnd_rate = m_trise.T_amb_r + m_trise.T_wnd_r;
            L = 0;
            L1 = 0;
            Time1 = 0;
            Time = 0;

            for (int i = 1; i < num; i++) {
                double T_amb_now = conLoad.T_amb.get(i - 1);
                double I_current_now = conLoad.I_H_current.get(r);
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
                    T_oil_now = rk.getY(seconds-1);

                    //基于平均油温计算顶层油温
                    //u_p = Math.exp(2797.3 / (T_top_now + 273)) / Math.exp(2797.3 / (T_top_rate + 273));
                    rk.setY0(T_top_now);
                    rk.solve_top();
                    T_top_now = rk.getY(seconds-1);

                    //基于平均油温计算绕组平均温度
                    //u_p = Math.exp(2797.3 / (T_wnd_now + 273)) / Math.exp(2797.3 / (T_wnd_rate + 273));
                    rk.setY0(T_wnd_now);
                    rk.solve_wnd();
                    T_wnd_now = rk.getY(seconds-1);

                    //热点温度估算值
                    T_hs_now = m_trise.H * (T_wnd_now - T_oil_now) + T_top_now;
                    //相对老化速率
                    if (Y_paper == 0) {
                        V_now = Math.pow(2, (T_hs_now - 98) / 6);
                    } else {
                        V_now = Math.exp(15000 / (110 + 273) - 15000 / (T_hs_now + 273));
                    }

                    L1 += V_now * seconds;

                    Time1 += seconds;    //运行10秒
                    if (T_hs_now >= Tlimit_hs || T_top_now >= Tlimit_top || K >= Klimit_fu || L >= Llimit_L) //判断负荷是否超出范围
                    {
                        break;
                    }
                } //for(k<interal) end

                T_top_G.set(i, T_top_now);
                T_oil_G.set(i, T_oil_now);
                T_wnd_G.set(i, T_wnd_now);
                T_hs_G.set(i, T_hs_now);
                V_G.set(i, V_now);
                L += L1;
                //System.out.println("i=" + i + ",L1=" + L1 + ",L=" + L+",T_top_now="+T_top_now+",T_oil_now="+T_oil_now+",T_wnd_now="+T_wnd_now);
                L1 = 0;
                Time += Time1;
                Time1 = 0;

                //判断负荷是否超出范围
                if (T_hs_now >= Tlimit_hs || T_top_now >= Tlimit_top || K >= Klimit_fu || L >= Llimit_L) {
                    break;
                }
            }//for(i<num) end

            //System.out.println("r="+r+",T_top_now="+T_top_now+",T_oil_now="+T_oil_now);

            //输出越限条件
            if (T_hs_now >= Tlimit_hs) {
                System.out.println("热点温度越限");
            } else if (T_top_now >= Tlimit_top) {
                System.out.println("顶层油温越限");
            } else if (K >= Klimit_fu) {
                System.out.println("负荷率越限");
            } else if (L >= Llimit_L) {
                System.out.println("相对老化速率越限");
            }

            T_hs_G.set(0, m_initial.T_hs_0);
            T_top_G.set(0, m_initial.T_top_0);
            V_G.set(0, m_initial.V_0);

            K2.set(r, K);
            T2.set(r, Time / 60);
        } //for(r:n_laod)
    }


    public void print() {
        //super.print();
        double k2 = K2.get(n_load - 1);
        if (Flimit_IC > 1) {
            System.out.println("存在潜伏性故障，变压器过负荷运行有风险，建议尽快检修。");
            K2.set(n_load - 1, (Flimit_IC - 1) * (k2 - 0.01));
        } else {
            K2.set(n_load - 1, Flimit_IC * (k2 - 0.01));
        }

        System.out.println("K2=");
        K2.print(8, 4);
        System.out.println("T2=");
        T2.print(8, 4);
    }
}
