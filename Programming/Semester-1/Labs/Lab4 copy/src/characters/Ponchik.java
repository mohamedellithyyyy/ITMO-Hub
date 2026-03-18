package characters;

import model.Position;
import world.Context;

public class Ponchik extends Character {
    
    public Ponchik(Position position) {
        super("Пончик", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Пончик ищет что-нибудь вкусное");
    }
}