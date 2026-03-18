package magical;

import entity.MagicalObject;
import characters.Character;
import model.Position;

public class Chest extends MagicalObject {
    private Hare content;
    
    public Chest(Position position) {
        super("Золотой сундук", position);
    }
    
    public Chest(Position position, Hare content) {
        super("Золотой сундук", position);
        this.content = content;
    }
    
    @Override
    public void interact(Character character) {
        System.out.println(character.getName() + " открывает сундук");
        if (content != null) {
            System.out.println("В сундуке: " + content.getName());
            content.interact(character);
        } else {
            System.out.println("Сундук пуст!");
        }
    }
    
    public Hare getContent() {
        return content;
    }
    
    public void setContent(Hare content) {
        this.content = content;
    }
}