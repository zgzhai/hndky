package edu.xjtu.ee.fhnlpg;

public class Size {
    private double l;   //输入变压器箱体长度（m）
    private double w;   //输入变压器箱体宽度（m）
    private double h;   //输入变压器箱体高度（m）
    public double S;       //变压器表面积

    public void Init() {
        l = 6.65;
        w = 2.308;
        h = 3.711;
        S = 2 * (l * w + w * h + l * h);
    }

}
