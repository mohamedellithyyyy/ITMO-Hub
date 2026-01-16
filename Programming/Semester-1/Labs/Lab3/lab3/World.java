package world;

import entity.Entity;
import entity.Character;
import entity.Insect;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Entity> entities = new ArrayList<>();
    private final Context context;

    public World(Context context) {
        this.context = context;
        context.setEntities(entities);
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void run() {
        System.out.println("Сцена начинается...\n");

        for (Entity entity : entities) {
            if (entity instanceof Character c) {
                c.act(context);
            }
            if (entity instanceof Insect i) {
                i.act(context);
            }
        }

        System.out.println("\nСцена завершена.");
    }
}
