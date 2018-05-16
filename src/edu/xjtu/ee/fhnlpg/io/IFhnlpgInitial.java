package edu.xjtu.ee.fhnlpg.io;

public class IFhnlpgInitial {
    private double T_top_0 = 60;  //接口参数
    private double T_oil_0 = 50;  //接口参数
    private double T_wnd_0 = 65;  //接口参数

    public IFhnlpgInitial() {
    }

    public IFhnlpgInitial(double t_top_0, double t_oil_0, double t_wnd_0) {
        T_top_0 = t_top_0;
        T_oil_0 = t_oil_0;
        T_wnd_0 = t_wnd_0;
    }

    public double getT_top_0() {
        return T_top_0;
    }

    public void setT_top_0(double t_top_0) {
        T_top_0 = t_top_0;
    }

    public double getT_oil_0() {
        return T_oil_0;
    }

    public void setT_oil_0(double t_oil_0) {
        T_oil_0 = t_oil_0;
    }

    public double getT_wnd_0() {
        return T_wnd_0;
    }

    public void setT_wnd_0(double t_wnd_0) {
        T_wnd_0 = t_wnd_0;
    }
}
