package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

/**
 * 热点温度计算（新）,对应HS.m
 */
public class HS {

    /**
     * 箱体尺寸
     */
    protected class Size {
        private double l;   //输入变压器箱体长度（m）
        private double w;   //输入变压器箱体宽度（m）
        private double h;   //输入变压器箱体高度（m）
        protected double S;   //变压器表面积

        public void init() {
            l = 6.65;
            w = 2.308;
            h = 3.711;
            S = 2 * (l * w + w * h + l * h);
        }
    }

    /**
     * 变压器绕组直流电阻及电压变比
     */
    private class Resistance {
        private int Tap_r;
        private int Tap;     //输入高压侧实际分接位置.
        private double U_fjt;
        private double T_C;
        private Vector R_H;
        private Vector R_M;
        private Vector R_Ld;
        private double R_p;
        private Vector R_L;
        private Vector K_V;
        private double k_HL;
        private double k_ML;
        private double k_HM;

        Resistance() {
        }

        public void init(IFhnlpgResistance iFhnlpgResistance, IFhnlpgRatio iFhnlpgRatio) {
            switch (kind) {
                case 1:
                    //TODO
                    break;
                //kind=2
                case 2:
                    /*
                    Tap_r = 9;
                    Tap = in_Tap;
                    U_fjt = 1.25;
                    T_C = 26;     //接口参数， D_H, Tap, D_M, D_L
                    double[] r_h = {0.2974, 0.298, 0.2983}; //ResistanceH第17行数据
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
                    */
                    break;
                case 3:
                    Tap_r = 9;
                    Tap = iFhnlpgResistance.getTap();
                    U_fjt = 2.25;
                    T_C = iFhnlpgResistance.getT_C();

                    ResistanceABC abc = iFhnlpgResistance.getD_H().get(Tap - 1);
                    //double[] r_h = {0.2974, 0.298, 0.2983}; //ResistanceH第17行数据
                    double[] r_h = {abc.getA(), abc.getB(), abc.getC()};
                    R_H = new Vector(r_h);

                    ResistanceABC abc1 = iFhnlpgResistance.getD_M().get(0);
                    //double[] r_m = {0.11589, 0.11594, 0.11667};
                    double[] r_m = {abc1.getA(), abc1.getB(), abc1.getC()};
                    R_M = new Vector(r_m);

                    ResistanceABC abc2 = iFhnlpgResistance.getD_L().get(0);
                    //double[] r_ld = {0.011712, 0.011727, 0.011759};
                    double[] r_ld = {abc2.getA(), abc2.getB(), abc2.getC()};
                    R_Ld = new Vector(r_ld);
                    R_p = R_Ld.sum() / 2;

                    R_L = new Vector(3);    //低压侧相电阻
                    R_L.set(0, (R_Ld.get(2) - R_p) - R_Ld.get(0) * R_Ld.get(1) / (R_Ld.get(2) - R_p));
                    R_L.set(1, (R_Ld.get(0) - R_p) - R_Ld.get(2) * R_Ld.get(1) / (R_Ld.get(0) - R_p));
                    R_L.set(2, (R_Ld.get(1) - R_p) - R_Ld.get(0) * R_Ld.get(2) / (R_Ld.get(1) - R_p));
                    /*
                    double[] kv = {11.524, 11.393, 11.262, 11.131, 11, 10.869, 10.738, 10.607,
                            10.476, 10.345, 10.214, 10.083, 9.9524, 9.8214, 9.6905, 9.5595, 5.143};
                    K_V = new Vector(kv);
                    k_HL = K_V.get(Tap - 1);
                    */
                    RatioHML ratioHML = iFhnlpgRatio.getRatio().get(Tap - 1);
                    k_HM = ratioHML.getHM();
                    k_ML = ratioHML.getML();
                    k_HL = ratioHML.getHL();
                    break;
            }
        }

    }

    /**
     * 温升试验数据
     */
    protected class TRise {
        protected double T_top_r;     //读取温升试验下的顶层油温升（K）.//接口参数
        protected double T_oil_r;     //读取温升试验下的平均油温升（K）.//接口参数
        protected double T_wnd_r;     //读取温升试验下的绕组平均温升（K）.//接口参数
        protected double T_amb_r;     //读取温升试验下的环境温度（℃）.//接口参数

        private double T_boil_r;    //计算温升试验下的底层油温升（K）.//接口参数
        private double T_hs_r;      //计算热点温度温升（K）.//接口参数

        protected double P_fe_r;      //读取温升试验下变压器的空载损耗（W）.//接口参数
        protected double P_cu_r;      //读取温升试验下变压器的负载损耗（W）.//接口参数
        protected double I_H_DC;      //读取温升试验下变压器高压侧负载电流（A）.//接口参数
        private double I_M_DC;       //接口参数
        private double I_L_DC;      //读取温升试验下变压器低压侧负载电流（A）.//接口参数
        protected double H;         //热点系数取值（1.1-1.5）//隐藏参数

        private double K_T;
        protected double P_dc_r;
        protected double P_fj_r;
        protected double R;


        private double R_th_top_air;   //顶层油温至环境温度的传热热阻.
        private double R_th_oil_air;   //平均油温至环境温度的传热热阻.
        private double R_th_wnd_oil;   //绕组平均温度至平均油温的传热热阻.

        TRise() {
        }

        public void init(Resistance resist, IFhnlpgBase base, TRiseV rise100, TRiseV rise70) {
            switch (kind) {
                case 1:
                    //TODO
                    break;
                case 2:
                     T_top_r = 41.2;
                     T_oil_r = 28.28;
                     T_wnd_r = 45.9;
                     T_amb_r = 32;
                     H = 1.1;

                     T_boil_r = 2 * T_oil_r - T_top_r;
                     T_hs_r = H * (T_wnd_r - T_oil_r) + T_top_r;

                     P_fe_r = 82169;
                     P_cu_r = 512386;
                     I_H_DC = 564.9;
                     I_L_DC = I_H_DC * resist.k_HL;

                     //附加损耗计算
                     if (Y_resis == 1) {
                     K_T = (235 + T_wnd_r) / (235 + resist.T_C);
                     //额定下的直流电阻损耗.
                     P_dc_r = K_T * resist.R_H.sum() * Math.pow(I_H_DC, 2) + K_T * resist.R_L.sum() * Math.pow(I_L_DC, 2);
                     } else {
                     P_dc_r = P_cu_r;
                     }

                     P_fj_r = P_cu_r - P_dc_r;   //额定下的附加损耗.
                     R = P_cu_r / P_fe_r;        //负载损耗比.
                    break;
                case 3:
                    if (base.getY_temdata() == 1 || (base.getY_temdata() == 0 && base.getY_cool() == 0)) {
                        init_rise(rise100);
                    } else if (base.getY_temdata() == 0 && base.getY_cool() == 1) {
                        init_rise(rise70);
                    } else {
                        T_top_r = (rise100.getT_top_r() + rise70.getT_top_r()) / 2;
                        T_oil_r = (rise100.getT_oil_r() + rise70.getT_oil_r()) / 2;
                        T_wnd_r = (rise100.getT_wnd_r() + rise70.getT_wnd_r()) / 2;
                        T_amb_r = (rise100.getT_amb_r() + rise70.getT_amb_r()) / 2;
                        H = rise100.getH();
                        T_boil_r = 2 * T_oil_r - T_top_r;
                        T_hs_r = H * (T_wnd_r - T_oil_r) + T_top_r;
                        P_fe_r = (rise100.getP_fe_r() + rise70.getP_fe_r()) / 2;
                        P_cu_r = (rise100.getP_cu_r() + rise70.getP_cu_r()) / 2;
                        I_H_DC = (rise100.getI_H_DC() + rise70.getI_H_DC()) / 2;
                        I_M_DC = (rise100.getI_M_DC() + rise70.getI_M_DC()) / 2;
                        I_L_DC = (rise100.getI_L_DC() + rise70.getI_L_DC()) / 2;
                    }
                    break;
            }

            //附加损耗计算
            if (Y_resis == 1) {
                K_T = (235 + T_wnd_r) / (235 + resist.T_C);
                //额定下的直流电阻损耗.
                P_dc_r = K_T * resist.R_H.sum() * Math.pow(I_H_DC, 2)
                        + K_T * resist.R_M.sum() * Math.pow(I_M_DC, 2)
                        + K_T * resist.R_L.sum() * Math.pow(I_L_DC, 2);
            } else {
                P_dc_r = P_cu_r;
            }
            P_fj_r = P_cu_r - P_dc_r;   //额定下的附加损耗.
            R = P_cu_r / P_fe_r;        //负载损耗比.


            if (Y_text == 1) {
                R_th_top_air = (T_top_r - T_oil_r) / (P_fe_r + P_cu_r);
                R_th_oil_air = T_oil_r / (P_fe_r + P_cu_r);
                R_th_wnd_oil = (T_wnd_r - T_oil_r) / P_cu_r;
            }
        }

        private void init_rise(TRiseV rise) {
            T_top_r = rise.getT_top_r();
            T_oil_r = rise.getT_oil_r();
            T_wnd_r = rise.getT_wnd_r();
            T_amb_r = rise.getT_amb_r();
            H = rise.getH();
            T_boil_r = 2 * T_oil_r - T_top_r;
            T_hs_r = H * (T_wnd_r - T_oil_r) + T_top_r;
            P_fe_r = rise.getP_fe_r();
            P_cu_r = rise.getP_cu_r();
            I_H_DC = rise.getI_H_DC();
            I_M_DC = rise.getI_M_DC();
            I_L_DC = rise.getI_L_DC();
        }
    }

    /**
     * 变压器材质及相关参数
     */
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

        public void init() {
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

    /**
     * 第一次计算时模型温度初值
     */
    protected class Initial {
        protected double T_top_0;  //接口参数
        protected double T_oil_0;  //接口参数
        protected double T_wnd_0;  //接口参数
        protected double T_hs_0;
        protected double V_0;

        public void init(double H, IFhnlpgInitial initial) {
            T_top_0 = initial.getT_top_0();     //读取变压器的顶层油温初值（℃）
            T_oil_0 = initial.getT_oil_0();     //读取变压器的平均油温初值（℃）
            T_wnd_0 = initial.getT_wnd_0();     //读取变压器的绕组平均温度初值（℃）

            T_hs_0 = H * (T_wnd_0 - T_oil_0) + T_top_0;   //读取变压器当前的热点温度（℃）.

            //相对老化速率
            if (Y_paper == 0) {
                V_0 = Math.pow(2, (T_hs_0 - 98) / 6);
            } else {
                V_0 = Math.exp(15000 / (110 + 273) - 15000 / (T_hs_0 + 273));
            }
        }
    }

    /**
     * 在线数据
     */
    protected class Onload {
        private Vector T_amb;          //读取环境温度    //接口参数
        private Vector I_H_current;   //读取负载电流  //接口参数
        private Vector I_M_current;   //读取负载电流  //接口参数
        private Vector I_L_current;   //读取负载电流  //接口参数
        protected Vector T_top_C;       //读取顶层油温测量值  //接口参数
        private Vector p_sun;         //读取太阳日辐射功率

        public void init(IFhnlpgOnLoad load) {
            //double[] amb = {30.5, 30.9, 31.3, 31.5, 32, 32.2, 32.9, 32.5, 32.5};
            T_amb = new Vector(load.getT_amb());
            //double[] h_current = {564.7, 565.02, 565.04, 564.62, 563.73, 564.2, 524.04, 523.22, 524.24};
            I_H_current = new Vector(load.getI_H_current());
            //double[] m_current = {564.7, 565.02, 565.04, 564.62, 563.73, 564.2, 524.04, 523.22, 524.24};
            I_M_current = new Vector(load.getI_M_current());
            //double[] l_current = {564.7, 565.02, 565.04, 564.62, 563.73, 564.2, 524.04, 523.22, 524.24};
            I_L_current = new Vector(load.getI_L_current());
            //double[] topc = {71.6, 72.2, 72.5, 72.8, 73.4, 73.8, 74.1, 72.8, 71.6};
            T_top_C = new Vector(load.getT_top_C());
            double[] psun = {0, 0, 0, 0, 0, 0, 0, 0, 0};
            p_sun = new Vector(psun);
        }
    }

    private int type;        //变压器类型：中大型变压器 ONAN type=1; 中大型变压器 ONAF type=2;中大型变压器 OF type=3;中大型变压器 OD type=4
    private int kind;        //变压器分类：自耦变压器时 kind=1；双绕组变压器时 kind=2； 三绕组变压器时 kind=3；
    private int Y_text;      //当变压器材质及相关参数存在时，Y_text=1; 否则，Y_text=0
    private int Y_temdata;   //是否存在变压器额定负荷（100%）的温升数据和60-80%负荷的温升数据；若有，则Y_temdata=2，否则Y_temdata=1；
    private double Y_cool;   //冷却器投入0%，Y_cool=0；冷却器投入50%，Y_cool=0.5；冷却器投入100%，Y_cool=1；
    private int Y_resis;     //变压器绕组直流电阻及电压变比数据存在，Y_resis=1；否则，Y_resis=0.
    protected int Y_paper; //变压器采用热改性绝缘纸，Y_paper=1；采用非热改性绝缘纸，Y_paper=0.
    protected double n; //输入计算指数（0.8~1.0） 一般情况下，对于AN类型的变压器n取值接近0.8 对于AF类型的变压器n取值接近1.0
    protected double n1; //输入计算指数（0.8~2.0） 一般情况下，对于ON类型的变压器m取值接近0.8 对于OD类型的变压器m取值接近2.0

    protected Size m_size = new Size();
    private Resistance m_resist = new Resistance();
    protected TRise m_trise = new TRise();
    private Texture m_texture = new Texture();
    protected Initial m_initial = new Initial();
    protected Onload m_onload = new Onload();

    protected double t_top;   //环境温度至顶层油温模型的时间常数
    protected double t_oil;   //环境温度至平均油温模型的时间常数
    protected double t_wnd;   //平均油温至绕组平均温度模型的时间常数

    protected double a;       //辐射功率吸收系数
    protected double b;       //日照辐射面积系数
    protected int interval;   //对计算时间间隔（min）
    private int C_bei;      //间隔缩小倍数
    protected int num;

    protected Vector T_top_G;
    protected Vector T_oil_G;
    protected Vector T_wnd_G;
    protected Vector T_hs_G;
    protected Vector V_G;
    protected double T_top_now;
    protected double T_oil_now;
    protected double T_wnd_now;
    protected double T_hs_now;
    protected double V_now;
    protected double T_top_rate;
    protected double T_oil_rate;
    protected double T_wnd_rate;

    public static void main(String[] args) {
        // write your code here
        /*
        HS hs = new HS();
        hs.init(new IFhnlpgBase(), new IFhnlpgInitial(), new IFhnlpgResistance(), new IFhnlpgTRise(), new IFhnlpgRatio(), new IFhnlpgOnLoad(new ArrayList<Load>()));
        hs.solve();
        hs.print();
        */
    }

    public void init(IFhnlpgBase iFhnlpgBase,
                     IFhnlpgInitial iFhnlpgInitial,
                     IFhnlpgResistance iFhnlpgResistance,
                     IFhnlpgTRise iFhnlpgTRise,
                     IFhnlpgRatio iFhnlpgRatio,
                     IFhnlpgOnLoad iFhnlpgOnLoad) {
        type = iFhnlpgBase.getType();
        kind = iFhnlpgBase.getKind();
        Y_text = iFhnlpgBase.getY_text();     //接口参数
        Y_temdata = iFhnlpgBase.getY_temdata();  //接口参数
        Y_cool = iFhnlpgBase.getY_cool();     //接口参数
        interval = iFhnlpgBase.getInterval();  //接口参数
        Y_resis = iFhnlpgBase.getY_resis();    //隐藏参数
        Y_paper = iFhnlpgBase.getY_paper();    //隐藏参数
        n = iFhnlpgBase.getN();       //隐藏参数
        n1 = iFhnlpgBase.getN1();       //隐藏参数

        m_size.init();  //接口参数
        m_resist.init(iFhnlpgResistance, iFhnlpgRatio);
        m_trise.init(m_resist, iFhnlpgBase, iFhnlpgTRise.getRise100(), iFhnlpgTRise.getRise70());

        //初始化t_top, t_oil,t_wnd
        init_t();

        m_initial.init(m_trise.H, iFhnlpgInitial);
        m_onload.init(iFhnlpgOnLoad);

        //S=2*(l*w+w*h+l*h); 变压器表面积. 在Size.init()里已计算
        a = 0.5;
        b = 0.5;
        //调整计算间隔进行插值
        C_bei = 1;
        m_onload.T_amb.expand(C_bei);
        m_onload.I_H_current.expand(C_bei);
        m_onload.T_top_C.expand(C_bei);
        m_onload.p_sun.expand(C_bei);
        m_onload.p_sun.times(a * b * m_size.S);

        interval = interval / C_bei;

        num = m_onload.T_amb.getSize();
    }

    public void init_t() {
        if (Y_text == 1) {
            m_texture.init();

            /**在Texture.init()里已计算
             * 	%%%%%%%%传热热容计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
             C_th_1=m_tank*c_tank+m_oil*c_oil+m_wnd*c_wnd+m_fe*c_fe;  %环境温度至顶层油温模型的热容.
             C_th_2=m_wnd*c_wnd+m_fe*c_fe;                            %环境温度至平均油温模型的热容.
             C_th_3=m_wnd*c_wnd;                                      %平均油温至绕组平均温度模型的热容.
             */

            /**在TRise.init()里已计算
             * 	%%%%%%%%试验下传热热阻计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
             R_th_top_air=(T_top_r-T_oil_r)/(P_fe_r+P_cu_r);                    %顶层油温至环境温度的传热热阻.
             R_th_oil_air=T_oil_r/(P_fe_r+P_cu_r);                              %平均油温至环境温度的传热热阻.
             R_th_wnd_oil=(T_wnd_r-T_oil_r)/P_cu_r;                             %绕组平均温度至平均油温的传热热阻.
             */

            t_top = m_trise.R_th_top_air * m_texture.C_th_1;
            t_oil = m_trise.R_th_oil_air * m_texture.C_th_2;
            t_wnd = m_trise.R_th_wnd_oil * m_texture.C_th_3;
        } else {
            switch (type) {
                case 1:
                    t_top = 210;                            //环境温度至顶层油温模型的时间常数.
                    t_oil = 210;                            //环境温度至平均油温模型的时间常数.
                    t_wnd = 10;                             //平均油温至绕组平均温度模型的时间常数.
                    break;
                case 2:
                    t_top = 150;                            //环境温度至顶层油温模型的时间常数.
                    t_oil = 150;                            //环境温度至平均油温模型的时间常数.
                    t_wnd = 7;                              //平均油温至绕组平均温度模型的时间常数.
                    break;
                case 3:
                    t_top = 90;                            //环境温度至顶层油温模型的时间常数.
                    t_oil = 90;                            //环境温度至平均油温模型的时间常数.
                    t_wnd = 7;                             //平均油温至绕组平均温度模型的时间常数.
                    break;
                case 4:
                    t_top = 90;                           //环境温度至顶层油温模型的时间常数.
                    t_oil = 90;                           //环境温度至平均油温模型的时间常数.
                    t_wnd = 7;                            //平均油温至绕组平均温度模型的时间常数.
                    break;
            }
        }
    }

    public void solve() {
        T_top_G = new Vector(num, 0.0d);
        T_oil_G = new Vector(num, 0.0d);
        T_wnd_G = new Vector(num, 0.0d);
        T_hs_G = new Vector(num, 0.0d);
        V_G = new Vector(num, 0.0d);

        T_top_now = m_initial.T_top_0;
        T_oil_now = m_initial.T_oil_0;
        T_wnd_now = m_initial.T_wnd_0;
        T_hs_now = m_initial.T_hs_0;

        T_top_rate = m_trise.T_amb_r + m_trise.T_top_r;
        T_oil_rate = m_trise.T_amb_r + m_trise.T_oil_r;
        T_wnd_rate = m_trise.T_amb_r + m_trise.T_wnd_r;

        int seconds = 10;
        Vector t = new Vector(1.0, 1.0, seconds);
        for (int i = 1; i < num; i++) {
            double T_amb_now = m_onload.T_amb.get(i - 1);
            double I_current_now = m_onload.I_H_current.get(i - 1);
            double P_sun_now = m_onload.p_sun.get(i - 1);

            double aT = T_top_now - m_onload.T_top_C.get(i - 1);
            T_top_now = T_top_now - aT;
            T_oil_now = T_oil_now - aT;
            T_wnd_now = T_wnd_now - aT;

            for (int k = 1; k < 60 * interval / seconds; k++) {
                double K = I_current_now / m_trise.I_H_DC;
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
            }

            T_top_G.set(i, T_top_now);
            T_oil_G.set(i, T_oil_now);
            T_wnd_G.set(i, T_wnd_now);
            T_hs_G.set(i, T_hs_now);
            V_G.set(i, V_now);
        }


        T_hs_G.set(0, m_initial.T_hs_0);
        T_top_G.set(0, m_onload.T_top_C.get(0));
        V_G.set(0, m_initial.V_0);
    }

    public void print() {
        int l = 8;
        int dot = 4;
        System.out.println("T_top_G=");
        T_top_G.print(l, dot);
        System.out.println("----");
        System.out.println("T_hs_G=");  // 输出参数
        T_hs_G.print(l, dot);
        System.out.println(String.format("HST= %-8.4f", T_hs_G.get(8))); //输出参数
        System.out.println("----");
        System.out.println("T_top_C="); // 输出参数
        m_onload.T_top_C.print(4, 1);
        System.out.println(String.format("TOPT= %-8.4f", m_onload.T_top_C.get(8))); //输出参数
        System.out.println("----");
        System.out.println("V_G=");
        V_G.print(l, dot);

    }

    public OHst output() {
        OHst hst = new OHst();
        hst.T_hs_G = T_hs_G.toArrayList();
        hst.T_top_C = m_onload.T_top_C.toArrayList();
        hst.HST = T_hs_G.get(T_hs_G.getSize() - 1);
        hst.TOPT = m_onload.T_top_C.get(m_onload.T_top_C.getSize() - 1);
        return hst;
    }
}
