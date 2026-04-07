package Task4;

/**
 * Клас для виведення результатів у вигляді таблиці.
 * Демонструє Overriding (перевизначення) та Overloading (перевантаження).
 */
public class ViewTable extends ViewResult {
    private int width;

    public ViewTable() { this(15); } // Overloading конструктора

    public ViewTable(int width) { this.width = width; }

    @Override // Overriding методу
    public void viewBody() {
        String format = "| %-" + width + "s | %-" + width + "s | %-" + width + "s | %-" + width + "s |\n";
        String line = "-".repeat((width + 3) * 4 + 1);

        System.out.println(line);
        System.out.printf(format, "Число", "Двійкове", "Вісімкове", "Hex");
        System.out.println(line);

        for (Item2d item : items) {
            System.out.printf(format, item.getValue(), item.getBinary(), item.getOctal(), item.getHex());
        }
        System.out.println(line);
    }
}