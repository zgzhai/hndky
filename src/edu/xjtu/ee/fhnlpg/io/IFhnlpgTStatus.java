package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.fhnlpg.TStatus;

public class IFhnlpgTStatus {
    private double Tlimit_top;    //顶层油温限值，热改性绝缘纸采用85，非热改性绝缘纸采用75.
    private double Tlimit_hs;     //热点温度限值,热改性绝缘纸采用110，非热改性绝缘纸采用98.

    private Nameplate nameplate;
    private Operation operation;

    public IFhnlpgTStatus() {
    }

    public IFhnlpgTStatus(double tlimit_top, double tlimit_hs, Nameplate nameplate, Operation operation) {
        Tlimit_top = tlimit_top;
        Tlimit_hs = tlimit_hs;
        this.nameplate = nameplate;
        this.operation = operation;
    }

    public double getTlimit_top() {
        return Tlimit_top;
    }

    public void setTlimit_top(double tlimit_top) {
        Tlimit_top = tlimit_top;
    }

    public double getTlimit_hs() {
        return Tlimit_hs;
    }

    public void setTlimit_hs(double tlimit_hs) {
        Tlimit_hs = tlimit_hs;
    }

    public Nameplate getNameplate() {
        return nameplate;
    }

    public void setNameplate(Nameplate nameplate) {
        this.nameplate = nameplate;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
