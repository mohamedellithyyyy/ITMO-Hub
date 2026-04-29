package managers;

/**
 * The type Id generator.
 */
public class IdGenerator {
    private int ID;

    /**
     * Generate id int.
     *
     * @return the int
     */
    public int generateId() {
        return ++ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }
}