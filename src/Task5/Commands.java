package Task5;

import java.util.*;

// Команда генерації
class GenCommand extends ConsoleCommand {
    private View view;
    public GenCommand(View v) { super('g'); this.view = v; }
    public void execute() {
        System.out.print("Введіть число: ");
        view.addResult(new Scanner(System.in).nextInt());
    }
}

// Команда перегляду
class ViewCommand extends ConsoleCommand {
    private View view;
    public ViewCommand(View v) { super('v'); this.view = v; }
    public void execute() { view.viewShow(); }
}

// Макрокоманда (виконує декілька дій)
class MacroCommand extends ConsoleCommand {
    private List<Command> list = new ArrayList<>();
    public MacroCommand(char key) { super(key); }
    public void add(Command c) { list.add(c); }
    public void execute() {
        System.out.println("Виконання макросу...");
        for (Command c : list) c.execute();
    }
}