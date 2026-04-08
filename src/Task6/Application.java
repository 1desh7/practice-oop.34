package Task6;

import Task5.GenCommand;
import Task5.ViewCommand;

import java.util.*;

/** Singleton менеджер команд */
class Application {
    private static Application instance;
    private List<ConsoleCommand> commands = new ArrayList<>();

    private Application() {}
    public static Application getInstance() {
        if (instance == null) instance = new Application();
        return instance;
    }

    public void addCommand(ConsoleCommand cmd) { commands.add(cmd); }
    public void execute(char key) {
        for (ConsoleCommand cmd : commands) {
            if (cmd.getHotKey() == key) {
                cmd.execute();
                return;
            }
        }
    }

    public void addCommand(ViewCommand viewCommand) {
    }

    public void addCommand(GenCommand genCommand) {
    }
}

/** Команда запуску паралельної обробки */
class ExecuteConsoleCommand extends ConsoleCommand {
    private ViewResult view;
    private CommandQueue queue;

    public ExecuteConsoleCommand(ViewResult v, CommandQueue q) {
        super('e');
        this.view = v;
        this.queue = q;
    }

    @Override
    public void execute() {
        if (view.getItems().isEmpty()) {
            System.out.println("Колекція порожня!");
            return;
        }
        queue.put(new MaxCommand(view.getItems()));
    }
}