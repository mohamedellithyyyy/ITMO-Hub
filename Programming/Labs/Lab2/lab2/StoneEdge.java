package attacks.physical;
import ru.ifmo.se.pokemon.*;

public final class StoneEdge extends PhysicalMove {
    public StoneEdge() {
        super(Type.ROCK, 100, 80);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        return 1.5; // higher critical chance
    }

    @Override
    protected String describe() {
        return "использует Stone Edge — наносит мощный удар с высоким шансом крита";
    }
}
