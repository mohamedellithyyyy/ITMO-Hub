package insects;

import entity.Insect;
import entity.Entity;
import enums.State;
import exceptions.CollisionException;
import interfaces.Aggressor;
import model.Position;
import world.Context;

import java.util.Random;

public class MayBug extends Insect implements Aggressor {

    private final Random random = new Random();

    public MayBug(Position position) {
        super(position);
        this.wings = 2;
        this.state = State.FLYING;
    }

    @Override
    public void act(Context ctx) {
        System.out.println("MayBug is buzzing in the sky.");
    }

    @Override
    public void attack(Entity target) throws CollisionException {
        // 50% chance to hit
        if (random.nextBoolean()) {
            if (target instanceof interfaces.Attackable a) {
                a.receiveHit(this);
            }
            throw new CollisionException("MayBug collides with " + target.getClass().getSimpleName());
        } else {
            System.out.println("MayBug misses " + target.getClass().getSimpleName());
        }
    }
}
