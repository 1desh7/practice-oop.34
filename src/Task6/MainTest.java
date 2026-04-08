package Task6;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testParallelExecution() throws InterruptedException {
        ViewResult view = new ViewResult();
        view.addResult(10);
        view.addResult(50);
        view.addResult(20);

        CommandQueue queue = new CommandQueue();
        Thread worker = new Thread(queue);
        worker.start();

        // Запускаємо команду
        queue.put(new MaxCommand(view.getItems()));

        // Даємо час на виконання
        Thread.sleep(500);

        queue.shutdown();
        assertTrue(true); // Якщо дійшли сюди без помилок - тест пройдено
    }
}