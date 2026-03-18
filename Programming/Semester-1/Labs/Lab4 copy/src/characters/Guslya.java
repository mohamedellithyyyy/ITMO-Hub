package characters;

import interfaces.Musician;
import model.Position;
import utils.Instrument;
import world.Context;

public class Guslya extends Character implements Musician {
    
    public Guslya(Position position) {
        super("Гусля", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Гусля готовится играть музыку");
    }
    
    @Override
    public void playMusic(Instrument instrument) {
        System.out.println("Гусля играет на " + instrument.getName());
        instrument.play();
    }
}