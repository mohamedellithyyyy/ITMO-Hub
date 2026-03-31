package models;

public class Location {
    private Float x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    /* @return X coordinate */
    public Float getX(){
        return x;
    }
    public void setX(Float x){
        this.x = x;
    }
    public Long getY(){
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
    public Integer getZ(){
        return z;
    }

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
    public Location(Float x, Long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}