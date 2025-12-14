package attacks.physical;
import ru.ifmo.se.pokemon.*;

public final class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        double power = 70;
        if (att.getCondition() == Status.BURN || att.getCondition() == Status.POISON || att.getCondition() == Status.PARALYZE)
            power *= 2;
        return super.calcBaseDamage(att, def) * (power / 70);
    }

    @Override
    protected String describe() {
        return "использует Facade — наносит сильный удар, усиливается при статусе";
    }
}
