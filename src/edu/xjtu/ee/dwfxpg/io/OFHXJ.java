package edu.xjtu.ee.dwfxpg.io;

import java.util.ArrayList;

public class OFHXJ extends OZLCL {
    public int status;    //1:不符合要求需引入松弛变量, 2:已找到最优解!, 3:此问题不存在最优解
    public ArrayList<Double> X;
    public double f;

    public void print() {
        switch (status) {
            case 1:
                System.out.println("不符合要求需引入松弛变量\r\n");
                break;
            case 2:
                System.out.println("已找到最优解\r\n");
                break;
            case 3:
                System.out.println("此问题不存在最优解\r\n");
                break;
            default:
                System.out.println("status=0\r\n");
                break;
        }

        System.out.println("基向量为:");
        new edu.xjtu.ee.tools.Vector(X).print(8, 4);
        System.out.println(String.format("目标函数值为 = %s", f));
        super.print();
    }
}
