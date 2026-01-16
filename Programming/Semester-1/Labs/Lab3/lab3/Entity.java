package entity;

import model.Position;
import interfaces.Movable;
import interfaces.Attackable;
import enums.Direction;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity implements Movable, Attackable {

    protected final UUID id = UUID.randomUUID();
    protected Position position;

    protected Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Direction dir) {
        System.out.println(this + " движется в направлении " + dir);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
