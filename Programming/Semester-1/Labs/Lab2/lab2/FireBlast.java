package attacks.special;
import ru.ifmo.se.pokemon.*;

public final class FireBlast extends SpecialMove {
    public FireBlast() {
        super(Type.FIRE, 110, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1)
            Effect.burn(p);
    }

    @Override
    protected String describe() {
        return "использует Fire Blast — мощное пламя может поджечь противника";
    }
}
