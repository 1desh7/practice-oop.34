package Task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ViewResult {
    protected ArrayList<Item2d> items = new ArrayList<>();
    public List<Item2d> getItems() { return items; }
    public void addResult(int n) {
        Item2d item = new Item2d();
        item.setResults(n, Integer.toBinaryString(n), Integer.toHexString(n).toUpperCase());
        items.add(item);
    }
    public void viewShow() {
        System.out.println("--- Results ---");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }
}