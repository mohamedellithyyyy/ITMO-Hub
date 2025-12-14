package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.status.DoubleTeam;
import attacks.physical.CrushClaw;
import attacks.physical.Facade;
import attacks.physical.StoneEdge;

public final class Sandslash extends Sandshrew {
    public Sandslash(String name, int level) {
        super(name, level);
        setType(Type.GROUND);
        setStats(75, 100, 110, 45, 55, 65);
        addMove(new StoneEdge());
    }
}
