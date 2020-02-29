package Client;

import javax.swing.*;
import java.awt.EventQueue;

public class Client extends JFrame {

    private static Generator generator;
    private static final int COL = 20;
    private static final int ROW = 10;
    private static final int CSIZE = 15;

    private Client() {
        int seed = (int) (Math.random() * 10000);
        generator = new Generator(seed, COL, ROW);
        initUI();
    }

    private void initUI() {
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(COL * CSIZE + 100, ROW * CSIZE + 100);
        final MazeDrawer md = new MazeDrawer(getCells(), CSIZE);
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
