package characters;

import model.Position;
import world.Context;
import enums.State;
import entity.Entity;

public class Neznaika extends Human {

    public Neznaika(Position position) {
        super(position);
    }

    @Override
    public void act(Context context) {
        changeState(State.WALKING);
        System.out.println("Незнайка гуляет по окрестностям");

        // Random chance to look around
        if (Math.random() < 0.5) {
            lookAround();
        }
    }

    public void fall() {
        changeState(State.FALLEN);
        System.out.println("Незнайка падает на землю");
    }

    public void standUp() {
        changeState(State.STANDING);
        System.out.println("Незнайка встает");
    }

    public void lookAround() {
        changeState(State.LOOKING_AROUND);
        System.out.println("Незнайка оглядывается по сторонам, но никого не видит");
    }

    @Override
    public void receiveHit(Entity attacker) {
        fall();
        System.out.println("Незнайка был сбит объектом " + attacker);
    }

    @Override
    public String toString() {
        return "Незнайка";
    }
}
