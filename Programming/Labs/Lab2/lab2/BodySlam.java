package attacks.physical;
import ru.ifmo.se.pokemon.*;

public final class BodySlam extends PhysicalMove {
    public BodySlam() {
        super(Type.NORMAL, 85, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3)
            Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "использует Body Slam — бросается телом и может парализовать";
    }
}
