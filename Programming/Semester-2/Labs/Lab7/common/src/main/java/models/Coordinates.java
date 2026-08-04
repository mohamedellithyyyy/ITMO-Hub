package models;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x;
    private Long y; // max 433, not null

    public Coordinates(double x, Long y) {
        this.x = x;
        setY(y);
    }

    public double getX() { return x; }
    public Long getY() { return y; }

    public void setX(double x) { this.x = x; }

    public void setY(Long y) {
        if (y == null) throw new IllegalArgumentException("y cannot be null");
        if (y > 433)   throw new IllegalArgumentException("y must be <= 433");
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}
