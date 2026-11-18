import java.util.ArrayList;
import java.util.List;

public final class History {

    public static final class Entry {
        public final double x, y, r;
        public final boolean hit;
        public final String time;
        public final long scriptMs;

        public Entry(double x, double y, double r, boolean hit, String time, long scriptMs) {
            this.x = x; this.y = y; this.r = r;
            this.hit = hit; this.time = time; this.scriptMs = scriptMs;
        }
    }

    private final List<Entry> entries = new ArrayList<>();

    public synchronized void add(Entry e) { entries.add(e); }

    public synchronized List<Entry> snapshot() {
        return new ArrayList<>(entries);
    }

    public synchronized void clear() { entries.clear(); }
}