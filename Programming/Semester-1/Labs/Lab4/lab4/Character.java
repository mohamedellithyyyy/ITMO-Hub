package characters;

import entity.Entity;
import enums.State;
import model.Position;
import world.Context;
import java.util.Objects;

public abstract class Character extends Entity {
    private String name;
    private State state;
    
    public Character(String name, Position position) {
        super(position);
        this.name = name;
        this.state = State.STANDING;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public abstract void act(Context ctx);
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{имя='" + name + "', состояние=" + state + 
               ", позиция=" + getPosition() + "}";
    }
}