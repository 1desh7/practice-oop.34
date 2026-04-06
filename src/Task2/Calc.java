package Task2;

import java.io.*;

public class Calc {
    private static final String FNAME = "Item2d.bin";
    private Item2d result = new Item2d();

    public Item2d getResult() {
        return result;
    }

    /** * оце виконує перетворення та зберігає результат.
     */
    public void init(int x) {
        String bin = Integer.toBinaryString(x);
        String oct = Integer.toOctalString(x);
        String hex = Integer.toHexString(x).toUpperCase();
        result.setResults(x, bin, oct, hex);
    }

    public void show() {
        System.out.println(result);
    }

    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d) is.readObject();
        is.close();
    }
}