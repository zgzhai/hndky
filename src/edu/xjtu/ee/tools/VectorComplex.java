package edu.xjtu.ee.tools;

public class VectorComplex {
    private Complex[] c;
    private int size;

    public static void main(String[] args) {
        System.out.println("hello tools!");
        double[] a = {1, 2, 3};
        Vector va = new Vector(a);
        double[] b = {4, 5, 6};
        Vector vb = new Vector(b);

        VectorComplex vc = new VectorComplex(va, vb);
        vc.print();
        vc.getReal().print();
        vc.getImag().print();
        Complex c = new Complex(9, 10);
        vc.set(1, c);
        vc.print();
    }

    public VectorComplex(int size) {
        this.size = size;
        c = new Complex[size];

        for (int i = 0; i < size; i++) {
            c[i] = new Complex();
        }
    }

    public VectorComplex(Vector a, Vector b) {
        this.size = a.getSize();
        c = new Complex[size];

        for (int i = 0; i < size; i++) {
            c[i] = new Complex(a.get(i), b.get(i));
        }
    }

    public Complex get(int i) {
        return c[i];
    }

    public void set(int i, Complex c) {
        this.c[i] = c;
    }

    public Vector getReal() {
        Vector v = new Vector(size);
        for (int i = 0; i < size; i++) {
            v.set(i, c[i].a);
        }
        return v;
    }

    public Vector getImag() {
        Vector v = new Vector(size);
        for (int i = 0; i < size; i++) {
            v.set(i, c[i].b);
        }
        return v;
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
        System.out.println("[");
        for (int i = 0; i < size; i++) {
            c[i].print();
        }
        System.out.println("]");
    }

    public void print(int l, int dot) {
        System.out.println("[");
        for (int i = 0; i < size; i++) {
            c[i].print(l, dot);
        }
        System.out.println("]");
    }
}
