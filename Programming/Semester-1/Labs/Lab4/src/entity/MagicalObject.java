package entity;

import characters.Character;

public abstract class MagicalObject extends Entity {
    private String name;
    
    public MagicalObject(String name, model.Position position) {
        super(position);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public abstract void interact(Character character);
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{имя='" + name + "', позиция=" + getPosition() + "}";
    }
}