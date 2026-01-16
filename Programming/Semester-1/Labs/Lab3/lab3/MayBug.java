package insects;

import entity.Entity;
import entity.Insect;
import interfaces.Aggressor;
import model.Position;
import world.Context;
import exceptions.CollisionException;

import java.util.Random;

public class MayBug extends Insect implements Aggressor {

    private final Random random = new Random();

    public MayBug(Position position) {
        super(position);
    }

    @Override
    public void attack(Entity target) throws CollisionException {
        // 50% chance to collide
        if (random.nextBoolean()) {
            throw new CollisionException("Майский жук врезался в " + target);
        }
        target.receiveHit(this);
    }

    @Override
    public void act(Context context) {
        fly();
        // Attempt to attack a random character
        context.getRandomCharacter().ifPresent(target -> {
            try {
                attack(target);
            } catch (CollisionException e) {
                System.out.println(e.getMessage());
                target.receiveHit(this);
            }
        });
        System.out.println("Майский жук улетает и скрывается вдали");
    }

    @Override
    public void receiveHit(Entity attacker) {
        System.out.println("Майский жук получил удар от " + attacker);
    }

    @Override
    public String toString() {
        return "Майский жук";
    }
}
