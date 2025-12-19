package entity;

import enums.State;
import interfaces.Attackable;
import interfaces.Movable;
import model.Position;
import world.Context;

public abstract class Character extends Entity implements Movable, Attackable {
    protected String name;
    protected State state;

    public Character(String name, Position position) {
        super(position);
        this.name = name;
        this.state = State.STANDING;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void move(enums.Direction direction) {
        int x = position.x();
        int y = position.y();
        switch (direction) {
            case LEFT -> x--;
            case RIGHT -> x++;
            case FORWARD, NORTH -> y++;
            case BACKWARD, SOUTH -> y--;
            case EAST -> x++;
            case WEST -> x--;
        }
        position = new Position(x, y, position.type());
        System.out.println(name + " moves " + direction + " to " + position);
        this.state = State.WALKING;
    }

    @Override
    public void receiveHit(Entity attacker) {
        this.state = State.FALLEN;
        System.out.println(name + " was hit by " + attacker.getClass().getSimpleName() + " and falls.");
    }

    @Override
    public abstract void act(Context context);
}
