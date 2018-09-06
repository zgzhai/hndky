package edu.xjtu.ee.dwfxpg.io;

import Jama.Matrix;
import edu.xjtu.ee.dwfxpg.CLine;
import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class OZLCL {
    //public Matrix Y;
    public ArrayList<Double> Delta;
    public ArrayList<CLinePIJ> PIJ;
    public ArrayList<LineMsg> lineMsgs;
    public ArrayList<BusMsg> busMsgs;
    private Vector rongliang;
    private Vector fuhezhuanyi;

    public ArrayList<Double> getDelta() {
        return Delta;
    }

    public void setDelta(ArrayList<Double> delta) {
        Delta = delta;
    }

    public ArrayList<CLinePIJ> getPIJ() {
        return PIJ;
    }

    public void setPIJ(ArrayList<CLinePIJ> PIJ) {
        this.PIJ = PIJ;
    }

    public ArrayList<LineMsg> getLineMsgs() {
        return lineMsgs;
    }

    public void setLineMsgs(ArrayList<LineMsg> lineMsgs) {
        this.lineMsgs = lineMsgs;
    }

    public ArrayList<BusMsg> getBusMsgs() {
        return busMsgs;
    }

    public void setBusMsgs(ArrayList<BusMsg> busMsgs) {
        this.busMsgs = busMsgs;
    }

    public void print() {
        //System.out.println("Y=\r\n");
        //Y.print(10, 2);
        int nb = Delta.size();
        int nl = PIJ.size();
        System.out.println("节点相角Delta：\r\n");
        for (int i = 0; i < Delta.size(); i++) {
            //System.out.println(i + "," + Delta.get(i) + "\r\n");
            System.out.println(busMsgs.get(i).msg1 + "\r\n");
        }

        System.out.println("线路潮流PIJ：\r\n");
        for (int i = 0; i < PIJ.size(); i++) {
            System.out.println(PIJ.get(i).sid + "-" + PIJ.get(i).eid + ":" + PIJ.get(i).pij + "\r\n");
        }

        System.out.println("线路风险：\r\n");
        for (int i = 0; i < nl; i++) {
            System.out.println(lineMsgs.get(i).msg1);
        }

    }

    public Vector getRongliang() {
        return rongliang;
    }

    public void setRongliang(Vector rongliang) {
        this.rongliang = rongliang;
    }

    public Vector getFuhezhuanyi() {
        return fuhezhuanyi;
    }

    public void setFuhezhuanyi(Vector fuhezhuanyi) {
        this.fuhezhuanyi = fuhezhuanyi;
    }
}
