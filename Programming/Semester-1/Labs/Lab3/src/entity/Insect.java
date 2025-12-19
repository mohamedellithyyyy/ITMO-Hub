package entity;

import enums.State;
import interfaces.Movable;
import model.Position;
import world.Context;

public abstract class Insect extends Entity implements Movable {
    protected int wings;
    protected State state;

    public Insect(Position position) {
        super(position);
        this.state = State.FLYING;
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
        System.out.println(this.getClass().getSimpleName() + " flies " + direction + " to " + position);
        this.state = State.FLYING;
    }

    public abstract void act(Context context);
}
