package edu.xjtu.ee.fhnlpg;

public class Texture {
    private double m_tank;  //输入变压器壳体质量（kg）
    private double m_oil;   //输入变压器油质量（kg
    private double m_wnd;   //输入变压器绕组质量（kg）
    private double m_fe;    //输入变压器铁芯质量（kg）
    private double c_tank;  //输入变压器壳体的比热容（J/(kg·℃）
    private double c_oil;   //输入变压器油的比热容（J/(kg·℃）
    private double c_wnd;   //输入变压器绕组的比热容（J/(kg·℃）
    private double c_fe;    //输入变压器铁芯的比热容（J/(kg·℃）

    public double C_th_1;  //环境温度至顶层油温模型的热容
    public double C_th_2;  //环境温度至平均油温模型的热容
    public double C_th_3;  //平均油温至绕组平均温度模型的热容

    public void Init() {
        m_tank = 17595;
        m_oil = 46000;
        m_wnd = 18975;
        m_fe = 66020;
        c_tank = 317.5;
        c_oil = 1440;
        c_wnd = 390;
        c_fe = 460;
        //传热热容计算
        C_th_1 = m_tank * c_tank + m_oil * c_oil + m_wnd * c_wnd + m_fe * c_fe;
        C_th_2 = m_wnd * c_wnd + m_fe * c_fe;
        C_th_3 = m_wnd * c_wnd;
    }
}
