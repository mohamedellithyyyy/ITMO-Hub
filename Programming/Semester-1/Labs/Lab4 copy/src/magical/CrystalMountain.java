package magical;

import entity.Entity;
import model.Position;
import java.util.ArrayList;
import java.util.List;

public class CrystalMountain extends Entity {
    private boolean isMelted = false;
    private List<Entity> trappedEntities = new ArrayList<>();
    
    public CrystalMountain(Position position) {
        super(position);
    }
    
    public void melt() {
        if (!isMelted) {
            isMelted = true;
            System.out.println("Хрустальная гора тает!");
            releaseTrappedEntities();
        }
    }
    
    public boolean isMelted() {
        return isMelted;
    }
    
    public void trapEntity(Entity entity) {
        trappedEntities.add(entity);
        System.out.println(entity.getClass().getSimpleName() + " заточен в хрустальной горе!");
    }
    
    private void releaseTrappedEntities() {
        System.out.println("Освобождаю " + trappedEntities.size() + " заточенных существ");
        trappedEntities.clear();
    }
    
    public List<Entity> getTrappedEntities() {
        return new ArrayList<>(trappedEntities);
    }
}