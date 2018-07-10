package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

public class OHst {
    public ArrayList<Double> T_hs_G;
    public ArrayList<Double> T_top_C;
    public ArrayList<Double> T_wnd_G;
    public double HST;
    public double TOPT;
    public double WSDT;

    public ArrayList<Double> getT_hs_G() {
        return T_hs_G;
    }

    public void setT_hs_G(ArrayList<Double> t_hs_G) {
        T_hs_G = t_hs_G;
    }

    public ArrayList<Double> getT_top_C() {
        return T_top_C;
    }

    public void setT_top_C(ArrayList<Double> t_top_C) {
        T_top_C = t_top_C;
    }

    public ArrayList<Double> getT_wnd_G() {
        return T_wnd_G;
    }

    public void setT_wnd_G(ArrayList<Double> t_wnd_G) {
        T_wnd_G = t_wnd_G;
    }

    public double getHST() {
        return HST;
    }

    public void setHST(double HST) {
        this.HST = HST;
    }

    public double getTOPT() {
        return TOPT;
    }

    public void setTOPT(double TOPT) {
        this.TOPT = TOPT;
    }

    public double getWSDT() {
        return WSDT;
    }

    public void setWSDT(double WSDT) {
        this.WSDT = WSDT;
    }

    public void print() {
        int l = 8;
        int dot = 4;
        System.out.println("----");
        System.out.println("T_hs_G=");
        new Vector(T_hs_G).print(l, dot);
        System.out.println(String.format("HST= %-8.4f", HST));
        System.out.println("----");
        System.out.println("T_top_C="); // 输出参数
        new Vector(T_top_C).print(l, dot);
        System.out.println(String.format("TOPT= %-8.4f", TOPT)); //输出参数
        System.out.println("T_wnd_G="); // 输出参数
        new Vector(T_wnd_G).print(l, dot);
        System.out.println(String.format("WSDT= %-8.4f", WSDT)); //输出参数

    }
}
