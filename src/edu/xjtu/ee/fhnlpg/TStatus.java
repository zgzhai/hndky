package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.fhnlpg.io.Nameplate;
import edu.xjtu.ee.fhnlpg.io.OFhzt;
import edu.xjtu.ee.fhnlpg.io.Operation;

/**
 * 计算变压器负荷状态，对应condition.m文件
 * 将变压器负荷状态分为过负荷，重载，正常，轻载四个状态。
 * 过负荷条件如下：
 * 热点温度超过98℃或110℃，高压、中压、低压超过额定负荷电流。
 */
public class TStatus {
    private double Tlimit_top;    //顶层油温限值，热改性绝缘纸采用85，非热改性绝缘纸采用75.
    private double Tlimit_hs;     //热点温度限值,热改性绝缘纸采用110，非热改性绝缘纸采用98.
    private String result;

    private Nameplate nameplate;

    private Operation operation;

    public TStatus() {
        nameplate = new Nameplate();
        operation = new Operation();
    }

    public TStatus(double tlimit_top, double tlimit_hs, Nameplate nameplate, Operation operation) {
        Tlimit_top = tlimit_top;
        Tlimit_hs = tlimit_hs;
        this.nameplate = nameplate;
        this.operation = operation;
    }

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

    public OFhzt output() {
        OFhzt oFhzt = new OFhzt();
        oFhzt.result = result;
        return oFhzt;
    }
}
