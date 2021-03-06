package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.tools.Vector;

/**
 * 热点温度计算（老），对应tempera.m
 */
public class CRDWD {

    private class Size {
        private double l;   //输入变压器箱体长度（m）
        private double w;   //输入变压器箱体宽度（m）
        private double h;   //输入变压器箱体高度（m）
        private double S;       //变压器表面积

        public void Init() {
            l = 6.65;
            w = 2.308;
            h = 3.711;
            S = 2 * (l * w + w * h + l * h);
        }
    }

    private class Texture {
        private double m_tank;  //输入变压器壳体质量（kg）
        private double m_oil;   //输入变压器油质量（kg
        private double m_wnd;   //输入变压器绕组质量（kg）
        private double m_fe;    //输入变压器铁芯质量（kg）
        private double c_tank;  //输入变压器壳体的比热容（J/(kg·℃）
        private double c_oil;   //输入变压器油的比热容（J/(kg·℃）
        private double c_wnd;   //输入变压器绕组的比热容（J/(kg·℃）
        private double c_fe;    //输入变压器铁芯的比热容（J/(kg·℃）

        private double C_th_1;  //环境温度至顶层油温模型的热容
        private double C_th_2;  //环境温度至平均油温模型的热容
        private double C_th_3;  //平均油温至绕组平均温度模型的热容

        public void Init() {
            m_tank = 17595;
            m_oil = 46000;
            m_wnd = 18975;
            m_fe = 66020;
            c_tank = 317.5;
            c_oil = 1440;
            c_wnd = 390;
            c_fe = 460;
            //传热热容计算
            C_th_1 = m_tank * c_tank + m_oil * c_oil + m_wnd * c_wnd + m_fe * c_fe;
            C_th_2 = m_wnd * c_wnd + m_fe * c_fe;
            C_th_3 = m_wnd * c_wnd;
        }
    }

    private class Nameplate {
        private double S_H_r;   //输入变压器高压侧额定容量（MVA）
        private double S_M_r;   //输入变压器中压侧额定容量（MVA）
        private double S_L_r;   //输入变压器低压侧额定容量（MVA
        private double U_H_r;   //输入变压器高压侧额定电压（kV）
        private double U_M_r;   //输入变压器中压侧额定电压（kV）
        private double U_L_r;   //输入变压器低压侧额定电压（kV）
        private double I_H_r;   //输入变压器高压侧额定电流（A）
        private double I_M_r;   //输入变压器中压侧额定电流（A）
        private double I_L_r;   //输入变压器低压侧额定电流（A）

        public void Init() {
            S_H_r = 180;
            S_L_r = 180;
            U_H_r = 198;
            U_L_r = 38.5;
            I_H_r = 564.7;
            I_L_r = 2699.4;
        }
    }

    private class Resistance {
        private int Tap_r;
        private int Tap;
        private double U_fjt;
        private double T_C;
        private Vector R_H;
        private Vector R_Ld;
        private double R_p;
        private Vector R_L;
        private Vector K_V;
        private double k_HL;

        public void Init() {
            Tap_r = 9;
            Tap = 17;
            U_fjt = 1.25;
            T_C = 26;


            switch (kind) {
                //kind=2
                case 2:
                    double[] r_h = {0.2702, 0.2704, 0.2704};
                    R_H = new Vector(r_h);
                    double[] r_ld = {0.011712, 0.011727, 0.011759};
                    R_Ld = new Vector(r_ld);
                    R_p = R_Ld.sum() / 2;

                    R_L = new Vector(3);    //低压侧相电阻
                    R_L.set(0, (R_Ld.get(2) - R_p) - R_Ld.get(0) * R_Ld.get(1) / (R_Ld.get(2) - R_p));
                    R_L.set(1, (R_Ld.get(0) - R_p) - R_Ld.get(2) * R_Ld.get(1) / (R_Ld.get(0) - R_p));
                    R_L.set(2, (R_Ld.get(1) - R_p) - R_Ld.get(0) * R_Ld.get(2) / (R_Ld.get(1) - R_p));

                    double[] kv = {11.524, 11.393, 11.262, 11.131, 11, 10.869, 10.738, 10.607,
                            10.476, 10.345, 10.214, 10.083, 9.9524, 9.8214, 9.6905, 9.5595, 5.143};
                    K_V = new Vector(kv);
                    k_HL = K_V.get(Tap - 1);
                    break;
            }
        }

    }

    private class TRise {
        private double T_top_r;     //读取温升试验下的顶层油温升（K）.
        private double T_oil_r;     //读取温升试验下的平均油温升（K）.
        private double T_wnd_r;     //读取温升试验下的绕组平均温升（K）.
        private double T_amb_r;     //读取温升试验下的环境温度（℃）.

        private double T_boil_r;    //计算温升试验下的底层油温升（K）.
        private double T_hs_r;      //计算热点温度温升（K）.

        private double P_fe_r;      //读取温升试验下变压器的空载损耗（W）.
        private double P_cu_r;      //读取温升试验下变压器的负载损耗（W）.
        private double I_H_DC;      //读取温升试验下变压器高压侧负载电流（A）.
        private double I_L_DC;      //读取温升试验下变压器低压侧负载电流（A）.

        private double K_T;
        private double P_dc_r;
        private double P_fj_r;
        private double R;

        private double R_th_top_air;   //顶层油温至环境温度的传热热阻.
        private double R_th_oil_air;   //平均油温至环境温度的传热热阻.
        private double R_th_wnd_oil;   //绕组平均温度至平均油温的传热热阻.

        public void Init(Resistance resist) {
            switch (kind) {
                case 2:
                    T_top_r = 41.2;
                    T_oil_r = 28.28;
                    T_wnd_r = 45.9;
                    T_amb_r = 32;

                    T_boil_r = 2 * T_oil_r - T_top_r;
                    T_hs_r = H * (T_wnd_r - T_oil_r) + T_top_r;

                    P_fe_r = 82169;
                    P_cu_r = 512386;
                    I_H_DC = 564.9;
                    I_L_DC = I_H_DC * resist.k_HL;

                    //附加损耗计算
                    K_T = (235 + T_wnd_r) / (235 + resist.T_C);
                    //额定下的直流电阻损耗.
                    P_dc_r = K_T * resist.R_H.sum() * Math.pow(I_H_DC, 2) + K_T * resist.R_L.sum() * Math.pow(I_L_DC, 2);
                    P_fj_r = P_cu_r - P_dc_r;   //额定下的附加损耗.
                    R = P_cu_r / P_fe_r;        //负载损耗比.
                    break;
            }

            R_th_top_air = (T_top_r - T_oil_r) / (P_fe_r + P_cu_r);
            R_th_oil_air = T_oil_r / (P_fe_r + P_cu_r);
            R_th_wnd_oil = (T_wnd_r - T_oil_r) / P_cu_r;
        }

    }

    private class Initial {
        private double T_top_0;
        private double T_oil_0;
        private double T_wnd_0;
        private double T_hs_0;

        public void Init() {
            T_top_0 = 71.6;     //读取变压器的顶层油温初值（℃）
            T_oil_0 = 60.6;     //读取变压器的平均油温初值（℃）
            T_wnd_0 = 81.6;     //读取变压器的绕组平均温度初值（℃）
            T_hs_0 = H * (T_wnd_0 - T_oil_0) + T_top_0;   //读取变压器当前的热点温度（℃）.
        }
    }

    private class Onload {
        private Vector T_amb;       //读取环境温度
        private Vector I_current;   //读取负载电流
        private Vector T_top_C;     //读取顶层油温测量值
        private Vector p_sun;       //读取太阳日辐射功率

        public void Init() {
            double[] amb = {30.5, 30.9, 31.3, 31.5, 32, 32.2, 32.9, 32.5, 32.5};
            T_amb = new Vector(amb);
            double[] current = {564.7,
                    565.02,
                    565.04,
                    564.62,
                    563.73,
                    564.2,
                    524.04,
                    523.22,
                    524.24};
            I_current = new Vector(current);

            double[] topc = {71.6,
                    72.2,
                    72.5,
                    72.8,
                    73.4,
                    73.8,
                    74.1,
                    72.8,
                    71.6};
            T_top_C = new Vector(topc);
            double[] psun = {658.75,
                    658.75,
                    658.75,
                    658.75,
                    289.75,
                    289.75,
                    289.75,
                    289.75,
                    289.75};
            p_sun = new Vector(psun);
        }
    }

    private Size m_size = new Size();
    private Texture m_texture = new Texture();
    private Nameplate m_nameplate = new Nameplate();
    private Resistance m_resist = new Resistance();
    private TRise m_trise = new TRise();
    private Initial m_initial = new Initial();
    private Onload m_onload = new Onload();

    private double H;   //热点到顶油温升系数取值（1.1-1.5）
    private int type;   //变压器类型：ONAN配电变压器时为type=0; 中大型变压器 ONAN type=1; 中大型变压器 ONAF type=2;中大型变压器 OF type=3;中大型变压器 OD type=4
    private int kind;   //变压器分类：自耦变压器时 kind=1；双绕组变压器时 kind=2； 三绕组变压器时 kind=3；

    private double t_top;   //环境温度至顶层油温模型的时间常数
    private double t_oil;   //环境温度至平均油温模型的时间常数
    private double t_wnd;   //平均油温至绕组平均温度模型的时间常数

    private double a;       //辐射功率吸收系数
    private double b;       //日照辐射面积系数
    private int interval;   //对计算时间间隔（min）
    private int C_bei;      //间隔缩小倍数
    private double n;
    private double n1;
    private int num;

    private Vector T_top_G;
    private Vector T_oil_G;
    private Vector T_wnd_G;
    private Vector T_hs_G;
    private double T_top_now;
    private double T_oil_now;
    private double T_wnd_now;
    private double T_hs_now;
    private double T_top_rate;
    private double T_oil_rate;
    private double T_wnd_rate;


    public static void main(String[] args) {
        // write your code here
        CRDWD cr = new CRDWD();
        cr.init();
        cr.solve();
        cr.print();
    }

    public void init() {
        H = 1.1;      //内部接口
        type = 2;     //
        kind = 2;
        m_size.Init();
        m_texture.Init();
        m_nameplate.Init();
        m_resist.Init();
        m_trise.Init(m_resist);
        m_initial.Init();
        m_onload.Init();

        t_top = m_trise.R_th_top_air * m_texture.C_th_1;
        t_oil = m_trise.R_th_oil_air * m_texture.C_th_2;
        t_wnd = m_trise.R_th_wnd_oil * m_texture.C_th_3;

        a = 0.5;
        b = 0.5;
        interval = 30;
        //调整计算间隔进行插值
        C_bei = 3;
        m_onload.T_amb.expand(C_bei);
        m_onload.I_current.expand(C_bei);
        m_onload.T_top_C.expand(C_bei);
        m_onload.p_sun.expand(C_bei);
        m_onload.p_sun.times(a * b * m_size.S);

        interval = interval / C_bei;

        n = 0.9;
        n1 = 2;
        num = m_onload.T_amb.getSize();

        T_top_G = new Vector(num, 0.0d);
        T_oil_G = new Vector(num, 0.0d);
        T_wnd_G = new Vector(num, 0.0d);

        T_top_now = m_initial.T_top_0;
        T_oil_now = m_initial.T_oil_0;
        T_wnd_now = m_initial.T_wnd_0;
        T_hs_now = m_initial.T_hs_0;

        T_top_rate = m_trise.T_amb_r + m_trise.T_top_r;
        T_oil_rate = m_trise.T_amb_r + m_trise.T_oil_r;
        T_wnd_rate = m_trise.T_amb_r + m_trise.T_wnd_r;
    }

    public void solve() {
        Vector t = new Vector(1.0, 1.0, 10.0);
        for (int i = 1; i < num; i++) {
            double T_amb_now = m_onload.T_amb.get(i - 1);
            double I_current_now = m_onload.I_current.get(i - 1);
            double P_sun_now = m_onload.p_sun.get(i - 1);

            double aT = T_top_now - m_onload.T_top_C.get(i - 1);
            T_top_now = T_top_now - aT;
            T_oil_now = T_oil_now - aT;
            T_wnd_now = T_wnd_now - aT;

            for (int k = 1; k < 6 * interval; k++) {
                double K = I_current_now / m_nameplate.I_H_r;
                double P_cu_now = (m_trise.P_dc_r / m_trise.P_cu_r) * ((T_wnd_now + 235) / (T_wnd_rate + 235)) + (m_trise.P_fj_r / m_trise.P_cu_r) * ((T_wnd_rate + 235) / (T_wnd_now + 235));
                //基于环境温度计算平均油温
                double u_p = Math.exp(2797.3 / (T_oil_now + 273)) / Math.exp(2797.3 / (T_oil_rate + 273));

                RungeKutta rk = new RungeKutta(t.getArray(), T_oil_now);
                rk.Init(m_trise.R, K, P_cu_now, P_sun_now, m_trise.P_fe_r, m_trise.T_top_r, m_trise.T_oil_r,
                        m_trise.T_wnd_r, T_amb_now, n, n1, u_p, t_oil, t_top, t_wnd, T_oil_now, T_top_now, T_wnd_now);
                rk.solve_oil();
                T_oil_now = rk.getY(4);

                //基于平均油温计算顶层油温
                u_p = Math.exp(2797.3 / (T_top_now + 273)) / Math.exp(2797.3 / (T_top_rate + 273));
                rk.setY0(T_top_now);
                rk.solve_top();
                T_top_now = rk.getY(9);

                //基于平均油温计算绕组平均温度
                u_p = Math.exp(2797.3 / (T_wnd_now + 273)) / Math.exp(2797.3 / (T_wnd_rate + 273));
                rk.setY0(T_wnd_now);
                rk.solve_wnd();
                T_wnd_now = rk.getY(9);
            }

            T_top_G.set(i, T_top_now);
            T_oil_G.set(i, T_oil_now);
            T_wnd_G.set(i, T_wnd_now);

        }

        T_hs_G = T_wnd_G.copy();
        T_hs_G.minus(T_oil_G);
        T_hs_G.times(H);
        T_hs_G.add(T_top_G);
        T_hs_G.set(0, m_initial.T_hs_0);

        T_top_G.set(0, m_onload.T_top_C.get(0));

    }

    public void print() {
        int l = 8;
        int dot = 4;
        System.out.println("T_top_G=");
        T_top_G.print(l, dot);
        System.out.println("----");
        System.out.println("T_hs_G=");
        T_hs_G.print(l, dot);
        System.out.println("----");
        System.out.println("T_top_C=");
        m_onload.T_top_C.print(4, 1);
    }

}
