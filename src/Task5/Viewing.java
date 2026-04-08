package Task5;

import java.io.*;
import java.util.ArrayList;

class ViewResult implements View {
    protected ArrayList<Item2d> items = new ArrayList<>();

    public void addResult(int n) {
        Item2d item = new Item2d();
        item.setResults(n, Integer.toBinaryString(n), Integer.toOctalString(n), Integer.toHexString(n).toUpperCase());
        items.add(item);
    }

    public void viewShow() {
        for (Item2d i : items) System.out.println(i);
    }

    public void viewSave() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            os.writeObject(items);
        }
    }

    public void viewRestore() throws Exception {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("data.bin"))) {
            items = (ArrayList<Item2d>) is.readObject();
        }
    }
}

class ViewTable extends ViewResult {
    @Override
    public void viewShow() {
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-10s |\n", "Value", "Binary", "Hex");
        System.out.println("--------------------------------------------------");
        for (Item2d i : items) {
            System.out.printf("| %-10d | %-15s | %-10s |\n", i.getValue(), i.getBinary(), i.getHex());
        }
        System.out.println("--------------------------------------------------");
    }
}