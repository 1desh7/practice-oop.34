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
    
    /**
     * Демонструє різні політики утримання анотацій (Retention Policies)
     */
    private static void demonstrateAnnotationRetention() {
        System.out.println("\n╔═════════════════════════════════════════════════╗");
        System.out.println("║   ДЕМОНСТРАЦІЯ RETENTION POLICIES АНОТАЦІЙ       ║");
        System.out.println("╚═════════════════════════════════════════════════╝\n");
        
        // RUNTIME Retention - видна під час виконання
        System.out.println("1. @ObserverInfo (RUNTIME Retention):");
        System.out.println("   └─ Видна під час виконання програми");
        System.out.println("   └─ Доступна через Reflection API");
        System.out.println("   └─ Використовується для аналізу спостерігачів\n");
        
        // CLASS Retention - видна під час компіляції
        System.out.println("2. @Watcher (CLASS Retention):");
        System.out.println("   └─ Видна під час компіляції та виконання");
        System.out.println("   └─ Видалюється під час завантаження класу");
        System.out.println("   └─ Використовується в IDE та IntelliSense\n");
        
        // SOURCE Retention - видна тільки у вихідному коді
        System.out.println("3. @CriticalMethod (SOURCE Retention):");
        System.out.println("   └─ Видна тільки в исходном коде");
        System.out.println("   └─ Видалюється під час компіляції");
        System.out.println("   └─ Використовується для документації розробника\n");
        
        // Практична демонстрація
        System.out.println("═══════════════════════════════════════════════════\n");
        System.out.println("АНАЛІЗ RETENTION POLICIES (Reflection API):\n");
        
        analyzeRetentionPolicies();
    }
    
    /**
     * Аналізує retention policies за допомогою Reflection API
     */
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
