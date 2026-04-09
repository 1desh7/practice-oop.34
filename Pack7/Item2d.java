package Pack7;

import java.io.Serializable;
import java.util.Objects;

/** Елемент колекції з підтримкою серіалізації */
public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private int value;
    private String binary, hex, octal;

    public Item2d() { this(0, "0", "0", "0"); }

    public Item2d(int value, String binary, String hex) {
        this(value, binary, hex, Integer.toOctalString(value));
    }

    public Item2d(int value, String binary, String hex, String octal) {
        this.value = value;
        this.binary = binary;
        this.hex = hex;
        this.octal = octal;
    }

    public void setResults(int value, String binary, String hex) {
        this.value = value;
        this.binary = binary;
        this.hex = hex;
        this.octal = Integer.toOctalString(value);
    }

    public void setResults(int value, String binary, String octal, String hex) {
        this.value = value;
        this.binary = binary;
        this.octal = octal;
        this.hex = hex;
    }

    public int getValue() { return value; }
    public String getBinary() { return binary; }
    public String getHex() { return hex; }
    public String getOctal() { return octal; }

    @Override
    public String toString() {
        return String.format("Value: %d | Bin: %s | Oct: %s | Hex: %s", value, binary, octal, hex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item2d)) return false;
        Item2d item = (Item2d) o;
        return value == item.value && Objects.equals(binary, item.binary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, binary, hex, octal);
    }
}