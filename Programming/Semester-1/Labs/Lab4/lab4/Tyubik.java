package characters;

import interfaces.Painter;
import model.Position;
import world.Context;

public class Tyubik extends Character implements Painter {
    
    public Tyubik(Position position) {
        super("Тюбик", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Тюбик рисует картину");
    }
    
    @Override
    public void drawPortrait(Character target) {
        System.out.println("Тюбик рисует портрет " + target.getName());
        cleanBrush(); // Используем дефолтный метод
        Painter.sharpenBrush(); // Используем статический метод
    }
}