package utility;

import models.MusicBand;

public class ConsoleUtils {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printError(String message) {
        System.out.println("Error: " + message);
    }

    public static void printSuccess(String message) {
        System.out.println("Success: " + message);
    }

    public static void printBand(MusicBand band) {
        System.out.println(band.toString());
    }
}
