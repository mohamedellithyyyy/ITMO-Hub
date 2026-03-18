package magical;

import entity.MagicalObject;
import characters.Character;
import model.Position;

public class Duck extends MagicalObject {
    private Egg content;
    
    public Duck(Position position) {
        super("Волшебная утка", position);
    }
    
    public Duck(Position position, Egg content) {
        super("Волшебная утка", position);
        this.content = content;
    }
    
    @Override
    public void interact(Character character) {
        System.out.println(character.getName() + " ловит утку");
        if (content != null) {
            System.out.println("В утке: " + content.getName());
            content.interact(character);
        }
    }
    
    public Egg getContent() {
        return content;
    }
    
    public void setContent(Egg content) {
        this.content = content;
    }
}