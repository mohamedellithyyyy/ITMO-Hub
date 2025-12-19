import characters.Neznaika;
import insects.MayBug;
import model.Position;
import enums.LocationType;
import world.World;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        Random random = new Random();

        // Random starting positions
        Neznaika neznaika = new Neznaika(new Position(random.nextInt(10), random.nextInt(10), LocationType.FIELD));
        MayBug bug = new MayBug(new Position(random.nextInt(10), random.nextInt(10), LocationType.SKY));

        world.add(neznaika);
        world.add(bug);

        world.run();
    }
}
