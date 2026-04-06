package Task2;

import java.io.Serializable;

/** * Зберігає вхідне ціле число та його представлення у різних системах числення.
 */
public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;

    private int value;
    private String binary;
    private String octal;
    private String hex;

    public Item2d() {
        value = 0;
        binary = "0";
        octal = "0";
        hex = "0";
    }

    // Геттери та сеттери
    public void setResults(int value, String bin, String oct, String hex) {
        this.value = value;
        this.binary = bin;
        this.octal = oct;
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "Число: " + value +
                "\nДвійкове: " + binary +
                "\nВісімкове: " + octal +
                "\nШістнадцяткове: " + hex;
    }
}