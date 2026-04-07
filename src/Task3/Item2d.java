package Task3;

import java.io.Serializable;

/** Зберігає дані про одне число */
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
    public String toString() {
        return "Число: " + value + " | Bin: " + binary + " | Oct: " + octal + " | Hex: " + hex;
    }
}