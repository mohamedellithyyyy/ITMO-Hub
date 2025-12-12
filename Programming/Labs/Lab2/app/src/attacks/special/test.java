package attacks.special;
import ru.ifmo.se.pokemon.*;

public class test extends SpecialMove {
    public test(){
        super(Type.NORMAL,20,100);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        super.applyOppDamage(def, damage);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        if (Math.random() <= 0.7)
            p.setMod(Stat.SPECIAL_ATTACK, 1);
    }
    @Override
    protected String describe(){
        return("Test | Special Attack");
    }
}