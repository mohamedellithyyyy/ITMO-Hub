package world;

import characters.Neznaika;
import entity.Entity;
import insects.MayBug;
import exceptions.CollisionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private final List<Entity> entities = new ArrayList<>();
    private final Context context = new Context();
    private final Logger logger = new Logger();
    private final Random random = new Random();

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void run() {
        logger.log("World is running...");

        Neznaika neznaika = null;
        MayBug bug = null;

        for (Entity e : entities) {
            logger.log(e.toString());
            if (e instanceof Neznaika n) neznaika = n;
            if (e instanceof MayBug m) bug = m;
        }

        // Scenario simulation
        if (neznaika != null && bug != null) {
            logger.log("MayBug is flying...");

            boolean hitOccurred = false;
            try {
                // MayBug tries to attack Neznaika
                bug.attack(neznaika);
            } catch (CollisionException e) {
                hitOccurred = true;
                logger.log(e.getMessage());
            }

            // Neznaika actions after potential attack
            try {
                neznaika.standUp();
                neznaika.lookAround();
            } catch (RuntimeException re) {
                // wrap unexpected runtime issues
                logger.log("Runtime problem during Neznaika actions: " + re.getMessage());
            }

            // Demonstrate and handle an unchecked exception example
            if (random.nextInt(10) == 0) {
                try {
                    throw new exceptions.RuntimeCollisionException("Unexpected air turbulence");
                } catch (exceptions.RuntimeCollisionException rce) {
                    logger.log("Handled unchecked exception: " + rce.getMessage());
                }
            }

            // Build and print a coherent narrative (full story)
            StringBuilder story = new StringBuilder();
            story.append("In particular, Neznaika became famous after one story.\n");
            story.append("One day he was walking through the city and wandered into the field.\n");
            story.append("There was not a soul around.\n");
            story.append("At that moment a MayBug was flying nearby.\n");

            if (hitOccurred) {
                story.append("The beetle blindly flew into Neznaika and struck him on the back of the head.\n");
                story.append("Neznaika tumbled to the ground. The beetle flew away at once and disappeared in the distance.\n");
                story.append("Neznaika jumped up, looked around to see who had hit him — but there was nobody.\n");
            } else {
                story.append("The beetle passed by and missed Neznaika. It buzzed away into the distance.\n");
                story.append("Neznaika glanced around but saw no one; the field remained empty.\n");
            }

            logger.log("--- Full Story ---");
            for (String line : story.toString().split("\\n")) {
                logger.log(line);
            }
        }
    }
}
