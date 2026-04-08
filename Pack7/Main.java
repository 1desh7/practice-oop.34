package Pack7;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Створюємо модель (Observable)
        ViewResult model = new ViewResult();

        // Створюємо та реєструємо спостерігачів
        ConsoleObserver consoleObs = new ConsoleObserver();
        StatisticObserver statisticObs = new StatisticObserver();
        SortingObserver sortingObs = new SortingObserver();
        GraphView graphView = new GraphView();

        model.addObserver(consoleObs);
        model.addObserver(statisticObs);
        model.addObserver(sortingObs);
        model.addObserver(graphView);

        // Діалоговий інтерфейс з користувачем
        inputDataViaGUI(model);
    }

    /**
     * Діалоговий інтерфейс для введення даних через вікно Swing
     * Вікно залишається активним під час введення
     */
    private static void inputDataViaGUI(ViewResult model) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Введіть число для графіка:\n(або скасуйте для виходу)",
                    "Введення даних",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Користувач натиснув Cancel або закрив вікно
            if (input == null) {
                break;
            }

            try {
                int value = Integer.parseInt(input.trim());
                if (value < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Будь ласка, введіть додатне число!",
                            "Помилка",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                model.addResult(value);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Невірний формат! Введіть ціле число.",
                        "Помилка введення",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        System.exit(0);
    }
}