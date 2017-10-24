package edu.xjtu.ee.tools;

import Jama.Matrix;

/** 矩阵附加运算
 * Created by Administrator on 2017/10/19.
 */
public class MatrixEx {
    public Matrix ma;

    public static void main(String[] args) {
        // write your code here
        double[][] m = {{1,1,1}, {2,2,2},{3,3,3}};
        Matrix mx = new Matrix(m);
        mx.print(8,3);
        double[] a = {2,4,6};
        double[] b = {2,4,6};
        Vector va = new Vector(a);
        Vector vb = new Vector(b);

        Matrix mr = mx.times(va.dotDiv(vb).getMatrix());
        mr.print(8,3);
        new Vector(mr).print(8,3);


    }

    public MatrixEx(Matrix ma) {
        this.ma = ma;
    }

    /**
     * 根据向量值进行矩阵行删除
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
     * @param v
     */
    public void deleteColumn(Vector v) {
        if (v == null) return;

        Matrix m = new Matrix(ma.getRowDimension(), ma.getColumnDimension()-v.getSize());
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
     * @param v
     */
    public void delete(Vector v){
        deleteRow(v);
        deleteColumn(v);
    }

}
