package world;

import enums.LocationType;

public class Location {
    private String name;
    private LocationType type;
    
    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public LocationType getType() {
        return type;
    }
    
    public void setType(LocationType type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Локация{имя='" + name + "', тип=" + type + "}";
    }
}