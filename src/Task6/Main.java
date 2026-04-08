package Task6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        ViewResult view = new ViewResult();
        CommandQueue queue = new CommandQueue();

        // Запуск фонового потоку (Worker Thread)
        Thread worker = new Thread(queue);
        worker.setDaemon(true);
        worker.start();

        // Реєстрація команд
        app.addCommand(new GenCommand(view));
        app.addCommand(new ViewCommand(view));
        app.addCommand(new ExecuteConsoleCommand(view, queue));

        Scanner sc = new Scanner(System.in);
        char key;
        do {
            System.out.println("\nМеню: [g]en, [v]iew, [e]xecute parallel, [q]uit");
            key = sc.next().charAt(0);
            if (key != 'q') app.execute(key);
        } while (key != 'q');

        queue.shutdown();
        System.out.println("Програму завершено.");
    }
}

/** Команда генерації */
class GenCommand extends ConsoleCommand {
    private ViewResult view;
    public GenCommand(ViewResult v) { super('g'); this.view = v; }
    @Override
    public void execute() {
        System.out.print("Введіть число: ");
        view.addResult(new Scanner(System.in).nextInt());
    }
}

/** Команда перегляду */
class ViewCommand extends ConsoleCommand {
    private ViewResult view;
    public ViewCommand(ViewResult v) { super('v'); this.view = v; }
    @Override
    public void execute() { 
        view.viewShow(); 
    }
}