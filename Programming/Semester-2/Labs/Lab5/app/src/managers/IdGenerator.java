package managers;
public class IdGenerator {
    private int ID;
    public int generateId() {
        return ++ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}