package Pack7;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель даних - Observable (спостережуваний об'єкт)
 * Спадкує з базового класу Observable та утримує колекцію Item2d
 * При додаванні нових елементів сповіщає всіх зареєстрованих спостерігачів
 */
@ObserverInfo(name = "ViewResult", description = "Модель даних з Observable паттерном")
public class ViewResult extends Observable {
    private List<Item2d> items = new ArrayList<>();

    /**
     * Додає новий результат до колекції та сповіщає спостерігачів
     * @param n числове значення для додавання
     */
    @Watcher(name = "DataAdder", action = "add")
    public void addResult(int n) {
        Item2d item = new Item2d(
                n,
                Integer.toBinaryString(n),
                Integer.toHexString(n).toUpperCase()
        );
        items.add(item);
        
        // Автоматичне сповіщення всіх спостерігачів
        notifyObservers(items);
    }

    public List<Item2d> getItems() {
        return items;
    }

    public void clearItems() {
        items.clear();
        notifyObservers(items);
    }
}