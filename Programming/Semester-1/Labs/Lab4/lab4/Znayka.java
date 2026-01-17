package characters;

import model.Position;
import world.Context;

public class Znayka extends Character {
    
    public Znayka(Position position) {
        super("Знайка", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Знайка читает книги и дает советы");
    }
    
    public void giveWisdom() {
        System.out.println("Знайка делится своими знаниями");
    }
}