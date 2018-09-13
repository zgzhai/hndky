package edu.xjtu.ee.fhnlpg;


public class TRise {
    public double T_top_r;     //读取温升试验下的顶层油温升（K）.
    public double T_oil_r;     //读取温升试验下的平均油温升（K）.
    public double T_wnd_r;     //读取温升试验下的绕组平均温升（K）.
    public double T_amb_r;     //读取温升试验下的环境温度（℃）.

    private double T_boil_r;    //计算温升试验下的底层油温升（K）.
    private double T_hs_r;      //计算热点温度温升（K）.

    public double P_fe_r;      //读取温升试验下变压器的空载损耗（W）.
    public double P_cu_r;      //读取温升试验下变压器的负载损耗（W）.
    private double I_H_DC;      //读取温升试验下变压器高压侧负载电流（A）.
    private double I_L_DC;      //读取温升试验下变压器低压侧负载电流（A）.

    private double K_T;
    public double P_dc_r;
    public double P_fj_r;
    public double R;

    public double R_th_top_air;   //顶层油温至环境温度的传热热阻.
    public double R_th_oil_air;   //平均油温至环境温度的传热热阻.
    public double R_th_wnd_oil;   //绕组平均温度至平均油温的传热热阻.

    private double H = 1.1;
    private int kind = 2;

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
