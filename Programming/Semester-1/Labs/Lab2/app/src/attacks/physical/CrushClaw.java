package attacks.physical;
import ru.ifmo.se.pokemon.*;

public final class CrushClaw extends PhysicalMove {
    public CrushClaw() {
        super(Type.NORMAL, 75, 95);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.5)
            p.setMod(Stat.DEFENSE, -1);
    }

    @Override
    protected String describe() {
        return "использует Crush Claw — наносит удар и может понизить защиту";
    }
}
