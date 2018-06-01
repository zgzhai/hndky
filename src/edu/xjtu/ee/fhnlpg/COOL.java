package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.fhnlpg.io.OCool;

/**
 * 冷却器
 * Created by Administrator on 2017/11/6.
 */
public class COOL {
    private double a1;
    private double b1;
    private double a2;
    private double b2;
    private double a3;
    private double b3;
    private double T1;
    private double TH1;
    private double K1;
    private double ratio;
    private double I_H;    //高压侧负荷电流
    private double I_H_r;  //高压侧额定电流

    public static void main(String[] args) {
        COOL cool = new COOL();
        cool.init(6, 6, 6, 6, 0.2, 0.2, 60, 80, 0.7);
        cool.solve(70, 85, 0.8);
        cool.print();

    }

    public void init(double a1, double b1, double a2, double b2, double a3, double b3, double T1, double TH1, double K1) {
        this.a1 = a1;
        this.b1 = b1;
        this.a2 = a2;
        this.b2 = b2;
        this.a3 = a3;
        this.b3 = b3;
        this.T1 = T1;
        this.TH1 = TH1;
        this.K1 = K1;
    }

    public void solve(double T2, double TH2, double K2) {
        double T_top;
        double T_hot;
        double K_c;
        for (int i = 1; i < 10; i++) {
            T_top = qujian(T1, T2, a1, b1);
            T_hot = qujian(TH1, TH2, a2, b2);
            K_c = qujian(K1, K2, a3, b3);
            T1 = T_top;
            TH1 = T_hot;
            K1 = K_c;
        }

        if (T1 < 50 && TH1 < 75 && K1 < 0.6) {
            ratio = 0;
        } else if (T1 >= 75 || TH1 >= 90 || K1 >= 0.8) {
            ratio = 1.0;
        } else {
            ratio = 0.5;
        }

    }

    private double qujian(double t1, double t2, double a, double b) {
        if (t2 > (t1 + a) && t2 < (t1 - b)) {
            return t1;
        }
        return t2;
    }

    public void print() {
        System.out.println(String.format("冷却器投入%3.0f%%", ratio * 100));
    }

    public OCool output() {
        OCool oCool = new OCool();
        oCool.ratio = String.format("冷却器投入%3.0f%%", ratio * 100);
        return oCool;
    }
}
