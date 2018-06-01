package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

public class OZcfhnlpg extends OHst {
    public double K;
    public double Flimit_IC;
    public String YXTJ;
    public ArrayList<Double> V_G;
    public double XDLHSL;

    public void print() {
        super.print();
        System.out.println(String.format("K= %-8.4f", K)); //输出参数
        System.out.println(String.format("Flimit_IC= %-8.4f", Flimit_IC)); //输出参数
        System.out.println(String.format("YXTJ= %s", YXTJ)); //输出参数
        System.out.println("----");
        System.out.println("V_G="); // 输出参数
        new Vector(V_G).print(8, 4);
        System.out.println(String.format("XDLHSL= %-8.4f", XDLHSL)); //输出参数

    }
}
