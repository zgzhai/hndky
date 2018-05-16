package edu.xjtu.ee.fhnlpg.io;

import edu.xjtu.ee.fhnlpg.HS;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/16.
 */
public class IFhnlpgRatio {
    private ArrayList<RatioHML> ratio;

    public IFhnlpgRatio() {
    }

    public IFhnlpgRatio(ArrayList<RatioHML> ratio) {
        this.ratio = ratio;
    }

    public ArrayList<RatioHML> getRatio() {
        return ratio;
    }

    public void setRatio(ArrayList<RatioHML> ratio) {
        this.ratio = ratio;
    }
}
