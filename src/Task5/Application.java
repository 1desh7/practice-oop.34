package Task5;

import java.util.*;

public class Application {
    private static Application instance;
    private List<ConsoleCommand> commands = new ArrayList<>();
    private Stack<Command> history = new Stack<>();

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
                history.push(cmd);
                return;
            }
        }
        System.out.println("Невідома команда.");
    }

    public void undo() {
        if (!history.isEmpty()) {
            history.pop();
            System.out.println("Останню дію скасовано (Undo).");
        } else {
            System.out.println("Історія порожня.");
        }
    }

    public List<ConsoleCommand> getCommands() { return commands; }
}