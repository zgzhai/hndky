package edu.xjtu.ee.fhnlpg.io;

//隐藏参数
public class IFhnlpgBase {
    private int type = 3;       //默认值
    private int kind = 3;       //默认值
    private int Y_text = 0;     //接口参数
    private int Y_temdata = 1;  //接口参数
    private int Y_cool = 1;     //接口参数
    private int interval = 10;  //接口参数
    private int Y_resis = 1;    //隐藏参数
    private int Y_paper = 0;    //隐藏参数
    private double n = 0.33;    //隐藏参数
    private double n1 = 0.5;    //隐藏参数

    public IFhnlpgBase() {
    }

    public IFhnlpgBase(int y_text, int y_temdata, int y_cool, int interval) {
        Y_text = y_text;
        Y_temdata = y_temdata;
        Y_cool = y_cool;
        this.interval = interval;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getY_text() {
        return Y_text;
    }

    public void setY_text(int y_text) {
        Y_text = y_text;
    }

    public int getY_temdata() {
        return Y_temdata;
    }

    public void setY_temdata(int y_temdata) {
        Y_temdata = y_temdata;
    }

    public int getY_cool() {
        return Y_cool;
    }

    public void setY_cool(int y_cool) {
        Y_cool = y_cool;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getY_resis() {
        return Y_resis;
    }

    public void setY_resis(int y_resis) {
        Y_resis = y_resis;
    }

    public int getY_paper() {
        return Y_paper;
    }

    public void setY_paper(int y_paper) {
        Y_paper = y_paper;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }
}
