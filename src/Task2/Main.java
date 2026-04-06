package Task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc();
        Scanner sc = new Scanner(System.in);
        String command;

        do {
            System.out.println("\nВведіть команду ('q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore):");
            command = sc.next();

            switch (command.charAt(0)) {
                case 'g':
                    System.out.print("Введіть ціле число: ");
                    int num = sc.nextInt();
                    calc.init(num);
                    calc.show();
                    break;
                case 'v':
                    calc.show();
                    break;
                case 's':
                    try { calc.save(); System.out.println("Збережено."); }
                    catch (Exception e) { System.out.println("Помилка: " + e); }
                    break;
                case 'r':
                    try { calc.restore(); System.out.println("Відновлено."); calc.show(); }
                    catch (Exception e) { System.out.println("Помилка: " + e); }
                    break;
            }
        } while (command.charAt(0) != 'q');
    }
}