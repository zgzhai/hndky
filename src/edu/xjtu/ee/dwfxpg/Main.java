package edu.xjtu.ee.dwfxpg;

import Jama.Matrix;

import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].toLowerCase().equals("h")) {
            System.out.println("show help");
        } else if (args.length > 0 && args[0].equals("ZLCL")) {
            CZLCL c = new CZLCL();
            c.Init();
            c.Calc();
            c.OutputScreen();
            c.OutToFile();
        } else {
            CPQ cpq = new CPQ(5, 5, 0.00001);
            cpq.init();       //数据初始化
            cpq.solve();
            cpq.output();
        }
    }

}
