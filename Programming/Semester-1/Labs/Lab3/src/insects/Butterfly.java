package insects;

import entity.Insect;
import model.Position;
import world.Context;

public class Butterfly extends Insect {

    public Butterfly(Position position) {
        super(position);
    }

    @Override
    public void act(Context context) {
        System.out.println("Butterfly is flying");
    }
}
