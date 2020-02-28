package Client;

import javax.swing.*;

public class Client extends JFrame {

    private static Generator generator;
    private static final int CWIDTH = 50;
    private static final int CHEIGHT = 50;
    private static final int CSIZE = 15;

    private Client() {
        initUI();
    }

    private void initUI() {
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(CWIDTH * CSIZE + 100, CHEIGHT * CSIZE + 100);
        final MazeDrawer md = new MazeDrawer(getCells(), CSIZE);
        add(md);
        md.setFocusable(true);
        setLocationRelativeTo(null);
    }

    private Cell[][] getCells() {
        return generator.cells;
    }

    public static void main(String args[]) {
        int seed = (int) (Math.random() * 10000);
        generator = new Generator(seed, CWIDTH, CHEIGHT);
        Client client = new Client();
        client.setVisible(true);
    }
}
