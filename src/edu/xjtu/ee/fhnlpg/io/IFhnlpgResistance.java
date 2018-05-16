package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.fhnlpg.HS;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/15.
 */
public class IFhnlpgResistance {

    private ArrayList<ResistanceABC> D_H;  //接口参数
    private ArrayList<ResistanceABC> D_M;  //接口参数
    private ArrayList<ResistanceABC> D_L;  //接口参数
    private int Tap = 17;  //接口参数
    private int T_C = 26;  //隐藏参数

    public IFhnlpgResistance() {
    }

    public IFhnlpgResistance(ArrayList<ResistanceABC> d_H, ArrayList<ResistanceABC> d_M, ArrayList<ResistanceABC> d_L, int tap) {
        D_H = d_H;
        D_M = d_M;
        D_L = d_L;
        Tap = tap;
    }

    public ArrayList<ResistanceABC> getD_H() {
        return D_H;
    }

    public void setD_H(ArrayList<ResistanceABC> d_H) {
        D_H = d_H;
    }

    public ArrayList<ResistanceABC> getD_M() {
        return D_M;
    }

    public void setD_M(ArrayList<ResistanceABC> d_M) {
        D_M = d_M;
    }

    public ArrayList<ResistanceABC> getD_L() {
        return D_L;
    }

    public void setD_L(ArrayList<ResistanceABC> d_L) {
        D_L = d_L;
    }

    public int getTap() {
        return Tap;
    }

    public void setTap(int tap) {
        Tap = tap;
    }

    public int getT_C() {
        return T_C;
    }

    public void setT_C(int t_C) {
        T_C = t_C;
    }
}
