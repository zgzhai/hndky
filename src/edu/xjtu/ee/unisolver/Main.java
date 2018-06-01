package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.dwfxpg.CBus;
import edu.xjtu.ee.dwfxpg.CGenerator;
import edu.xjtu.ee.dwfxpg.CLine;
import edu.xjtu.ee.dwfxpg.CLoad;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgDW;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgPQ;
import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.unisolver.FXPG.IFXPG;

import java.util.ArrayList;

/**
 * 使用说明：
 */
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
        } else if (args.length > 0 && args[0].toUpperCase().equals("ZLCL")) {
            ZLCL();
        } else if (args.length > 0 && args[0].toUpperCase().equals("PQ")) {
            PQ();
        } else if (args.length > 0 && args[0].toUpperCase().equals("FHXJ")) {
            FHXJ();
        } else if (args.length > 0 && args[0].toUpperCase().equals("FXPG")) {
            FXPG();
        } else {
            FXPG();
            System.out.println("welcome to unisolver， please input mode : ");
            System.out.println("COOL");
            System.out.println("HST");
            System.out.println("ZCFHNLPG");
            System.out.println("CQFHNLPG");
            System.out.println("DQFHNLPG");
            System.out.println("FHZT");
            System.out.println("ZLCL");
            System.out.println("PQ");
            System.out.println("FHXJ");
            System.out.println("FXPG");
        }
    }

    public static void COOL() {
        UniParameter uniParameter = new UniParameter();
        //1.
        uniParameter.setiFhnlpgCool(new IFhnlpgCool(6, 6, 6, 6, 0.2, 0.2, 60, 80, 0.7, 70, 85, 0.8));
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "COOL");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oCool.print();
        }
    }

    public static void HST() {
        UniParameter uniParameter = new UniParameter();
        //1. 基本参数
        IFhnlpgBase iFhnlpgBase = new IFhnlpgBase(1, 1, 1, 10);
        //iFhnlpgBase.setKind(2);
        //iFhnlpgBase.setInterval(30);
        //iFhnlpgBase.setC_bei(2);
        uniParameter.setiFhnlpgBase(iFhnlpgBase);

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
        IFhnlpgBase iFhnlpgBase = new IFhnlpgBase(1, 1, 1, 10);
        //iFhnlpgBase.setKind(2);
        //iFhnlpgBase.setInterval(30);
        //iFhnlpgBase.setC_bei(2);
        uniParameter.setiFhnlpgBase(iFhnlpgBase);

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
        load.add(new Load(32.5, 524.24, 524.24, 524.24, 71.6));
        uniParameter.setiFhnlpgOnLoad(new IFhnlpgOnLoad(load));

        //7. 参数7
        IFhnlpgLimit iFhnlpgLimit = new IFhnlpgLimit(34, 564, 1);
        iFhnlpgLimit.setI_H_current_coef(0.9);
        iFhnlpgLimit.setLlimit_L_coef(1);

        uniParameter.setiFhnlpgLimit(iFhnlpgLimit);

        //开始求解
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
        IFhnlpgBase iFhnlpgBase = new IFhnlpgBase(1, 1, 1, 10);
        //iFhnlpgBase.setKind(2);
        //iFhnlpgBase.setInterval(30);
        //iFhnlpgBase.setC_bei(2);
        uniParameter.setiFhnlpgBase(iFhnlpgBase);


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
        //7.
        IFhnlpgLimit iFhnlpgLimit = new IFhnlpgLimit(34, 564, 1);
        iFhnlpgLimit.setI_H_current_coef(1);
        iFhnlpgLimit.setLlimit_L_coef(10);
        uniParameter.setiFhnlpgLimit(iFhnlpgLimit);

        //开始求解
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
        IFhnlpgBase iFhnlpgBase = new IFhnlpgBase(1, 1, 1, 10);
        //iFhnlpgBase.setKind(2);
        //iFhnlpgBase.setInterval(30);
        //iFhnlpgBase.setC_bei(2);
        uniParameter.setiFhnlpgBase(iFhnlpgBase);

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
        UniParameter uniParameter = new UniParameter();
        //1.
        uniParameter.setiFhnlpgTStatus(new IFhnlpgTStatus(75, 98, new Nameplate(), new Operation()));

        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "FHZT");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oFhzt.print();
        }
    }

    public static void ZLCL() {
        UniParameter uniParameter = new UniParameter();
        //1.
        ArrayList<CBus> Bus = new ArrayList<CBus>();
        ArrayList<CLoad> Load = new ArrayList<CLoad>();
        ArrayList<CGenerator> Generator = new ArrayList<CGenerator>();
        ArrayList<CLine> Line = new ArrayList<CLine>();
        Bus.add(new CBus(1, 0.0, 1));
        Bus.add(new CBus(2, 0.0, 2));
        Bus.add(new CBus(3, 0.0, 1));
        Bus.add(new CBus(4, 0.0, 1));
        Bus.add(new CBus(5, 0.0, 2));
        Bus.add(new CBus(6, 0.0, 1));
        Bus.add(new CBus(7, 0.0, 1));
        Bus.add(new CBus(8, 0.0, 1));
        Bus.add(new CBus(9, 0.0, 1));
        Bus.add(new CBus(10, 0.0, 2));
        Bus.add(new CBus(11, 0.0, 1));
        Bus.add(new CBus(12, 0.0, 1));
        Bus.add(new CBus(13, 0.0, 1));
        Bus.add(new CBus(14, 0.0, 2));
        Bus.add(new CBus(15, 0.0, 1));
        Bus.add(new CBus(16, 0.0, 2));
        Bus.add(new CBus(17, 0.0, 1));
        Bus.add(new CBus(18, 0.0, 1));
        Bus.add(new CBus(19, 0.0, 1));
        Bus.add(new CBus(20, 0.0, 1));
        Bus.add(new CBus(21, 0.0, 1));
        Bus.add(new CBus(22, 0.0, 3));

        Load.add(new CLoad(1, 20));
        Load.add(new CLoad(3, 220));
        Load.add(new CLoad(5, 258.5));
        Load.add(new CLoad(6, 125));
        Load.add(new CLoad(8, 54));
        Load.add(new CLoad(10, 11.2));
        Load.add(new CLoad(11, 215));
        Load.add(new CLoad(13, 298.1));
        Load.add(new CLoad(15, 60));
        Load.add(new CLoad(18, 135));
        Load.add(new CLoad(20, 41));
        Load.add(new CLoad(21, 58.6));
        Load.add(new CLoad(22, 29.2));

        Generator.add(new CGenerator(1, 260));
        Generator.add(new CGenerator(8, 658));
        Generator.add(new CGenerator(9, 152.1));
        Generator.add(new CGenerator(16, 260.2));
        Generator.add(new CGenerator(19, 138.7));
        Generator.add(new CGenerator(22, 70));

        Line.add(new CLine(1, 2, 1, 0.02751));
        Line.add(new CLine(1, 4, 1, 0.02341));
        Line.add(new CLine(1, 9, 1, 0.00504));
        Line.add(new CLine(1, 9, 2, 0.00504));
        Line.add(new CLine(2, 3, 1, 0.09144));
        Line.add(new CLine(2, 4, 1, 0.00497));
        Line.add(new CLine(4, 5, 1, 0.09144));
        Line.add(new CLine(4, 7, 1, 0.00551));
        Line.add(new CLine(4, 7, 2, 0.00551));
        Line.add(new CLine(4, 11, 1, 0.01108));
        Line.add(new CLine(4, 12, 1, 0.01099));
        Line.add(new CLine(5, 6, 1, 0.12008));
        Line.add(new CLine(6, 7, 1, 0.09228));
        Line.add(new CLine(7, 8, 1, 0.01319));
        Line.add(new CLine(7, 8, 2, 0.01319));
        Line.add(new CLine(8, 9, 1, 0.00316));
        Line.add(new CLine(9, 10, 1, 0.06411));
        Line.add(new CLine(9, 12, 1, 0.02475));
        Line.add(new CLine(9, 12, 2, 0.02287));
        Line.add(new CLine(11, 12, 2, 0.01084));
        Line.add(new CLine(12, 13, 1, 0.10293));
        Line.add(new CLine(12, 16, 1, 0.00996));
        Line.add(new CLine(12, 17, 1, 0.00586));
        Line.add(new CLine(13, 14, 1, 0.03167));
        Line.add(new CLine(14, 15, 1, 0.04285));
        Line.add(new CLine(15, 18, 1, 0.08608));
        Line.add(new CLine(16, 17, 1, 0.01465));
        Line.add(new CLine(16, 18, 1, 0.02847));
        Line.add(new CLine(18, 19, 1, 0.01864));
        Line.add(new CLine(18, 20, 1, 0.05737));
        Line.add(new CLine(18, 22, 1, 0.08440));
        Line.add(new CLine(19, 21, 1, 0.11680));
        Line.add(new CLine(20, 21, 1, 0.03097));
        Line.add(new CLine(21, 22, 1, 0.01093));
        uniParameter.setiDwfxpgDW(new IDwfxpgDW(Bus, Load, Generator, Line));
        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "ZLCL");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oZlcl.print();
        }
    }

    public static void PQ() {
        UniParameter uniParameter = new UniParameter();
        //1.
        ArrayList<CBus> Bus = new ArrayList<CBus>();
        ArrayList<CLine> Line = new ArrayList<CLine>();

        Bus.add(new CBus(1, 3, 1.05, 0, 0, 0));
        Bus.add(new CBus(2, 1, 1.05, 0, -3.7, -1.3));
        Bus.add(new CBus(3, 1, 1.05, 0, -2, -1));
        Bus.add(new CBus(4, 1, 1.05, 0, -1.6, -0.8));
        Bus.add(new CBus(5, 2, 1.05, 0, 5, 0));

        Line.add(new CLine(1, 2, 0, 0, 0, 0, 0.03, 1.05, 0));
        Line.add(new CLine(2, 3, 0.08, 0.3, 0.5, 0, 0, 0, 0));
        Line.add(new CLine(2, 4, 0.1, 0.35, 0, 0, 0, 0, 0));
        Line.add(new CLine(3, 4, 0.04, 0.25, 0.5, 0, 0, 0, 0));
        Line.add(new CLine(3, 5, 0, 0, 0, 0, 0.015, 1.05, 1));

        uniParameter.setiDwfxpgPQ(new IDwfxpgPQ(Bus, Line));

        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "PQ");

        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oPq.print();
        }

    }

    public static void FHXJ() {
        UniParameter uniParameter = new UniParameter();
        //1.
        ArrayList<CBus> Bus = new ArrayList<CBus>();
        ArrayList<CLoad> Load = new ArrayList<CLoad>();
        ArrayList<CGenerator> Generator = new ArrayList<CGenerator>();
        ArrayList<CLine> Line = new ArrayList<CLine>();
        Bus.add(new CBus(1, 0.0, 1));
        Bus.add(new CBus(2, 0.0, 2));
        Bus.add(new CBus(3, 0.0, 3));

        Load.add(new CLoad(1, 230));
        Load.add(new CLoad(2, 580));
        Load.add(new CLoad(3, 296));

        Generator.add(new CGenerator(1, 420));
        Generator.add(new CGenerator(2, 0));
        Generator.add(new CGenerator(3, 560));

        Line.add(new CLine(1, 2, 1, 0.00497, 224));
        Line.add(new CLine(1, 3, 1, 0.00551, 289));
        Line.add(new CLine(2, 3, 1, 0.00504, 251));

        uniParameter.setiDwfxpgDW(new IDwfxpgDW(Bus, Load, Generator, Line));
        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "FHXJ");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oFhxj.print();
        }
    }

    public static void FXPG() {
        UniParameter uniParameter = new UniParameter();
        uniParameter.setiFxpg(new IFXPG());
        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "FXPG");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oFxpg.print();
        }
    }
}
