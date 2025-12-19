package world;

import java.util.Random;

public class Context {
    private final Random random = new Random();

    public Random getRandom() {
        return random;
    }
}
