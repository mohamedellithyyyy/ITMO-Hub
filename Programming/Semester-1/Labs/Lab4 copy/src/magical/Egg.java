package magical;

import entity.MagicalObject;
import characters.Character;
import model.Position;

public class Egg extends MagicalObject {
    private Seed content;
    
    public Egg(Position position) {
        super("Волшебное яйцо", position);
    }
    
    public Egg(Position position, Seed content) {
        super("Волшебное яйцо", position);
        this.content = content;
    }
    
    @Override
    public void interact(Character character) {
        System.out.println(character.getName() + " разбивает яйцо");
        if (content != null) {
            System.out.println("В яйце: " + content.getName());
            content.interact(character);
        }
    }
    
    public Seed getContent() {
        return content;
    }
    
    public void setContent(Seed content) {
        this.content = content;
    }
}