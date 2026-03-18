package animals;

import entity.Animal;
import model.Position;
import world.Context;

public class Falcon extends Animal {
    
    public Falcon(Position position) {
        super("Сокол", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Сокол парит высоко в небе");
    }
    
    public void speakToCharacter(characters.Character character) {
        System.out.println("Сокол говорит " + character.getName() + 
                          ": 'Спасибо, что разделил лошадь!'");
    }
    
    public void grantTransformationPower(characters.Character character) {
        System.out.println("Сокол дарует силу превращения " + character.getName());
    }
}