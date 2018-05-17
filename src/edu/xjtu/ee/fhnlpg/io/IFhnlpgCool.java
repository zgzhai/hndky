package edu.xjtu.ee.fhnlpg.io;

public class IFhnlpgCool {
    private double a1;
    private double b1;
    private double a2;
    private double b2;
    private double a3;
    private double b3;
    private double T1;
    private double TH1;
    private double K1;
    private double T2;
    private double TH2;
    private double K2;

    public IFhnlpgCool() {
    }

    public IFhnlpgCool(double a1, double b1, double a2, double b2, double a3, double b3, double t1, double TH1, double k1, double t2, double TH2, double k2) {
        this.a1 = a1;
        this.b1 = b1;
        this.a2 = a2;
        this.b2 = b2;
        this.a3 = a3;
        this.b3 = b3;
        T1 = t1;
        this.TH1 = TH1;
        K1 = k1;
        T2 = t2;
        this.TH2 = TH2;
        K2 = k2;
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

    public double getK2() {
        return K2;
    }

    public void setK2(double k2) {
        K2 = k2;
    }
}
