package attacks.special;
import ru.ifmo.se.pokemon.*;

public final class DreamEater extends SpecialMove {
    public DreamEater() {
        super(Type.PSYCHIC, 100, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        super.applyOppDamage(def, damage);
    }

    @Override
    protected void applySelfDamage(Pokemon p, double damage) {
        // Heals user by half of damage dealt
        p.setMod(Stat.HP, (int) -(damage / 2));
    }

    @Override
    protected String describe() {
        return "использует Dream Eater — поедает сны противника и восстанавливает здоровье";
    }
}
