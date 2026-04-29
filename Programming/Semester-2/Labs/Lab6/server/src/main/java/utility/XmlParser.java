package utility;

import exceptions.FileLoadException;
import models.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.xml.sax.InputSource;

/**
 * The type Xml parser.
 */
public class XmlParser {

    /**
     * To xml string.
     *
     * @param collection the collection
     * @return the string
     */
    public static String toXml(PriorityQueue<MusicBand> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<musicBands>\n");
        for (MusicBand band : collection) {
            if (band.getFrontMan() == null) throw new IllegalStateException("frontMan cannot be null");

            sb.append("  <musicBand>\n");
            sb.append("    <id>").append(band.getId()).append("</id>\n");
            sb.append("    <name>").append(escapeXml(band.getName())).append("</name>\n");
            sb.append("    <coordinates>\n");
            sb.append("      <x>").append(band.getCoordinates().getX()).append("</x>\n");
            sb.append("      <y>").append(band.getCoordinates().getY()).append("</y>\n");
            sb.append("    </coordinates>\n");
            sb.append("    <creationDate>").append(band.getCreationDate()).append("</creationDate>\n");
            sb.append("    <numberOfParticipants>").append(band.getNumberOfParticipants()).append("</numberOfParticipants>\n");
            sb.append("    <albumsCount>").append(band.getAlbumsCount()).append("</albumsCount>\n");
            sb.append("    <genre>");
            if (band.getGenre() != null) sb.append(band.getGenre());
            sb.append("</genre>\n");
            sb.append("    <frontMan>\n");
            sb.append("      <name>").append(escapeXml(band.getFrontMan().getName())).append("</name>\n");
            sb.append("      <birthday>").append(band.getFrontMan().getBirthday()).append("</birthday>\n");
            sb.append("      <passportID>").append(escapeXml(band.getFrontMan().getPassportID())).append("</passportID>\n");
            sb.append("      <hairColor>");
            if (band.getFrontMan().getHairColor() != null) sb.append(band.getFrontMan().getHairColor());
            sb.append("</hairColor>\n");
            if (band.getFrontMan().getLocation() != null) {
                sb.append("      <location>\n");
                sb.append("        <x>").append(band.getFrontMan().getLocation().getX()).append("</x>\n");
                sb.append("        <y>").append(band.getFrontMan().getLocation().getY()).append("</y>\n");
                sb.append("        <z>").append(band.getFrontMan().getLocation().getZ()).append("</z>\n");
                sb.append("      </location>\n");
            }
            sb.append("    </frontMan>\n");
            sb.append("  </musicBand>\n");
        }

        sb.append("</musicBands>");
        return sb.toString();
    }

    /**
     * From xml priority queue.
     *
     * @param reader the reader
     * @return the priority queue
     */
    public static PriorityQueue<MusicBand> fromXml(InputStreamReader reader) {
        PriorityQueue<MusicBand> collection = new PriorityQueue<>();
        Set<Integer> ids = new HashSet<>();
        Set<String> passports = new HashSet<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();

            NodeList bandList = doc.getElementsByTagName("musicBand");

            for (int i = 0; i < bandList.getLength(); i++) {
                Element el = (Element) bandList.item(i);

                String idStr = getTag(el, "id");
                if (idStr == null) throw new FileLoadException("Missing id");
                int id = Integer.parseInt(idStr);
                if (id <= 0) throw new FileLoadException("id must be > 0");
                if (!ids.add(id)) throw new FileLoadException("Duplicate id: " + id);

                String name = getTag(el, "name");
                if (name == null || name.isEmpty()) throw new FileLoadException("name cannot be empty");

                String creationDateStr = getTag(el, "creationDate");
                if (creationDateStr == null) throw new FileLoadException("Missing creationDate");
                ZonedDateTime creationDate = ZonedDateTime.parse(creationDateStr);
                if (creationDate.isAfter(ZonedDateTime.now())) {
                    throw new FileLoadException("creationDate cannot be in the future: " + creationDateStr);
                }

                String participantsStr = getTag(el, "numberOfParticipants");
                if (participantsStr == null) throw new FileLoadException("Missing numberOfParticipants");
                long numberOfParticipants = Long.parseLong(participantsStr);
                if (numberOfParticipants <= 0) throw new FileLoadException("numberOfParticipants must be > 0");

                String albumsStr = getTag(el, "albumsCount");
                if (albumsStr == null) throw new FileLoadException("Missing albumsCount");
                long albumsCount = Long.parseLong(albumsStr);
                if (albumsCount <= 0) throw new FileLoadException("albumsCount must be > 0");

                String genreStr = getTag(el, "genre");
                MusicGenre genre = (genreStr == null || genreStr.isEmpty()) ? null : MusicGenre.valueOf(genreStr);

                Element coordEl = (Element) el.getElementsByTagName("coordinates").item(0);
                if (coordEl == null) throw new FileLoadException("Missing coordinates");
                double coordX = Double.parseDouble(getTag(coordEl, "x"));
                String coordYStr = getTag(coordEl, "y");
                if (coordYStr == null) throw new FileLoadException("Missing coordinates y");
                Long coordY = Long.parseLong(coordYStr);
                if (coordY > 433) throw new FileLoadException("coordinates y must be <= 433");
                Coordinates coordinates = new Coordinates(coordX, coordY);

                Element frontManEl = (Element) el.getElementsByTagName("frontMan").item(0);
                if (frontManEl == null) throw new FileLoadException("Missing frontMan");

                String fmName = getTag(frontManEl, "name");
                if (fmName == null || fmName.isEmpty()) throw new FileLoadException("frontMan name cannot be empty");

                String birthdayStr = getTag(frontManEl, "birthday");
                if (birthdayStr == null) throw new FileLoadException("Missing birthday");
                LocalDate birthday = LocalDate.parse(birthdayStr);

                String passportID = getTag(frontManEl, "passportID");
                if (passportID == null || passportID.isEmpty()) throw new FileLoadException("passportID cannot be empty");
                if (!passports.add(passportID)) throw new FileLoadException("Duplicate passportID: " + passportID);

                String hairColorStr = getTag(frontManEl, "hairColor");
                Color hairColor = (hairColorStr == null || hairColorStr.isEmpty()) ? null : Color.valueOf(hairColorStr);

                Location location = null;
                NodeList locationList = frontManEl.getElementsByTagName("location");
                if (locationList.getLength() > 0) {
                    Element locEl = (Element) locationList.item(0);
                    String locXStr = getTag(locEl, "x");
                    String locYStr = getTag(locEl, "y");
                    String locZStr = getTag(locEl, "z");
                    if (locXStr == null || locYStr == null || locZStr == null)
                        throw new FileLoadException("Missing location fields");
                    Float locX = Float.parseFloat(locXStr);
                    Long locY = Long.parseLong(locYStr);
                    Integer locZ = Integer.parseInt(locZStr);
                    location = new Location(locX, locY, locZ);
                }
                Person frontMan = new Person(fmName, birthday, passportID, hairColor, location);
                MusicBand band = new MusicBand(id, name, coordinates, creationDate,
                        numberOfParticipants, albumsCount, genre, frontMan);
                collection.add(band);
            }
        } catch (FileLoadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse XML: " + e.getMessage());
        }
        return collection;
    }

    private static String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private static String getTag( Element el, String tagName) {
        NodeList list = el.getElementsByTagName(tagName);
        if (list.getLength() == 0) return null;
        return list.item(0).getTextContent().trim();
    }
}