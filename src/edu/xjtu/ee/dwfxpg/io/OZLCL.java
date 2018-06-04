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
}
