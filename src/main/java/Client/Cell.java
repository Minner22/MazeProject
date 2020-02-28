package Client;

public class Cell {

    boolean[] walls = {true, true, true, true}; //starting cell has all walls up. UP, RIGHT, DOWN, LEFT
    int x;
    int y;
    boolean visited = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkAllWallsUp() {
        return walls[0] && walls[1] && walls[2] && walls[3];
    }

    public boolean isVisited() {
        return visited;
    }
}
