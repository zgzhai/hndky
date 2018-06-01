package edu.xjtu.ee.unisolver.FXPG;

/**
 * Created by Administrator on 2018/6/1.
 */
public class OFXPG {
    public String riskLevel;
    public String riskDisp;
    public String scoreMsg;          //状态评价的风险因素描述
    public String diagnoseResult;    //诊断结果：异常状态描述

    public void print(){
        System.out.println(riskLevel);
        System.out.println(riskDisp);
        System.out.println(scoreMsg);
        System.out.println(diagnoseResult);
    }
}
