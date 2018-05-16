package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.fhnlpg.io.*;

import java.util.ArrayList;

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
            DQFHNLPG();
        }
    }

    public static void COOL() {

    }

    public static void HST() {
        UniParameter uniParameter = new UniParameter();
        //1. 基本参数
        uniParameter.setiFhnlpgBase(new IFhnlpgBase(0, 1, 1, 10));

        //2. 变压器绕组直流电阻及电压变比
        ArrayList<ResistanceABC> D_H = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_M = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_L = new ArrayList<ResistanceABC>();
        D_H.add(new ResistanceABC(0.2973, 0.2982, 0.2983));
        D_H.add(new ResistanceABC(0.2939, 0.2945, 0.2948));
        D_H.add(new ResistanceABC(0.2903, 0.291, 0.2913));
        D_H.add(new ResistanceABC(0.2868, 0.2874, 0.2877));
        D_H.add(new ResistanceABC(0.2838, 0.2846, 0.2848));
        D_H.add(new ResistanceABC(0.2803, 0.2809, 0.2812));
        D_H.add(new ResistanceABC(0.2772, 0.278, 0.2782));
        D_H.add(new ResistanceABC(0.2737, 0.2743, 0.2747));
        D_H.add(new ResistanceABC(0.2702, 0.2704, 0.2704));
        D_H.add(new ResistanceABC(0.2744, 0.2749, 0.2753));
        D_H.add(new ResistanceABC(0.2778, 0.2785, 0.2788));
        D_H.add(new ResistanceABC(0.2814, 0.2819, 0.2832));
        D_H.add(new ResistanceABC(0.2844, 0.2851, 0.2854));
        D_H.add(new ResistanceABC(0.288, 0.2885, 0.2889));
        D_H.add(new ResistanceABC(0.2909, 0.2915, 0.2918));
        D_H.add(new ResistanceABC(0.2946, 0.2951, 0.2955));
        D_H.add(new ResistanceABC(0.2974, 0.298, 0.2983));
        D_M.add(new ResistanceABC(0.11589, 0.11594, 0.11667));
        D_L.add(new ResistanceABC(0.011712, 0.011727, 0.011759));
        uniParameter.setiFhnlpgResistance(new IFhnlpgResistance(D_H, D_M, D_L, 17));

        //3. 变比信息
        ArrayList<RatioHML> ratio = new ArrayList<RatioHML>();
        ratio.add(new RatioHML(2.104, 10.95, 23.0388));
        ratio.add(new RatioHML(2.08, 10.95, 22.776));
        ratio.add(new RatioHML(2.057, 10.95, 22.52415));
        ratio.add(new RatioHML(2.033, 10.95, 22.26135));
        ratio.add(new RatioHML(2.009, 10.95, 21.99855));
        ratio.add(new RatioHML(1.985, 10.95, 21.73575));
        ratio.add(new RatioHML(1.961, 10.95, 21.47295));
        ratio.add(new RatioHML(1.937, 10.95, 21.21015));
        ratio.add(new RatioHML(1.913, 10.95, 20.94735));
        ratio.add(new RatioHML(1.889, 10.95, 20.68455));
        ratio.add(new RatioHML(1.865, 10.95, 20.42175));
        ratio.add(new RatioHML(1.841, 10.95, 20.15895));
        ratio.add(new RatioHML(1.817, 10.95, 19.89615));
        ratio.add(new RatioHML(1.793, 10.95, 19.63335));
        ratio.add(new RatioHML(1.77, 10.95, 19.3815));
        ratio.add(new RatioHML(1.746, 10.95, 19.1187));
        ratio.add(new RatioHML(1.722, 10.95, 18.8559));
        uniParameter.setiFhnlpgRatio(new IFhnlpgRatio(ratio));

        //4. 变压器温升
        IFhnlpgTRise iFhnlpgTRise = new IFhnlpgTRise(new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4),
                new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4));
        uniParameter.setiFhnlpgTRise(iFhnlpgTRise);

        //5.初始数据
        uniParameter.setiFhnlpgInitial(new IFhnlpgInitial(60, 50, 65));

        //6.在线数据
        ArrayList<Load> load = new ArrayList<Load>();
        load.add(new Load(30.5, 564.7, 564.7, 564.7, 71.6));
        load.add(new Load(30.9, 565.02, 565.02, 565.02, 72.2));
        load.add(new Load(31.3, 565.04, 565.04, 565.04, 72.5));
        load.add(new Load(31.5, 564.62, 564.62, 564.62, 72.8));
        load.add(new Load(32.0, 563.73, 563.73, 563.73, 73.4));
        load.add(new Load(32.2, 564.2, 564.2, 564.2, 73.8));
        load.add(new Load(32.9, 524.04, 524.04, 524.04, 74.1));
        load.add(new Load(32.5, 523.22, 523.22, 523.22, 72.8));
        load.add(new Load(32.5, 524.24, 524.24, 524.24, 71.6));
        uniParameter.setiFhnlpgOnLoad(new IFhnlpgOnLoad(load));

        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "HST");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oHst.print();
        }
    }


    public static void ZCFHNLPG() {
        UniParameter uniParameter = new UniParameter();
        //1. 基本参数
        uniParameter.setiFhnlpgBase(new IFhnlpgBase(0, 1, 1, 10));

        //2. 变压器绕组直流电阻及电压变比
        ArrayList<ResistanceABC> D_H = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_M = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_L = new ArrayList<ResistanceABC>();
        D_H.add(new ResistanceABC(0.2973, 0.2982, 0.2983));
        D_H.add(new ResistanceABC(0.2939, 0.2945, 0.2948));
        D_H.add(new ResistanceABC(0.2903, 0.291, 0.2913));
        D_H.add(new ResistanceABC(0.2868, 0.2874, 0.2877));
        D_H.add(new ResistanceABC(0.2838, 0.2846, 0.2848));
        D_H.add(new ResistanceABC(0.2803, 0.2809, 0.2812));
        D_H.add(new ResistanceABC(0.2772, 0.278, 0.2782));
        D_H.add(new ResistanceABC(0.2737, 0.2743, 0.2747));
        D_H.add(new ResistanceABC(0.2702, 0.2704, 0.2704));
        D_H.add(new ResistanceABC(0.2744, 0.2749, 0.2753));
        D_H.add(new ResistanceABC(0.2778, 0.2785, 0.2788));
        D_H.add(new ResistanceABC(0.2814, 0.2819, 0.2832));
        D_H.add(new ResistanceABC(0.2844, 0.2851, 0.2854));
        D_H.add(new ResistanceABC(0.288, 0.2885, 0.2889));
        D_H.add(new ResistanceABC(0.2909, 0.2915, 0.2918));
        D_H.add(new ResistanceABC(0.2946, 0.2951, 0.2955));
        D_H.add(new ResistanceABC(0.2974, 0.298, 0.2983));
        D_M.add(new ResistanceABC(0.11589, 0.11594, 0.11667));
        D_L.add(new ResistanceABC(0.011712, 0.011727, 0.011759));
        uniParameter.setiFhnlpgResistance(new IFhnlpgResistance(D_H, D_M, D_L, 17));

        //3. 变比信息
        ArrayList<RatioHML> ratio = new ArrayList<RatioHML>();
        ratio.add(new RatioHML(2.104, 10.95, 23.0388));
        ratio.add(new RatioHML(2.08, 10.95, 22.776));
        ratio.add(new RatioHML(2.057, 10.95, 22.52415));
        ratio.add(new RatioHML(2.033, 10.95, 22.26135));
        ratio.add(new RatioHML(2.009, 10.95, 21.99855));
        ratio.add(new RatioHML(1.985, 10.95, 21.73575));
        ratio.add(new RatioHML(1.961, 10.95, 21.47295));
        ratio.add(new RatioHML(1.937, 10.95, 21.21015));
        ratio.add(new RatioHML(1.913, 10.95, 20.94735));
        ratio.add(new RatioHML(1.889, 10.95, 20.68455));
        ratio.add(new RatioHML(1.865, 10.95, 20.42175));
        ratio.add(new RatioHML(1.841, 10.95, 20.15895));
        ratio.add(new RatioHML(1.817, 10.95, 19.89615));
        ratio.add(new RatioHML(1.793, 10.95, 19.63335));
        ratio.add(new RatioHML(1.77, 10.95, 19.3815));
        ratio.add(new RatioHML(1.746, 10.95, 19.1187));
        ratio.add(new RatioHML(1.722, 10.95, 18.8559));
        uniParameter.setiFhnlpgRatio(new IFhnlpgRatio(ratio));

        //4. 变压器温升
        IFhnlpgTRise iFhnlpgTRise = new IFhnlpgTRise(new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4),
                new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4));
        uniParameter.setiFhnlpgTRise(iFhnlpgTRise);

        //5.初始数据
        uniParameter.setiFhnlpgInitial(new IFhnlpgInitial(60, 50, 65));

        //6.在线数据
        ArrayList<Load> load = new ArrayList<Load>();
        load.add(new Load(30.5, 564.7, 564.7, 564.7, 71.6));
        load.add(new Load(30.9, 565.02, 565.02, 565.02, 72.2));
        load.add(new Load(31.3, 565.04, 565.04, 565.04, 72.5));
        load.add(new Load(31.5, 564.62, 564.62, 564.62, 72.8));
        load.add(new Load(32.0, 563.73, 563.73, 563.73, 73.4));
        load.add(new Load(32.2, 564.2, 564.2, 564.2, 73.8));
        load.add(new Load(32.9, 524.04, 524.04, 524.04, 74.1));
        load.add(new Load(32.5, 523.22, 523.22, 523.22, 72.8));
        load.add(new Load(32.5, 524.24, 524.24, 524.24, 71.6));
        uniParameter.setiFhnlpgOnLoad(new IFhnlpgOnLoad(load));
        IFhnlpgLimit iFhnlpgLimit = new IFhnlpgLimit(34, 564, 1);
        iFhnlpgLimit.setI_H_current_coef(0.9);
        iFhnlpgLimit.setLlimit_L_coef(1);

        uniParameter.setiFhnlpgLimit(iFhnlpgLimit);

        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "ZCFHNLPG");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oZcfhnlpg.print();
        }
    }

    public static void CQFHNLPG() {
        UniParameter uniParameter = new UniParameter();
        //1. 基本参数
        uniParameter.setiFhnlpgBase(new IFhnlpgBase(0, 1, 1, 10));

        //2. 变压器绕组直流电阻及电压变比
        ArrayList<ResistanceABC> D_H = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_M = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_L = new ArrayList<ResistanceABC>();
        D_H.add(new ResistanceABC(0.2973, 0.2982, 0.2983));
        D_H.add(new ResistanceABC(0.2939, 0.2945, 0.2948));
        D_H.add(new ResistanceABC(0.2903, 0.291, 0.2913));
        D_H.add(new ResistanceABC(0.2868, 0.2874, 0.2877));
        D_H.add(new ResistanceABC(0.2838, 0.2846, 0.2848));
        D_H.add(new ResistanceABC(0.2803, 0.2809, 0.2812));
        D_H.add(new ResistanceABC(0.2772, 0.278, 0.2782));
        D_H.add(new ResistanceABC(0.2737, 0.2743, 0.2747));
        D_H.add(new ResistanceABC(0.2702, 0.2704, 0.2704));
        D_H.add(new ResistanceABC(0.2744, 0.2749, 0.2753));
        D_H.add(new ResistanceABC(0.2778, 0.2785, 0.2788));
        D_H.add(new ResistanceABC(0.2814, 0.2819, 0.2832));
        D_H.add(new ResistanceABC(0.2844, 0.2851, 0.2854));
        D_H.add(new ResistanceABC(0.288, 0.2885, 0.2889));
        D_H.add(new ResistanceABC(0.2909, 0.2915, 0.2918));
        D_H.add(new ResistanceABC(0.2946, 0.2951, 0.2955));
        D_H.add(new ResistanceABC(0.2974, 0.298, 0.2983));
        D_M.add(new ResistanceABC(0.11589, 0.11594, 0.11667));
        D_L.add(new ResistanceABC(0.011712, 0.011727, 0.011759));
        uniParameter.setiFhnlpgResistance(new IFhnlpgResistance(D_H, D_M, D_L, 17));

        //3. 变比信息
        ArrayList<RatioHML> ratio = new ArrayList<RatioHML>();
        ratio.add(new RatioHML(2.104, 10.95, 23.0388));
        ratio.add(new RatioHML(2.08, 10.95, 22.776));
        ratio.add(new RatioHML(2.057, 10.95, 22.52415));
        ratio.add(new RatioHML(2.033, 10.95, 22.26135));
        ratio.add(new RatioHML(2.009, 10.95, 21.99855));
        ratio.add(new RatioHML(1.985, 10.95, 21.73575));
        ratio.add(new RatioHML(1.961, 10.95, 21.47295));
        ratio.add(new RatioHML(1.937, 10.95, 21.21015));
        ratio.add(new RatioHML(1.913, 10.95, 20.94735));
        ratio.add(new RatioHML(1.889, 10.95, 20.68455));
        ratio.add(new RatioHML(1.865, 10.95, 20.42175));
        ratio.add(new RatioHML(1.841, 10.95, 20.15895));
        ratio.add(new RatioHML(1.817, 10.95, 19.89615));
        ratio.add(new RatioHML(1.793, 10.95, 19.63335));
        ratio.add(new RatioHML(1.77, 10.95, 19.3815));
        ratio.add(new RatioHML(1.746, 10.95, 19.1187));
        ratio.add(new RatioHML(1.722, 10.95, 18.8559));
        uniParameter.setiFhnlpgRatio(new IFhnlpgRatio(ratio));

        //4. 变压器温升
        IFhnlpgTRise iFhnlpgTRise = new IFhnlpgTRise(new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4),
                new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4));
        uniParameter.setiFhnlpgTRise(iFhnlpgTRise);

        //5.初始数据
        uniParameter.setiFhnlpgInitial(new IFhnlpgInitial(60, 50, 65));

        //6.在线数据
        ArrayList<Load> load = new ArrayList<Load>();
        load.add(new Load(30.5, 564.7, 564.7, 564.7, 71.6));
        load.add(new Load(30.9, 565.02, 565.02, 565.02, 72.2));
        load.add(new Load(31.3, 565.04, 565.04, 565.04, 72.5));
        load.add(new Load(31.5, 564.62, 564.62, 564.62, 72.8));
        load.add(new Load(32.0, 563.73, 563.73, 563.73, 73.4));
        load.add(new Load(32.2, 564.2, 564.2, 564.2, 73.8));
        load.add(new Load(32.9, 524.04, 524.04, 524.04, 74.1));
        load.add(new Load(32.5, 523.22, 523.22, 523.22, 72.8));
        load.add(new Load(32.5, 524.24, 524.24, 524.24, 71.6));
        uniParameter.setiFhnlpgOnLoad(new IFhnlpgOnLoad(load));
        IFhnlpgLimit iFhnlpgLimit = new IFhnlpgLimit(34, 564, 1);
        iFhnlpgLimit.setI_H_current_coef(1);
        iFhnlpgLimit.setLlimit_L_coef(10);
        uniParameter.setiFhnlpgLimit(iFhnlpgLimit);

        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "CQFHNLPG");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oZcfhnlpg.print();
        }
    }

    public static void DQFHNLPG() {
        //load1.m
        //hs.init(3, 3, 17, 100, 0.9, 1.2, 105, 120, 1);
        //load2.m
        //int in_type, int in_kind, int in_Tap, double IC, double I_H_current_coef, double in_Klimit_fu, double in_Tlimit_top, double in_Tlimit_hs, double Llimit_L_coef
        //hs.init(3, 3, 17, 100, 1, 1.2, 105, 120, 10);

        //load3.m
        //int in_type, int in_kind, int in_Tap, double IC, double I_H_current_coef, double in_Klimit_fu, double in_Tlimit_top, double in_Tlimit_hs, double Llimit_L_coef
        //hs.init(3, 3, 9, 100, 1, 2, 115, 140, 100);

        UniParameter uniParameter = new UniParameter();
        //1. 基本参数
        uniParameter.setiFhnlpgBase(new IFhnlpgBase(0, 1, 1, 10));

        //2. 变压器绕组直流电阻及电压变比
        ArrayList<ResistanceABC> D_H = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_M = new ArrayList<ResistanceABC>();
        ArrayList<ResistanceABC> D_L = new ArrayList<ResistanceABC>();
        D_H.add(new ResistanceABC(0.2973, 0.2982, 0.2983));
        D_H.add(new ResistanceABC(0.2939, 0.2945, 0.2948));
        D_H.add(new ResistanceABC(0.2903, 0.291, 0.2913));
        D_H.add(new ResistanceABC(0.2868, 0.2874, 0.2877));
        D_H.add(new ResistanceABC(0.2838, 0.2846, 0.2848));
        D_H.add(new ResistanceABC(0.2803, 0.2809, 0.2812));
        D_H.add(new ResistanceABC(0.2772, 0.278, 0.2782));
        D_H.add(new ResistanceABC(0.2737, 0.2743, 0.2747));
        D_H.add(new ResistanceABC(0.2702, 0.2704, 0.2704));
        D_H.add(new ResistanceABC(0.2744, 0.2749, 0.2753));
        D_H.add(new ResistanceABC(0.2778, 0.2785, 0.2788));
        D_H.add(new ResistanceABC(0.2814, 0.2819, 0.2832));
        D_H.add(new ResistanceABC(0.2844, 0.2851, 0.2854));
        D_H.add(new ResistanceABC(0.288, 0.2885, 0.2889));
        D_H.add(new ResistanceABC(0.2909, 0.2915, 0.2918));
        D_H.add(new ResistanceABC(0.2946, 0.2951, 0.2955));
        D_H.add(new ResistanceABC(0.2974, 0.298, 0.2983));
        D_M.add(new ResistanceABC(0.11589, 0.11594, 0.11667));
        D_L.add(new ResistanceABC(0.011712, 0.011727, 0.011759));
        uniParameter.setiFhnlpgResistance(new IFhnlpgResistance(D_H, D_M, D_L, 9));

        //3. 变比信息
        ArrayList<RatioHML> ratio = new ArrayList<RatioHML>();
        ratio.add(new RatioHML(2.104, 10.95, 23.0388));
        ratio.add(new RatioHML(2.08, 10.95, 22.776));
        ratio.add(new RatioHML(2.057, 10.95, 22.52415));
        ratio.add(new RatioHML(2.033, 10.95, 22.26135));
        ratio.add(new RatioHML(2.009, 10.95, 21.99855));
        ratio.add(new RatioHML(1.985, 10.95, 21.73575));
        ratio.add(new RatioHML(1.961, 10.95, 21.47295));
        ratio.add(new RatioHML(1.937, 10.95, 21.21015));
        ratio.add(new RatioHML(1.913, 10.95, 20.94735));
        ratio.add(new RatioHML(1.889, 10.95, 20.68455));
        ratio.add(new RatioHML(1.865, 10.95, 20.42175));
        ratio.add(new RatioHML(1.841, 10.95, 20.15895));
        ratio.add(new RatioHML(1.817, 10.95, 19.89615));
        ratio.add(new RatioHML(1.793, 10.95, 19.63335));
        ratio.add(new RatioHML(1.77, 10.95, 19.3815));
        ratio.add(new RatioHML(1.746, 10.95, 19.1187));
        ratio.add(new RatioHML(1.722, 10.95, 18.8559));
        uniParameter.setiFhnlpgRatio(new IFhnlpgRatio(ratio));

        //4. 变压器温升
        IFhnlpgTRise iFhnlpgTRise = new IFhnlpgTRise(new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4),
                new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4));
        uniParameter.setiFhnlpgTRise(iFhnlpgTRise);

        //5.初始数据
        uniParameter.setiFhnlpgInitial(new IFhnlpgInitial(60, 50, 65));

        //6.在线数据
        ArrayList<Load> load = new ArrayList<Load>();
        load.add(new Load(30.5, 564.7, 564.7, 564.7, 71.6));
        load.add(new Load(30.9, 565.02, 565.02, 565.02, 72.2));
        load.add(new Load(31.3, 565.04, 565.04, 565.04, 72.5));
        load.add(new Load(31.5, 564.62, 564.62, 564.62, 72.8));
        load.add(new Load(32.0, 563.73, 563.73, 563.73, 73.4));
        load.add(new Load(32.2, 564.2, 564.2, 564.2, 73.8));
        load.add(new Load(32.9, 524.04, 524.04, 524.04, 74.1));
        load.add(new Load(32.5, 523.22, 523.22, 523.22, 72.8));
        load.add(new Load(32.5, 524.24, 524.24, 524.24, 71.6));
        uniParameter.setiFhnlpgOnLoad(new IFhnlpgOnLoad(load));
        IFhnlpgLimit iFhnlpgLimit = new IFhnlpgLimit(34, 564, 1);
        iFhnlpgLimit.setI_H_current_coef(1);
        iFhnlpgLimit.setLlimit_L_coef(100);
        iFhnlpgLimit.setKlimit_fu(2);
        iFhnlpgLimit.setTlimit_top(115);
        iFhnlpgLimit.setTlimit_hs(140);
        uniParameter.setiFhnlpgLimit(iFhnlpgLimit);

        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "DQFHNLPG");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oDqfhnlpg.print();
        }

    }

    public static void FHZT() {

    }

    public static void HSBase(UniParameter uniParameter) {

    }
}
