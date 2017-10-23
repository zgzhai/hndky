package edu.xjtu.ee.tools;

import Jama.Matrix;

/**
 * Created by Administrator on 2017/10/19.
 */
public class MatrixEx {
    public Matrix ma;

    public static void main(String[] args) {
        // write your code here
        /*
        double[][] a = {{-33.3333, 31.7460, 0, 0, 0},
                {31.7460, -35.7379, 3.1120, 2.6415, 0},
                {0, 3.1120, -66.9808, 3.9002, 63.4921},
                {0, 2.6415, 3.9002, -6.2917, 0},
                {0, 0, 63.4921, 0, -66.6667}};
        */

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

    public void delete(Vector v){
        deleteRow(v);
        deleteColumn(v);
    }

}
