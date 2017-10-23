package edu.xjtu.ee.fhnlpg;

public class Main {
    public static void main(String[] args) {
        // write your code here
        if (args.length > 0 && args[0].toUpperCase().equals("RDWD")) {
            CRDWD cr = new CRDWD();
            cr.Init();
            cr.solve();
            cr.print();
        } else {
            System.out.println("show help");
        }
    }
}
