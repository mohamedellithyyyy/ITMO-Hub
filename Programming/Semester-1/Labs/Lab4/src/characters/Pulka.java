package characters;

import model.Position;
import world.Context;

public class Pulka extends Character {
    
    public Pulka(Position position) {
        super("Пулька", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Пулька охотится со своей собакой");
    }
}