package managers;

import models.MusicBand;
import java.time.ZonedDateTime;
import java.util.*;

public class CollectionManager {
    private final PriorityQueue<MusicBand> collection;   // use PriorityQueue directly
    private final ZonedDateTime initDate;

    public CollectionManager() {
        this.collection = new PriorityQueue<>();
        this.initDate = ZonedDateTime.now();
    }

    public void loadFromDB(List<MusicBand> bands) {
        collection.clear();
        collection.addAll(bands);
    }

    public Collection<MusicBand> getCollection() {
        return Collections.unmodifiableCollection(collection);
    }

    public String info() {
        return "Type: " + collection.getClass().getName() +
                "\nInit date: " + initDate +
                "\nSize: " + collection.size();
    }

    public synchronized void add(MusicBand band) {
        collection.add(band);
    }

    public synchronized void update(MusicBand band) {
        collection.removeIf(b -> b.getId().equals(band.getId()));
        collection.add(band);
    }

    public synchronized void removeById(int id) {
        collection.removeIf(b -> b.getId() == id);
    }

    public synchronized void clearByUser(int userId) {
        collection.removeIf(b -> b.getOwnerId() == userId);
    }

    public synchronized MusicBand getById(int id) {
        return collection.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public synchronized List<MusicBand> getAll() {
        return new ArrayList<>(collection);
    }

    public synchronized List<MusicBand> filterStartsWithName(String prefix) {
        return collection.stream()
                .filter(b -> b.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .toList();
    }

    public synchronized long sumOfParticipants() {
        return collection.stream().mapToLong(MusicBand::getNumberOfParticipants).sum();
    }

    public synchronized List<Long> printUniqueParticipants() {
        return collection.stream().map(MusicBand::getNumberOfParticipants).distinct().sorted().toList();
    }

    // HEAD – correct using PriorityQueue.peek()
    public synchronized MusicBand head() {
        return collection.peek();
    }

    // POLL HEAD – correct using PriorityQueue.poll()
    public synchronized MusicBand pollHead() {
        return collection.poll();
    }

    // REMOVE LOWER – now respects ownership (only remove bands owned by the user)
    public synchronized boolean removeLower(MusicBand band, int userId) {
        return collection.removeIf(b -> b.getOwnerId() == userId && b.compareTo(band) < 0);
    }
}