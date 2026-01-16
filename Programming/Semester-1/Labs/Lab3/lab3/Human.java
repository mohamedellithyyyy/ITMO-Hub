package characters;

import entity.Character;
import enums.Gender;
import enums.State;
import model.Position;
import world.Context;

public class Human extends Character {

    protected Gender gender = Gender.MALE;

    public Human(Position position) {
        super(position);
    }

    protected void standUp() {
        changeState(State.STANDING);
        System.out.println(this + " поднялся с земли");
    }

    protected void lookAround() {
        changeState(State.LOOKING_AROUND);
        System.out.println(this + " оглядывается по сторонам");
    }

    @Override
    public void act(Context context) {
        lookAround();
    }
}
