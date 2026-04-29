package console;

import models.MusicBand;
import network.CommandType;
import network.Request;
import network.UpdateRequest;

import java.util.Scanner;

/**
 * The type Command builder.
 */
public class CommandBuilder {

    private final ConsoleReader consoleReader;
    private final Scanner scanner;

    /**
     * Instantiates a new Command builder.
     *
     * @param consoleReader the console reader
     */
    public CommandBuilder(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Build request.
     *
     * @param input the input
     * @return the request
     */
// ---------------- MAIN BUILD METHOD ----------------
    public Request build(String input) {
        if (input == null) return null;

        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toUpperCase();
        String argStr = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "HELP":
                return new Request(CommandType.HELP, null);
            case "INFO":
                return new Request(CommandType.INFO, null);
            case "SHOW":
                return new Request(CommandType.SHOW, null);
            case "CLEAR":
                return new Request(CommandType.CLEAR, null);
            case "EXIT":
                return new Request(CommandType.EXIT, null);
            case "HEAD":
                return new Request(CommandType.HEAD, null);
            case "REMOVE_HEAD":
                return new Request(CommandType.REMOVE_HEAD, null);
            case "SUM_OF_NUMBER_OF_PARTICIPANTS":
                return new Request(CommandType.SUM_OF_NUMBER_OF_PARTICIPANTS, null);
            case "PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS":
                return new Request(CommandType.PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS, null);
            case "REMOVE_BY_ID":
                return new Request(CommandType.REMOVE_BY_ID, Integer.parseInt(argStr));
            case "FILTER_STARTS_WITH_NAME":
                return new Request(CommandType.FILTER_STARTS_WITH_NAME, argStr);
            case "EXECUTE_SCRIPT":
                return new Request(CommandType.EXECUTE_SCRIPT, argStr);
            case "UPDATE": {
                int id = Integer.parseInt(argStr);
                MusicBand band = consoleReader.readMusicBand();
                return new Request(CommandType.UPDATE, new UpdateRequest(id, band));
            }
            case "ADD": {
                MusicBand band = consoleReader.readMusicBand();
                return new Request(CommandType.ADD, band);
            }
            case "REMOVE_LOWER": {
                MusicBand band = consoleReader.readMusicBand();
                return new Request(CommandType.REMOVE_LOWER, band);
            }
            default:
                System.out.println("Unknown command: " + input);
                return null;
        }
    }
    // ---------------- HELPER ----------------

    private long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}