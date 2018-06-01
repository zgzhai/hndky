package edu.xjtu.ee.fhnlpg.io;

public class IFhnlpgCool {
    private double a1 = 6;
    private double b1 = 6;
    private double a2 = 6;
    private double b2 = 6;
    private double a3 = 0.1;
    private double b3 = 0.1;
    private double T1 = 60;
    private double TH1 = 80;
    private double K1 = 0.7;
    private double T2;
    private double TH2;
    private double I_H;    //高压侧负荷电流
    private double I_H_r;  //高压侧额定电流

    public IFhnlpgCool() {
    }

    public IFhnlpgCool(double T2, double TH2, double I_H, double I_H_r) {
        this.T2 = T2;
        this.TH2 = TH2;
        this.I_H = I_H;
        this.I_H_r = I_H_r;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double getB3() {
        return b3;
    }

    public void setB3(double b3) {
        this.b3 = b3;
    }

    public double getT1() {
        return T1;
    }

    public void setT1(double t1) {
        T1 = t1;
    }

    public double getTH1() {
        return TH1;
    }

    public void setTH1(double TH1) {
        this.TH1 = TH1;
    }

    public double getK1() {
        return K1;
    }

    public void setK1(double k1) {
        K1 = k1;
    }

    public double getT2() {
        return T2;
    }

    public void setT2(double t2) {
        T2 = t2;
    }

    public double getTH2() {
        return TH2;
    }

    public void setTH2(double TH2) {
        this.TH2 = TH2;
    }

    public double getI_H() {
        return I_H;
    }

    public void setI_H(double i_H) {
        I_H = i_H;
    }

    public double getI_H_r() {
        return I_H_r;
    }

    public void setI_H_r(double i_H_r) {
        I_H_r = i_H_r;
    }
}
