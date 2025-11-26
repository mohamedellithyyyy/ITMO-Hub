package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.special.Psychic;
import attacks.special.FireBlast;
import attacks.physical.BodySlam;
import attacks.special.DreamEater;

public final class Wigglytuff extends Jigglypuff {
    public Wigglytuff(String name, int level) {
        super(name, level);
        setStats(140, 70, 45, 85, 50, 45);
        addMove(new DreamEater());
    }
}
