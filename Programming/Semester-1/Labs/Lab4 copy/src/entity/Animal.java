package entity;

import model.Position;
import world.Context;

public abstract class Animal extends Entity {
    private String species;
    
    public Animal(String species, Position position) {
        super(position);
        this.species = species;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public abstract void act(Context ctx);
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{вид='" + species + "', позиция=" + getPosition() + "}";
    }
}