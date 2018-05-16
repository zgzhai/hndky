package edu.xjtu.ee.fhnlpg.io;

/**
 * Created by Administrator on 2018/5/16.
 */
public class Load {
    private double T_amb;         //读取环境温度    //接口参数
    private double I_H_current;   //读取负载电流    //接口参数
    private double I_M_current;   //读取负载电流    //接口参数
    private double I_L_current;   //读取负载电流    //接口参数
    private double T_top_C;       //读取顶层油温测量值  //接口参数

    public Load() {
    }

    public Load(double t_amb, double i_H_current, double i_M_current, double i_L_current, double t_top_C) {
        T_amb = t_amb;
        I_H_current = i_H_current;
        I_M_current = i_M_current;
        I_L_current = i_L_current;
        T_top_C = t_top_C;
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

    public double getI_M_current() {
        return I_M_current;
    }

    public void setI_M_current(double i_M_current) {
        I_M_current = i_M_current;
    }

    public double getI_L_current() {
        return I_L_current;
    }

    public void setI_L_current(double i_L_current) {
        I_L_current = i_L_current;
    }

    public double getT_top_C() {
        return T_top_C;
    }

    public void setT_top_C(double t_top_C) {
        T_top_C = t_top_C;
    }
}
