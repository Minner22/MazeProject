package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeDrawer extends JPanel implements ActionListener {

    private final int DELAY = 150;
    private Timer timer;
    Cell[][] cells;
    int cellSize;

    public MazeDrawer(Cell[][] cells, int cellSize){
        this.cells = cells;
        this.cellSize = cellSize;
        //initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public Timer getTimer() {
        return timer;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int x = cells[i][j].x * cellSize+10;
                int y = cells[i][j].y * cellSize+10;

                if (cells[i][j].walls[0]) {
                    g2d.drawLine(x, y, x, y + cellSize);
                }
                if (cells[i][j].walls[1]) {
                    g2d.drawLine(x , y + cellSize, x + cellSize, y + cellSize);
                }
                if (cells[i][j].walls[2]) {
                    g2d.drawLine(x + cellSize, y + cellSize, x + cellSize, y);
                }
                if (cells[i][j].walls[3]) {
                    g2d.drawLine(x + cellSize, y, x , y);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
