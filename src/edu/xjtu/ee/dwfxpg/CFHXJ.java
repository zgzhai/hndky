package edu.xjtu.ee.dwfxpg;

import Jama.Matrix;
import edu.xjtu.ee.dwfxpg.io.CLinePIJ;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgDW;
import edu.xjtu.ee.dwfxpg.io.OFHXJ;
import edu.xjtu.ee.dwfxpg.io.OZLCL;
import edu.xjtu.ee.tools.MatrixEx;
import edu.xjtu.ee.tools.VElement;
import edu.xjtu.ee.tools.Vector;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/20.
 * 负荷消减程序
 */
public class CFHXJ extends CZLCL {
    private static final double eps = 2.22e-16; //代表最小浮点数
    private static final double inf = 2.22e32;  //代表最大浮点数
    private Matrix B;
    private Matrix T;
    private Matrix A;
    private Vector b;
    private Vector c;
    private Matrix D;
    private Vector X;
    private int status = 0;
    private Matrix f;

    public static void main(String[] args) {
        // write your code here
        CFHXJ c = new CFHXJ();
        c.init();
        c.solve();
        c.print();
    }

    public CFHXJ() {
        super();
    }

    public CFHXJ(IDwfxpgDW dw) {
        super(dw);
    }

    @Override
    public void init() {
        Bus.add(new CBus(1, 1));
        Bus.add(new CBus(2, 2));
        Bus.add(new CBus(3, 3));

        Load.add(new CLoad(1, 230));
        Load.add(new CLoad(2, 580));
        Load.add(new CLoad(3, 296));

        Generator.add(new CGenerator(1, 420));
        Generator.add(new CGenerator(2, 0));
        Generator.add(new CGenerator(3, 560));

        Line.add(new CLine(1, 2, 1, 0.00497, 224));
        Line.add(new CLine(1, 3, 1, 0.00551, 289));
        Line.add(new CLine(2, 3, 1, 0.00504, 251));
    }

    @Override
    public void solve() {
        super.solve();
        int panduantiaojian = panduantiaojian(super.output().getRongliang());
        if (panduantiaojian == 1) {
            OTP();
        } else {
            status = 0;
        }
    }

    private int panduantiaojian(Vector rongliang) {
        int flag = 0;
        for (int i = 0; i < rongliang.getSize(); i++) {
            if (rongliang.get(i) > 90) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    private void OTP() {
        B = calcB();
        T = calcT();

        Vector p = calc_p();
        Vector pl = calc_pl();
        double padd = -p.sum();
        p = new Vector(T.times(p.getMatrix()));

        Vector pmax = new Vector(Line.size(), 0);
        Vector pmin = new Vector(Line.size(), 0);
        for (int i = 0; i < pmax.getSize(); i++) {
            pmax.set(i, Line.get(i).capacity - p.get(i));
            pmin.set(i, -Line.get(i).capacity - p.get(i));
        }

        calcAbc(pmin, pmax, pl, padd);
        calcD();
        calcX();
    }

    /**
     * 求解B
     *
     * @return
     */
    private Matrix calcB() {
        Matrix Yinv = Y.inverse();
        Matrix B = new Matrix(Y.getRowDimension() + 1, Y.getRowDimension() + 1, 0.0);
        for (int i = 0; i < Y.getRowDimension(); i++) {
            for (int j = 0; j < Y.getColumnDimension(); j++) {
                B.set(i, j, Yinv.get(i, j));
            }
        }
        return B;
    }

    /**
     * 求解T
     *
     * @return
     */
    private Matrix calcT() {
        Matrix T = new Matrix(Line.size(), B.getRowDimension(), 0.0);
        for (int i = 0; i < T.getRowDimension(); i++) {
            for (int j = 0; j < T.getColumnDimension(); j++) {
                double val = (B.get(Line.get(i).sid - 1, j) - B.get(Line.get(i).eid - 1, j)) / Line.get(i).resistance;
                T.set(i, j, val);
            }
        }
        return T;
    }

    /**
     * 求解p
     *
     * @return
     */
    private Vector calc_p() {
        Vector p = new Vector(Bus.size(), 0.0);
        for (int i = 0; i < Bus.size(); i++) {
            int ID = Bus.get(i).id;
            for (int j = 0; j < Load.size(); j++) {
                if (Load.get(j).id == ID) {
                    double val = p.get(ID - 1) - Load.get(j).pl;
                    p.set(ID - 1, val);
                }
            }

            for (int j = 0; j < Generator.size(); j++) {
                if (Generator.get(j).id == ID) {
                    double val = p.get(ID - 1) + Generator.get(j).pg;
                    p.set(ID - 1, val);
                }
            }
        }

        return p;
    }

    /**
     * 求解pl
     *
     * @return
     */
    private Vector calc_pl() {
        Vector pl = new Vector(Bus.size(), 0.0);
        for (int i = 0; i < pl.getSize(); i++) {
            pl.set(i, Load.get(i).pl);
        }

        return pl;
    }

    /**
     * 求解A, b, c
     *
     * @param pmin
     * @param pmax
     * @param pl
     * @param padd
     */
    private void calcAbc(Vector pmin, Vector pmax, Vector pl, double padd) {

        c = new Vector(2 * Bus.size() + 2 * T.getRowDimension());
        A = new Matrix(2 * T.getRowDimension() + Bus.size() + 1, 2 * Bus.size() + 2 * T.getRowDimension());
        b = new Vector(2 * T.getRowDimension() + Bus.size() + 1);
        for (int i = 0; i < Bus.size(); i++) {
            c.set(i, 1);
        }
        for (int i = 0; i < T.getRowDimension(); i++) {
            for (int j = 0; j < T.getColumnDimension(); j++) {
                A.set(i, j, -T.get(i, j));
            }
            A.set(i, i + T.getColumnDimension(), 1);
        }

        for (int i = T.getRowDimension(); i < 2 * T.getRowDimension(); i++) {
            for (int j = 0; j < T.getColumnDimension(); j++) {
                A.set(i, j, T.get(i - T.getRowDimension(), j));
            }
            A.set(i, i + T.getColumnDimension(), 1);
        }

        int j = 0;
        for (int i = 2 * T.getRowDimension(); i < 2 * T.getRowDimension() + Bus.size(); i++) {
            A.set(i, j, 1);
            j++;
            A.set(i, T.getColumnDimension() + i, 1);
        }

        for (int i = 0; i < Bus.size(); i++) {
            A.set(2 * T.getRowDimension() + Bus.size(), i, -1);
        }

        int index = 0;
        for (int i = 0; i < pmin.getSize(); i++) {
            b.set(index, -pmin.get(i));
            index++;
        }
        for (int i = 0; i < pmax.getSize(); i++) {
            b.set(index, pmax.get(i));
            index++;
        }
        for (int i = 0; i < pl.getSize(); i++) {
            b.set(index, pl.get(i));
            index++;
        }
        b.set(index, -padd);
        b.setType(b.ROW);
        c.setType(c.ROW);
    }

    /**
     * 求解D
     */
    private void calcD() {
        int m = A.getRowDimension();
        int n = A.getColumnDimension();
        D = new Matrix(m, 2);
        for (int i = 0; i < m; i++) {
            D.set(i, 0, i + 1);
        }
        for (int i = 0; i < m; i++) {
            D.set(i, 1, n - m + 1 + i);
        }
    }

    /**
     * 求解X和f
     */
    private void calcX() {
        int m = A.getRowDimension();
        int n = A.getColumnDimension();
        X = new Vector(n);
        X.setType(Vector.ROW);

        int flag = 0;

        //判断是否为标准型
        if (n < m) {
            flag = 0;
            status = 1;
            //System.out.println("不符合要求需引入松弛变量");
        } else {
            flag = 1;
            B = new MatrixEx(A).partCol(n - m, n - 1);
            Matrix cB = c.part(n - m, n - 1).getMatrix();
            while (flag > 0) {
                Matrix w = MatrixEx.rightDiv(cB, B);
                VElement v = new Vector(w.times(A).minus(c.getMatrix())).maxE();
                double z = v.getVal();
                int k = v.getIndex();
                if (z < 0.000000001) {
                    flag = 0;       //所有判别数都小于0时达到最优解。
                    status = 2;
                    //System.out.println("已找到最优解!");
                    Matrix xB = MatrixEx.leftDiv(B, b.getMatrix().transpose()).transpose();
                    f = cB.times(xB.transpose());

                    for (int i = 0; i < n; i++) {
                        int mark = 0;
                        for (int j = 0; j < m; j++) {
                            if ((int) D.get(j, 1) == i + 1) {
                                mark = 1;
                                //利用D找出xB与X之间的关系
                                X.set(i, xB.get(0, (int) D.get(j, 0) - 1));
                            }
                        }
                        if (mark == 0) {
                            //如果D中没有X(i),则X(i)为非基变量，所以X(i)＝0
                            X.set(i, 0);
                        }
                    }

                    /*
                    System.out.println("基向量为:");
                    X.print(8, 4);
                    System.out.println("目标函数值为:");
                    f.print(8, 4);
                    */
                    if (f.get(0, 0) <= 0) {
                        status = 4;
                    } else {
                        status = 5;
                    }


                } else {
                    //if(B\A(:,k)<=0),  B\A(;,k)中的每一个分量都小于零
                    if (MatrixEx.lte(MatrixEx.leftDiv(B, new MatrixEx(A).partCol(k, k)), 0)) {
                        flag = 0;
                        status = 3;
                        //System.out.println("此问题不存在最优解!"); //若B\A(:,k)的第k列均不大于0，则该问题不存在最优解
                    } else {
                        Matrix b1 = MatrixEx.leftDiv(B, b.getMatrix().transpose());
                        double temp = inf;
                        int r = 0;
                        for (int i = 0; i < m; i++) {
                            if (A.get(i, k) > 0 && (b1.get(i, 0) / (A.get(i, k) + eps)) < temp) {
                                temp = b1.get(i, 0) / A.get(i, k); //找退基变量
                                r = i;
                            }
                        }

                        //System.out.println("x(" + (k + 1) + ")进基, x(" + D.get(r, 1) + ")退基");
                        MatrixEx.setCol(B, r, A, k);
                        cB.set(0, r, c.get(k));  //确定进基退基变量后，相应的基矩阵及新基对应的目标值的c也相应改变
                        D.set(r, 1, k + 1);
                    }
                }
            }
        }

    }

    @Override
    public OFHXJ output() {
        OFHXJ ofhxj = new OFHXJ();
        /*
        OZLCL ozlcl = super.output();
        //ofhxj.Y = ozlcl.Y;
        ofhxj.Delta = ozlcl.Delta;
        ofhxj.PIJ = ozlcl.PIJ;
        */
        ofhxj.setFuhezhuanyi(super.output().getFuhezhuanyi());
        ofhxj.status = status;
        if (status != 0) {
            ofhxj.f = f.get(0, 0);
            //ofhxj.X = X.toArrayList();
        }

        switch (status) {
            case 0:
                ofhxj.msg1 = "此状态下不需要负荷削减，请继续监控";
                break;
            case 1:
                ofhxj.msg1 = "不符合要求需引入松弛变量";
                break;
            case 2:
                ofhxj.msg1 = "已找到最优解";
                break;
            case 3:
                ofhxj.msg1 = "此状态下不存在潮流优化最优解! 请加强重载线路及重载变压器的运行监控，可参考负荷转移建议！";
                break;
            case 4:
                ofhxj.msg1 = "此种状态下不需要削减负荷，可参考负荷转移建议！";
                break;
            case 5:
                ofhxj.msg1 = String.format("此种状态下可以进行负荷转移以优化电网静态稳定性，可参考负荷转移建议！");
                break;
            default:
                ofhxj.msg1 = String.format("未知状态，status=%d", status);
                break;
        }

        return ofhxj;
    }
}
