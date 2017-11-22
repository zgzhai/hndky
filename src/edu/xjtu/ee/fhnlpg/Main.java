package edu.xjtu.ee.fhnlpg;

public class Main {
    public static void main(String[] args) {
        // write your code here
        if (args.length > 0 && args[0].toUpperCase().equals("RDWD")) {
            CRDWD c = new CRDWD();
            c.init();
            c.solve();
            c.print();
        } else if (args.length > 0 && args[0].toUpperCase().equals("COOL")) {
            COOL c = new COOL();
            c.init(6, 6, 6, 6, 0.2, 0.2, 60, 80, 0.7);
            c.solve(70, 85, 0.8);
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
