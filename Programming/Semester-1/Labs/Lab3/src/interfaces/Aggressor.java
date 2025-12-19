package interfaces;

import entity.Entity;
import exceptions.CollisionException;

public interface Aggressor {
    void attack(Entity target) throws CollisionException;
}
