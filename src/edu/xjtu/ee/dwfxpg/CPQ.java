package edu.xjtu.ee.dwfxpg;

import Jama.Matrix;
import edu.xjtu.ee.tools.*;
/**
 * 采用PQ法求解
 * Created by Administrator on 2017/10/18.
 */
public class CPQ {

    public int x;        //节点数
    public int y;        //支路数
    public double e;     //精度
    public Vector TYPE;  //节点类型
    public Vector U;     //节点电压
    public Vector a;     //节点相角
    public Vector P;     //节点有功
    public Vector Q;     //节点无功
    public Vector I;     //起始节点
    public Vector J;     //终止节点
    public Vector Rij;   //线路电阻
    public Vector Xij;   //线路电抗

    public Vector B0;         //对地导纳
    public Vector RT;         //变压器电阻
    public Vector XT;         //变压器电抗
    public Vector KT;         //标准变比
    public Vector W;          //这算标志
    ///////////////以上为输入数据/////////

    public MatrixComplex Y;   //导纳复矩阵
    public Matrix G;          //Y实部
    public Matrix B;          //Y虚部
    private Matrix B1;
    private Matrix B2;
    private Complex Sph;
    private VectorComplex Sij;
    private VectorComplex Sji;
    private VectorComplex deltaSij;
    private VectorComplex S;
    private Complex sumdeltaS;


    public static void main(String[] args) {
        // write your code here
        CPQ cpq = new CPQ(5, 5, 0.00001);
        cpq.init();       //数据初始化
        cpq.solve();
        cpq.output();
    }

    public CPQ(int x, int y, double e) {
        this.x = x;
        this.y = y;
        this.e = e;
        Y = new MatrixComplex(x, x);
    }

    public Complex Zij(int m) {
        return new Complex(Rij.get(m), Xij.get(m));
    }

    public Complex ZT(int m) {
        return new Complex(RT.get(m), XT.get(m));
    }

    public void init() {
        double[] init_TYPE = {3, 1, 1, 1, 2};
        TYPE = new Vector(init_TYPE);
        double[] init_u = {1.05, 1.05, 1.05, 1.05, 1.05};
        U = new Vector(init_u);
        double[] init_a = {0, 0, 0, 0, 0};
        a = new Vector(init_a);
        double[] init_P = {0, -3.7, -2, -1.6, 5};
        P = new Vector(init_P);
        double[] init_Q = {0, -1.3, -1, -0.8, 0};
        Q = new Vector(init_Q);
        double[] init_I = {1, 2, 2, 3, 3};
        I = new Vector(init_I);
        double[] init_J = {2, 3, 4, 4, 5};
        J = new Vector(init_J);
        double[] init_Rij = {0, 0.08, 0.1, 0.04, 0};
        Rij = new Vector(init_Rij);
        double[] init_Xij = {0, 0.3, 0.35, 0.25, 0};
        Xij = new Vector(init_Xij);
        //Zij不用初始化，通过函数获得
        //B,G不用初始化
        double[] init_B0 = {0, 0.5, 0, 0.5, 0};
        B0 = new Vector(init_B0);
        double[] init_RT = {0, 0, 0, 0, 0};
        RT = new Vector(init_RT);
        double[] init_XT = {0.03, 0, 0, 0, 0.015};
        XT = new Vector(init_XT);
        //ZT不用初始化，通过函数获得
        double[] init_KT = {1.05, 0, 0, 0, 1.05};
        KT = new Vector(init_KT);
        double[] init_W = {0, 0, 0, 0, 1};
        W = new Vector(init_W);
    }

    public void solve() {
        solveY();     //求解节点导纳矩阵Y
        solveGB1B2(); //求B'矩阵及其逆矩阵B1，B2
        solveLoop();  //循环求解电压U, 相角a
        solveSph();   //求解平衡节点的复功率
        solvePower(); //求解线路功率Sij和Sji
        solveLoss();  //求解线路功率损耗
    }

    /**
     * 求节点导纳矩阵Y
     */
    public void solveY() {
        for (int m = 0; m < y; m++) {
            Complex c;
            if (KT.get(m) == 0) {
                c = Zij(m).inverse(-1);
            } else {
                c = ZT(m).times(KT.get(m)).inverse(-1);
            }
            Y.set((int) I.get(m) - 1, (int) J.get(m) - 1, c);
            Y.set((int) J.get(m) - 1, (int) I.get(m) - 1, c);
        }

        for (int m = 0; m < x; m++) {
            for (int n = 0; n < y; n++) {
                if (KT.get(n) == 0) {
                    if (I.get(n) == m + 1 || J.get(n) == m + 1) {
                        Complex c = Y.get(m, m);
                        c.sub(Y.get((int) I.get(n) - 1, (int) J.get(n) - 1));
                        c.add(new Complex(0, B0.get(n) / 2));
                        Y.set(m, m, c);
                    }
                } else if (W.get(n) == 0) {
                    if (I.get(n) == m + 1) {
                        Y.set(m, m, solveYFun1(m, n));
                    } else if (J.get(n) == m + 1) {
                        Y.set(m, m, solveYFun2(m, n));
                    }
                } else {
                    if (I.get(n) == m + 1) {
                        Y.set(m, m, solveYFun2(m, n));
                    } else if (J.get(n) == m + 1) {
                        Y.set(m, m, solveYFun1(m, n));
                    }
                }
            }
        }


    }

    private Complex solveYFun1(int m, int n) {
        Complex c = Y.get(m, m);
        c.sub(Y.get((int) I.get(n) - 1, (int) J.get(n) - 1));
        Complex cc = ZT(n).inverse().times((KT.get(n) - 1) / KT.get(n));
        c.add(cc);
        return c;
    }

    private Complex solveYFun2(int m, int n) {
        Complex c = Y.get(m, m);
        c.sub(Y.get((int) I.get(n) - 1, (int) J.get(n) - 1));
        Complex cc = ZT(n).inverse().times((1 - KT.get(n)) / KT.get(n) / KT.get(n));
        c.add(cc);
        return c;
    }

    public void solveGB1B2() {
        G = Y.getReal();
        B = Y.getImag();
        Vector ph = TYPE.find(3, Vector.EQ);

        MatrixEx BB = new MatrixEx(B.copy());
        BB.delete(ph);
        B1 = BB.ma.inverse();

        Vector phpv = TYPE.find(1, Vector.GT);
        MatrixEx BB2 = new MatrixEx(B.copy());
        BB2.delete(phpv);
        B2 = BB2.ma.inverse();
    }


    public void solveLoop() {
        int k = 0;
        int kp = 1;
        int kq = 1;

        Vector ph = TYPE.find(3, Vector.EQ);
        Vector notph = TYPE.find(3, Vector.LT);
        Vector phpv = TYPE.find(1, Vector.GT);
        Vector pq = TYPE.find(1, Vector.EQ);
        int pqnum = B2.getRowDimension();

        while (((kp != 0) || (kq != 0)) && k <= 20) {
            kp = 1;
            kq = 1;
            Vector deltaQi = new Vector(pqnum);
            Vector deltaPi = new Vector(x - 1);

            //计算deltaPi
            for (int m = 0; m < x - 1; m++) {
                double sum1 = 0;
                for (int n = 0; n < x; n++) {
                    double GTmp = G.get((int) notph.get(m) - 1, n) * Math.cos(a.get((int) notph.get(m) - 1) - a.get(n));
                    double BTmp = B.get((int) notph.get(m) - 1, n) * Math.sin(a.get((int) notph.get(m) - 1) - a.get(n));
                    sum1 += U.get((int) notph.get(m) - 1) * U.get(n) * (GTmp + BTmp);
                }
                deltaPi.set(m, P.get((int) notph.get(m) - 1) - sum1);
            }

            double max1 = deltaPi.copy().abs().max();


            if (max1 <= e) {
                kp = 0;
                if (kq == 0) break;
                else { //否则计算电压修正量

                    modifydeltaQi(deltaQi, pqnum, pq);

                    double max2 = deltaQi.copy().abs().max();
                    if (max2 <= e) {
                        kq = 0;
                        if (kp == 0) break;
                        else {
                            k++;
                        }
                    } else {
                        modifyU(phpv, pqnum, pq, deltaQi);
                        kp = 1;
                        k = k + 1;
                    }
                }
            } else {  //若有功不平衡量不满足精度要求
                MatrixEx mx = new MatrixEx(U.getMatrix());
                mx.deleteRow(ph);
                Vector Unotph = new Vector(mx.ma);
                //相角修正量计算
                // deltaa=((-B1*(deltaPi./Unotph))./Unotph);   %相角修正量计算
                Vector deltaa = new Vector(B1.times(deltaPi.dotDiv(Unotph).getMatrix()).times(-1)).dotDiv(Unotph);

                //相角修正
                for (int m = 0; m < x - 1; m++) {
                    double aTmp = a.get((int) notph.get(m) - 1);
                    a.set((int) notph.get(m) - 1, aTmp + deltaa.get(m));
                }
                kq = 1;

                //无功不平衡量计算
                modifydeltaQi(deltaQi, pqnum, pq);

                modifyU(phpv, pqnum, pq, deltaQi);
                kp = 1;
                k++;
            }
        }

        System.out.println("迭代次数k为: " + k);
    }

    private void modifydeltaQi(Vector deltaQi, int pqnum, Vector pq) {
        for (int m = 0; m < pqnum; m++) {
            double sum2 = 0;
            for (int n = 0; n < x; n++) {
                double GTmp = G.get((int) pq.get(m) - 1, n) * Math.sin(a.get((int) pq.get(m) - 1) - a.get(n));
                double BTmp = B.get((int) pq.get(m) - 1, n) * Math.cos(a.get((int) pq.get(m) - 1) - a.get(n));
                sum2 += U.get((int) pq.get(m) - 1) * U.get(n) * (GTmp - BTmp);
            }
            deltaQi.set(m, Q.get((int) pq.get(m) - 1) - sum2);
        }
    }

    private void modifyU(Vector phpv, int pqnum, Vector pq, Vector deltaQi) {
        MatrixEx mx = new MatrixEx(U.getMatrix());
        mx.deleteRow(phpv);
        Vector deltaU = new Vector(B2.times(deltaQi.dotDiv(new Vector(mx.ma)).getMatrix()).times(-1));

        for (int m = 0; m < pqnum; m++) {
            double UTmp = U.get((int) pq.get(m) - 1);
            U.set((int) pq.get(m) - 1, UTmp + deltaU.get(m));
        }
    }

    public void solveSph() {
        int ph = (int) TYPE.find(3, Vector.EQ).get(0);
        Complex sum3 = new Complex();
        for (int m = 0; m < x; m++) {
            Complex conj = Y.get(ph - 1, m).conj();
            Complex u = new Complex(U.get(m) * Math.cos(a.get(m)), -U.get(m) * Math.sin(a.get(m)));
            sum3.add(conj.multipule(u));
        }

        Sph = new Complex(U.get(ph - 1) * Math.cos(a.get(ph - 1)), U.get(ph - 1) * Math.sin(a.get(ph - 1)));
        Sph.multipule(sum3);
    }

    private Complex calcOneSij1(int m) {
        //(-i*B0(m)/2)
        return new Complex(0, -B0.get(m) / 2);
    }

    private Complex calcOneSij2(int m) {
        //(1-KT(m))/(KT(m))^2*conj(1/ZT(m))
        double times = (1 - KT.get(m)) / KT.get(m) / KT.get(m);
        return ZT(m).inverse().conj().times(times);
    }

    private Complex calcOneSij3(int m) {
        //(KT(m)-1)/KT(m)*conj(1/ZT(m))
        double times = (KT.get(m) - 1) / KT.get(m);
        return ZT(m).inverse().conj().times(times);
    }

    private Complex calcOneSij(int m, int select) {
        //U(I(m))*cos(a(I(m)))  + i* U(I(m))*sin(a(I(m)))
        Complex t1 = new Complex(U.get((int) I.get(m) - 1) * Math.cos(a.get((int) I.get(m) - 1)), U.get((int) I.get(m) - 1) * Math.sin(a.get((int) I.get(m) - 1)));
        //(U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m))))
        Complex s1a = new Complex(U.get((int) I.get(m) - 1) * Math.cos(a.get((int) I.get(m) - 1)), -U.get((int) I.get(m) - 1) * Math.sin(a.get((int) I.get(m) - 1)));
        Complex s1b = null;
        switch (select) {
            case 1:
                s1b = calcOneSij1(m);
                break;
            case 2:
                s1b = calcOneSij2(m);
                break;
            case 3:
                s1b = calcOneSij3(m);
                break;
        }

        Complex s1 = s1a.multipule(s1b);

        //U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m)))
        Complex s2a = new Complex(U.get((int) I.get(m) - 1) * Math.cos(a.get((int) I.get(m) - 1)), -U.get((int) I.get(m) - 1) * Math.sin(a.get((int) I.get(m) - 1)));
        //-U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m)))
        Complex s2b = new Complex(-U.get((int) J.get(m) - 1) * Math.cos(a.get((int) J.get(m) - 1)), U.get((int) J.get(m) - 1) * Math.sin(a.get((int) J.get(m) - 1)));
        Complex conj = Y.get((int) I.get(m) - 1, (int) J.get(m) - 1).clone().times(-1).conj(); //conj(-Y(I(m),J(m)))
        Complex s2 = s2a.add(s2b).multipule(conj);

        Complex t2 = s1.add(s2);
        Complex result = t1.multipule(t2);
        return result;
    }

    private Complex calcOneSji(int m, int select) {
        //U(I(m))*cos(a(I(m)))  + i* U(I(m))*sin(a(I(m)))
        Complex t1 = new Complex(U.get((int) J.get(m) - 1) * Math.cos(a.get((int) J.get(m) - 1)), U.get((int) J.get(m) - 1) * Math.sin(a.get((int) J.get(m) - 1)));
        //(U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m))))
        Complex s1a = new Complex(U.get((int) J.get(m) - 1) * Math.cos(a.get((int) J.get(m) - 1)), -U.get((int) J.get(m) - 1) * Math.sin(a.get((int) J.get(m) - 1)));
        Complex s1b = null;
        switch (select) {
            case 1:
                s1b = calcOneSij1(m);
                break;
            case 2:
                s1b = calcOneSij3(m);
                break;
            case 3:
                s1b = calcOneSij2(m);
                break;
        }

        Complex s1 = s1a.multipule(s1b);

        //U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m)))
        Complex s2a = new Complex(U.get((int) J.get(m) - 1) * Math.cos(a.get((int) J.get(m) - 1)), -U.get((int) J.get(m) - 1) * Math.sin(a.get((int) J.get(m) - 1)));
        //-U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m)))
        Complex s2b = new Complex(-U.get((int) I.get(m) - 1) * Math.cos(a.get((int) I.get(m) - 1)), U.get((int) I.get(m) - 1) * Math.sin(a.get((int) I.get(m) - 1)));
        Complex conj = Y.get((int) I.get(m) - 1, (int) J.get(m) - 1).clone().times(-1).conj(); //conj(-Y(I(m),J(m)))
        Complex s2 = s2a.add(s2b).multipule(conj);

        Complex t2 = s1.add(s2);
        Complex result = t1.multipule(t2);
        return result;
    }

    /**
     * 求解线路功率Sij和Sji
     */
    public void solvePower() {
        Sij = new VectorComplex(y);
        Sji = new VectorComplex(y);
        for (int m = 0; m < y; m++) {
            if (KT.get(m) == 0) {
                Sij.set(m, calcOneSij(m, 1));
                Sji.set(m, calcOneSji(m, 1));
            } else {
                if (W.get(m) == 1) {
                    Sij.set(m, calcOneSij(m, 2));
                    Sji.set(m, calcOneSji(m, 2));
                } else {
                    Sij.set(m, calcOneSij(m, 3));
                    Sji.set(m, calcOneSji(m, 3));
                }
            }
        }
    }

    /**
     * 求解线路功率损耗
     */
    public void solveLoss() {
        deltaSij = new VectorComplex(Sij.getReal().copy().add(Sji.getReal()), Sij.getImag().copy().add(Sji.getImag()));
        S = new VectorComplex(x);
        for (int b = 0; b < x; b++) {
            for (int m = 0; m < y; m++) {
                if ((int) I.get(m) - 1 == b) {
                    S.get(b).add(Sij.get(m));
                } else {
                    if ((int) J.get(m) - 1 == b) {
                        S.get(b).add(Sji.get(m));
                    }
                }
            }

        }

        P = S.getReal();
        Q = S.getImag();
        sumdeltaS = new Complex(S.getReal().sum(), S.getImag().sum());
    }

    public void output() {
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

    }
}
