package edu.xjtu.ee.tools;

public class Main {
    public static void main(String[] args) {

        Complex u = new Complex(1.0335, 0.0774);
        Complex conf = new Complex(0, -31.7460);
        Complex sum = new Complex(0,35);
        conf.multiply(u).print();
        sum.add(conf);
        sum.print();
    }

}
