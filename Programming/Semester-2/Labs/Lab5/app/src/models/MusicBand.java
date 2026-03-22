package models;

import java.time.ZonedDateTime;
public class MusicBand implements Comparable<MusicBand> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private long numberOfParticipants;
    private long albumsCount;
    private MusicGenre genre;
    private Person frontMan;
    public MusicBand(Integer id, String name, Coordinates coordinates,
                     ZonedDateTime creationDate, long numberOfParticipants,
                     long albumsCount, MusicGenre genre, Person frontMan) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.genre = genre;
        this.frontMan = frontMan;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }
    public ZonedDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(ZonedDateTime creationDate) { this.creationDate = creationDate; }
    public long getNumberOfParticipants() { return numberOfParticipants; }
    public void setNumberOfParticipants(long numberOfParticipants) { this.numberOfParticipants = numberOfParticipants; }
    public long getAlbumsCount() { return albumsCount; }
    public void setAlbumsCount(long albumsCount) { this.albumsCount = albumsCount; }
    public MusicGenre getGenre() { return genre; }
    public void setGenre(MusicGenre genre) { this.genre = genre; }
    public Person getFrontMan() { return frontMan; }
    public void setFrontMan(Person frontMan) { this.frontMan = frontMan; }
    @Override
    public int compareTo(MusicBand other) {
        return Long.compare(this.numberOfParticipants, other.numberOfParticipants);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", albumsCount=" + albumsCount +
                ", genre=" + genre +
                ", frontMan=" + frontMan +
                "}";
    }
}