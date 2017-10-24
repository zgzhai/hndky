package edu.xjtu.ee.tools;

import Jama.Matrix;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 复数矩阵
 * Created by Administrator on 2017/10/19.
 */
public class MatrixComplex {
    public Complex[][] cc;
    public int rows;
    public int cols;

    public static void main(String[] args) {
        // write your code here

        double[][] a = {{-3333.3333, -3231.7460, 0, 0, 0},
                {31.7460, -35.7379, 3.1120, 2.6415, 0},
                {0, 3.1120, -66.9808, 3.9002, 63.4921},
                {0, 2.6415, 3.9002, -6.2917, 0},
                {0, 0, 63.4921, 0, 6.6667}};
        double[][] b = {{-5545.3432, -5545.3432, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        MatrixComplex mc = new MatrixComplex(new Matrix(a), new Matrix(b));
        mc.print(8, 4);
        mc.getReal().print(8, 4);
        mc.getImag().print(8, 4);

    }

    public MatrixComplex(int row, int col) {
        rows = row;
        cols = col;
        cc = new Complex[row][col];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cc[i][j] = new Complex();
            }
        }
    }

    public MatrixComplex(Matrix a, Matrix b) {
        this(a.getRowDimension(), a.getColumnDimension());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cc[i][j].a = a.get(i, j);
                cc[i][j].b = b.get(i, j);
            }
        }
    }

    public Complex get(int i, int j) {
        return cc[i][j];
    }

    public void set(int i, int j, Complex c) {
        cc[i][j] = c;
    }

    public int getRows() {
        return cc.length;
    }

    public int getColumns() {
        return cc[0].length;
    }

    public Matrix getReal() {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, cc[i][j].a);
            }
        }
        return m;
    }

    public Matrix getImag() {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, cc[i][j].b);
            }
        }
        return m;
    }

    public void print(){
        print(8,4);
    }

    public void print(int l, int dot) {
        int maxLen = 2 * l + 8;
        ArrayList<String> arrayList = new ArrayList<>();
        int[] len = new int[cols];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                String str = cc[i][j].toFormatString(l, dot);
                arrayList.add(str);
                if (str.trim().length() > len[j]) len[j] = str.trim().length();
            }
        }

        for (int i = 0; i < getRows(); i++) {
            StringBuffer sf = new StringBuffer();
            for (int j = 0; j < getColumns(); j++) {
                sf.append(arrayList.get(i * cols + j).substring(maxLen - len[j]) + "  ");
            }
            System.out.println(sf.toString());
        }


    }
}
