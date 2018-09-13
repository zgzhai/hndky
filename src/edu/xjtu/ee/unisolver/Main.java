package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.dwfxpg.CBus;
import edu.xjtu.ee.dwfxpg.CGenerator;
import edu.xjtu.ee.dwfxpg.CLine;
import edu.xjtu.ee.dwfxpg.CLoad;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgDW;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgPQ;
import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.unisolver.FXPG.IFXPG;
import sun.security.pkcs.PKCS10;

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
            FHXJ();
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
        uniParameter.setiFhnlpgCool(new IFhnlpgCool(70, 85, 451, 564));
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
        TRiseV tRiseV100 = new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4);
        TRiseV tRiseV70 = new TRiseV(41.2, 28.28, 45.9, 32, 82169, 512386, 564.9, 564.9, 2699.4);
        IFhnlpgTRise iFhnlpgTRise = new IFhnlpgTRise(tRiseV100,
                tRiseV70);

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

        Bus.add(new CBus(1, 1));
        Bus.add(new CBus(2, 1));
        Bus.add(new CBus(3, 1));
        Bus.add(new CBus(4, 1));
        Bus.add(new CBus(5, 1));
        Bus.add(new CBus(6, 1));
        Bus.add(new CBus(7, 1));
        Bus.add(new CBus(8, 1));
        Bus.add(new CBus(9, 1));
        Bus.add(new CBus(10, 1));
        Bus.add(new CBus(11, 1));
        Bus.add(new CBus(12, 1));
        Bus.add(new CBus(13, 1));
        Bus.add(new CBus(14, 1));
        Bus.add(new CBus(15, 1));
        Bus.add(new CBus(16, 1));
        Bus.add(new CBus(17, 1));
        Bus.add(new CBus(18, 1));
        Bus.add(new CBus(19, 1));
        Bus.add(new CBus(20, 1));
        Bus.add(new CBus(21, 1));
        Bus.add(new CBus(22, 3));

        Load.add(new CLoad(1, 1.979));
        Load.add(new CLoad(2, 0));
        Load.add(new CLoad(3, 0));
        Load.add(new CLoad(4, 0));
        Load.add(new CLoad(5, 0));
        Load.add(new CLoad(6, 3.25));
        Load.add(new CLoad(7, 0));
        Load.add(new CLoad(8, 0));
        Load.add(new CLoad(9, 1.587));
        Load.add(new CLoad(10, 1.862));
        Load.add(new CLoad(11, 0));
        Load.add(new CLoad(12, 2.152));
        Load.add(new CLoad(13, 0));
        Load.add(new CLoad(14, 0));
        Load.add(new CLoad(15, 0));
        Load.add(new CLoad(16, 0));
        Load.add(new CLoad(17, 1.60));
        Load.add(new CLoad(18, 0.60));
        Load.add(new CLoad(19, 0));
        Load.add(new CLoad(20, 0.001));
        Load.add(new CLoad(21, 0));
        Load.add(new CLoad(22, 0));

        Generator.add(new CGenerator(1, 0));
        Generator.add(new CGenerator(2, 0));
        Generator.add(new CGenerator(3, 0));
        Generator.add(new CGenerator(4, 0));
        Generator.add(new CGenerator(5, 0));
        Generator.add(new CGenerator(6, 0));
        Generator.add(new CGenerator(7, 0));
        Generator.add(new CGenerator(8, 1.403));
        Generator.add(new CGenerator(9, 0));
        Generator.add(new CGenerator(10, 0));
        Generator.add(new CGenerator(11, 0.685));
        Generator.add(new CGenerator(12, 0));
        Generator.add(new CGenerator(13, 0));
        Generator.add(new CGenerator(14, 0));
        Generator.add(new CGenerator(15, 0));
        Generator.add(new CGenerator(16, 0));
        Generator.add(new CGenerator(17, 0));
        Generator.add(new CGenerator(18, 0));
        Generator.add(new CGenerator(19, 2.602));
        Generator.add(new CGenerator(20, 0));
        Generator.add(new CGenerator(21, 2.394));
        Generator.add(new CGenerator(22, 6.032));

        Line.add(new CLine(1, 12, 1, 0.01108, 10000));
        Line.add(new CLine(3, 12, 1, 0.01084, 10000));
        Line.add(new CLine(21, 8, 1, 0.00252, 10000));
        Line.add(new CLine(22, 10, 1, 0.006595, 10000));
        Line.add(new CLine(10, 1, 1, 0.002755, 10000));
        Line.add(new CLine(13, 14, 1, 0.0001, 10000));
        Line.add(new CLine(13, 15, 0, 0.09144, 1.80));
        Line.add(new CLine(14, 16, 0, 0.09164, 1.80));
        Line.add(new CLine(15, 17, 0, -0.006, 1.80));
        Line.add(new CLine(16, 17, 0, -0.00603, 1.80));
        Line.add(new CLine(15, 18, 0, 0.05461, 0.90));
        Line.add(new CLine(16, 18, 0, 0.05442, 0.90));
        Line.add(new CLine(2, 3, 1, 0.0001, 10000));
        Line.add(new CLine(2, 4, 0, 0.10293, 1.50));
        Line.add(new CLine(3, 5, 0, 0.09144, 1.80));
        Line.add(new CLine(4, 6, 0, -0.00709, 1.50));
        Line.add(new CLine(5, 6, 0, -0.006, 1.80));
        Line.add(new CLine(4, 7, 0, 0.0544, 0.50));
        Line.add(new CLine(5, 7, 0, 0.05461, 0.90));
        Line.add(new CLine(2, 8, 1, 0.02475, 10000));
        Line.add(new CLine(3, 8, 1, 0.02287, 10000));
        Line.add(new CLine(9, 11, 1, 0.01864, 10000));
        Line.add(new CLine(21, 14, 1, 0.02751, 10000));
        Line.add(new CLine(1, 13, 1, 0.00497, 10000));
        Line.add(new CLine(21, 1, 1, 0.02341, 10000));
        Line.add(new CLine(22, 8, 1, 0.00316, 10000));
        Line.add(new CLine(2, 19, 1, 0.00996, 10000));
        Line.add(new CLine(19, 9, 1, 0.02847, 10000));
        Line.add(new CLine(2, 20, 1, 0.00586, 10000));
        Line.add(new CLine(20, 19, 1, 0.01465, 10000));
        Line.add(new CLine(3, 1, 1, 0.01099, 10000));

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


        Bus.add(new CBus(1, 1, 0.988, 0, 0.91, 0.249));
        Bus.add(new CBus(2, 3, 0.988, 0, 1.294, 0.065));
        Bus.add(new CBus(3, 1, 1.05, 0, 0, 0));
        Bus.add(new CBus(4, 1, 1.05, 0, 0, 0));
        Bus.add(new CBus(5, 1, 1.05, 0, -1.6, -0.27));
        Bus.add(new CBus(6, 1, 1.05, 0, -0.6, 0.19));

        Line.add(new CLine(1, 2, 0, 0.0001, 0, 0, 0, 0, 0, 1000));
        Line.add(new CLine(1, 3, 0, 0, 0, 0.00102, 0.09144, 0.987, 0, 1.8));
        Line.add(new CLine(2, 4, 0, 0, 0, 0.00103, 0.09164, 0.987, 0, 1.8));
        Line.add(new CLine(3, 5, 0, 0, 0, 0.00054, -0.006, 1, 0, 1.8));
        Line.add(new CLine(4, 5, 0, 0, 0, 0.00054, -0.00603, 1, 0, 1.8));
        Line.add(new CLine(3, 6, 0, 0, 0, 0.00252, 0.05461, 1, 0, 0.9));
        Line.add(new CLine(4, 6, 0, 0, 0, 0.00253, 0.05442, 1, 0, 0.9));

        //Line.add(new CLine(2, 3, 0.08, 0.3, 0.5, 0, 0, 0, 0, 4));

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
        Bus.add(new CBus(1, 1));
        Bus.add(new CBus(2, 1));
        Bus.add(new CBus(3, 1));
        Bus.add(new CBus(4, 1));
        Bus.add(new CBus(5, 1));
        Bus.add(new CBus(6, 1));
        Bus.add(new CBus(7, 1));
        Bus.add(new CBus(8, 1));
        Bus.add(new CBus(9, 1));
        Bus.add(new CBus(10, 1));
        Bus.add(new CBus(11, 1));
        Bus.add(new CBus(12, 1));
        Bus.add(new CBus(13, 1));
        Bus.add(new CBus(14, 1));
        Bus.add(new CBus(15, 1));
        Bus.add(new CBus(16, 1));
        Bus.add(new CBus(17, 1));
        Bus.add(new CBus(18, 1));
        Bus.add(new CBus(19, 1));
        Bus.add(new CBus(20, 1));
        Bus.add(new CBus(21, 1));
        Bus.add(new CBus(22, 1));
        Bus.add(new CBus(23, 1));
        Bus.add(new CBus(24, 1));
        Bus.add(new CBus(25, 3));

        Load.add(new CLoad(1, 0));
        Load.add(new CLoad(2, 0));
        Load.add(new CLoad(3, 0));
        Load.add(new CLoad(4, 0));
        Load.add(new CLoad(5, 0.3));
        Load.add(new CLoad(6, 0));
        Load.add(new CLoad(7, 0));
        Load.add(new CLoad(8, 0));
        Load.add(new CLoad(9, 0));
        Load.add(new CLoad(10, 0));
        Load.add(new CLoad(11, 2.2));
        Load.add(new CLoad(12, 0));
        Load.add(new CLoad(13, 0.511));
        Load.add(new CLoad(14, 0.629));
        Load.add(new CLoad(15, 0));
        Load.add(new CLoad(16, 0));
        Load.add(new CLoad(17, 0));
        Load.add(new CLoad(18, 2.055));
        Load.add(new CLoad(19, 0));
        Load.add(new CLoad(20, 0.55));
        Load.add(new CLoad(21, 0.51));
        Load.add(new CLoad(22, 0.15));
        Load.add(new CLoad(23, 0.4));
        Load.add(new CLoad(24, 0));
        Load.add(new CLoad(25, 0));

        Generator.add(new CGenerator(1, 1.668));
        Generator.add(new CGenerator(2, 1.806));
        Generator.add(new CGenerator(3, 0));
        Generator.add(new CGenerator(4, 0));
        Generator.add(new CGenerator(5, 0));
        Generator.add(new CGenerator(6, 0));
        Generator.add(new CGenerator(7, 0));
        Generator.add(new CGenerator(8, 0));
        Generator.add(new CGenerator(9, 0));
        Generator.add(new CGenerator(10, 0));
        Generator.add(new CGenerator(11, 0));
        Generator.add(new CGenerator(12, 0));
        Generator.add(new CGenerator(13, 0));
        Generator.add(new CGenerator(14, 0));
        Generator.add(new CGenerator(15, 0));
        Generator.add(new CGenerator(16, 0));
        Generator.add(new CGenerator(17, 0));
        Generator.add(new CGenerator(18, 0));
        Generator.add(new CGenerator(19, 0));
        Generator.add(new CGenerator(20, 0));
        Generator.add(new CGenerator(21, 0));
        Generator.add(new CGenerator(22, 0));
        Generator.add(new CGenerator(23, 0));
        Generator.add(new CGenerator(24, 0.479));
        Generator.add(new CGenerator(25, 3.449));

        Line.add(new CLine(8,	14,	1,	0.01278,		1000));
        Line.add(new CLine(2,	9,	1,	0.02772	,	1000));
        Line.add(new CLine(2,	9,	1,	0.02483	,	1000));
        Line.add(new CLine(1,	7,	1,	0.0262	,	1000));
        Line.add(new CLine(2,	7,	1,	0.0262	,	1000));
        Line.add(new CLine(7,	13,	1,	0.02403	,	1000));
        Line.add(new CLine(7,	14,	1,	0.02403	,	1000));
        Line.add(new CLine(5,	24,	1,	0.03698	,	1000));
        Line.add(new CLine(11,	20,	1,	0.04749	,	1000));
        Line.add(new CLine(20,	24,	1,	0.07568	,	1000));
        Line.add(new CLine(18,	21,	1,	0.04749	,	1000));
        Line.add(new CLine(21,	11,	1,	0.01187	,	1000));
        Line.add(new CLine(18,	22,	1,	0.04749	,	1000));
        Line.add(new CLine(22,	23,	1,	0.0505	,	1000));
        Line.add(new CLine(23,	11,	1,	0.089	,	1000));
        Line.add(new CLine(13,	15,	0,	0.12725	,	1.2));
        Line.add(new CLine(18,	15,	0,	-0.01135,	1.2));
        Line.add(new CLine(19,	15,	0,	0.07842	,	0.6));
        Line.add(new CLine(13,	16,	0,	0.12916	,	1.2));
        Line.add(new CLine(18,	16,	0,	-0.00987,	1.2));
        Line.add(new CLine(19,	16,	0,	0.7608	,	0.6));
        Line.add(new CLine(14,	17,	0,	0.09144	,	1.8));
        Line.add(new CLine(18,	17,	0,	-0.006	,	1.8));
        Line.add(new CLine(19,	17,	0,	0.05461	,	0.9));
        Line.add(new CLine(9,	10,	0,	0.09144	,	1.8));
        Line.add(new CLine(11,	10,	0,	-0.006	,	1.8));
        Line.add(new CLine(12,	10,	0,	0.05461	,	0.9));
        Line.add(new CLine(1,	3,  0,	0.12042	,	1.2));
        Line.add(new CLine(5,	3,	0,	-0.00958,	1.2));
        Line.add(new CLine(6,	3,	0,	0.07958	,	0.6));
        Line.add(new CLine(2,	4,	0,	0.12042	,	1.2));
        Line.add(new CLine(5,	4,	0,	-0.00958,	1.2));
        Line.add(new CLine(6,	4,	0,	0.07958	,	0.6));
        Line.add(new CLine(1,	2,	1,	0.0001	,	1000));
        Line.add(new CLine(8,	9,	1,	0.0001	,	1000));
        Line.add(new CLine(13,	14,	1,	0.0001	,	1000));
        Line.add(new CLine(25,	1,	1,	0.0419	,	1000));
        Line.add(new CLine(25,	2,	1,	0.04341	,	1000));

        uniParameter.setiDwfxpgDW(new IDwfxpgDW(Bus, Load, Generator, Line));
        //开始求解
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "FHXJ");
        if (uniResult.errcode != 0) {
            System.out.println(uniResult.errmsg);
        } else {
            uniResult.oFhxj.print();
            uniResult.oFhxj.getFuzhuanyiMsg("传入风险预测的此结果：ofxpg.riskLevel");
        }
    }

    public static void FXPG() {
        UniParameter uniParameter = new UniParameter();
        IFXPG ifxpg = new IFXPG(80, 10, 180, 0, 800);
        ifxpg.setDiagnoseResult("变压器受潮");
        ifxpg.setScoreMsg("变压器漏油");
        uniParameter.setiFxpg(ifxpg);
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
