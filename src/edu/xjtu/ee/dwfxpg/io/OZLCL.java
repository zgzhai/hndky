package edu.xjtu.ee.dwfxpg.io;

import Jama.Matrix;
import edu.xjtu.ee.dwfxpg.CLine;

import java.util.ArrayList;
import java.util.HashMap;

public class OZLCL {
    public Matrix Y;
    public ArrayList<Double> Delta;
    public ArrayList<CLinePIJ> PIJ;

    public void print() {
        System.out.println("Y=\r\n");
        Y.print(10, 2);

        System.out.println("Delta=\r\n");
        for (int i = 0; i < Delta.size(); i++) {
            System.out.println(i + "," + Delta.get(i) + "\r\n");
        }

        System.out.println("PIJ=\r\n");
        for (int i = 0; i < PIJ.size(); i++) {
            System.out.println(PIJ.get(i).sid + "-" + PIJ.get(i).eid + ":" + PIJ.get(i).pij + "\r\n");
        }
    }
}
