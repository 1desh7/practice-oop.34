package Pack7;

import java.lang.annotation.*;
import java.util.*;

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
