package characters;

import interfaces.Transformable;
import enums.State;
import model.Position;
import world.Context;

public class IvanTsarevich extends Character implements Transformable {
    private boolean isTransformed = false;
    private String currentForm = "человек";
    
    public IvanTsarevich(Position position) {
        super("Иван-царевич", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Иван-царевич отправляется в путь");
        setState(State.WALKING);
    }
    
    @Override
    public void transformToFalcon() {
        if (!isTransformed) {
            isTransformed = true;
            currentForm = "ясный сокол";
            setState(State.TRANSFORMED);
            System.out.println("Иван-царевич превращается в ясного сокола!");
        }
    }
    
    @Override
    public void transformToAnt() {
        if (!isTransformed) {
            isTransformed = true;
            currentForm = "муравей";
            setState(State.TRANSFORMED);
            System.out.println("Иван-царевич превращается в муравья!");
        }
    }
    
    @Override
    public void revertTransformation() {
        if (isTransformed) {
            isTransformed = false;
            currentForm = "человек";
            setState(State.STANDING);
            System.out.println("Иван-царевич возвращается в человеческий облик");
        }
    }
    
    public boolean isTransformed() {
        return isTransformed;
    }
    
    public String getCurrentForm() {
        return currentForm;
    }
    
    public void fightDragon(int dragonHeads) {
        System.out.println("Иван-царевич сражается с " + dragonHeads + "-главым змеем!");
    }
}