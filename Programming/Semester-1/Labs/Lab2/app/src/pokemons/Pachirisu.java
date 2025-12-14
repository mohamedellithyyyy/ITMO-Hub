package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.special.ChargeBeam;
import attacks.status.ThunderWave;
import attacks.status.DoubleTeam;
import attacks.physical.Nuzzle;


public final class Pachirisu extends Pokemon {
    public Pachirisu(String name, int level) {
        super(name, level);
        setType(Type.ELECTRIC);
        setStats(60, 45, 70, 45, 90, 95);
        setMove(new ChargeBeam(), new Nuzzle(), new ThunderWave(), new DoubleTeam());
    }
}
