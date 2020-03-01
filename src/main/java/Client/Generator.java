package Client;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static java.lang.Math.abs;

class Generator {

    private Random rng;

    private Stack<Cell> cellStack = new Stack<Cell>();
    Cell[][] cells;
    private int row, col;


    Generator(int seed, int col, int row) {
        rng = new Random(seed);
        this.row = row;
        this.col = col;
        cells = new Cell[col][row];
        cellInit();
        randomDFSMaze();
    }

    private void cellInit() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                cells[i][j] = new Cell(i, j);

            }
        }
    }

    private void randomDFSMaze() {

        ArrayList<Cell> neighboursNotVisited;
        Cell currCell = cells[0][0];
        currCell.visited = true;
        cellStack.push(currCell);

        while (!cellStack.isEmpty()) {
            currCell = cellStack.pop();
            neighboursNotVisited = checkNeighbours(currCell);
            if (!neighboursNotVisited.isEmpty()) {
                cellStack.push(currCell);
                Cell nextCell = neighboursNotVisited.get(getNextRandVal() % neighboursNotVisited.size());
                remWalls(currCell, nextCell);
                nextCell.visited = true;
                cellStack.push(nextCell);
            }

        }
    }

    private void remWalls(Cell currCell, Cell tmpCell) {
        int x = currCell.x - tmpCell.x;
        int y = currCell.y - tmpCell.y;

        if (x == 1) {
            currCell.walls[3] = false;
            tmpCell.walls[1] = false;
        } else if (x == -1) {
            currCell.walls[1] = false;
            tmpCell.walls[3] = false;
        }
        if (y == 1) {
            currCell.walls[0] = false;
            tmpCell.walls[2] = false;
        } else if (y == -1) {
            currCell.walls[2] = false;
            tmpCell.walls[0] = false;
        }
    }


    private ArrayList<Cell> checkNeighbours(Cell current) {
        ArrayList<Cell> list = new ArrayList<Cell>();
        int x = current.x;
        int y = current.y;

        if (x - 1 >= 0 && !cells[x - 1][y].isVisited()) { //top
            list.add(cells[x - 1][y]);
        }
        if (y + 1 < cells[0].length && !cells[x][y + 1].isVisited()) {  //right
            list.add(cells[x][y + 1]);
        }
        if (x + 1 < cells.length && !cells[x + 1][y].isVisited()) {   //bottom
            list.add(cells[x + 1][y]);
        }
        if (y - 1 >= 0 && !cells[x][y - 1].isVisited()) {  //left
            list.add(cells[x][y - 1]);
        }
        return list;
    }

    int getNextRandVal() {
        return abs(rng.nextInt());
    }
}
