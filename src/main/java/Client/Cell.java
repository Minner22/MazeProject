package client;

class Cell {

    boolean[] walls = {true, true, true, true}; //starting cell has all walls up. UP, RIGHT, DOWN, LEFT
    int x;
    int y;
    boolean visited = false;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isVisited() {
        return visited;
    }
}
