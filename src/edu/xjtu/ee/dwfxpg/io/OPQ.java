package edu.xjtu.ee.dwfxpg.io;

import Jama.Matrix;
import edu.xjtu.ee.tools.Complex;
import edu.xjtu.ee.tools.Vector;
import edu.xjtu.ee.tools.VectorComplex;

import java.util.ArrayList;

public class OPQ {
    public Vector U;     //节点电压
    public Vector a;     //节点相角
    public Vector P;     //节点有功
    public Vector Q;     //节点无功
    public Vector I;     //起始节点
    public Vector J;     //终止节点
    public Complex Sph;
    public VectorComplex Sij;
    public VectorComplex Sji;
    public VectorComplex deltaSij;
    public VectorComplex S;
    public Complex sumdeltaS;
    public int k;                  //迭代次数
    public ArrayList<LineMsg> lineMsgs;
    public ArrayList<BusMsg> busMsgs;


    public Vector getU() {
        return U;
    }

    public void setU(Vector u) {
        U = u;
    }

    public Vector getA() {
        return a;
    }

    public void setA(Vector a) {
        this.a = a;
    }

    public Vector getP() {
        return P;
    }

    public void setP(Vector p) {
        P = p;
    }

    public Vector getQ() {
        return Q;
    }

    public void setQ(Vector q) {
        Q = q;
    }

    public Vector getI() {
        return I;
    }

    public void setI(Vector i) {
        I = i;
    }

    public Vector getJ() {
        return J;
    }

    public void setJ(Vector j) {
        J = j;
    }

    public Complex getSph() {
        return Sph;
    }

    public void setSph(Complex sph) {
        Sph = sph;
    }

    public VectorComplex getSij() {
        return Sij;
    }

    public void setSij(VectorComplex sij) {
        Sij = sij;
    }

    public VectorComplex getSji() {
        return Sji;
    }

    public void setSji(VectorComplex sji) {
        Sji = sji;
    }

    public VectorComplex getDeltaSij() {
        return deltaSij;
    }

    public void setDeltaSij(VectorComplex deltaSij) {
        this.deltaSij = deltaSij;
    }

    public VectorComplex getS() {
        return S;
    }

    public void setS(VectorComplex s) {
        S = s;
    }

    public Complex getSumdeltaS() {
        return sumdeltaS;
    }

    public void setSumdeltaS(Complex sumdeltaS) {
        this.sumdeltaS = sumdeltaS;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
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

        int y = I.getSize();
        int x = busMsgs.size();

        System.out.println("迭代次数k为: " + k);

        Sph.print("平衡节点1的复功率Sph为: = ", 8, 6);

        U.print("节点电压U为:", 8, 6);

        a.print("节点相角a为:", 8, 6);

        S.print("点复功率S为:", 8, 6);

        P.print("节点有功功率P为:", 8, 6);

        Q.print("节点无功功率Q为:", 8, 6);

        System.out.println("线路功率Sij和Sji为:");
        for (int i = 0; i < y; i++) {
            String fSij = String.format("节点%d到节点%d的功率为:", (int) I.get(i), (int) J.get(i));
            System.out.println(fSij + Sij.get(i).toFormatString(8, 6));
            String fSji = String.format("节点%d到节点%d的功率为:", (int) J.get(i), (int) I.get(i));
            System.out.println(fSji + Sji.get(i).toFormatString(8, 6));
        }

        sumdeltaS.print("网络总损耗sumdeltaS为: ", 8, 6);

        deltaSij.print("线路功率损耗deltaSij为:", 8, 6);

        for (int i = 0; i < y; i++) {
            System.out.println(lineMsgs.get(i).msg1);
        }

        for (int i = 0; i < x; i++) {
            System.out.println(busMsgs.get(i).msg1);
        }
    }
}
