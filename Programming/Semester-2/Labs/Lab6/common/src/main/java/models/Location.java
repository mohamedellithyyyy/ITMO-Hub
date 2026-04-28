package models;
import java.io.Serializable;
/**
 * The type Location.
 */
public class Location implements Serializable{
    private Float x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private Integer z; //Поле не может быть null

    /**
     * Get x float.
     *
     * @return the float
     */
    /* @return X coordinate */
    public Float getX(){
        return x;
    }

    /**
     * Set x.
     *
     * @param x the x
     */
    public void setX(Float x){
        this.x = x;
    }

    /**
     * Get y long.
     *
     * @return the long
     */
    public Long getY(){
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * Get z integer.
     *
     * @return the integer
     */
    public Integer getZ(){
        return z;
    }

    /**
     * Sets z.
     *
     * @param z the z
     */
    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * Instantiates a new Location.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Location(Float x, Long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}