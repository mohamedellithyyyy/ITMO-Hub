package animals;

import interfaces.Dividable;
import entity.Animal;
import model.DivisionResult;
import model.Position;
import world.Context;

public class Horse extends Animal implements Dividable {
    private boolean isFallen = false;
    private int age;
    
    public Horse(Position position) {
        super("Лошадь", position);
        this.age = 5; // возраст по умолчанию
    }
    
    public Horse(Position position, int age) {
        super("Лошадь", position);
        this.age = age;
    }
    
    @Override
    public void act(Context ctx) {
        if (isFallen) {
            System.out.println("Лошадь лежит на земле");
        } else {
            System.out.println("Лошадь стоит");
        }
    }
    
    public void fall() {
        isFallen = true;
        System.out.println("Лошадь падает!");
    }
    
    public boolean isFallen() {
        return isFallen;
    }
    
    public int getAge() {
        return age;
    }
    
    @Override
    public DivisionResult divideHorse() {
        if (!isFallen) {
            throw new IllegalStateException("Лошадь должна быть павшей, чтобы ее разделить");
        }
        
        System.out.println("Разделяю лошадь...");
        return new DivisionResult(
            "кости - зверям",
            "мясо - птицам", 
            "кожа - гадам",
            "голова - муравьям"
        );
    }
}