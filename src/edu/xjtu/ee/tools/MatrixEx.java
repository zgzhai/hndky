package edu.xjtu.ee.tools;

import Jama.Matrix;

/**
 * 矩阵附加运算
 * Created by Administrator on 2017/10/19.
 */
public class MatrixEx {
    public Matrix ma;

    public static void main(String[] args) {
        // write your code here
        double[][] m = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 5}};
        Matrix ma = new Matrix(m);
        double[][] n = {{1, 8}, {2, 9}, {3, 10}, {4, 11}};
        Matrix mb = new Matrix(n);

        MatrixEx.setCol(ma, 2, mb, 1);
        ma.print(8, 4);

    }

    public MatrixEx(Matrix ma) {
        this.ma = ma;
    }

    /**
     * 获取ma
     *
     * @return
     */
    public Matrix get() {
        return ma;
    }

    /**
     * 根据向量值进行矩阵行删除
     *
     * @param v
     */
    public void deleteRow(Vector v) {
        if (v == null) return;

        Matrix m = new Matrix(ma.getRowDimension() - v.getSize(), ma.getColumnDimension());
        int row = 0;
        for (int i = 0; i < ma.getRowDimension(); i++) {
            if (v.isIn(i + 1)) continue;
            for (int j = 0; j < ma.getColumnDimension(); j++) {
                m.set(row, j, ma.get(i, j));
            }
            row++;
        }

        ma = m;
    }

    /**
     * 根据向量值进行矩阵列删除
     *
     * @param v
     */
    public void deleteColumn(Vector v) {
        if (v == null) return;

        Matrix m = new Matrix(ma.getRowDimension(), ma.getColumnDimension() - v.getSize());
        for (int i = 0; i < ma.getRowDimension(); i++) {
            int column = 0;
            for (int j = 0; j < ma.getColumnDimension(); j++) {
                if (v.isIn(j + 1)) continue;
                m.set(i, column, ma.get(i, j));
                column++;
            }

        }

        ma = m;
    }

    /**
     * 根据向量值进行矩阵行列删除
     *
     * @param v
     */
    public void delete(Vector v) {
        deleteRow(v);
        deleteColumn(v);
    }

    /**
     * 获取矩阵的部分列
     *
     * @param n1 起始列号
     * @param n2 结束列号
     * @return
     */
    public Matrix partCol(int n1, int n2) {
        int m1 = 0;
        int m2 = ma.getRowDimension() - 1;
        return part(m1, m2, n1, n2);

    }

    /**
     * 获取矩阵的部分行
     *
     * @param m1 起始行号
     * @param m2 结束行号
     * @return
     */
    public Matrix partRow(int m1, int m2) {
        int n1 = 0;
        int n2 = ma.getColumnDimension() - 1;
        return part(m1, m2, n1, n2);
    }

    /**
     * 获取矩阵的部分行列
     *
     * @param m1 起始行号
     * @param m2 结束行号
     * @param n1 起始列号
     * @param n2 结束列号
     * @return
     */
    private Matrix part(int m1, int m2, int n1, int n2) {
        Matrix m = new Matrix(m2 - m1 + 1, n2 - n1 + 1);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                m.set(i, j, ma.get(m1 + i, n1 + j));
            }
        }

        return m;

    }

    /**
     * 矩阵左除 A\B=A.inv*B
     *
     * @param A
     * @param B
     * @return
     */
    static public Matrix leftDiv(Matrix A, Matrix B) {
        return A.inverse().times(B);
    }

    /**
     * 矩阵右除 A/B=A*B.inv
     *
     * @param A
     * @param B
     * @return
     */
    static public Matrix rightDiv(Matrix A, Matrix B) {
        return A.times(B.inverse());
    }

    /**
     * 矩阵所有元素是否大于a
     *
     * @param A
     * @param a
     * @return
     */
    static public Boolean gt(Matrix A, double a) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                if (A.get(i, j) <= a) return false;
            }
        }
        return true;
    }

    /**
     * 矩阵所有元素是否大于等于a
     *
     * @param A
     * @param a
     * @return
     */
    static public Boolean gte(Matrix A, double a) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                if (A.get(i, j) < a) return false;
            }
        }
        return true;
    }

    /**
     * 矩阵所有元素是否小于a
     *
     * @param A
     * @param a
     * @return
     */
    static public Boolean lt(Matrix A, double a) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                if (A.get(i, j) >= a) return false;
            }
        }
        return true;
    }

    /**
     * 矩阵所有元素是否小于等于a
     *
     * @param A
     * @param a
     * @return
     */
    static public Boolean lte(Matrix A, double a) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                if (A.get(i, j) > a) return false;
            }
        }
        return true;
    }

    /**
     * 将B的bn列赋值给A的an列
     *
     * @param A
     * @param an
     * @param B
     * @param bn
     */
    static public void setCol(Matrix A, int an, Matrix B, int bn) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            A.set(i, an, B.get(i, bn));
        }
    }
}
