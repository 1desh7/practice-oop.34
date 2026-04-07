package Task4;

import java.io.*;
import java.util.ArrayList;

public class ViewResult implements View {
    private static final String FNAME = "items.bin";
    protected ArrayList<Item2d> items = new ArrayList<>();

    public ArrayList<Item2d> getItems() { return items; }

    public void addResult(int n) {
        Item2d item = new Item2d();
        item.setResults(n, Integer.toBinaryString(n), Integer.toOctalString(n), Integer.toHexString(n).toUpperCase());
        items.add(item);
    }

    @Override
    public void viewHeader() { System.out.println("--- Результати ---"); }

    @Override
    public void viewBody() {
        for (Item2d item : items) System.out.println(item);
    }

    @Override
    public void viewFooter() { System.out.println("--- Кінець ---"); }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME))) {
            os.writeObject(items);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws Exception {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME))) {
            items = (ArrayList<Item2d>) is.readObject();
        }
    }
}