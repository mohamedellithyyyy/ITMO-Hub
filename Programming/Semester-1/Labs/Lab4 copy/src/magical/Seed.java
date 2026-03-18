package magical;

import entity.MagicalObject;
import characters.Character;
import model.Position;

public class Seed extends MagicalObject {
    private boolean isBurning = false;
    
    public Seed(Position position) {
        super("Волшебное семечко", position);
    }
    
    @Override
    public void interact(Character character) {
        System.out.println(character.getName() + " берет волшебное семечко");
    }
    
    public void burn() {
        isBurning = true;
        System.out.println("Волшебное семечко начинает гореть!");
    }
    
    public boolean isBurning() {
        return isBurning;
    }
    
    public void meltCrystalMountain(CrystalMountain mountain) {
        if (isBurning) {
            System.out.println("Горящее семечко плавит хрустальную гору!");
            mountain.melt();
        } else {
            System.out.println("Семечко должно гореть, чтобы расплавить гору");
        }
    }
}