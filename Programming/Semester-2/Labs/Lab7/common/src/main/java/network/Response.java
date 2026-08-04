package network;

import models.MusicBand;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Response implements Serializable {
    private static final long serialVersionUID = 2L;

    private final String message;
    private final List<MusicBand> data;
    private final Integer authenticatedUserId;

    public Response(String message, List<MusicBand> data) {
        this(message, data, null);
    }

    public Response(String message, List<MusicBand> data, Integer authenticatedUserId) {
        this.message = message;
        this.data = data;
        this.authenticatedUserId = authenticatedUserId;
    }

    public String getMessage() { return message; }
    public List<MusicBand> getData() { return data; }
    public Integer getAuthenticatedUserId() { return authenticatedUserId; }

    @Override
    public String toString() {
        return "Response{message='" + message + "', dataSize=" +
               (data == null ? 0 : data.size()) + ", userId=" + authenticatedUserId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message) &&
               Objects.equals(data, response.data) &&
               Objects.equals(authenticatedUserId, response.authenticatedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, data, authenticatedUserId);
    }
}
