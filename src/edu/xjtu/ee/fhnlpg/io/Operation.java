package edu.xjtu.ee.fhnlpg.io;

public class Operation {
    public double TH_hs;   //读取变压器当前热点温度值
    public double TH_top;  //读取变压器当前顶层油温值
    public double V_H_C;   //读取变压器当前高压侧电压值
    public double V_M_C;   //读取变压器当前中压侧电压值
    public double V_L_C;   //读取变压器当前低压侧电压值
    public double I_H_C;   //读取变压器当前高压侧电流值
    public double I_M_C;   //读取变压器当前中压侧电流值
    public double I_L_C;   //读取变压器当前低压侧电流值

    public Operation() {
        init();
    }

    public Operation(double TH_hs, double TH_top, double v_H_C, double v_M_C, double v_L_C, double i_H_C, double i_M_C, double i_L_C) {
        this.TH_hs = TH_hs;
        this.TH_top = TH_top;
        V_H_C = v_H_C;
        V_M_C = v_M_C;
        V_L_C = v_L_C;
        I_H_C = i_H_C;
        I_M_C = i_M_C;
        I_L_C = i_L_C;
    }

    public void init() {
        TH_hs = 95;
        TH_top = 70;
        V_H_C = 220;
        V_M_C = 121;
        V_L_C = 38.5;
        I_H_C = 810;
        I_M_C = 1600;
        I_L_C = 2300;
    }

    public void setTH_hs(double TH_hs) {
        this.TH_hs = TH_hs;
    }

    public void setTH_top(double TH_top) {
        this.TH_top = TH_top;
    }

    public void setV_H_C(double v_H_C) {
        V_H_C = v_H_C;
    }

    public void setV_M_C(double v_M_C) {
        V_M_C = v_M_C;
    }

    public void setV_L_C(double v_L_C) {
        V_L_C = v_L_C;
    }

    public void setI_H_C(double i_H_C) {
        I_H_C = i_H_C;
    }

    public void setI_M_C(double i_M_C) {
        I_M_C = i_M_C;
    }

    public void setI_L_C(double i_L_C) {
        I_L_C = i_L_C;
    }
}