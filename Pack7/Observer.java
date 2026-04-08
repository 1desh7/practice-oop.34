package Pack7;

import java.lang.annotation.*;
import java.util.*;

/** Анотація для спостерігачів (RUNTIME retention) */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@interface ObserverInfo {
    String name() default "Unknown";
    String description() default "Observer";
}

/** Анотація для методів, що реагують на зміни (CLASS retention) */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Documented
@interface Watcher {
    String name() default "DefaultWatcher";
    String action() default "update";
}

/** Анотація для критичних методів (SOURCE retention - видалюється компілятором) */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@Documented
@interface CriticalMethod {
    String note() default "";
}

/** Інтерфейс спостерігача */
public interface Observer {
    void update(List<Item2d> items);
}

/** Базовий клас для об'єктів, за якими спостерігають */
class Observable {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer o) { 
        observers.add(o); 
    }
    
    protected void notifyObservers(List<Item2d> items) {
        for (Observer o : observers) {
            o.update(items);
        }
    }
}