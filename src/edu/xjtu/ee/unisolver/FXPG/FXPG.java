package edu.xjtu.ee.unisolver.FXPG;

/**
 * Created by Administrator on 2018/6/1.
 */
public class FXPG {

    private IFXPG ifxpg;
    private String Res;
    private String riskLevel;
    private String riskDisp;

    public FXPG(IFXPG ifxpg) {
        this.ifxpg = ifxpg;
    }

    public void solve() {
        //% 概率等级设置：
        double a1 = 0.03;                            //%超过该值，变压器正常故障极少发生
        double a2 = 0.07;                            //%超过该值，变压器正常故障偶尔发生
        double a3 = 0.12;                            //%超过该值，变压器正常故障可能发生
        double a4 = 0.18;                            //%超过该值，变压器正常故障很可能发生
        //% 风险后果等级：
        double b1 = 2.2;                             //%超过该值，风险后果适中
        double b2 = 2.8;                             //%超过该值，风险后果稍微严重
        double b3 = 3.0;                             //%超过该值，风险后果严重
        //% 风险后果权重：
        double w1 = 0.1313;
        double w2 = 0.1863;
        double w3 = 0.0888;
        double w4 = 0.0888;
        double w5 = 0.2813;
        double w6 = 0.2234;
        //% 调用函数：
        // %变压器总站概率  %变压器当前概率为诱因性故障概率与偶然故障概率值之和。当前只考虑变压器整体状态。
        double Pro = P_YY(ifxpg.getScore(), ifxpg.getK2()) + P_OR(ifxpg.getT(), ifxpg.getK1());
        //%变压器风险后果为重要性后果与损失性后果的加权。
        double F_R = F_ZY(w1, w2, ifxpg.getV(), ifxpg.getX(), ifxpg.getI()) + F_HG(w3, w4, w5, w6);

        //% 判断等级：
        //%其中a0,a1,a2,a3,a4和b0,b1,b2,b3分别为故障概率等级和风险后果等级
        if (Pro > a4 && F_R > b1) {
            Res = "A";
        } else if (Pro > a4) {
            Res = "B";
        } else if (Pro > a3 && F_R > b2) {
            Res = "A";
        } else if (Pro > a3 && F_R > b1) {
            Res = "B";
        } else if (Pro > a3) {
            Res = "C";
        } else if (Pro > a2 && F_R > b3) {
            Res = "A";
        } else if (Pro > a2 && F_R > b2) {
            Res = "B";
        } else if (Pro > a2 && F_R > b1) {
            Res = "C";
        } else if (Pro > a2) {
            Res = "D";
        } else if (Pro > a1 && F_R > b2) {
            Res = "B";
        } else if (Pro > a1 && F_R > b1) {
            Res = "C";
        } else if (Pro > a1) {
            Res = "D";
        } else if (F_R > b3) {
            Res = "B";
        } else if (F_R > b2) {
            Res = "C";
        } else {
            Res = "D";
        }

        riskLevel = String.format("风险等级：%s", Res);
        //% 输出结果：
        switch (Res) {
            case "A":
                riskDisp = "含义：高风险，应立刻采取行动";
                break;
            case "B":
                riskDisp = "含义：风险适中，应采取办法予以消除";
                break;
            case "C":
                riskDisp = "含义：低风险，应采取办法予以减轻";
                break;
            case "D":
                riskDisp = "含义：小风险，可接受";
                break;
        }
    }

    public OFXPG output() {
        OFXPG ofxpg = new OFXPG();
        ofxpg.riskLevel = riskLevel;
        ofxpg.riskDisp = riskDisp;
        return ofxpg;
    }

    /**
     * %%%%1.1、资产价值
     * function y=F1(x)     %x为变压器容量，单位：MVA
     * if x>180
     * F11=10;
     * elseif x>150
     * F11=9;
     * elseif x>120
     * F11=7;
     * elseif x>90
     * F11=5;
     * else
     * F11=3;
     * end
     * y=F11;
     * end
     *
     * @return
     */
    private double F1(double x) {
        double y = 0;
        if (x > 180) {
            y = 10;
        } else if (x > 150) {
            y = 9;
        } else if (x > 120) {
            y = 7;
        } else if (x > 90) {
            y = 5;
        } else {
            y = 3;
        }
        return y;
    }

    /**
     * %%%%1.2、设备等级
     * function y=F2(x,I)     %x为变压器设备标识，x=1时，指主变为常年保电设备，x=0时，主变为普通设备；I为变压器高压侧电流
     * I_C=1000;
     * if x==1
     * F22=5+5*(I/I_C);
     * else
     * F22=5*(I/I_C);
     * end
     * y=F22;
     * end
     *
     * @param x
     * @param I
     * @return
     */
    private double F2(int x, double I) {
        double I_C = 1000;
        double y = 0;
        if (x == 1) {
            y = 5 + 5 * (I / I_C);
        } else {
            y = 5 * (I / I_C);
        }
        return y;
    }


    /**
     * function y=F_HG(w3,w4,w5,w6)  %w1,w2,w3,w4,w5,w6为不同风险后果的重要程度，默认值分别为0.1313，0.1863，0.0888，0.0888，0.2813，0.2234
     * %%%%2.1、资产损失
     * F3=0.125*3+0.083*6+0.00001*9;      % 变压器成本损失按照一般程度，中度程度，重度程度分别为12．5％，8．3％，0．O％。
     * %%%%2.2、环境损失
     * F4=0.083*3+0.028*6+0.00001*9;      % 变压器环境损失按照一般程度，中度程度，重度程度分别为8．3％，2．8％，O．O％。
     * %%%%2.3、人身安全
     * F5=0.001*1+0.0001*3+0.00001*6+0.000001*9; % 变压器人身安全损失按照重轻伤事故，一般人身事故，重大人身事故和特大人身事故分别为0.1％，0.01％，O．O01％和O．O001％。
     * %%%%2.4、电网安全
     * F6=0.028*4+0.001*6+0.0001*7+0.00001*10; % 变压器人身安全损失按照一级电网事故，二级电网事故，三级电网事故和四级电网事故分别为2.8％，0.1％，O．01％和O．O01％。
     * y=w3*F3+w4*F4+w5*F5+w6*F6;
     * end
     *
     * @return
     */
    private double F_HG(double w3, double w4, double w5, double w6) {
        double F3 = 0.125 * 3 + 0.083 * 6 + 0.00001 * 9;
        double F4 = 0.083 * 3 + 0.028 * 6 + 0.00001 * 9;
        double F5 = 0.001 * 1 + 0.0001 * 3 + 0.00001 * 6 + 0.000001 * 9;
        double F6 = 0.028 * 4 + 0.001 * 6 + 0.0001 * 7 + 0.00001 * 10;
        double y = w3 * F3 + w4 * F4 + w5 * F5 + w6 * F6;
        return y;
    }

    /**
     * %%%%调用风险后果评估模型
     * function y=F_ZY(w1,w2)                  %w1,w2,w3,w4,w5,w6为不同风险后果的重要程度，默认值分别为0.1313，0.1863，0.0888，0.0888，0.2813，0.2234
     * A=xlsread('BYQstate');              %变压器状态信息。
     * V=A(3,1);                           %变压器容量
     * b=A(4,1);
     * I=A(5,1);
     * y=w1*F1(V)+w2*F2(b,I);
     * end
     *
     * @param w1
     * @param w2
     * @param V  %变压器容量
     * @param x  %是否为常年保电主变标识
     * @param I  %变压器高压侧电流
     * @return
     */
    private double F_ZY(double w1, double w2, double V, int x, double I) {
        double y = w1 * F1(V) + w2 * F2(x, I);
        return y;
    }

    /**
     * %%%%2、偶然性故障概率
     * function y=P_OR(t)           %此处t为变压器运行年限
     * k=4.5777;                    %函数参数，隐藏可调
     * c=28.287;
     * if t<=15
     * y=0.0169;
     * else
     * y=(k/c)*(t/c)^(k-1);
     * end
     * end
     *
     * @return
     */
    private double P_OR(double t, double k) {
        double c = 28.287;
        double y;
        if (t <= 15) {
            y = 0.0169;
        } else {
            y = (k / c) * Math.pow(t / c, k - 1);
        }
        return y;
    }

    /**
     * %%调用故障概率模型
     * %%%%1、诱因性故障概率
     * function y=P_YY(x)         %x为变压器状态评分值
     * k=8640;                    %函数参数，隐藏可调
     * c=0.15958;
     * if x<60
     * x=60;
     * y=0.33*                       k*exp(-100*c)*(exp(c*(100-x))-1);
     * elseif x<70
     * y=(-0.0067*x*x+0.938*x-31.83)*k*exp(-100*c)*(exp(c*(100-x))-1);
     * else
     * y=                            k*exp(-100*c)*(exp(c*(100-x))-1);
     * end
     * end
     *
     * @return
     */
    private double P_YY(int score, double k) {
        double c = 0.15958;
        double y;
        double x = score < 60 ? 60 : score;
        double expV = k * Math.exp(-100 * c) * (Math.exp(c * (100 - x)) - 1);
        if (score < 60) {
            y = 0.33 * expV;
        } else if (score < 70) {
            y = (-0.0067 * x * x + 0.938 * x - 31.83) * expV;
        } else {
            y = expV;
        }
        return y;
    }
}
