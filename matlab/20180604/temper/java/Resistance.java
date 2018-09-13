package edu.xjtu.ee.fhnlpg;

public class Resistance {

    private int Tap_r;
    private int Tap;
    private double U_fjt;
    public double T_C;
    public Vector R_H;
    private Vector R_Ld;
    private double R_p;
    public Vector R_L;
    private Vector K_V;
    public double k_HL;

    private int kind = 2;

    public void Init() {
        Tap_r = 9;
        Tap = 17;
        U_fjt = 1.25;
        T_C = 26;


        switch (kind) {
            //kind=2
            case 2:
                double[] r_h = {0.2702, 0.2704, 0.2704};
                R_H = new Vector(r_h);
                double[] r_ld = {0.011712, 0.011727, 0.011759};
                R_Ld = new Vector(r_ld);
                R_p = R_Ld.sum() / 2;

                R_L = new Vector(3);    //低压侧相电阻
                R_L.set(0, (R_Ld.get(2) - R_p) - R_Ld.get(0) * R_Ld.get(1) / (R_Ld.get(2) - R_p));
                R_L.set(1, (R_Ld.get(0) - R_p) - R_Ld.get(2) * R_Ld.get(1) / (R_Ld.get(0) - R_p));
                R_L.set(2, (R_Ld.get(1) - R_p) - R_Ld.get(0) * R_Ld.get(2) / (R_Ld.get(1) - R_p));

                double[] kv = {11.524, 11.393, 11.262, 11.131, 11, 10.869, 10.738, 10.607,
                        10.476, 10.345, 10.214, 10.083, 9.9524, 9.8214, 9.6905, 9.5595, 5.143};
                K_V = new Vector(kv);
                k_HL = K_V.get(Tap - 1);
                break;
        }
    }

}
