package Client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void getNextRandValTest() {
        int seed = (int) (Math.random() * 10000);
        Generator gen1 = new Generator(seed);
        Generator gen2 = new Generator(seed);
        for (int i = 0; i < seed; i++) {
            assertEquals(gen1.getNextRandVal(), gen2.getNextRandVal());
        }
    }
}