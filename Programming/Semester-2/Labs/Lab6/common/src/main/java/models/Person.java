package models;
import java.io.Serializable;

import java.time.LocalDate;

/**
 * The type Person.
 */
public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday ; //Поле не может быть null
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле может быть null

    /**
     * Instantiates a new Person.
     *
     * @param name       the name
     * @param birthday   the birthday
     * @param passportID the passport id
     * @param hairColor  the hair color
     * @param location   the location
     */
    public Person(String name, LocalDate birthday, String passportID, Color hairColor, Location location){
        this.name = name;
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
    }
    /* Setters */

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {return name;}

    /**
     * Get birthday local date.
     *
     * @return the local date
     */
    public LocalDate getBirthday(){return birthday;}

    /**
     * Get passport id string.
     *
     * @return the string
     */
    public String getPassportID(){return passportID;}

    /**
     * Get hair color color.
     *
     * @return the color
     */
    public Color getHairColor(){return hairColor;}

    /**
     * Get location location.
     *
     * @return the location
     */
    public Location getLocation(){return location;}

    /**
     * Set name.
     *
     * @param name the name
     */
    /* getters */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Sets passport id.
     *
     * @param passportID the passport id
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * Sets hair color.
     *
     * @param hairColor the hair color
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * To string string.
     *
     * @return the string
     */
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