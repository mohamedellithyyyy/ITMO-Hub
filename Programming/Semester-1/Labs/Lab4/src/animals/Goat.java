package animals;

import entity.Animal;
import model.Position;
import world.Context;

public class Goat extends Animal {
    
    public Goat(Position position) {
        super("Коза", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Золотая коза бегает вокруг");
    }
    
    public void runAway() {
        System.out.println("Коза быстро убегает!");
    }
}