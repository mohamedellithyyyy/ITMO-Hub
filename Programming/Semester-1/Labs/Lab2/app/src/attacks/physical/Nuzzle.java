package attacks.physical;
import ru.ifmo.se.pokemon.*;

public final class Nuzzle extends PhysicalMove {
    public Nuzzle() {
        super(Type.ELECTRIC, 20, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "использует Nuzzle — мило трётся и парализует противника";
    }
}
