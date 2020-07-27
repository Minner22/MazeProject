package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MazeDrawer extends JPanel implements ActionListener, KeyListener {

    private Cell[][] cells;
    private int cellSize, seed;
    private Cell currentCell;
    private ArrayList<Cell> visitedCells = new ArrayList<Cell>();
    private int moveCounter = 0;
    long tStart = 0;

    MazeDrawer(Cell[][] cells, int cellSize, int seed) {
        this.cells = cells;
        this.cellSize = cellSize;
        this.seed = seed;
        currentCell = cells[0][0];
        visitedCells.add(currentCell);
        addKeyListener(this);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        int x, y;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                x = cells[i][j].x * cellSize + 10;
                y = cells[i][j].y * cellSize + 10;

                if (cells[i][j].walls[0]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x, y, x + cellSize, y);
                }
                if (cells[i][j].walls[1]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                }
                if (cells[i][j].walls[2]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x + cellSize, y + cellSize, x, y + cellSize);
                }
                if (cells[i][j].walls[3]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x, y + cellSize, x, y);
                }
            }
        }
        String msg = "Moves made: " + moveCounter;
        g2d.drawString(msg, cells.length * cellSize + 20, 10);
    }

    private void fillRect(Cell cell, Graphics g) {
        int x = cell.x * cellSize + 10;
        int y = cell.y * cellSize + 10;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.red);

        if (cell.x == cells.length - 1 && cell.y == cells[0].length - 1) {
            g2d.setPaint(Color.yellow);
        }
        if (cell.equals(currentCell)) {
            g2d.setPaint(Color.MAGENTA);
        }

        g2d.fill(new Rectangle2D.Double(x, y, cellSize, cellSize));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fillRect(cells[cells.length - 1][cells[0].length - 1], g);
        for (Cell c : visitedCells) {
            fillRect(c, g);
        }
        fillRect(currentCell, g);
        doDrawing(g);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int x = currentCell.x;
        int y = currentCell.y;
        boolean[] cWalls = currentCell.walls;
        if (tStart == 0) {
            tStart = System.currentTimeMillis();
        }

        if (key == KeyEvent.VK_UP) {
            if (!cWalls[0]) {
                currentCell = cells[x][y - 1];
                visitedCells.add(currentCell);
                moveCounter++;
            }
            repaint();
        } else if (key == KeyEvent.VK_RIGHT) {
            if (!cWalls[1]) {
                currentCell = cells[x + 1][y];
                visitedCells.add(currentCell);
                moveCounter++;
            }
            repaint();
        } else if (key == KeyEvent.VK_DOWN) {
            if (!cWalls[2]) {
                currentCell = cells[x][y + 1];
                visitedCells.add(currentCell);
                moveCounter++;
            }
            repaint();
        } else if (key == KeyEvent.VK_LEFT) {
            if (!cWalls[3]) {
                currentCell = cells[x - 1][y];
                visitedCells.add(currentCell);
                moveCounter++;
            }
            repaint();
        }
        if (currentCell.x == cells.length - 1 && currentCell.y == cells[0].length - 1) {
            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedTime = tDelta / 1000.0;
            String msg = "Congratulations, you've finished maze created with seed: " + seed
                    + " and with size of: " + cells.length + "x" + cells[0].length + " in " + moveCounter
                    + " moves and " + elapsedTime + " seconds.";
            String title = "Congratuations!";
            infoBox(msg, title);
        }

    }

    private static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void keyReleased(KeyEvent e) {

    }
}
