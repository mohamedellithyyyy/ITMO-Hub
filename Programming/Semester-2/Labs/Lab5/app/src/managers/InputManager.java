package managers;

import exceptions.InvalidInputException;
import models.*;
import utility.ValidationUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class InputManager {
    private Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public MusicBand readMusicBand() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        long numberOfParticipants = readNumberOfParticipants();
        long albumsCount = readAlbumsCount();
        MusicGenre genre = readGenre();
        Person frontMan = readFrontMan();
        return new MusicBand(null, name, coordinates, null, numberOfParticipants, albumsCount, genre, frontMan);
    }

    private String readName() {
        while (true) {
            try {
                System.out.print("Enter band name: ");
                String name = scanner.nextLine().trim();
                ValidationUtils.validateNotEmpty(name, "name");
                return name;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
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
                long value = Long.parseLong(scanner.nextLine().trim());
                ValidationUtils.validatePositive(value, "numberOfParticipants");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private long readAlbumsCount() {
        while (true) {
            try {
                System.out.print("Enter albums count (> 0): ");
                long value = Long.parseLong(scanner.nextLine().trim());
                ValidationUtils.validatePositive(value, "albumsCount");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private MusicGenre readGenre() {
        while (true) {
            try {
                System.out.println("Available genres: " + java.util.Arrays.toString(MusicGenre.values()));
                System.out.print("Enter genre (or empty for null): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return null;
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
            try {
                System.out.print("Enter front man name: ");
                String name = scanner.nextLine().trim();
                ValidationUtils.validateNotEmpty(name, "frontMan name");
                return name;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private LocalDate readBirthday() {
        while (true) {
            try {
                System.out.print("Enter birthday (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Error: invalid date format, use YYYY-MM-DD");
            }
        }
    }

    private String readPassportID() {
        while (true) {
            try {
                System.out.print("Enter passport ID: ");
                String id = scanner.nextLine().trim();
                ValidationUtils.validateNotEmpty(id, "passportID");
                return id;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Color readHairColor() {
        while (true) {
            try {
                System.out.println("Available hair colors: " + java.util.Arrays.toString(Color.values()));
                System.out.print("Enter hair color (or empty for null): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return null;
                return Color.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: invalid color");
            }
        }
    }

    private Location readLocation() {
        System.out.print("Add location? (y/n): ");
        String answer = scanner.nextLine().trim();
        if (!answer.equalsIgnoreCase("y")) return null;

        Float x = readFloat("Enter location x: ");
        Long y = readLong("Enter location y: ");
        Integer z = readInteger("Enter location z: ");
        return new Location(x, y, z);
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            }
        }
    }

    private Long readLongMax(String prompt, Long max) {
        while (true) {
            try {
                System.out.print(prompt);
                Long value = Long.parseLong(scanner.nextLine().trim());
                if (value > max) {
                    System.out.println("Error: value must be <= " + max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            }
        }
    }

    private Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            }
        }
    }

    private Float readFloat(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            }
        }
    }

    private Integer readInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number");
            }
        }
    }
}