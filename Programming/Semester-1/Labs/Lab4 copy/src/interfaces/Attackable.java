package interfaces;

import entity.Entity;

public interface Attackable {
    void receiveHit(Entity attacker);
}