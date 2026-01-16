package entity;

import enums.State;
import model.Position;
import world.Context;

public abstract class Character extends Entity {

    protected State state = State.STANDING;

    protected Character(Position position) {
        super(position);
    }

    protected void changeState(State newState) {
        state = newState;
        System.out.println(this + " теперь в состоянии " + state);
    }

    @Override
    public void receiveHit(Entity attacker) {
        changeState(State.FALLEN);
        System.out.println(this + " был сбит с ног объектом " + attacker);
    }

    public abstract void act(Context context);
}
