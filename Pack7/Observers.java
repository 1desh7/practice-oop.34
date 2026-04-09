package Pack7;

import java.lang.reflect.Method;
import java.util.List;

/** Спостерігач №1: Консольний логер */
@ObserverInfo(name = "ConsoleObserver", description = "Виводить повідомлення у консоль")
class ConsoleObserver implements Observer {
    @Override
    @Watcher(name = "ConsoleLogger", action = "log")
    public void update(List<Item2d> items) {
    }
}

/** Спостерігач №2: Сортування та статистика */
@ObserverInfo(name = "StatisticObserver", description = "Обчислює статистику колекції")
class StatisticObserver implements Observer {
    @Override
    @Watcher(name = "StatisticCalculator", action = "calculate")
    public void update(List<Item2d> items) {
        if (items.isEmpty()) return;
        
        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (Item2d item : items) {
            sum += item.getValue();
            max = Math.max(max, item.getValue());
            min = Math.min(min, item.getValue());
        }
        double avg = (double) sum / items.size();
    }
}

/** Спостерігач №3: Сортування та вивід */
@ObserverInfo(name = "SortingObserver", description = "Сортує та виводить дані")
class SortingObserver implements Observer {
    @Override
    @Watcher(name = "Sorter", action = "sort")
    public void update(List<Item2d> items) {
        if (items.isEmpty()) return;
        
        List<Integer> sorted = items.stream()
                .map(Item2d::getValue)
                .sorted()
                .toList();
    }
}

/** Клас для демонстрації Reflection API */
@ObserverInfo(name = "ReflectionHelper", description = "Демонструє Reflection API")
class ReflectionHelper {
    
    @CriticalMethod(note = "Shows all annotations in runtime")
    public static void showAnnotations(Observer observer) {
        Class<?> clazz = observer.getClass();
        
        // Аналіз анотацій на рівні класу (RUNTIME)
        if (clazz.isAnnotationPresent(ObserverInfo.class)) {
            ObserverInfo info = clazz.getAnnotation(ObserverInfo.class);
        }
        
        // Аналіз методів з анотацією Watcher (CLASS retention)
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Watcher.class)) {
                Watcher w = m.getAnnotation(Watcher.class);
            }
        }
    }
    
    @CriticalMethod(note = "Shows all observers in the system")
    public static void listAllObservers(Observer... observers) {
        for (Observer obs : observers) {
            if (obs.getClass().isAnnotationPresent(ObserverInfo.class)) {
                ObserverInfo info = obs.getClass().getAnnotation(ObserverInfo.class);
            }
        }
    }
}