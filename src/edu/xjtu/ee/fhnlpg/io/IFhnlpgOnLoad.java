package edu.xjtu.ee.fhnlpg.io;

import java.util.ArrayList;

public class IFhnlpgOnLoad {
    private ArrayList<Double> T_amb = new ArrayList<Double>();         //读取环境温度    //接口参数
    private ArrayList<Double> I_H_current = new ArrayList<Double>();   //读取负载电流    //接口参数
    private ArrayList<Double> I_M_current = new ArrayList<Double>();   //读取负载电流    //接口参数
    private ArrayList<Double> I_L_current = new ArrayList<Double>();   //读取负载电流    //接口参数
    private ArrayList<Double> T_top_C = new ArrayList<Double>();       //读取顶层油温测量值  //接口参数

    public IFhnlpgOnLoad(ArrayList<Load> load) {
        for (Load l : load) {
            T_amb.add(l.getT_amb());
            I_H_current.add(l.getI_H_current());
            I_M_current.add(l.getI_M_current());
            I_L_current.add(l.getI_L_current());
            T_top_C.add(l.getT_top_C());
        }
    }

    public ArrayList<Double> getT_amb() {
        return T_amb;
    }

    public ArrayList<Double> getI_H_current() {
        return I_H_current;
    }

    public ArrayList<Double> getI_M_current() {
        return I_M_current;
    }

    public ArrayList<Double> getI_L_current() {
        return I_L_current;
    }

    public ArrayList<Double> getT_top_C() {
        return T_top_C;
    }
}
