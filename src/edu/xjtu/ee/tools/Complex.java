package edu.xjtu.ee.tools;

/**
 * Created by Administrator on 2017/10/19.
 */
public class Complex {
    public double a;
    public double b;

    public static void main(String[] args) {

    }

    public Complex() {
        this.a = 0.0;
        this.b = 0.0;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * 倒数
     *
     * @return
     */
    public Complex inverse() {
        double aabb = a * a + b * b;
        if (aabb == 0) return new Complex();
        return new Complex(a / aabb, -b / aabb);
    }

    /**
     * 负数倒数
     *
     * @return
     */
    public Complex inverse(int sign) {
        double aabb = a * a + b * b;
        if (aabb == 0) return new Complex();
        return new Complex(sign * a / aabb, sign * -b / aabb);
    }

    /**
     * 共轭值
     *
     * @return
     */
    public Complex conj() {
        return new Complex(a, -b);
    }

    public Complex add(Complex c) {
        a += c.a;
        b += c.b;
        return this;
    }

    public Complex sub(Complex c) {
        a -= c.a;
        b -= c.b;
        return this;
    }

    public Complex times(double x) {
        a *= x;
        b *= x;
        return this;
    }

    public Complex clone() {
        return new Complex(a, b);
    }

    public Complex multipule(Complex c) {
        double aa = this.a;
        double bb = this.b;
        a = aa * c.a - bb * c.b;
        b = aa * c.b + bb * c.a;
        return this;
    }

    public Complex divide(Complex c) {
        double ccdd = c.a * c.a + c.b * c.b;
        if (ccdd == 0) return new Complex();
        double x = (a * c.a + b * c.b) / ccdd;
        double y = (c.a * x - a) / c.b;
        a = x;
        b = y;
        return this;
    }

    /**
     * 模
     *
     * @return
     */

    public double det() {
        return Math.sqrt(a * a + b * b);
    }

    public String toFormatString(int l, int dot) {
        String formatStr = "%-" + l + "." + dot + "f";
        String astr = String.format(formatStr, a).trim();
        String bstr = String.format(formatStr, Math.abs(b)).trim();
        String fr = String.format("%%%ds", 2 * l + 8);
        String sign = b > 0 ? " + " : " - ";
        return String.format(fr, astr + sign + bstr + "j");
    }

    public void print(int l, int dot) {
        System.out.println(toFormatString(l, dot));
    }

    public void print() {
        print(8, 4);
    }

    public void print(String title, int l, int dot) {
        System.out.println(title);
        print(l, dot);
    }

    public void print(String title) {
        System.out.println(title);
        print();
    }
}
