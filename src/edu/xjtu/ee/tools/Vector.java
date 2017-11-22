package edu.xjtu.ee.tools;

import Jama.Matrix;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

/**
 * 向量运算
 * Created by Administrator on 2017/9/25.
 */
public class Vector {
    public static final int COL = 1;
    public static final int ROW = 2;

    public static final int EQ = 1;
    public static final int NEQ = 2;
    public static final int GT = 3;
    public static final int GTE = 4;
    public static final int LT = 5;
    public static final int LTE = 6;

    private double[] A;
    private int type;
    private int size;

    public static void main(String[] args) {
        double[] a = {3, 1, 1, 1, 2};
        Vector v = new Vector(a);
        v.maxE().print(8,4);
    }

    public Vector(int size) {
        this.type = COL;
        this.size = size;
        A = new double[size];
        for (int i = 0; i < size; i++) {
            A[i] = 0.0d;
        }
    }

    public Vector(int size, double initial) {
        this.type = COL;
        this.size = size;
        A = new double[size];
        for (int i = 0; i < size; i++) {
            A[i] = initial;
        }
    }

    public Vector(double[] a) {
        this.A = a;
        this.size = a.length;
        this.type = COL;
    }

    /**
     * 根据步长起终值获取行列式
     *
     * @param start 起始值
     * @param delta 步长
     * @param end   终值
     */
    public Vector(double start, double delta, double end) {
        ArrayList<Double> tmp = new ArrayList<Double>();
        for (; start <= end; start += delta) {
            tmp.add(start);
        }
        size = tmp.size();
        type = COL;
        if (size > 0) {
            A = new double[size];
            for (int i = 0; i < size; i++) {
                A[i] = tmp.get(i);
            }
        }
    }

    /**
     * 获取矩阵的行或列
     *
     * @param a     矩阵
     * @param index 行号或列号， 从0开始
     * @param type  1：列，2：行
     */

    public Vector(Matrix a, int index, int type) {
        this.type = type;
        if (type == ROW) {
            this.size = a.getColumnDimension();
            int rows = a.getRowDimension();
            int sn = index > (rows - 1) ? (rows - 1) : index;
            A = new double[this.size];
            for (int i = 0; i < this.size; i++) {
                A[i] = a.get(sn, i);
            }
        } else {
            this.size = a.getRowDimension();
            int cols = a.getColumnDimension();
            int sn = index > (cols - 1) ? (cols - 1) : index;
            A = new double[this.size];
            for (int i = 0; i < this.size; i++) {
                A[i] = a.get(i, sn);
            }
        }
    }

    public Vector(Matrix a) {
        this(a, 0, COL);
    }

    public void set(double[] a) {
        this.A = a;
        this.size = a.length;
        this.type = COL;
    }

    public String toFormatString(int l, int dot) {
        StringBuffer sf = new StringBuffer();
        sf.append("[");
        String formatStr = "%-" + l + "." + dot + "f";
        for (int i = 0; i < size; i++) {
            sf.append(String.format(formatStr, A[i]).trim() + " ");
        }
        return sf.toString().trim() + "]";
    }

    public void print(String title) {
        System.out.println(title);
        print();
    }

    public void print(String title, int l, int dot) {
        System.out.println(title);
        print(l, dot);
    }

    public void print() {
        print(8, 4);
    }

    /**
     * 打印行列式
     */
    public void print(int l, int dot) {
        if (type == ROW) {
            System.out.println(toFormatString(l, dot));
        } else {
            System.out.println("[");
            for (int i = 0; i < size; i++) {
                String formatStr = "%-" + l + "." + dot + "f";
                String str = String.format(formatStr, A[i]);
                System.out.println(str);
            }
            System.out.println("]");
        }
    }

    /**
     * 对行列式求和
     *
     * @return 返回和
     */
    public double sum() {
        double s = 0.0d;
        for (int i = 0; i < size; i++) {
            s += A[i];
        }
        return s;
    }

    /**
     * 对行列乘一个倍数
     *
     * @return 返回
     */
    public Vector times(double t) {
        for (int i = 0; i < size; i++) {
            A[i] *= t;
        }
        return this;
    }

    /**
     * 对行列乘一个倍数
     *
     * @return 返回
     */
    public Vector div(double t) {
        for (int i = 0; i < size; i++) {
            A[i] /= t;
        }
        return this;
    }

    public Vector add(Vector a) {
        for (int i = 0; i < size; i++) {
            A[i] += a.get(i);
        }

        return this;
    }

    public Vector minus(Vector a) {
        for (int i = 0; i < size; i++) {
            A[i] -= a.get(i);
        }
        return this;
    }

    public Vector sub(Vector a) {
        return minus(a);
    }

    /**
     * 获取元素值
     *
     * @param i 元素序号
     * @return
     */
    public double get(int i) {
        return A[i];
    }

    public void set(int i, double value) {
        A[i] = value;
    }

    /**
     * 获取大小
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 行列式转换为矩阵
     *
     * @return 返回矩阵
     */
    public Matrix getMatrix() {
        Matrix m = type == ROW ? new Matrix(1, size, 0.0) : new Matrix(size, 1, 0.0);
        if (type == ROW) {
            for (int i = 0; i < size; i++) {
                m.set(0, i, A[i]);
            }
        } else {
            for (int i = 0; i < size; i++) {
                m.set(i, 0, A[i]);
            }
        }
        return m;
    }

    public double[] getArray() {
        return this.A;
    }

    public void expand(int times) {
        double[] a = new double[size];
        for (int i = 0; i < size; i++) {
            a[i] = A[i];
        }

        A = new double[size * times];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < times; j++) {
                A[j + i * times] = a[i];
            }
        }
        size *= times;
    }

    public Vector copy() {
        Vector v = new Vector(size);
        for (int i = 0; i < size; i++) {
            v.set(i, A[i]);
        }
        v.setType(type);
        return v;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public Vector find(double x, int condition) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            switch (condition) {
                case EQ:
                    if (A[i] == x) {
                        tmp.add(i + 1);
                    }
                    break;
                case NEQ:
                    if (A[i] != x) {
                        tmp.add(i + 1);
                    }
                    break;
                case GT:
                    if (A[i] > x) {
                        tmp.add(i + 1);
                    }
                    break;
                case GTE:
                    if (A[i] >= x) {
                        tmp.add(i + 1);
                    }
                    break;
                case LT:
                    if (A[i] < x) {
                        tmp.add(i + 1);
                    }
                    break;
                case LTE:
                    if (A[i] <= x) {
                        tmp.add(i + 1);
                    }
                    break;
            }
        }

        if (tmp.size() == 0) return null;
        Vector v = new Vector(tmp.size());
        for (int i = 0; i < v.size; i++) {
            v.set(i, tmp.get(i));
        }

        return v;
    }

    /**
     * x 是否等于vector中的某个值
     *
     * @param x
     * @return
     */
    public boolean isIn(int x) {
        for (int i = 0; i < size; i++) {
            if (A[i] == x) {
                return true;
            }
        }
        return false;
    }

    public Vector abs() {
        for (int i = 0; i < size; i++) {
            A[i] = Math.abs(A[i]);
        }

        return this;
    }

    public double max() {
        double maxv = A[0];
        for (int i = 1; i < size; i++) {
            if (A[i] > maxv) {
                maxv = A[i];
            }
        }
        return maxv;
    }

    public double min() {
        double minv = A[0];
        for (int i = 1; i < size; i++) {
            if (A[i] < minv) {
                minv = A[i];
            }
        }
        return minv;
    }

    /**
     * 向量点除
     *
     * @param b
     * @return
     */
    public Vector dotDiv(Vector b) {
        if (b.size != size) return null;
        Vector v = new Vector(size);
        for (int i = 0; i < size; i++) {
            if (b.get(i) == 0d) return null;
            v.set(i, A[i] / b.get(i));
        }
        v.type = COL;
        return v;
    }

    /**
     * 向量点乘
     *
     * @param b
     * @return
     */
    public Vector dotMul(Vector b) {
        if (b.size != size) return null;
        Vector v = new Vector(size);
        for (int i = 0; i < size; i++) {
            v.set(i, A[i] * b.get(i));
        }
        v.type = COL;
        return v;
    }

    /**
     * 获取部分向量
     *
     * @param m1 起始元素下标
     * @param m2 结束元素下标
     * @return
     */
    public Vector part(int m1, int m2) {
        if (m1 > size - 1 || m2 > size - 1 || m1 < 0 || m2 < 0) return null;
        Vector v = new Vector(m2 - m1 + 1);
        v.setType(type);
        for (int i = m1; i <= m2; i++) {
            v.set(i - m1, A[i]);
        }
        return v;
    }

    public VElement maxE() {
        VElement maxv = new VElement(0, A[0]);
        for (int i = 1; i < size; i++) {
            if (A[i] > maxv.getVal()) {
                maxv.setIndex(i);
                maxv.setVal(A[i]);
            }
        }
        return maxv;
    }
}
