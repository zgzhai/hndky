package edu.xjtu.ee.tools;

/**
 * 每个Vector元素信息
 * Created by Administrator on 2017/11/21.
 */
public class VElement {
    int index;
    double val;

    public VElement(int index, double val) {
        this.index = index;
        this.val = val;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public void print(int l, int dot) {
        String formatStr = "%d, %-" + l + "." + dot + "f";
        String str = String.format(formatStr, index, val);
        System.out.println(str);
    }

}
