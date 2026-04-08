package Task6;

import java.io.*;
import java.util.*;

/** Клас для зберігання даних (Пункт 1) */
class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private int value;
    private String binary, hex;

    public Item2d() {}
    public void setResults(int v, String b, String h) {
        this.value = v; this.binary = b; this.hex = h;
    }
    public int getValue() { return value; }
    public String getBinary() { return binary; }
    public String getHex() { return hex; }
    @Override
    public String toString() { return String.format("Число: %d | Hex: %s", value, hex); }
}

/** Інтерфейс для команд (Пункт 2) */
interface Command {
    void execute();
}

/** Базовий клас для консольних команд */
abstract class ConsoleCommand implements Command {
    private char hotKey;
    public ConsoleCommand(char key) { this.hotKey = key; }
    public char getHotKey() { return hotKey; }
}