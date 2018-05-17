package edu.xjtu.ee.fhnlpg.io;

public class Nameplate {
    public double C_H_r;   //输入变压器高压侧额定容量（MVA）
    public double C_M_r;   //输入变压器中压侧额定容量（MVA）
    public double C_L_r;   //输入变压器低压侧额定容量（MVA
    public double V_H_r;   //输入变压器高压侧额定电压（kV）
    public double V_M_r;   //输入变压器中压侧额定电压（kV）
    public double V_L_r;   //输入变压器低压侧额定电压（kV）
    public double I_H_r;   //输入变压器高压侧额定电流（A）
    public double I_M_r;   //输入变压器中压侧额定电流（A）
    public double I_L_r;   //输入变压器低压侧额定电流（A）

    public Nameplate() {
        init();
    }

    public Nameplate(double c_H_r, double c_M_r, double c_L_r, double v_H_r, double v_M_r, double v_L_r, double i_H_r, double i_M_r, double i_L_r) {
        C_H_r = c_H_r;
        C_M_r = c_M_r;
        C_L_r = c_L_r;
        V_H_r = v_H_r;
        V_M_r = v_M_r;
        V_L_r = v_L_r;
        I_H_r = i_H_r;
        I_M_r = i_M_r;
        I_L_r = i_L_r;
    }

    public void init() {
        C_H_r = 180000;
        C_M_r = 180000;
        C_L_r = 90000;
        V_H_r = 220;
        V_M_r = 121;
        V_L_r = 38.5;
        I_H_r = 818;
        I_M_r = 1636;
        I_L_r = 2337;
    }

    public void setC_H_r(double c_H_r) {
        C_H_r = c_H_r;
    }

    public void setC_M_r(double c_M_r) {
        C_M_r = c_M_r;
    }

    public void setC_L_r(double c_L_r) {
        C_L_r = c_L_r;
    }

    public void setV_H_r(double v_H_r) {
        V_H_r = v_H_r;
    }

    public void setV_M_r(double v_M_r) {
        V_M_r = v_M_r;
    }

    public void setV_L_r(double v_L_r) {
        V_L_r = v_L_r;
    }

    public void setI_H_r(double i_H_r) {
        I_H_r = i_H_r;
    }

    public void setI_M_r(double i_M_r) {
        I_M_r = i_M_r;
    }

    public void setI_L_r(double i_L_r) {
        I_L_r = i_L_r;
    }
}