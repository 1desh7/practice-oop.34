/**
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │                      PACK7 - АРХІТЕКТУРНА ДІАГРАМА                      │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * 
 * 1. OBSERVER PATTERN STRUCTURE
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *                          ┌──────────────┐
 *                          │  Observable  │
 *                          └──────────────┘
 *                              │
 *                   ┌──────────┴──────────┐
 *                   │                     │
 *                   ▼                     ▼
 *            ┌────────────────┐  ┌───────────────────┐
 *            │ ViewResult     │  │ addObserver()     │
 *            │ (Observable)   │  │ notifyObservers() │
 *            └────────────────┘  └───────────────────┘
 *                   │
 *                   │ contains
 *                   ▼
 *            ┌────────────────┐
 *            │ List<Item2d>   │
 *            │   [42, 10, 5]  │
 *            └────────────────┘
 * 
 *                    addResult(42) ───► notifyObservers()
 *                                              │
 *                    ┌─────────────────────────┼──────────────────────┐
 *                    │                         │                      │
 *                    ▼                         ▼                      ▼
 *            ┌──────────────────┐     ┌──────────────────┐    ┌─────────────┐
 *            │ConsoleObserver   │     │StatisticObserver │    │SortingObs...│
 *            │    update()      │     │    update()      │    │  update()   │
 *            │                  │     │                  │    │             │
 *            │Output: Console   │     │Calc: Sum, Max    │    │Sort: Values │
 *            └──────────────────┘     └──────────────────┘    └─────────────┘
 *                                                                    │
 *                                                                    ▼
 *                                                            ┌──────────────────┐
 *                                                            │  GraphView       │
 *                                                            │  (JFrame + Swing)│
 *                                                            │  update()        │
 *                                                            │  repaint()       │
 *                                                            └──────────────────┘
 * 
 * 
 * 2. ANNOTATIONS - RETENTION POLICIES
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * SOURCE Retention:
 *  ┌──────────────────────────────────┐
 *  │ Source Code                      │
 *  │ @CriticalMethod                  │  ← Видна только тут
 *  │ public static void method()      │
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (javac)
 *  ┌──────────────────────────────────┐
 *  │ Bytecode (.class)                │
 *  │ (без анотації)                   │  ← Видалена при компіляції
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (java)
 *  ┌──────────────────────────────────┐
 *  │ Runtime (памятью)                │
 *  │ (без анотації)                   │  ← Не видна
 *  └──────────────────────────────────┘
 * 
 * 
 * CLASS Retention (За замовчуванням):
 *  ┌──────────────────────────────────┐
 *  │ Source Code                      │
 *  │ @Watcher(...)                    │  ← Видна
 *  │ public void update()             │
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (javac)
 *  ┌──────────────────────────────────┐
 *  │ Bytecode (.class)                │
 *  │ @Watcher(...)                    │  ← Видна
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (java)
 *  ┌──────────────────────────────────┐
 *  │ Runtime (памятью)                │
 *  │ (без анотації)                   │  ← Видалена при завантаженні
 *  └──────────────────────────────────┘
 * 
 * 
 * RUNTIME Retention:
 *  ┌──────────────────────────────────┐
 *  │ Source Code                      │
 *  │ @ObserverInfo(...)               │  ← Видна
 *  │ class ConsoleObserver {}         │
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (javac)
 *  ┌──────────────────────────────────┐
 *  │ Bytecode (.class)                │
 *  │ @ObserverInfo(...)               │  ← Видна
 *  └──────────────────────────────────┘
 *           │
 *           ▼  (java)
 *  ┌──────────────────────────────────┐
 *  │ Runtime (памятью)                │
 *  │ @ObserverInfo(...)               │  ← ВИДНА ← доступна для Reflection
 *  └──────────────────────────────────┘
 * 
 * 
 * 3. REFLECTION API - ANALYSIS FLOW
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *  ConsoleObserver object
 *         │
 *         ▼
 *  observer.getClass()  ──────┐
 *                              ▼
 *                      Class<?> clazz
 *                              │
 *          ┌─────────────────────┼──────────────────────┐
 *          │                     │                      │
 *          ▼                     ▼                      ▼
 *  isAnnotationPresent()  getAnnotation()  getDeclaredMethods()
 *          │                     │                      │
 *          ▼                     ▼                      ▼
 *       boolean             Annotation              Method[]
 *        (true)             (metadata)              (functions)
 *                                                     │
 *                                                     ▼
 *                                        method.isAnnotationPresent()
 *                                                     │
 *                                                     ▼
 *                                             method.getAnnotation()
 * 
 * 
 * 4. EXECUTION FLOW
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *  ┌─────────────────────────────────────┐
 *  │ main() запущена                     │
 *  └─────────────────────────────────────┘
 *           │
 *           ▼
 *  ┌─────────────────────────────────────┐
 *  │ Демонстрація Retention Policies     │
 *  │ (консольний вивід)                  │
 *  └─────────────────────────────────────┘
 *           │
 *           ▼
 *  ┌─────────────────────────────────────┐
 *  │ Reflection API:                     │
 *  │ listAllObservers()                  │
 *  │ showAnnotations() for each observer │
 *  └─────────────────────────────────────┘
 *           │
 *           ▼
 *  ┌─────────────────────────────────────┐
 *  │ inputDataViaGUI()                   │
 *  │ Відкрити JOptionPane                │
 *  └─────────────────────────────────────┘
 *           │
 *      ┌────┴─────────────────────────┐
 *      │                              │
 *      ▼                              ▼
 *   User Input                    User Cancel
 *      │                              │
 *      ▼                              ▼
 *   Parse Integer              System.exit(0)
 *      │
 *      ▼
 *   model.addResult(value)
 *      │
 *      ▼
 *   notifyObservers(items)
 *      │
 *  ┌──┴─────────┬──────────────┬──────────────┐
 *  ▼            ▼              ▼              ▼
 *  ConsoleObs StatisticObs SortingObs     GraphView
 *  update()   update()       update()       update()
 *  println()  calculate()    sort()         repaint()
 *      │            │            │              │
 *      └────────────┴────────────┴──────────────┘
 *           │
 *           ▼
 *   Вернуться в inputDataViaGUI()
 *           │
 *           ▼
 *   Показать новий JOptionPane
 *           │
 *      ┌────┴─────────────────────────┐
 *      │                              │
 *      ▼                              ▼
 *   Repeat                        Exit
 * 
 * 
 * 5. GUI HIERARCHY
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *      GraphView
 *    (extends JFrame)
 *         │
 *         ▼
 *    setVisible(true)
 *         │
 *         ├─ Title: "Графічне відображення даних"
 *         ├─ Size: 700 x 500
 *         └─ Add: GraphPanel
 *              │
 *              ▼
 *         GraphPanel
 *       (extends JPanel)
 *              │
 *              ├─ paintComponent(Graphics g)
 *              │  ├─ Graphics2D g2d = (Graphics2D) g
 *              │  ├─ setRenderingHint() - anti-aliasing
 *              │  ├─ Малюємо осі
 *              │  ├─ Розраховуємо масштаб
 *              │  ├─ Малюємо стовпці
 *              │  └─ Виводимо значення
 *              │
 *              └─ setItems(List<Item2d>)
 *                 └─ items = items
 * 
 *  Під час update():
 *  GraphView.update() ──► graphPanel.setItems() ──► graphPanel.repaint()
 *                                                           │
 *                                                           ▼
 *                                                  paintComponent() викликається
 *                                                           │
 *                                                           ▼
 *                                                    Екран оновлюється
 * 
 * 
 * 6. DATA FLOW IN ITEM2D CONVERSION
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *  Input: 42 (decimal)
 *         │
 *         ├─► Integer.toBinaryString(42) = "101010"
 *         ├─► Integer.toOctalString(42) = "52"
 *         └─► Integer.toHexString(42).toUpperCase() = "2A"
 *         │
 *         ▼
 *  Item2d {
 *      value: 42,
 *      binary: "101010",
 *      octal: "52",
 *      hex: "2A"
 *  }
 * 
 * 
 * 7. STATISTICS CALCULATION
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *  Items: [42, 10, 5]
 *      │
 *      ├─► Sum = 42 + 10 + 5 = 57
 *      ├─► Count = 3
 *      ├─► Avg = 57 / 3 = 19.00
 *      ├─► Min = 5
 *      └─► Max = 42
 *      │
 *      ▼
 *  Output: "Сума: 57 | Середнє: 19.00 | Мін: 5 | Макс: 42"
 * 
 * 
 * 8. SORTING FLOW
 * ════════════════════════════════════════════════════════════════════════════
 * 
 *  Items: [42, 10, 5, 20]
 *      │
 *      ▼ items.stream()
 *      │  .map(Item2d::getValue)
 *      │  .sorted()
 *      │  .toList()
 *      ▼
 *  Output: [5, 10, 20, 42]
 * 
 */
public class ArchitectureDiagram {
    // This file serves as visual documentation of the Pack7 architecture
}
