import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final List<MusicBand> data;

    public Response(String message, List<MusicBand> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public List<MusicBand> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", dataSize=" + (data == null ? 0 : data.size()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message) &&
                Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, data);
    }
}