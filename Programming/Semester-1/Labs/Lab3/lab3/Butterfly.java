package insects;

import entity.Insect;
import entity.Entity;
import model.Position;
import world.Context;

public class Butterfly extends Insect {

    public Butterfly(Position position) {
        super(position);
    }

    @Override
    public void act(Context context) {
        fly();
        System.out.println("Бабочка спокойно летает");
    }

    @Override
    public void receiveHit(Entity attacker) {
        System.out.println("Бабочка получила удар от " + attacker);
    }

    @Override
    public String toString() {
        return "Бабочка";
    }
}
