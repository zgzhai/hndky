package edu.xjtu.ee.fhnlpg;

/**
 * 计算故障因子
 */
public class GZZT {

    private class StateData {
        private double d1;
        private double d2;
        private double d3;
        private double d4;
        private double d5;
        private double d6;
        private double d7;
        private double d8;

        public void init() {
            d1 = 0;
            d2 = 0;
            d3 = 0;
            d4 = 0;
            d5 = 0;
            d6 = 0;
            d7 = 0;
            d8 = 0;
        }
    }

    private double K1;
    private double C1;
    private double k1;
    private double k2;
    private double y1;
    private double y2;
    private StateData stateData = new StateData();

    public static void main(String[] args) {
        // write your code here
        GZZT gzzt = new GZZT();
        gzzt.init();
        System.out.println(gzzt.solve(1.2));
    }

    public void init() {
        K1 = 1.73 * 0.000001;
        C1 = 0.193;
        k1 = 1.7300e-06;

        stateData.init();
    }

    /**
     * 计算故障状态影响因子
     *
     * @param q
     * @return
     */
    public double solve(double q) {
        double y;
        k2 = K1 * Math.exp(-100 * C1) * Math.exp(C1 * (100 - q) - 1);
        y1 = (k1 - k2) / k1;
        y2 = stateData.d1 + stateData.d2 + stateData.d3 + stateData.d4 +
                stateData.d5 + stateData.d6 + stateData.d7 + stateData.d8;

        if (y2 >= 1) {
            y = y1 + y2;
        } else {
            y = y1;
        }
        return y;
    }
}
