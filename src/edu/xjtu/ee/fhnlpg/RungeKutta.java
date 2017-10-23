package edu.xjtu.ee.fhnlpg;

import edu.xjtu.ee.tools.Vector;

/**
 * Created by Administrator on 2017/9/25.
 */
public class RungeKutta {
    private double[] x;
    private double[] y;
    private int steps;

    //构造f**函数所需参数
    private double R;
    private double K;
    private double P_cu_now;
    private double P_sun_now;
    private double P_fe_r;
    private double T_top_r;
    private double T_oil_r;
    private double T_wnd_r;
    private double T_amb_now;

    private double n;
    private double n1;
    private double u_p;
    private double t_oil;
    private double t_top;
    private double t_wnd;

    public double T_oil_now;
    public double T_top_now;
    public double T_wnd_now;

    public static void main(String[] args) {
        Vector mx = new Vector(1, 1, 10);
        RungeKutta rk = new RungeKutta(mx.getArray(), 1);

    }

    public RungeKutta(double[] x, double y0) {
        this.x = x;
        this.steps = x.length;
        y = new double[this.steps];
        y[0] = y0;
    }

    public void setY0(double y0) {
        y[0] = y0;
    }

    public void Init(double r, double k, double p_cu_now, double p_sun_now, double p_fe_r, double t_top_r, double t_oil_r, double t_wnd_r, double t_amb_now, double n, double n1, double u_p, double t_oil, double t_top, double t_wnd, double t_oil_now, double t_top_now, double t_wnd_now) {
        R = r;
        K = k;
        P_cu_now = p_cu_now;
        P_sun_now = p_sun_now;
        P_fe_r = p_fe_r;
        T_top_r = t_top_r;
        T_oil_r = t_oil_r;
        T_wnd_r = t_wnd_r;
        T_amb_now = t_amb_now;
        this.n = n;
        this.n1 = n1;
        this.u_p = u_p;
        this.t_oil = t_oil;
        this.t_top = t_top;
        this.t_wnd = t_wnd;
        T_oil_now = t_oil_now;
        T_top_now = t_top_now;
        T_wnd_now = t_wnd_now;
    }


    public double f_oil(double xx, double yy) {
        double f = (((1 + R * Math.pow(K, 2) * P_cu_now + P_sun_now / P_fe_r) / (1 + R)) * T_oil_r - (Math.pow(yy - T_amb_now, n + 1) / Math.pow(u_p * T_oil_r, n))) / t_oil;
        return f;
    }

    public double f_top(double xx, double yy) {
        double f = ((1 + R * Math.pow(K, 2) * P_cu_now) / (1 + R) * (T_top_r - T_oil_r) - (Math.pow(yy - T_oil_now, n1 + 1) / Math.pow(u_p * (T_top_r - T_oil_r), n1))) / t_top;
        return f;
    }

    public double f_wnd(double xx, double yy) {
        double f = (Math.pow(K, 2) * P_cu_now * (T_wnd_r - T_oil_r) - (Math.pow(yy - T_oil_now, n1 + 1) / Math.pow(u_p * (T_wnd_r - T_oil_r), n1))) / t_wnd;
        return f;
    }

    public void solve_oil() {
        double h = x[1] - x[0];
        for (int i = 0; i < steps - 1; i++) {
            double k1 = f_oil(x[i], y[i]);
            double k2 = f_oil(x[i] + h / 2, y[i] + k1 * h / 2);
            double k3 = f_oil(x[i] + h / 2, y[i] + k2 * h / 2);
            double k4 = f_oil(x[i] + h, y[i] + k3 * h);
            y[i + 1] = y[i] + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
    }

    public void solve_top() {
        double h = x[1] - x[0];
        for (int i = 0; i < steps - 1; i++) {
            double k1 = f_top(x[i], y[i]);
            double k2 = f_top(x[i] + h / 2, y[i] + k1 * h / 2);
            double k3 = f_top(x[i] + h / 2, y[i] + k2 * h / 2);
            double k4 = f_top(x[i] + h, y[i] + k3 * h);
            y[i + 1] = y[i] + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
    }

    public void solve_wnd() {
        double h = x[1] - x[0];
        for (int i = 0; i < steps - 1; i++) {
            double k1 = f_wnd(x[i], y[i]);
            double k2 = f_wnd(x[i] + h / 2, y[i] + k1 * h / 2);
            double k3 = f_wnd(x[i] + h / 2, y[i] + k2 * h / 2);
            double k4 = f_wnd(x[i] + h, y[i] + k3 * h);
            y[i + 1] = y[i] + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
    }

    public void print() {
        for (int i = 0; i < steps; i++) {
            System.out.println("x,y=" + x[i] + "," + y[i]);
        }
    }

    /**
     * 获取元素值
     *
     * @param i 元素序号
     * @return
     */
    public double getY(int i) {
        return y[i];
    }
}
