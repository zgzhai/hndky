package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

public class ODqfhnlpg extends OHst {
    public double Flimit_IC;
    public String YXTJ;
    public ArrayList<Double> K2;
    public ArrayList<Double> T2;

    public void print() {
        super.print();
        System.out.println(String.format("Flimit_IC= %-8.4f", Flimit_IC)); //输出参数
        System.out.println(String.format("YXTJ= %s", YXTJ)); //输出参数
        int l = 8;
        int dot = 4;
        System.out.println("K2=");
        new Vector(K2).print(l, dot);
        System.out.println("T2=");
        new Vector(T2).print(l, dot);
        System.out.println("----");
    }
}
