package edu.xjtu.ee.dwfxpg.io;

import java.util.ArrayList;

public class OFHXJ extends OZLCL {
    public int status;
    //不同状态下msg1取值如下：
    //0: 此状态下不需要负荷削减，请加强监控
    //1:不符合要求需引入松弛变量
    //2:已找到最优解!
    //3:此状态下不存在优化最优解! 请加强重载线路及重载变压器的运行监控，过载设备请根据变压器负荷能力评估结果进行负荷转移！
    //4:此种状态下不需要削减负荷，请加强重载线路及重载变压器的运行监控
    //5:此种状态下可以进行负荷转移以优化电网静态稳定性，重载线路受端及重载变压器最少需要转移的负荷量总量约为:%f  p.u.，请根据主变负荷能力评估结果进行优化 \n',f
    public String msg1;
    //public ArrayList<Double> X;
    public double f;

    public void print() {
        System.out.println(msg1);
        /*
        if (status != 0) {
            System.out.println("基向量为:");
            new edu.xjtu.ee.tools.Vector(X).print(8, 4);
            System.out.println(String.format("目标函数值为 = %s", f));
            super.print();
        }
        */
    }
}
