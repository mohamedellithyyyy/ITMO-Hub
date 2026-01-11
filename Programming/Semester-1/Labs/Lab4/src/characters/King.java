package characters;

import model.Position;
import world.Context;

public class King extends Character {
    
    public King(String name, Position position) {
        super(name, position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println(getName() + " правит царством");
    }
    
    public void giveOrder(String order) {
        System.out.println("Царь повелевает: " + order);
    }
    
    public void hireCharacter(Character character) {
        System.out.println("Царь нанимает " + character.getName());
    }
}