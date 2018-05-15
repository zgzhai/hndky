package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.fhnlpg.HS;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/15.
 */
public class IFhnlpgResistance {

    private ArrayList<HS.ResistanceABC> D_H;
    private ArrayList<HS.ResistanceABC> D_M;
    private ArrayList<HS.ResistanceABC> D_L;
    private int Tap;
    private int T_C;  //隐藏参数

    public IFhnlpgResistance() {
        T_C = 26;
    }

    public IFhnlpgResistance(ArrayList<HS.ResistanceABC> d_H, ArrayList<HS.ResistanceABC> d_M, ArrayList<HS.ResistanceABC> d_L, int tap) {
        D_H = d_H;
        D_M = d_M;
        D_L = d_L;
        Tap = tap;
        T_C = 26;
    }

    public void setD_H(ArrayList<HS.ResistanceABC> d_H) {
        D_H = d_H;
    }

    public void setD_M(ArrayList<HS.ResistanceABC> d_M) {
        D_M = d_M;
    }

    public void setD_L(ArrayList<HS.ResistanceABC> d_L) {
        D_L = d_L;
    }

    public void setTap(int tap) {
        Tap = tap;
    }

    public void setT_C(int t_C) {
        T_C = t_C;
    }
}
