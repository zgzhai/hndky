package edu.xjtu.ee.fhnlpg;

public class Initial {
    public double T_top_0;
    public double T_oil_0;
    public double T_wnd_0;
    public double T_hs_0;

    private double H = 1.1;

    public void Init() {
        T_top_0 = 71.6;     //读取变压器的顶层油温初值（℃）
        T_oil_0 = 60.6;     //读取变压器的平均油温初值（℃）
        T_wnd_0 = 81.6;     //读取变压器的绕组平均温度初值（℃）
        T_hs_0 = H * (T_wnd_0 - T_oil_0) + T_top_0;   //读取变压器当前的热点温度（℃）.
    }
}
