package characters;

import model.Position;
import world.Context;

public class DoctorPilulkin extends Character {
    
    public DoctorPilulkin(Position position) {
        super("Доктор Пилюлькин", position);
    }
    
    @Override
    public void act(Context ctx) {
        System.out.println("Доктор Пилюлькин готовит лекарства");
    }
    
    public void treatPatient(Character patient) {
        System.out.println("Доктор Пилюлькин лечит " + patient.getName());
    }
}