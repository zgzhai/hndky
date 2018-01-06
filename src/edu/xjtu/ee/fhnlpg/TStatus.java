package edu.xjtu.ee.fhnlpg;

/**
 * 计算变压器负荷状态，对应condition.m文件
 * 将变压器负荷状态分为过负荷，重载，正常，轻载四个状态。
 * 过负荷条件如下：
 * 热点温度超过98℃或110℃，高压、中压、低压超过额定负荷电流。
 */
public class TStatus {

    private class Nameplate {
        private double C_H_r;   //输入变压器高压侧额定容量（MVA）
        private double C_M_r;   //输入变压器中压侧额定容量（MVA）
        private double C_L_r;   //输入变压器低压侧额定容量（MVA
        private double V_H_r;   //输入变压器高压侧额定电压（kV）
        private double V_M_r;   //输入变压器中压侧额定电压（kV）
        private double V_L_r;   //输入变压器低压侧额定电压（kV）
        private double I_H_r;   //输入变压器高压侧额定电流（A）
        private double I_M_r;   //输入变压器中压侧额定电流（A）
        private double I_L_r;   //输入变压器低压侧额定电流（A）

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
    }

    private class Operation {
        private double TH_hs;   //读取变压器当前热点温度值
        private double TH_top;  //读取变压器当前顶层油温值
        private double V_H_C;   //读取变压器当前高压侧电压值
        private double V_M_C;   //读取变压器当前中压侧电压值
        private double V_L_C;   //读取变压器当前低压侧电压值
        private double I_H_C;   //读取变压器当前高压侧电流值
        private double I_M_C;   //读取变压器当前中压侧电流值
        private double I_L_C;   //读取变压器当前低压侧电流值

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
    }

    private double Tlimit_top;    //顶层油温限值，热改性绝缘纸采用85，非热改性绝缘纸采用75.
    private double Tlimit_hs;     //热点温度限值,热改性绝缘纸采用110，非热改性绝缘纸采用98.
    private String result;

    private Nameplate nameplate = new Nameplate();
    private Operation operation = new Operation();

    public static void main(String[] args) {
        // write your code here
        TStatus tStatus = new TStatus();
        tStatus.init();
        tStatus.solve();
        tStatus.print();
    }

    public void init() {
        Tlimit_top = 75;
        Tlimit_hs = 98;
        nameplate.init();
        operation.init();
    }

    public void solve() {
        if (operation.TH_hs >= Tlimit_hs || operation.TH_top >= Tlimit_top) {
            result = "变压器过热引起的过负荷";
        } else if (operation.I_H_C >= nameplate.I_H_r) {
            result = "变压器高压侧过负荷";
        } else if (operation.I_M_C >= nameplate.I_M_r) {
            result = "变压器中压侧过负荷";
        } else if (operation.I_L_C >= nameplate.I_L_r) {
            result = "变压器低压侧过负荷";
        } else if (operation.I_H_C >= 0.8 * nameplate.I_H_r) {
            result = "变压器重载";
        } else if (operation.I_H_C >= 0.5 * nameplate.I_H_r) {
            result = "变压器正常负荷运行";
        } else {
            result = "变压器轻载";
        }
    }

    public void print() {
        System.out.println(result);
    }
}
