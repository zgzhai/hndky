package edu.xjtu.ee.dwfxpg;

import Jama.Matrix;

import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals("ZLCL")) {
            CZLCL c = new CZLCL();
            c.Init();
            c.Calc();
            c.OutputScreen();
            c.OutToFile();
        } else if (args.length > 0 && args[0].toUpperCase().equals("PQ")) {
            CPQ cpq = new CPQ(5, 5, 0.00001);
            cpq.init();       //数据初始化
            cpq.solve();
            cpq.output();
        } else {
            System.out.println("                        .---.                                                            ");
            System.out.println("                        |   |                                                            ");
            System.out.println("                        '---'                         __.....__           __.....__      ");
            System.out.println("                        .---.                     .-''         '.     .-''         '.    ");
            System.out.println("                        |   |     .|             /     .-''''-.  `.  /     .-''''-.  `.  ");
            System.out.println("   ____     _____       |   |   .' |_           /     /________\\   \\/     /________\\   \\ ");
            System.out.println("  `.   \\  .'    /       |   | .'     |   _    _ |                  ||                  | ");
            System.out.println("    `.  `'    .'        |   |'--.  .-'  | '  / |\\    .-------------'\\    .-------------' ");
            System.out.println("      '.    .'          |   |   |  |   .' | .' | \\    '-.____...---. \\    '-.____...---. ");
            System.out.println("      .'     `.         |   |   |  |   /  | /  |  `.             .'   `.             .'  ");
            System.out.println("    .'  .'`.   `.    __.'   '   |  '.'|   `'.  |    `''-...... -'       `''-...... -'    ");
            System.out.println("  .'   /    `.   `. |      '    |   / '   .'|  '/                                        ");
            System.out.println(" '----'       '----'|____.'     `'-'   `-'  `--'                                         ");
        }
    }

}
