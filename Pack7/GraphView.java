package Pack7;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

/** Спостерігач №4: Графічне відображення */
@ObserverInfo(name = "GraphView", description = "Виводить графік у вікні Swing")
public class GraphView extends JFrame implements Observer {
    private GraphPanel graphPanel;

    public GraphView() {
        setTitle("Графічне відображення даних");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        graphPanel = new GraphPanel();
        add(graphPanel);
        setVisible(true);
    }

    @Override
    @Watcher(name = "GraphUpdater", action = "paint")
    public void update(List<Item2d> items) {
        graphPanel.setItems(items);
        graphPanel.repaint();
    }

    /** Внутрішній клас для малювання графіка */
    private static class GraphPanel extends JPanel {
        private List<Item2d> items;
        private static final int MARGIN = 50;
        private static final int BAR_WIDTH = 30;
        private static final int BAR_SPACING = 40;

        public void setItems(List<Item2d> items) {
            this.items = items;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (items == null || items.isEmpty()) {
                g2d.drawString("Немає даних для відображення", MARGIN, 30);
                return;
            }

            // Знайти максимальне значення для масштабування
            int maxValue = items.stream()
                    .mapToInt(Item2d::getValue)
                    .max()
                    .orElse(1);
            if (maxValue == 0) maxValue = 1;

            int graphWidth = getWidth() - 2 * MARGIN;
            int graphHeight = getHeight() - 2 * MARGIN;

            // Малюємо осі
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(MARGIN, MARGIN, MARGIN, getHeight() - MARGIN);
            g2d.drawLine(MARGIN, getHeight() - MARGIN, getWidth() - MARGIN, getHeight() - MARGIN);

            // Малюємо бари
            int x = MARGIN + 20;
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));

            for (Item2d item : items) {
                if (x + BAR_WIDTH > getWidth() - MARGIN) break;

                int height = (int) ((item.getValue() / (double) maxValue) * graphHeight);
                int y = getHeight() - MARGIN - height;

                // Бар
                g2d.setColor(new Color(70, 130, 180));
                g2d.fillRect(x, y, BAR_WIDTH, height);

                // Контур
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, BAR_WIDTH, height);

                // Значення над баром
                g2d.drawString(String.valueOf(item.getValue()), 
                        x + 5, y - 5);

                x += BAR_SPACING;
            }

            // Напис
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("Максимум: " + maxValue, 20, 20);
            g2d.drawString("Елементів: " + items.size(), 20, 40);
        }
    }
}