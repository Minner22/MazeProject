package Client;

import java.util.Random;

public class Generator {

    int val = 0;
    Random rng;
    public Generator(int seed) {
        rng = new Random(seed);
    }

    public int getNextRandVal() {
        val = rng.nextInt();
        return val;
    }
}
