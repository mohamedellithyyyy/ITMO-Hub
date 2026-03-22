package models;

import java.time.LocalDate;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday ; //Поле не может быть null
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, LocalDate birthday, String passportID, Color hairColor, Location location){
        this.name = name;
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
    }
    /* Setters */
    /** @return Name */
    public String getName() {return name;}
    public LocalDate getBirthday(){return birthday;}
    public String getPassportID(){return passportID;}
    public Color getHairColor(){return hairColor;}
    public Location getLocation(){return location;}
    /* getters */
    public void setName(String name){
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", passportID='" + passportID + '\'' +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }
}