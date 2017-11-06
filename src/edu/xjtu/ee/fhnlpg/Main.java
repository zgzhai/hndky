package edu.xjtu.ee.fhnlpg;

public class Main {
    public static void main(String[] args) {
        // write your code here
        if (args.length > 0 && args[0].toUpperCase().equals("RDWD")) {
            CRDWD cr = new CRDWD();
            cr.init();
            cr.solve();
            cr.print();
        } else if (args.length > 0 && args[0].toUpperCase().equals("RDWD")) {

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
