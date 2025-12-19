package characters;

import entity.Character;
import model.Position;
import enums.State;
import enums.Direction;
import world.Context;

public class Human extends Character {

    public Human(String name, Position position) {
        super(name, position);
    }

    public void walk(Direction direction) {
        setState(State.WALKING);
        System.out.println(name + " walks " + direction);
    }

    public void fall() {
        setState(State.FALLEN);
        System.out.println(name + " falls");
    }

    public void standUp() {
        setState(State.STANDING);
        System.out.println(name + " stands up");
    }

    public void lookAround() {
        setState(State.LOOKING_AROUND);
        System.out.println(name + " looks around");
    }

    @Override
    public void act(Context context) {
        walk(Direction.FORWARD);
    }
}
