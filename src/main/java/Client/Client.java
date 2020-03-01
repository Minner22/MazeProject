package Client;

import javax.swing.*;
import java.awt.EventQueue;

public class Client extends JFrame {

    private static Generator generator;
    private static final int COL = 25;
    private static final int ROW = 25;
    private static final int CSIZE = 10;
    int seed;

    private Client() {
        seed = (int) (Math.random() * 100000);
        generator = new Generator(seed, COL, ROW);
        initUI();
    }

    private void initUI() {
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(COL * CSIZE + 150, ROW * CSIZE + 75);
        final MazeDrawer md = new MazeDrawer(getCells(), CSIZE, seed);
        add(md);
        //addKeyListener(md);
        setContentPane(md);
        md.setFocusable(true);
        setLocationRelativeTo(null);
    }

    private Cell[][] getCells() {
        return generator.cells;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Client client = new Client();
                client.setVisible(true);
            }
        });

    }
}
