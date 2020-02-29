package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MazeDrawer extends JPanel implements ActionListener, KeyListener {

    private Cell[][] cells;
    private int cellSize;
    private Cell currentCell;
    private ArrayList<Cell> visitedCells = new ArrayList<Cell>();

    MazeDrawer(Cell[][] cells, int cellSize){
        this.cells = cells;
        this.cellSize = cellSize;
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
// TODO: 29.02.2020  poprawic rysowanie linii up: x, y, x+1, y; co sie wiaze z poprawieniem algorytmu bo sie pierdoli OK!
                if (cells[i][j].walls[0]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x, y, x + cellSize, y);
                }
                if (cells[i][j].walls[1]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x + cellSize, y , x + cellSize, y + cellSize);
                }
                if (cells[i][j].walls[2]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x + cellSize, y + cellSize, x , y + cellSize);
                }
                if (cells[i][j].walls[3]) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(x, y + cellSize, x, y);
                }
            }
        }
    }

    private void fillRect(Cell cell, Graphics g) {
        int x = cell.x * cellSize + 10;
        int y = cell.y * cellSize + 10;
        Graphics2D g2d = (Graphics2D) g;
//System.out.println("cell length:" + cells.length + " cels[0].length:"+cells[0].length);
        g2d.setPaint(Color.red);
        if (cell.x == cells.length-1 && cell.y == cells[0].length-1){
            g2d.setPaint(Color.yellow);
        }
        if (cell.equals(currentCell)) {
            g2d.setPaint(Color.MAGENTA);
        }
        g2d.fill(new Rectangle2D.Double(x, y, cellSize, cellSize));
        g2d.drawLine(30,30,100,50);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cell c: visitedCells) {
            fillRect(c, g);
        }
        fillRect(currentCell, g);

        /*if (pressed == 1) {
            String a = "";
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    //System.out.println(i + "-"+cells[i][j].x+" " + j+"-"+cells[i][j].y);
                    fillRect(cells[i][j], g);


                    a+= i+":"+cells[i][j].x+"-"+j+":"+cells[i][j].y+" ";
                }
                a+="\n";
            }
            System.out.println(a);
        }*/
        doDrawing(g);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Cell nextCell;

        //System.out.println("key pressed: " + key + " x:" + currentCell.x + " y:" + currentCell.y);
        if (key == KeyEvent.VK_UP) {
            nextCell = cells[currentCell.x][currentCell.y - 1];
            System.out.println("Up " + currentCell.walls[0]+ " "+ currentCell.walls[0]+ "|" + currentCell.walls[1]+"|" + currentCell.walls[2]+"|" + currentCell.walls[3]+ " x:" + currentCell.x + " y:" + currentCell.y);
            if (currentCell.y - 1 >= 0 && currentCell.walls[0]) {
                currentCell = nextCell;
                visitedCells.add(currentCell);
            }
            repaint();
        } else if (key == KeyEvent.VK_RIGHT) {
            nextCell = cells[currentCell.x + 1][currentCell.y];
            System.out.println("right " + currentCell.walls[0]+ "|" + currentCell.walls[1]+"|" + currentCell.walls[2]+"|" + currentCell.walls[3]+ " x:" + currentCell.x + " y:" + currentCell.y);
            if (currentCell.x + 1 < cells.length && currentCell.walls[1]) {
                currentCell = nextCell;
                visitedCells.add(currentCell);

            }
            repaint();
        } else if (key == KeyEvent.VK_DOWN) {
            nextCell = cells[currentCell.x][currentCell.y + 1];
            System.out.println("Down " + currentCell.walls[2] + " "+ currentCell.walls[0]+ "|" + currentCell.walls[1]+"|" + currentCell.walls[2]+"|" + currentCell.walls[3]+ " x:" + currentCell.x + " y:" + currentCell.y);
            if (currentCell.y + 1 < cells[0].length && currentCell.walls[2]) {
                currentCell = nextCell;
                visitedCells.add(currentCell);
            }
            repaint();
        } else if (key == KeyEvent.VK_LEFT) {
            nextCell = cells[currentCell.x - 1][currentCell.y];
            System.out.println("left " + currentCell.walls[3] + " "+ currentCell.walls[0]+ "|" + currentCell.walls[1]+"|" + currentCell.walls[2]+"|" + currentCell.walls[3]+" x:" + currentCell.x + " y:" + currentCell.y);
            if (currentCell.x - 1 >= 0 && currentCell.walls[3]) {
                currentCell = nextCell;
                visitedCells.add(currentCell);
            }
            repaint();
        }

    }

    public void keyReleased(KeyEvent e) {

    }
}
