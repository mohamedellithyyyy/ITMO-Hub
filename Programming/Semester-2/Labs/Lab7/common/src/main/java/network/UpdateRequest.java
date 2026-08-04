package network;

import models.MusicBand;
import java.io.Serializable;

public class UpdateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final MusicBand band;

    public UpdateRequest(int id, MusicBand band) {
        this.id = id;
        this.band = band;
    }

    public int getId() { return id; }
    public MusicBand getBand() { return band; }
}
