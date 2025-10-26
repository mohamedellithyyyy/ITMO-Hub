package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.status.DoubleTeam;
import attacks.physical.CrushClaw;
import attacks.physical.Facade;

public class Sandshrew extends Pokemon {
    public Sandshrew(String name, int level) {
        super(name, level);
        setType(Type.GROUND);
        setStats(50, 75, 85, 20, 30, 40);
        setMove(new DoubleTeam(), new CrushClaw(), new Facade());
    }
}
