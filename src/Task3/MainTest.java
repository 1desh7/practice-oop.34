package Task3;

import java.io.File;
import java.io.IOException;

public class MainTest {
    private static final String FNAME = "items.bin";

    public static void main(String[] args) {
        MainTest test = new MainTest();

        try {
            System.out.println("=== ЗАПУСК ТЕСТІВ ===\n");

            test.testFactory();
            System.out.println("✓ testFactory() пройшла успішно\n");

            test.testAddAndGet();
            System.out.println("✓ testAddAndGet() пройшла успішно\n");

            test.testRestore();
            System.out.println("✓ testRestore() пройшла успішно\n");

            System.out.println("=== ВСІ ТЕСТИ ПРОЙШЛИ ===");

        } catch (AssertionError e) {
            System.err.println("✗ ТЕСТ НЕ ПРОЙШОВ: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ ПОМИЛКА: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private static void cleanup() {
        File file = new File(FNAME);
        if (file.exists()) {
            file.delete();
        }
    }

    private void setUp() {
        File file = new File(FNAME);
        if (file.exists()) {
            file.delete();
        }
    }

    private void tearDown() {
        cleanup();
    }

    public void testFactory() throws AssertionError {
        setUp();
        try {
            // Перевіряємо, чи фабрика створює правильний об'єкт
            Viewable viewable = new ViewableResult();
            View view = viewable.getView();

            // Перевірка на null та тип класу
            if (view == null) {
                throw new AssertionError("view не повинна бути null");
            }
            if (!(view instanceof ViewResult)) {
                throw new AssertionError("view повинна бути екземпляром ViewResult");
            }
        } finally {
            tearDown();
        }
    }

    public void testAddAndGet() throws AssertionError {
        setUp();
        try {
            ViewResult vr = new ViewResult();

            // Додаємо тестове число
            vr.addResult(255);

            // Перевіряємо чи додалося в колекцію (items)
            if (vr.getItems().size() != 1) {
                throw new AssertionError("Кількість елементів повинна бути 1, отримано: " + vr.getItems().size());
            }

            // Перевіряємо правильність конвертації (255 у Hex це FF)
            String hex = vr.getItems().get(0).getHex();
            if (!hex.equals("FF")) {
                throw new AssertionError("Hex повинен бути 'FF', отримано: '" + hex + "'");
            }

            // Перевіряємо правильність конвертації у двійкову систему
            String binary = vr.getItems().get(0).getBinary();
            if (!binary.equals("11111111")) {
                throw new AssertionError("Binary повинен бути '11111111', отримано: '" + binary + "'");
            }

            // Перевіряємо правильність конвертації у вісімкову систему
            String octal = vr.getItems().get(0).getOctal();
            if (!octal.equals("377")) {
                throw new AssertionError("Octal повинен бути '377', отримано: '" + octal + "'");
            }
        } finally {
            tearDown();
        }
    }

    public void testRestore() throws AssertionError, Exception {
        setUp();
        try {
            ViewResult vr1 = new ViewResult();
            vr1.addResult(10);

            vr1.viewSave(); // Зберігаємо

            ViewResult vr2 = new ViewResult();
            vr2.viewRestore(); // Відновлюємо в інший об'єкт

            // Перевіряємо чи дані збігаються
            if (vr1.getItems().size() != vr2.getItems().size()) {
                throw new AssertionError("Розміри не збігаються: " + vr1.getItems().size() + " != " + vr2.getItems().size());
            }

            if (vr2.getItems().size() != 1) {
                throw new AssertionError("Кількість елементів повинна бути 1, отримано: " + vr2.getItems().size());
            }

            String binary1 = vr1.getItems().get(0).getBinary();
            String binary2 = vr2.getItems().get(0).getBinary();
            if (!binary1.equals(binary2)) {
                throw new AssertionError("Binary не збігаються: '" + binary1 + "' != '" + binary2 + "'");
            }

            if (!binary2.equals("1010")) {
                throw new AssertionError("Binary повинен бути '1010', отримано: '" + binary2 + "'");
            }

            String octal1 = vr1.getItems().get(0).getOctal();
            String octal2 = vr2.getItems().get(0).getOctal();
            if (!octal1.equals(octal2)) {
                throw new AssertionError("Octal не збігаються: '" + octal1 + "' != '" + octal2 + "'");
            }

            if (!octal2.equals("12")) {
                throw new AssertionError("Octal повинен бути '12', отримано: '" + octal2 + "'");
            }

            String hex1 = vr1.getItems().get(0).getHex();
            String hex2 = vr2.getItems().get(0).getHex();
            if (!hex1.equals(hex2)) {
                throw new AssertionError("Hex не збігаються: '" + hex1 + "' != '" + hex2 + "'");
            }

            if (!hex2.equals("A")) {
                throw new AssertionError("Hex повинен бути 'A', отримано: '" + hex2 + "'");
            }
        } finally {
            tearDown();
        }
    }
}