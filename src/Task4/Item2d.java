package Task4;

import java.io.Serializable;
import java.util.Objects;

public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    private int value;
    private String binary;
    private String octal;
    private String hex;

    public Item2d() {}

    public void setResults(int value, String bin, String oct, String hex) {
        this.value = value;
        this.binary = bin;
        this.octal = oct;
        this.hex = hex;
    }

    public int getValue() { return value; }
    public String getBinary() { return binary; }
    public String getOctal() { return octal; }
    public String getHex() { return hex; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item2d item2d = (Item2d) o;
        return value == item2d.value && Objects.equals(binary, item2d.binary);
    }

    @Override
    public String toString() {
        return String.format("Число: %d | Bin: %s | Oct: %s | Hex: %s", value, binary, octal, hex);
    }
}