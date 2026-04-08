package Task6;

import java.util.List;

public class MaxCommand implements Command {
    private List<Item2d> items;
    private int result;
    private int progress = 0;

    public MaxCommand(List<Item2d> items) { this.items = items; }

    @Override
    public void execute() {
        System.out.println("MaxCommand started...");
        result = Integer.MIN_VALUE;
        for (int i = 0; i < items.size(); i++) {
            int val = items.get(i).getValue();
            if (val > result) result = val;

            // Імітація тривалих обчислень та прогресу
            progress = (i + 1) * 100 / items.size();
            try { Thread.sleep(10); } catch (InterruptedException e) {}
            if (progress % 20 == 0) System.out.println("MaxCommand: " + progress + "%");
        }
        System.out.println("MaxCommand done. Max value: " + result);
    }
}