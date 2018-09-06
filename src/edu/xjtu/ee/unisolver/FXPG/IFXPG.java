package edu.xjtu.ee.unisolver.FXPG;

/**
 * Created by Administrator on 2018/6/1.
 */
public class IFXPG {
    private int score = 80;           //健康评分
    private String scoreMsg = "";          //状态评价的风险因素描述
    private String diagnoseResult = "";    //诊断结果：异常状态描述
    private double t = 10;            //运行年限（年）
    private double V = 180;            //变压器容量（MVA）
    private int x = 0;               //设备标识
    private double I = 800;            //高压侧电流

    private double k1 = 4.5777;  //隐藏参数for P_OR
    private double k2 = 8640;    //隐藏参数for P_YY;

    public IFXPG() {
    }

    public IFXPG(int score, double t, double V, int x, double I) {
        this.score = score;
        this.t = t;
        this.V = V;
        this.x = x;
        this.I = I;
    }

    public IFXPG(String scoreStr, double t, double V, int x, double I) {
        if ("正常状态".equals(scoreStr)) {
            this.score = 95;
        } else if ("注意状态".equals(scoreStr)) {
            this.score = 85;
        }else if ("异常状态".equals(scoreStr)) {
            this.score = 75;
        }else if ("严重状态".equals(scoreStr)) {
            this.score = 65;
        }else {
            this.score = 100;
        }
        this.t = t;
        this.V = V;
        this.x = x;
        this.I = I;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getI() {
        return I;
    }

    public void setI(double i) {
        I = i;
    }

    public double getK1() {
        return k1;
    }

    public void setK1(double k1) {
        this.k1 = k1;
    }

    public double getK2() {
        return k2;
    }

    public void setK2(double k2) {
        this.k2 = k2;
    }

    public String getScoreMsg() {
        return scoreMsg;
    }

    public void setScoreMsg(String scoreMsg) {
        this.scoreMsg = scoreMsg;
    }

    public String getDiagnoseResult() {
        return diagnoseResult;
    }

    public void setDiagnoseResult(String diagnoseResult) {
        this.diagnoseResult = diagnoseResult;
    }
}
