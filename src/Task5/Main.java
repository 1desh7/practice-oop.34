package Task5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        View view = new ViewTable();

        // Реєстрація команд
        app.addCommand(new GenCommand(view));
        app.addCommand(new ViewCommand(view));

        // Створення макрокоманди (g + v)
        MacroCommand macro = new MacroCommand('m');
        macro.add(new GenCommand(view));
        macro.add(new ViewCommand(view));
        app.addCommand(macro);

        Scanner sc = new Scanner(System.in);
        char key;
        do {
            System.out.println("\nМеню: [g]en, [v]iew, [m]acro, [u]ndo, [q]uit");
            key = sc.next().charAt(0);
            if (key == 'u') app.undo();
            else if (key != 'q') app.execute(key);
        } while (key != 'q');
    }
}