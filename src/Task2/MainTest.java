package Task2;

/**
 * Клас для ручної перевірки обчислень та серіалізації.
 * Це простий спосіб зрозуміти, як працює ваш код.
 */
public class MainTest {
    public static void main(String[] args) {
        // Створюємо об'єкт класу, який виконує логіку
        Calc calc = new Calc();

        try {
            // 1. Перевіряємо обчислення для кількох чисел
            int[] numbers = {10, 16, 255};

            System.out.println("--- ЕТАП 1: ПЕРЕВІРКА ОБЧИСЛЕНЬ ---");
            for (int n : numbers) {
                calc.init(n); // виконуємо перетворення
                System.out.println("Число: " + n);
                System.out.println(calc.getResult()); // виводимо результат на екран
                System.out.println("-------------------------");
            }

            // 2. Перевіряємо збереження (Серіалізація)
            System.out.println("\n--- ЕТАП 2: ЗБЕРЕЖЕННЯ ТА ВІДНОВЛЕННЯ ---");
            calc.init(1024);
            System.out.println("Готуємо до збереження число 1024...");
            calc.save();
            System.out.println("Файл збережено успішно.");

            // 3. Псуємо поточні дані, щоб перевірити відновлення
            calc.init(0);
            System.out.println("Зараз у пам'яті дані занулено.");

            // 4. Відновлюємо дані з файлу (Десеріалізація)
            calc.restore();
            System.out.println("Дані відновлено з файлу:");
            System.out.println(calc.getResult());

            // Якщо на екрані знову з'явилося 1024 — все працює ідеально!

        } catch (Exception e) {
            // Якщо виникне будь-яка помилка, ми про це дізнаємось
            System.err.println("СТАЛАСЯ ПОМИЛКА ТЕСТУВАННЯ:");
            e.printStackTrace();
        }
    }
}