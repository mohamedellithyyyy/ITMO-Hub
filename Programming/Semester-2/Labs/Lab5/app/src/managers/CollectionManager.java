package managers;

import models.MusicBand;
import utility.XmlParser;

import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CollectionManager {
    private PriorityQueue<MusicBand> collection;
    private ZonedDateTime initDate;
    private FileManager fileManager;
    private IdGenerator idGenerator;

    public CollectionManager(FileManager fileManager) {
        this.collection = new PriorityQueue<>();
        this.initDate = ZonedDateTime.now();
        this.fileManager = fileManager;
        this.idGenerator = new IdGenerator();
    }

    public void load() {
        try {
            String xml = fileManager.read();
            InputStreamReader reader = new InputStreamReader(
                    new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")), "UTF-8"
            );
            collection = XmlParser.fromXml(reader);
            collection.stream()
                    .mapToInt(MusicBand::getId)
                    .max()
                    .ifPresent(idGenerator::setID);
        } catch (Exception e) {
            System.out.println("Warning: could not load collection: " + e.getMessage());
            collection = new PriorityQueue<>();
        }
    }

    public void save() {
        String xml = XmlParser.toXml(collection);
        fileManager.write(xml);
    }

    public void add(MusicBand band) {
        band.setId(idGenerator.generateId());
        band.setCreationDate(ZonedDateTime.now());
        collection.add(band);
    }

    public MusicBand getById(int id) {
        return collection.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateById(int id, MusicBand updated) {
        MusicBand existing = getById(id);
        if (existing == null) return false;
        collection.remove(existing);
        updated.setId(id);
        updated.setCreationDate(existing.getCreationDate());
        collection.add(updated);
        return true;
    }

    public boolean removeById(int id) {
        return collection.removeIf(b -> b.getId() == id);
    }

    public void clear() {
        collection.clear();
    }

    public MusicBand head() {
        return collection.peek();
    }

    public MusicBand removeHead() {
        return collection.poll();
    }

    public void removeLower(MusicBand band) {
        collection.removeIf(b -> b.compareTo(band) < 0);
    }

    public long sumOfParticipants() {
        return collection.stream()
                .mapToLong(MusicBand::getNumberOfParticipants)
                .sum();
    }

    public List<MusicBand> filterStartsWith(String prefix) {
        return collection.stream()
                .filter(b -> b.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public List<Long> uniqueParticipants() {
        return collection.stream()
                .map(MusicBand::getNumberOfParticipants)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public String getInfo() {
        return "Type: " + collection.getClass().getName() + "\n" +
                "Init date: " + initDate + "\n" +
                "Size: " + collection.size();
    }

    public List<MusicBand> getAll() {
        return collection.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public int size() {
        return collection.size();
    }
}