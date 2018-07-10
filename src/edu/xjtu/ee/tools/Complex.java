package edu.xjtu.ee.tools;

/** 进行复数的的各种数学运算
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

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
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
     * @param sign  -1
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

    /**
     * 加一个复数，改变本对象值并返回本对象
     * @param c
     * @return
     */
    public Complex add(Complex c) {
        a += c.a;
        b += c.b;
        return this;
    }

    /**
     * 减一个复数，改变本对象值并返回本对象
     * @param c
     * @return
     */
    public Complex sub(Complex c) {
        a -= c.a;
        b -= c.b;
        return this;
    }

    /**
     * 乘一个实数，改变本对象值并返回本对象
     * @param x
     * @return
     */
    public Complex times(double x) {
        a *= x;
        b *= x;
        return this;
    }

    /**
     * 乘一个复数，改变本对象值并返回本对象
     * @param c
     * @return
     */
    public Complex multiply(Complex c) {
        double aa = this.a;
        double bb = this.b;
        a = aa * c.a - bb * c.b;
        b = aa * c.b + bb * c.a;
        return this;
    }

    /**
     * 除一个复数，改变本对象值并返回本对象
     * @param c
     * @return
     */
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
     * 克隆对象
     * @return
     */
    public Complex clone() {
        return new Complex(a, b);
    }

    /**
     * 拷贝对象，同克隆对象
     * @return
     */
    public Complex copy() {
        return new Complex(a, b);
    }

    /**
     * 取模
     *
     * @return
     */

    public double det() {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * 格式化复数字符串
     * @param l    长度
     * @param dot  小数位数
     * @return
     */
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
