package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

public class OHst {
    public ArrayList<Double> T_hs_G;
    public ArrayList<Double> T_top_C;
    public double HST;
    public double TOPT;

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
    }
}
