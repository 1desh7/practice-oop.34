package Task5;

import java.io.Serializable;
import java.util.Objects;

public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private int value;
    private String binary, octal, hex;

    public Item2d() {}
    public void setResults(int v, String b, String o, String h) {
        this.value = v; this.binary = b; this.octal = o; this.hex = h;
    }
    public int getValue() { return value; }
    public String getBinary() { return binary; }
    public String getOctal() { return octal; }
    public String getHex() { return hex; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item2d)) return false;
        Item2d item = (Item2d) o;
        return value == item.value && 
               Objects.equals(binary, item.binary) && 
               Objects.equals(octal, item.octal) && 
               Objects.equals(hex, item.hex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, binary, octal, hex);
    }

    @Override
    public String toString() {
        return String.format("Число: %d | Bin: %s | Oct: %s | Hex: %s", value, binary, octal, hex);
    }
}