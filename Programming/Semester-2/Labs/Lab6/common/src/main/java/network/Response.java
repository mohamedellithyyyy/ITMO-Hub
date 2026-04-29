package network;
import models.MusicBand;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type Response.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final List<MusicBand> data;

    /**
     * Instantiates a new Response.
     *
     * @param message the message
     * @param data    the data
     */
    public Response(String message, List<MusicBand> data) {
        this.message = message;
        this.data = data;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<MusicBand> getData() {
        return data;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", dataSize=" + (data == null ? 0 : data.size()) +
                '}';
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message) &&
                Objects.equals(data, response.data);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(message, data);
    }
}