package network;

import auth.LoginManager;
import console.CommandBuilder;
import models.MusicBand;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Client {
    private final CommandBuilder commandBuilder;
    private final ServerConnection connection;
    private final Scanner scanner;
    private final LoginManager loginManager;

    public Client(CommandBuilder commandBuilder, ServerConnection connection, LoginManager loginManager) {
        this.commandBuilder = commandBuilder;
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.loginManager = loginManager;
    }

    public void start() {
        // Connect to server once
        connection.connect();

        if (!authenticate()) {
            System.out.println("Authentication failed. Exiting.");
            connection.disconnect();
            return;
        }

        System.out.println("Client started. Type commands:");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting client...");
                connection.disconnect();
                break;
            }
            if (input.toUpperCase().startsWith("EXECUTE_SCRIPT")) {
                String[] parts = input.split("\\s+", 2);
                String path = parts.length > 1 ? parts[1] : "";
                executeScript(path);
                continue;
            }
            Request request = commandBuilder.build(input);
            if (request == null) continue;
            Response response = connection.sendRequest(request);
            printResponse(response);
        }
    }

    private boolean authenticate() {
        while (true) {
            System.out.print("Login (1) or Register (2)? ");
            String choice = scanner.nextLine().trim();
            if (!choice.equals("1") && !choice.equals("2")) continue;

            System.out.print("Username: ");
            String user = scanner.nextLine().trim();
            System.out.print("Password: ");
            String pass = scanner.nextLine().trim();

            CommandType cmd = choice.equals("1") ? CommandType.LOGIN : CommandType.REGISTER;
            Response resp = connection.sendRequest(new Request(cmd, new String[]{user, pass}));
            System.out.println(resp.getMessage());

            if (cmd == CommandType.LOGIN && resp.getMessage().contains("Login successful")) {
                UserCredentials creds = new UserCredentials(user, pass);
                creds.setAuthenticated(true);
                if (resp.getAuthenticatedUserId() != null) {
                    creds.setUserId(resp.getAuthenticatedUserId());
                }
                loginManager.setCredentials(creds);
                return true;
            }

            if (cmd == CommandType.REGISTER && resp.getMessage().contains("successful")) {
                System.out.println("Please log in now.");
            }
        }
    }

    private void executeScript(String path) {
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                if (line.equalsIgnoreCase("exit")) break;
                if (line.toLowerCase().startsWith("execute_script")) {
                    System.out.println("Error: nested execute_script not allowed");
                    continue;
                }
                Request request = commandBuilder.build(line);
                if (request == null) continue;
                Response response = connection.sendRequest(request);
                printResponse(response);
            }
        } catch (Exception e) {
            System.out.println("Error reading script: " + e.getMessage());
        }
    }

    private void printResponse(Response response) {
        if (response == null) {
            System.out.println("No response from server");
            return;
        }
        if (response.getData() != null && !response.getData().isEmpty()) {
            printTable(response.getData());
        } else {
            System.out.println("\n" + response.getMessage() + "\n");
        }
    }

    private void printTable(List<MusicBand> bands) {
        String fmt = "| %-4s | %-20s | %-22s | %-8s | %-6s | %-12s | %-12s | %-10s |%n";
        String separator = "+------+----------------------+------------------------+----------+--------+--------------+--------------+------------+";
        System.out.println(separator);
        System.out.printf(fmt, "ID", "Name", "Genre", "Albums", "Parts", "FrontMan", "PassportID", "Date");
        System.out.println(separator);
        for (MusicBand b : bands) {
            System.out.printf(fmt,
                    b.getId(),
                    truncate(b.getName(), 20),
                    b.getGenre() != null ? truncate(b.getGenre().toString(), 22) : "null",
                    b.getAlbumsCount(),
                    b.getNumberOfParticipants(),
                    b.getFrontMan() != null ? truncate(b.getFrontMan().getName(), 12) : "null",
                    b.getFrontMan() != null ? truncate(b.getFrontMan().getPassportID(), 12) : "null",
                    b.getCreationDate() != null ? b.getCreationDate().toLocalDate() : "N/A"
            );
        }
        System.out.println(separator);
        System.out.println("Total: " + bands.size() + " band(s)\n");
    }

    private String truncate(String s, int max) {
        if (s == null) return "null";
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }
}