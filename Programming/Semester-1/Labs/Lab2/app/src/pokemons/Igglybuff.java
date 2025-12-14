package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.special.Psychic;
import attacks.special.FireBlast;
import attacks.special.test;

public class Igglybuff extends Pokemon {
    public Igglybuff(String name, int level) {
        super(name, level);
        setType(Type.NORMAL, Type.FAIRY);
        setStats(90, 30, 15, 40, 20, 15);
        setMove(new Psychic(), new FireBlast(),new test());
    }
}
