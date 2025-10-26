import ru.ifmo.se.pokemon.Battle;

import pokemons.Pachirisu;
import pokemons.Sandshrew;
import pokemons.Sandslash;
import pokemons.Igglybuff;
import pokemons.Jigglypuff;
import pokemons.Wigglytuff;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        b.addAlly(new Pachirisu("Pachi", 35));
        b.addAlly(new Sandshrew("Shrew", 25));
        b.addAlly(new Sandslash("Slash", 40));

        b.addFoe(new Igglybuff("Iggy", 20));
        b.addFoe(new Jigglypuff("Jiggly", 30));
        b.addFoe(new Wigglytuff("Wiggly", 40));

        b.go();
    }
}
