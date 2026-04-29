package console;

import models.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The type Console reader.
 */
public class ConsoleReader {

    private final Scanner scanner = new Scanner(System.in);

    // ─────────────────────────────
    // SAFE INPUT (IMPORTANT FIX)
    // ─────────────────────────────
    private String nextLine() {
        if (!scanner.hasNextLine()) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
        return scanner.nextLine();
    }

    /**
     * Read music band music band.
     *
     * @return the music band
     */
// ─────────────────────────────
    // MUSIC BAND
    // ─────────────────────────────
    public MusicBand readMusicBand() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        long numberOfParticipants = readNumberOfParticipants();
        long albumsCount = readAlbumsCount();
        MusicGenre genre = readGenre();
        Person frontMan = readFrontMan();

        return new MusicBand(
                null,
                name,
                coordinates,
                ZonedDateTime.now(),
                numberOfParticipants,
                albumsCount,
                genre,
                frontMan
        );
    }

    // ─────────────────────────────
    // NAME
    // ─────────────────────────────
    private String readName() {
        while (true) {
            try {
                System.out.print("Enter band name: ");
                String name = nextLine().trim();
                if (name.isEmpty()) throw new Exception("name cannot be empty");
                return name;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ─────────────────────────────
    // COORDINATES
    // ─────────────────────────────
    private Coordinates readCoordinates() {
        double x = readDouble("Enter coordinates x: ");
        Long y = readLongMax("Enter coordinates y (max 433): ", 433L);
        return new Coordinates(x, y);
    }

    // ─────────────────────────────
    // PARTICIPANTS
    // ─────────────────────────────
    private long readNumberOfParticipants() {
        while (true) {
            try {
                System.out.print("Enter number of participants (> 0): ");
                long v = Long.parseLong(nextLine().trim());
                if (v <= 0) throw new Exception();
                return v;
            } catch (Exception e) {
                System.out.println("Error: numberOfParticipants must be > 0");
            }
        }
    }

    private long readAlbumsCount() {
        while (true) {
            try {
                System.out.print("Enter albums count (> 0): ");
                long v = Long.parseLong(nextLine().trim());
                if (v <= 0) throw new Exception();
                return v;
            } catch (Exception e) {
                System.out.println("Error: albumsCount must be > 0");
            }
        }
    }

    // ─────────────────────────────
    // GENRE
    // ─────────────────────────────
    private MusicGenre readGenre() {
        while (true) {
            try {
                System.out.println("Available genres: " + Arrays.toString(MusicGenre.values()));
                System.out.print("Enter genre (or empty): ");
                String input = nextLine().trim();
                if (input.isEmpty()) return null;
                return MusicGenre.valueOf(input.toUpperCase());
            } catch (Exception e) {
                System.out.println("Error: invalid genre");
            }
        }
    }

    // ─────────────────────────────
    // FRONT MAN
    // ─────────────────────────────
    private Person readFrontMan() {
        System.out.println("--- Enter front man details ---");

        String name = readFrontManName();
        LocalDate birthday = readBirthday();
        String passportID = readPassportID();
        Color hairColor = readHairColor();
        Location location = readLocation();

        return new Person(name, birthday, passportID, hairColor, location);
    }

    private String readFrontManName() {
        while (true) {
            try {
                System.out.print("Enter front man name: ");
                String name = nextLine().trim();
                if (name.isEmpty()) throw new Exception();
                return name;
            } catch (Exception e) {
                System.out.println("Error: name cannot be empty");
            }
        }
    }

    private LocalDate readBirthday() {
        while (true) {
            try {
                System.out.print("Enter birthday (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(nextLine().trim());

                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Error: future date not allowed");
                    continue;
                }

                int age = Period.between(date, LocalDate.now()).getYears();
                if (age < 18) {
                    System.out.println("Error: must be 18+");
                    continue;
                }

                return date;

            } catch (Exception e) {
                System.out.println("Error: invalid date format");
            }
        }
    }

    private String readPassportID() {
        while (true) {
            try {
                System.out.print("Enter passport ID: ");
                String id = nextLine().trim();
                if (id.isEmpty()) throw new Exception();
                return id;
            } catch (Exception e) {
                System.out.println("Error: passportID cannot be empty");
            }
        }
    }

    private Color readHairColor() {
        while (true) {
            try {
                System.out.println("Available colors: " + Arrays.toString(Color.values()));
                System.out.print("Enter hair color (or empty): ");
                String input = nextLine().trim();
                if (input.isEmpty()) return null;
                return Color.valueOf(input.toUpperCase());
            } catch (Exception e) {
                System.out.println("Error: invalid color");
            }
        }
    }

    // ─────────────────────────────
    // LOCATION (IMPORTANT FIX FOR TESTS)
    // ─────────────────────────────
    private Location readLocation() {
        while (true) {
            try {
                System.out.print("Add location? (y/n): ");
                String ans = nextLine().trim();

                if (ans.equalsIgnoreCase("n")) return null;
                if (!ans.equalsIgnoreCase("y")) {
                    System.out.println("Enter y or n only");
                    continue;
                }

                Float x = readFloat("Enter location x: ");
                Long y = readLong("Enter location y: ");
                Integer z = readInteger("Enter location z: ");

                return new Location(x, y, z);

            } catch (Exception e) {
                System.out.println("Error: invalid location");
            }
        }
    }

    // ─────────────────────────────
    // PRIMITIVES
    // ─────────────────────────────
    private double readDouble(String p) {
        while (true) {
            try {
                System.out.print(p);
                return Double.parseDouble(nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Long readLongMax(String p, Long max) {
        while (true) {
            try {
                System.out.print(p);
                Long v = Long.parseLong(nextLine().trim());
                if (v > max) throw new Exception();
                return v;
            } catch (Exception e) {
                System.out.println("Error: must be <= " + max);
            }
        }
    }

    private Long readLong(String p) {
        while (true) {
            try {
                System.out.print(p);
                return Long.parseLong(nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Float readFloat(String p) {
        while (true) {
            try {
                System.out.print(p);
                return Float.parseFloat(nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Integer readInteger(String p) {
        while (true) {
            try {
                System.out.print(p);
                return Integer.parseInt(nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: number required");
            }
        }
    }
}