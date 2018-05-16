package edu.xjtu.ee.fhnlpg.io;

/**
 * Created by Administrator on 2018/5/16.
 */
/**
 * A,B,C三相值
 */
public class ResistanceABC {
    private double A;
    private double B;
    private double C;

    public ResistanceABC() {
    }

    public ResistanceABC(double a, double b, double c) {
        A = a;
        B = b;
        C = c;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }
}
