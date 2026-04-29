package models;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * The type Music band.
 */
public class MusicBand implements Comparable<MusicBand>, Serializable {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private long numberOfParticipants;
    private long albumsCount;
    private MusicGenre genre;
    private Person frontMan;
    private static final long serialVersionUID = 1L;


    /**
     * Instantiates a new Music band.
     *
     * @param id                   the id
     * @param name                 the name
     * @param coordinates          the coordinates
     * @param creationDate         the creation date
     * @param numberOfParticipants the number of participants
     * @param albumsCount          the albums count
     * @param genre                the genre
     * @param frontMan             the front man
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() { return coordinates; }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public ZonedDateTime getCreationDate() { return creationDate; }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(ZonedDateTime creationDate) { this.creationDate = creationDate; }

    /**
     * Gets number of participants.
     *
     * @return the number of participants
     */
    public long getNumberOfParticipants() { return numberOfParticipants; }

    /**
     * Sets number of participants.
     *
     * @param numberOfParticipants the number of participants
     */
    public void setNumberOfParticipants(long numberOfParticipants) { this.numberOfParticipants = numberOfParticipants; }

    /**
     * Gets albums count.
     *
     * @return the albums count
     */
    public long getAlbumsCount() { return albumsCount; }

    /**
     * Sets albums count.
     *
     * @param albumsCount the albums count
     */
    public void setAlbumsCount(long albumsCount) { this.albumsCount = albumsCount; }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public MusicGenre getGenre() { return genre; }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(MusicGenre genre) { this.genre = genre; }

    /**
     * Gets front man.
     *
     * @return the front man
     */
    public Person getFrontMan() { return frontMan; }

    /**
     * Sets front man.
     *
     * @param frontMan the front man
     */
    public void setFrontMan(Person frontMan) { this.frontMan = frontMan; }

    /**
     * Compare to int.
     *
     * @param other the other
     * @return the int
     */
    @Override
    public int compareTo(MusicBand other) {
        return Long.compare(this.numberOfParticipants, other.numberOfParticipants);
    }

    /**
     * To string string.
     *
     * @return the string
     */
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