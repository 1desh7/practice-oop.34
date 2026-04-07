package Task4;

import java.util.Scanner;

public class Main {
    private View view;

    public Main(Viewable viewable) {
        this.view = viewable.getView();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.println("\nКоманди: 'g' - додати, 'v' - показати, 's' - зберегти, 'r' - відновити, 'q' - вихід");
            s = sc.next();
            switch (s.charAt(0)) {
                case 'g' -> {
                    System.out.print("Введіть число: ");
                    ((ViewResult)view).addResult(sc.nextInt());
                }
                case 'v' -> view.viewShow();
                case 's' -> {
                    try { view.viewSave(); System.out.println("Збережено."); } catch (Exception e) { e.printStackTrace(); }
                }
                case 'r' -> {
                    try { view.viewRestore(); System.out.println("Відновлено."); } catch (Exception e) { e.printStackTrace(); }
                }
            }
        } while (s.charAt(0) != 'q');
    }

    public static void main(String[] args) {
        // Демонстрація динамічного призначення (Late Binding)
        Main main = new Main(new ViewableTable());
        main.menu();
    }
}