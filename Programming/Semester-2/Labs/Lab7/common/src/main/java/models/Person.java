package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;
    private LocalDate birthday;
    private String passportID;
    private String hairColor;
    private double height;
    private String eyeColor;
    private String nationality;
    private Location location;

    public Person() {}

    public Person(String name, LocalDate birthday, String passportID,
                  String hairColor, double height, String eyeColor,
                  String nationality, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.height = height;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.location = location;
    }

    // Backward-compatible constructor used by ConsoleReader
    public Person(String name, LocalDate birthday, String passportID,
                  Color hairColorEnum, Location location) {
        this(name, birthday, passportID,
             hairColorEnum == null ? null : hairColorEnum.name(),
             0.0, null, null, location);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getPassportID() { return passportID; }
    public void setPassportID(String passportID) { this.passportID = passportID; }

    public String getHairColor() { return hairColor; }
    public void setHairColor(String hairColor) { this.hairColor = hairColor; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public String getEyeColor() { return eyeColor; }
    public void setEyeColor(String eyeColor) { this.eyeColor = eyeColor; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', passportID='" + passportID + "'}";
    }
}
