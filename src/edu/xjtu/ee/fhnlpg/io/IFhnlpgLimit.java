package edu.xjtu.ee.fhnlpg.io;

/**
 * Created by Administrator on 2018/5/16.
 */
public class IFhnlpgLimit {
    private int n_iters = 96;                   //迭代次数，一天24小时，每小时采4个点 //隐藏参数
    private double Klimit_fu = 1.2;             //辅助设备容量等级约束              //隐藏参数
    private double Tlimit_top = 105;            //顶层油温约束                     //隐藏参数
    private double Tlimit_hs = 120;             //热点温度约束                     //隐藏参数
    private double Llimit_L_coef = 10;          //相对寿命损失约束(系数)            //隐藏参数
    private double IC = 100;                    //变压器状态评分值                  //隐藏参数
    private double T_amb_offset = 10;           //环境温度的调整值                  //隐藏参数
    private double T_amb = 34;                  //load1,2,3
    private double I_H_current = 564;           //load1,2
    private double I_H_current_coef = 1;        //load1,2

    public IFhnlpgLimit() {
    }

    public IFhnlpgLimit(double t_amb) {
        T_amb = t_amb;
    }

    public IFhnlpgLimit(double t_amb, double i_H_current, double i_H_current_coef) {
        T_amb = t_amb;
        I_H_current = i_H_current;
        I_H_current_coef = i_H_current_coef;
    }

    public int getN_iters() {
        return n_iters;
    }

    public void setN_iters(int n_iters) {
        this.n_iters = n_iters;
    }

    public double getKlimit_fu() {
        return Klimit_fu;
    }

    public void setKlimit_fu(double klimit_fu) {
        Klimit_fu = klimit_fu;
    }

    public double getTlimit_top() {
        return Tlimit_top;
    }

    public void setTlimit_top(double tlimit_top) {
        Tlimit_top = tlimit_top;
    }

    public double getTlimit_hs() {
        return Tlimit_hs;
    }

    public void setTlimit_hs(double tlimit_hs) {
        Tlimit_hs = tlimit_hs;
    }

    public double getLlimit_L_coef() {
        return Llimit_L_coef;
    }

    public void setLlimit_L_coef(double llimit_L_coef) {
        Llimit_L_coef = llimit_L_coef;
    }

    public double getIC() {
        return IC;
    }

    public void setIC(double IC) {
        this.IC = IC;
    }

    public void setIC(String ICStr) {
        if ("正常状态".equals(ICStr)) {
            this.IC = 95;
        } else if ("注意状态".equals(ICStr)) {
            this.IC = 85;
        }else if ("异常状态".equals(ICStr)) {
            this.IC = 75;
        }else if ("严重状态".equals(ICStr)) {
            this.IC = 65;
        }else {
            this.IC = 100;
        }
    }

    public double getT_amb() {
        return T_amb;
    }

    public void setT_amb(double t_amb) {
        T_amb = t_amb;
    }

    public double getI_H_current() {
        return I_H_current;
    }

    public void setI_H_current(double i_H_current) {
        I_H_current = i_H_current;
    }

    public double getI_H_current_coef() {
        return I_H_current_coef;
    }

    public void setI_H_current_coef(double i_H_current_coef) {
        I_H_current_coef = i_H_current_coef;
    }

    public double getT_amb_offset() {
        return T_amb_offset;
    }

    public void setT_amb_offset(double t_amb_offset) {
        T_amb_offset = t_amb_offset;
    }
}
