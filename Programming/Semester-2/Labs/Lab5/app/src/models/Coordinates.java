package models;

public class Coordinates {
    private double x;
    private Long y; //Максимальное значение поля: 433, Поле не может быть null
    public Coordinates(double x, Long y) {
        setX(x);
        setY(y);
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }

    public void setY(long y) {
        if (y == null)  throw new IllegalArgumentException("Coordinates.y cannot be null");
        if (y > 433)    throw new IllegalArgumentException("Coordinates.y must be <= 433, got: " + y);
        this.y = y;
    }
    @Override
    public String toString(){
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}