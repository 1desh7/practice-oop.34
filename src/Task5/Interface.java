package Task5;

import java.io.IOException;

// Інтерфейс для відображення
interface View {
    void viewShow();
    void viewSave() throws IOException;
    void viewRestore() throws Exception;
    void addResult(int n);
}

// Інтерфейс команди
interface Command {
    void execute();
}

// Базовий клас для консольних команд
abstract class ConsoleCommand implements Command {
    private char hotKey;
    public ConsoleCommand(char key) { this.hotKey = key; }
    public char getHotKey() { return hotKey; }
}