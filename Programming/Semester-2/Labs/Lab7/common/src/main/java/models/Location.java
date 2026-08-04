package models;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long x;
    private Double y;
    private Long z;
    private String name;

    public Location() {}

    public Location(Long x, Double y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    // Legacy constructor for ConsoleReader
    public Location(Float x, Long y, Integer z) {
        this(x == null ? null : x.longValue(),
             y == null ? null : y.doubleValue(),
             z == null ? null : z.longValue(),
             null);
    }

    public Long getX() { return x; }
    public void setX(Long x) { this.x = x; }

    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public Long getZ() { return z; }
    public void setZ(Long z) { this.z = z; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + ", z=" + z + ", name='" + name + "'}";
    }
}
