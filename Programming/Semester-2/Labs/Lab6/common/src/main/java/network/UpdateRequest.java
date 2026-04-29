package network;
import models.MusicBand;
import java.io.Serializable;

/**
 * The type Update request.
 */
public class UpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final MusicBand band;

    /**
     * Instantiates a new Update request.
     *
     * @param id   the id
     * @param band the band
     */
    public UpdateRequest(int id, MusicBand band) {
        this.id = id;
        this.band = band;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets band.
     *
     * @return the band
     */
    public MusicBand getBand() {
        return band;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "UpdateRequest{" +
                "id=" + id +
                ", band=" + band +
                '}';
    }
}