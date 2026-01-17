package entity;

import model.Position;
import java.util.UUID;
import java.util.Objects;

public abstract class Entity {
    private final UUID id;
    private Position position;
    
    public Entity(Position position) {
        this.id = UUID.randomUUID();
        this.position = position;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public UUID getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + id + ", позиция=" + position + "}";
    }
}