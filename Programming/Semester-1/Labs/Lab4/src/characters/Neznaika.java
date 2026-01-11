package characters;

import enums.State;
import model.Position;
import world.Context;

public class Neznaika extends Character {
    
    public Neznaika(Position position) {
        super("Незнайка", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Незнайка бродит вокруг, рассказывая небылицы");
        setState(State.WALKING);
    }
    
    public void getConfused() {
        setState(State.CONFUSED);
        System.out.println("Незнайка запутался!");
    }
}