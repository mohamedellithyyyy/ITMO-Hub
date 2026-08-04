package console;

import models.MusicBand;
import network.CommandType;
import network.Request;
import network.UpdateRequest;
import auth.LoginManager;

public class CommandBuilder {
    private final ConsoleReader consoleReader;
    private final LoginManager loginManager;

    public CommandBuilder(ConsoleReader consoleReader, LoginManager loginManager) {
        this.consoleReader = consoleReader;
        this.loginManager = loginManager;
    }

    public Request build(String input) {
        if (input == null) return null;
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toUpperCase();
        String argStr = parts.length > 1 ? parts[1].trim() : "";

        if (command.equals("LOGIN") || command.equals("REGISTER")) {
            System.out.println("Already authenticated. Use commands directly.");
            return null;
        }

        if (!loginManager.isLoggedIn()) {
            System.out.println("You must login first. Use LOGIN or REGISTER.");
            return null;
        }

        switch (command) {
            case "HELP":
                return new Request(CommandType.HELP, null, loginManager.getCredentials());
            case "INFO":
                return new Request(CommandType.INFO, null, loginManager.getCredentials());
            case "SHOW":
                return new Request(CommandType.SHOW, null, loginManager.getCredentials());
            case "CLEAR":
                return new Request(CommandType.CLEAR, null, loginManager.getCredentials());
            case "EXIT":
                return new Request(CommandType.EXIT, null, loginManager.getCredentials());
            case "HEAD":
                return new Request(CommandType.HEAD, null, loginManager.getCredentials());
            case "REMOVE_HEAD":
                return new Request(CommandType.REMOVE_HEAD, null, loginManager.getCredentials());
            case "SUM_OF_NUMBER_OF_PARTICIPANTS":
                return new Request(CommandType.SUM_OF_NUMBER_OF_PARTICIPANTS, null, loginManager.getCredentials());
            case "PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS":
                return new Request(CommandType.PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS, null, loginManager.getCredentials());
            case "REMOVE_BY_ID":
                try {
                    int id = Integer.parseInt(argStr);
                    return new Request(CommandType.REMOVE_BY_ID, id, loginManager.getCredentials());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format");
                    return null;
                }
            case "FILTER_STARTS_WITH_NAME":
                return new Request(CommandType.FILTER_STARTS_WITH_NAME, argStr, loginManager.getCredentials());
            case "EXECUTE_SCRIPT":
                return new Request(CommandType.EXECUTE_SCRIPT, argStr, loginManager.getCredentials());
            case "UPDATE":
                try {
                    int id = Integer.parseInt(argStr);
                    MusicBand band = consoleReader.readMusicBand();
                    return new Request(CommandType.UPDATE, new UpdateRequest(id, band), loginManager.getCredentials());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format");
                    return null;
                }
            case "ADD":
                return new Request(CommandType.ADD, consoleReader.readMusicBand(), loginManager.getCredentials());
            case "REMOVE_LOWER":
                return new Request(CommandType.REMOVE_LOWER, consoleReader.readMusicBand(), loginManager.getCredentials());
            default:
                System.out.println("Unknown command: " + input);
                return null;
        }
    }
}
