package edu.xjtu.ee.fhnlpg;

public class Nameplate {
    public double S_H_r;   //输入变压器高压侧额定容量（MVA）
    private double S_M_r;   //输入变压器中压侧额定容量（MVA）
    public double S_L_r;   //输入变压器低压侧额定容量（MVA
    public double U_H_r;   //输入变压器高压侧额定电压（kV）
    private double U_M_r;   //输入变压器中压侧额定电压（kV）
    public double U_L_r;   //输入变压器低压侧额定电压（kV）
    public double I_H_r;   //输入变压器高压侧额定电流（A）
    private double I_M_r;   //输入变压器中压侧额定电流（A）
    public double I_L_r;   //输入变压器低压侧额定电流（A）

    public void Init() {
        S_H_r = 180;
        S_L_r = 180;
        U_H_r = 198;
        U_L_r = 38.5;
        I_H_r = 564.7;
        I_L_r = 2699.4;
    }
}
