package client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void getNextRandValTest() {
        int seed = (int) (Math.random() * 10000);
        Generator gen1 = new Generator(seed, 50, 50);
        Generator gen2 = new Generator(seed, 50, 50);
        for (int i = 0; i < seed; i++) {
            assertEquals(gen1.getNextRandVal(), gen2.getNextRandVal());
        }
    }
}