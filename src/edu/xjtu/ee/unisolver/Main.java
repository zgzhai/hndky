package edu.xjtu.ee.unisolver;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals("COOL")) {
            COOL();
        } else if (args.length > 0 && args[0].toUpperCase().equals("HST")) {
            HST();
        } else if (args.length > 0 && args[0].toUpperCase().equals("ZCFHNLPG")) {
            ZCFHNLPG();
        } else if (args.length > 0 && args[0].toUpperCase().equals("CQFHNLPG")) {
            CQFHNLPG();
        } else if (args.length > 0 && args[0].toUpperCase().equals("DQFHNLPG")) {
            DQFHNLPG();
        } else if (args.length > 0 && args[0].toUpperCase().equals("FHZT")) {
            FHZT();
        } else {

        }
    }

    public static void COOL() {

    }

    public static void HST() {
        UniParameter uniParameter = new UniParameter();
        uniParameter.iFhnlpgResistance = new IFhnlpgResistance();
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "HST");
    }


    public static void ZCFHNLPG() {

    }

    public static void CQFHNLPG() {

    }

    public static void DQFHNLPG() {

    }

    public static void FHZT() {

    }
}
