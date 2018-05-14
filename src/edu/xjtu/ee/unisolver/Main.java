package edu.xjtu.ee.unisolver;

public class Main {
    public static void main(String[] args) {

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

        UniParameter uniParameter = new UniParameter();
        UniSolver uniSolver = new UniSolver();
        UniResult uniResult = uniSolver.solve(uniParameter, "HST");

        System.out.println(String.format("errcode= %d", uniResult.errcode));
    }
}
