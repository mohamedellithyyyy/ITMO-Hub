package entity;

import model.Position;
import world.Context;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
    private final UUID id = UUID.randomUUID();
    protected Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void act(Context context) throws Exception;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity entity)) return false;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " at " + position;
    }
}
