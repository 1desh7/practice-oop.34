package Task6;

import java.util.Vector;

/** Реалізація шаблону Worker Thread */
public class CommandQueue implements Runnable {
    private Vector<Command> tasks = new Vector<>();
    private boolean waiting = false;
    private boolean shutdown = false;

    public void put(Command task) {
        tasks.add(task);
        if (waiting) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public Command take() {
        if (tasks.isEmpty()) {
            synchronized (this) {
                waiting = true;
                try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                waiting = false;
            }
        }
        return tasks.isEmpty() ? null : tasks.remove(0);
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) { notifyAll(); }
    }

    @Override
    public void run() {
        while (!shutdown) {
            Command task = take();
            if (task != null) task.execute();
        }
    }
}