package entity;

import model.Position;
import world.Context;

public abstract class Insect extends Entity {

    protected Insect(Position position) {
        super(position);
    }

    public void fly() {
        System.out.println(this + " летит");
    }

    public abstract void act(Context context);
}
