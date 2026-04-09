package Pack7;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDemo {
    
    public static void main(String[] args) {
        // Демонстрація анотацій та їх retention policies
        demonstrateAnnotationRetention();
        
        // Запуск основної програми
        Main.main(args);
    }
    
    private static void analyzeRetentionPolicies() {
        Class<?> consoleClass = ConsoleObserver.class;
        
        // 1. Перевірка ObserverInfo (RUNTIME)
        System.out.println("📌 Клас ConsoleObserver:");
        if (consoleClass.isAnnotationPresent(ObserverInfo.class)) {
            ObserverInfo info = consoleClass.getAnnotation(ObserverInfo.class);
            System.out.println("   ✓ @ObserverInfo присутня (RUNTIME policy)");
            System.out.println("     └─ Ім'я: " + info.name());
            System.out.println("     └─ Опис: " + info.description() + "\n");
        }
        
        // 2. Перевірка Watcher (CLASS)
        System.out.println("📍 Методи з @Watcher (CLASS policy):");
        for (Method method : consoleClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Watcher.class)) {
                Watcher w = method.getAnnotation(Watcher.class);
                System.out.println("   ✓ Метод: " + method.getName());
                System.out.println("     └─ Watcher ім'я: " + w.name());
                System.out.println("     └─ Action: " + w.action() + "\n");
            }
        }
        
        // 3. CriticalMethod не буде видна при завантаженні класу (SOURCE policy)
        System.out.println("📄 @CriticalMethod (SOURCE policy):");
        System.out.println("   ℹ Не видна під час виконання (видалена при компіляції)");
        System.out.println("   └─ Служить для документування коду\n");
    }
}
