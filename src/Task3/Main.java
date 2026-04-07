package Task3;

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
                case 'g':
                    System.out.print("Введіть ціле число: ");
                    if (sc.hasNextInt()) {
                        ((ViewResult)view).addResult(sc.nextInt());
                    } else {
                        System.out.println("Помилка: введіть число!");
                        sc.next();
                    }
                    break;
                case 'v':
                    view.viewShow();
                    break;
                case 's':
                    try { view.viewSave(); System.out.println("Збережено у файл."); }
                    catch (Exception e) { System.out.println("Помилка збереження: " + e); }
                    break;
                case 'r':
                    try { view.viewRestore(); System.out.println("Відновлено з файлу."); }
                    catch (Exception e) { System.out.println("Помилка відновлення: " + e); }
                    break;
            }
        } while (s.charAt(0) != 'q');
    }

    public static void main(String[] args) {
        Main main = new Main(new ViewableResult());
        main.menu();
    }
}