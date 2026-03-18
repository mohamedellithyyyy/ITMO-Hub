package characters;

import model.Position;
import world.Context;

public class Princess extends Character {
    private boolean captured = false;
    private boolean rescued = false;
    
    public Princess(String name, Position position) {
        super(name, position);
    }
    
    @Override
    public void act(Context ctx) {
        if (captured) {
            System.out.println(getName() + " заточена в хрустальной горе!");
        } else {
            System.out.println(getName() + " ждет спасения");
        }
    }
    
    public boolean isCaptured() {
        return captured;
    }
    
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }
    
    public boolean isRescued() {
        return rescued;
    }
    
    public void setRescued(boolean rescued) {
        this.rescued = rescued;
    }
    
    public void giveAdvice() {
        System.out.println("Царевна дает совет: 'Найди семечко в яйце!'");
    }
}