package models;
import java.io.Serializable;

/**
 * The type Coordinates.
 */
public class Coordinates implements Serializable{
    private double x;
    private Long y; //Максимальное значение поля: 433, Поле не может быть null

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(double x, Long y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() { return x; }

    /**
     * Gets y.
     *
     * @return the y
     */
    public Long getY() { return y; }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) { this.x = x; }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(Long y) {
        if (y == null) throw new IllegalArgumentException("Coordinates.y cannot be null");
        if (y > 433)   throw new IllegalArgumentException("Coordinates.y must be <= 433");
        this.y = y;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString(){
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}