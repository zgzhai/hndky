package edu.xjtu.ee.fhnlpg;

public class Onload {
    public Vector T_amb;       //读取环境温度
    public Vector I_current;   //读取负载电流
    public Vector T_top_C;     //读取顶层油温测量值
    public Vector p_sun;       //读取太阳日辐射功率

    public void Init() {
        double[] amb = {30.5, 30.9, 31.3, 31.5, 32, 32.2, 32.9, 32.5, 32.5};
        T_amb = new Vector(amb);
        double[] current = {564.7,
                565.02,
                565.04,
                564.62,
                563.73,
                564.2,
                524.04,
                523.22,
                524.24};
        I_current = new Vector(current);

        double[] topc = {71.6,
                72.2,
                72.5,
                72.8,
                73.4,
                73.8,
                74.1,
                72.8,
                71.6};
        T_top_C = new Vector(topc);
        double[] psun = {658.75,
                658.75,
                658.75,
                658.75,
                289.75,
                289.75,
                289.75,
                289.75,
                289.75};
        p_sun = new Vector(psun);
    }
}
