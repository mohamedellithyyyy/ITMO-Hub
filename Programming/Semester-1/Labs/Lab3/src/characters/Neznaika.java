package characters;

import entity.Character;
import enums.State;
import model.Position;
import world.Context;

public class Neznaika extends Character {

    public Neznaika(Position position) {
        super("Neznaika", position);
        this.state = State.WALKING;
    }

    @Override
    public void act(Context ctx) {
        switch (state) {
            case WALKING -> System.out.println("Neznaika is walking around the field.");
            case FALLEN -> System.out.println("Neznaika is lying on the ground.");
            case STANDING -> System.out.println("Neznaika is standing.");
            case LOOKING_AROUND -> System.out.println("Neznaika is looking around.");
            default -> System.out.println("Neznaika is confused.");
        }
    }

    public void fall() {
        state = State.FALLEN;
        System.out.println("Neznaika falls to the ground.");
    }

    public void standUp() {
        state = State.STANDING;
        System.out.println("Neznaika stands up.");
    }

    public void lookAround() {
        state = State.LOOKING_AROUND;
        System.out.println("Neznaika looks around but sees nobody.");
    }
}
