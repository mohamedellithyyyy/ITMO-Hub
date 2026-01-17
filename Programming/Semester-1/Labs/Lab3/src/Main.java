import world.World;
import world.Context;
import characters.Neznaika;
import insects.MayBug;
import model.Position;
import enums.LocationType;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        World world = new World(context);
        Random random = new Random();
        Neznaika neznaika = new Neznaika(
            new Position(random.nextInt(10), random.nextInt(10), LocationType.FIELD)
        );

        MayBug bug = new MayBug(
            new Position(random.nextInt(10), random.nextInt(10), LocationType.SKY)
        );

        world.add(neznaika);
        world.add(bug);

        world.run();
    }
}
