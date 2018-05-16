package edu.xjtu.ee.fhnlpg.io;

/**
 * Created by Administrator on 2018/5/16.
 */
public class TRiseV {
    private double T_top_r = 41.2;     //读取温升试验下的顶层油温升（K）.//接口参数
    private double T_oil_r = 28.28;     //读取温升试验下的平均油温升（K）.//接口参数
    private double T_wnd_r = 45.9;     //读取温升试验下的绕组平均温升（K）.//接口参数
    private double T_amb_r = 32;     //读取温升试验下的环境温度（℃）.         //接口参数

    private double P_fe_r = 82169;      //读取温升试验下变压器的空载损耗（W）.    //接口参数
    private double P_cu_r = 512386;      //读取温升试验下变压器的负载损耗（W）.    //接口参数
    private double I_H_DC = 564.9;      //读取温升试验下变压器高压侧负载电流（A）.//接口参数
    private double I_M_DC = 564.9;                                           //接口参数
    private double I_L_DC = 2699.4;      //读取温升试验下变压器低压侧负载电流（A）.//接口参数
    private double H = 1.1;           //热点系数取值（1.1-1.5）              //隐藏参数

    public TRiseV() {
    }

    public TRiseV(double t_top_r, double t_oil_r, double t_wnd_r, double t_amb_r, double p_fe_r, double p_cu_r, double i_H_DC, double i_M_DC, double i_L_DC) {
        T_top_r = t_top_r;
        T_oil_r = t_oil_r;
        T_wnd_r = t_wnd_r;
        T_amb_r = t_amb_r;
        P_fe_r = p_fe_r;
        P_cu_r = p_cu_r;
        I_H_DC = i_H_DC;
        I_M_DC = i_M_DC;
        I_L_DC = i_L_DC;
    }

    public double getT_top_r() {
        return T_top_r;
    }

    public void setT_top_r(double t_top_r) {
        T_top_r = t_top_r;
    }

    public double getT_oil_r() {
        return T_oil_r;
    }

    public void setT_oil_r(double t_oil_r) {
        T_oil_r = t_oil_r;
    }

    public double getT_wnd_r() {
        return T_wnd_r;
    }

    public void setT_wnd_r(double t_wnd_r) {
        T_wnd_r = t_wnd_r;
    }

    public double getT_amb_r() {
        return T_amb_r;
    }

    public void setT_amb_r(double t_amb_r) {
        T_amb_r = t_amb_r;
    }

    public double getP_fe_r() {
        return P_fe_r;
    }

    public void setP_fe_r(double p_fe_r) {
        P_fe_r = p_fe_r;
    }

    public double getP_cu_r() {
        return P_cu_r;
    }

    public void setP_cu_r(double p_cu_r) {
        P_cu_r = p_cu_r;
    }

    public double getI_H_DC() {
        return I_H_DC;
    }

    public void setI_H_DC(double i_H_DC) {
        I_H_DC = i_H_DC;
    }

    public double getI_M_DC() {
        return I_M_DC;
    }

    public void setI_M_DC(double i_M_DC) {
        I_M_DC = i_M_DC;
    }

    public double getI_L_DC() {
        return I_L_DC;
    }

    public void setI_L_DC(double i_L_DC) {
        I_L_DC = i_L_DC;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }
}
