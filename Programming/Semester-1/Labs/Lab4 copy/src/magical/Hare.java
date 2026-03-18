package magical;

import entity.MagicalObject;
import characters.Character;
import model.Position;

public class Hare extends MagicalObject {
    private Duck content;
    
    public Hare(Position position) {
        super("Волшебный заяц", position);
    }
    
    public Hare(Position position, Duck content) {
        super("Волшебный заяц", position);
        this.content = content;
    }
    
    @Override
    public void interact(Character character) {
        System.out.println(character.getName() + " ловит зайца");
        if (content != null) {
            System.out.println("В зайце: " + content.getName());
            content.interact(character);
        }
    }
    
    public Duck getContent() {
        return content;
    }
    
    public void setContent(Duck content) {
        this.content = content;
    }
}