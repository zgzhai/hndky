package edu.xjtu.ee.dwfxpg;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals("ZLCL")) {
            CZLCL c = new CZLCL();
            c.init();
            c.solve();
            c.print();
        } else if (args.length > 0 && args[0].toUpperCase().equals("PQ")) {
            CPQ c = new CPQ(5, 5, 0.00001);
            c.init();       //数据初始化
            c.solve();
            c.print();
        } else if (args.length > 0 && args[0].toUpperCase().equals("FHXJ")) {
            CFHXJ c = new CFHXJ();
            c.init();
            c.solve();
            c.print();
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
