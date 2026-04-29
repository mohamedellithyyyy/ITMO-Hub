package managers;

import models.MusicBand;
import network.UpdateRequest;
import utility.XmlParser;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Collection manager.
 */
public class CollectionManager {

    private PriorityQueue<MusicBand> collection;
    private final ZonedDateTime initDate;
    private final FileManager fileManager;
    private final IdGenerator idGenerator;

    /**
     * Instantiates a new Collection manager.
     *
     * @param fileManager the file manager
     */
    public CollectionManager(FileManager fileManager) {
        this.collection = new PriorityQueue<>();
        this.initDate = ZonedDateTime.now();
        this.fileManager = fileManager;
        this.idGenerator = new IdGenerator();
    }

    // ---------------- LOAD / SAVE ----------------

    /**
     * Load.
     */
    public void load() {
        try {
            String xml = fileManager.read();

            InputStreamReader reader = new InputStreamReader(
                    new java.io.ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.UTF_8
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

    /**
     * Save.
     */
    public void save() {
        String xml = XmlParser.toXml(collection);
        fileManager.write(xml);
    }

    // ---------------- CORE ACCESS ----------------

    /**
     * Gets collection.
     *
     * @return the collection
     */
    public PriorityQueue<MusicBand> getCollection() {
        return collection;
    }

    /**
     * Info string.
     *
     * @return the string
     */
    public String info() {
        return "Type: " + collection.getClass().getName() +
                "\nInit date: " + initDate +
                "\nSize: " + collection.size();
    }

    // ---------------- ADD ----------------

    /**
     * Add string.
     *
     * @param arg the arg
     * @return the string
     */
    public String add(Object arg) {
        MusicBand band = (MusicBand) arg;

        band.setId(idGenerator.generateId());
        band.setCreationDate(ZonedDateTime.now());

        collection.add(band);

        return "Added successfully";
    }

    // ---------------- UPDATE ----------------

    /**
     * Update string.
     *
     * @param arg the arg
     * @return the string
     */
    public String update(Object arg) {
        UpdateRequest req = (UpdateRequest) arg;

        int id = req.getId();
        MusicBand updated = req.getBand();

        MusicBand existing = getById(id);
        if (existing == null) return "Not found";

        collection.remove(existing);

        updated.setId(id);
        updated.setCreationDate(existing.getCreationDate());

        collection.add(updated);

        return "Updated successfully";
    }

    // ---------------- REMOVE ----------------

    /**
     * Remove by id string.
     *
     * @param arg the arg
     * @return the string
     */
    public String removeById(Object arg) {
        int id = (Integer) arg;

        boolean removed = collection.removeIf(b -> b.getId() == id);

        return removed ? "Removed successfully" : "Not found";
    }

    /**
     * Clear.
     */
    public void clear() {
        collection.clear();
    }

    // ---------------- HEAD ----------------

    /**
     * Head string.
     *
     * @return the string
     */
    public String head() {
        MusicBand band = collection.peek();
        return band == null ? "Collection is empty" : band.toString();
    }

    /**
     * Remove head string.
     *
     * @return the string
     */
    public String removeHead() {
        MusicBand band = collection.poll();
        return band == null ? "Collection is empty" : "Removed: " + band;
    }

    // ---------------- REMOVE LOWER ----------------

    /**
     * Remove lower string.
     *
     * @param arg the arg
     * @return the string
     */
    public String removeLower(Object arg) {
        MusicBand band = (MusicBand) arg;

        collection.removeIf(b -> b.compareTo(band) < 0);

        return "Lower elements removed";
    }

    // ---------------- ANALYTICS ----------------

    /**
     * Sum of participants long.
     *
     * @return the long
     */
    public long sumOfParticipants() {
        return collection.stream()
                .mapToLong(MusicBand::getNumberOfParticipants)
                .sum();
    }

    /**
     * Filter starts with name list.
     *
     * @param arg the arg
     * @return the list
     */
    public List<MusicBand> filterStartsWithName(Object arg) {
        String prefix = (String) arg;

        return collection.stream()
                .filter(b -> b.getName() != null &&
                        b.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Print unique participants list.
     *
     * @return the list
     */
    public List<Long> printUniqueParticipants() {
        return collection.stream()
                .map(MusicBand::getNumberOfParticipants)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public MusicBand getById(int id) {
        return collection.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return collection.size();
    }
}