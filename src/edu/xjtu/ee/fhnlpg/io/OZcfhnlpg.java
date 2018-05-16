package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.tools.Vector;

public class OZcfhnlpg extends OHst {
    public double K;
    public double Flimit_IC;
    public String YXTJ;

    public void print() {
        super.print();
        System.out.println(String.format("K= %-8.4f", K)); //输出参数
        System.out.println(String.format("Flimit_IC= %-8.4f", Flimit_IC)); //输出参数
        System.out.println(String.format("YXTJ= %s", YXTJ)); //输出参数
        System.out.println("----");
    }
}
