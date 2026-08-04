package console;

import models.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner = new Scanner(System.in);

    private String nextLine() {
        if (!scanner.hasNextLine()) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
        return scanner.nextLine();
    }

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

    private String readName() {
        while (true) {
            System.out.print("Enter band name: ");
            String name = nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("Error: name cannot be empty");
        }
    }

    private Coordinates readCoordinates() {
        double x = readDouble("Enter coordinates x: ");
        Long y = readLongMax("Enter coordinates y (max 433): ", 433L);
        return new Coordinates(x, y);
    }

    private long readNumberOfParticipants() {
        while (true) {
            try {
                System.out.print("Enter number of participants (> 0): ");
                long v = Long.parseLong(nextLine().trim());
                if (v > 0) return v;
                System.out.println("Error: must be > 0");
            } catch (NumberFormatException e) {
                System.out.println("Error: integer expected");
            }
        }
    }

    private long readAlbumsCount() {
        while (true) {
            try {
                System.out.print("Enter albums count (> 0): ");
                long v = Long.parseLong(nextLine().trim());
                if (v > 0) return v;
                System.out.println("Error: must be > 0");
            } catch (NumberFormatException e) {
                System.out.println("Error: integer expected");
            }
        }
    }

    private MusicGenre readGenre() {
        while (true) {
            System.out.println("Available genres: " + Arrays.toString(MusicGenre.values()));
            System.out.print("Enter genre (or empty): ");
            String input = nextLine().trim();
            if (input.isEmpty()) return null;
            try {
                return MusicGenre.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: invalid genre");
            }
        }
    }

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
            System.out.print("Enter front man name: ");
            String name = nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("Error: name cannot be empty");
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
                if (Period.between(date, LocalDate.now()).getYears() < 18) {
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
            System.out.print("Enter passport ID: ");
            String id = nextLine().trim();
            if (!id.isEmpty()) return id;
            System.out.println("Error: passportID cannot be empty");
        }
    }

    private Color readHairColor() {
        while (true) {
            System.out.println("Available colors: " + Arrays.toString(Color.values()));
            System.out.print("Enter hair color (or empty): ");
            String input = nextLine().trim();
            if (input.isEmpty()) return null;
            try {
                return Color.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: invalid color");
            }
        }
    }

    private Location readLocation() {
        while (true) {
            System.out.print("Add location? (y/n): ");
            String ans = nextLine().trim();
            if (ans.equalsIgnoreCase("n")) return null;
            if (!ans.equalsIgnoreCase("y")) {
                System.out.println("Enter y or n only");
                continue;
            }
            try {
                Float x = readFloat("Enter location x: ");
                Long y = readLong("Enter location y: ");
                Integer z = readInteger("Enter location z: ");
                return new Location(x, y, z);
            } catch (Exception e) {
                System.out.println("Error: invalid location input");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Long readLongMax(String prompt, Long max) {
        while (true) {
            try {
                System.out.print(prompt);
                Long v = Long.parseLong(nextLine().trim());
                if (v <= max) return v;
                System.out.println("Error: must be <= " + max);
            } catch (NumberFormatException e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Float readFloat(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Float.parseFloat(nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: number required");
            }
        }
    }

    private Integer readInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: number required");
            }
        }
    }
}
