package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.special.Psychic;
import attacks.special.FireBlast;
import attacks.physical.BodySlam;

public class Jigglypuff extends Igglybuff {
    public Jigglypuff(String name, int level) {
        super(name, level);
        setStats(115, 45, 20, 45, 25, 20);
        addMove(new BodySlam());
    }
}
