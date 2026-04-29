package network;
import java.util.List;
import models.MusicBand;
import console.CommandBuilder;
import java.io.File;
import java.util.Scanner;

/**
 * The type Client.
 */
public class Client {

    private final CommandBuilder commandBuilder;
    private final ServerConnection connection;
    private final Scanner scanner;

    /**
     * Instantiates a new Client.
     *
     * @param commandBuilder the command builder
     * @param connection     the connection
     */
    public Client(CommandBuilder commandBuilder, ServerConnection connection) {
        this.commandBuilder = commandBuilder;
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start.
     */
// ---------------- MAIN LOOP ----------------
    public void start() {
        System.out.println("Client started. Type commands:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            // ---------------- LOCAL EXIT ----------------
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting client...");
                break;
            }

            // ---------------- EXECUTE SCRIPT LOCAL ----------------
            if (input.toUpperCase().startsWith("EXECUTE_SCRIPT")) {
                String[] parts = input.trim().split("\\s+", 2);
                String path = parts.length > 1 ? parts[1] : "";
                executeScript(path);
                continue;
            }

            // ---------------- BUILD REQUEST ----------------
            Request request = commandBuilder.build(input);
            if (request == null) {
                continue;
            }

            // ---------------- SEND TO SERVER ----------------
            Response response = connection.sendRequest(request);

            // ---------------- PRINT RESPONSE ----------------
            printResponse(response);
        }
    }

    // ---------------- EXECUTE SCRIPT ----------------
    private void executeScript(String path) {
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                // handle exit inside script
                if (line.equalsIgnoreCase("exit")) break;

                // handle nested execute_script
                if (line.toLowerCase().startsWith("execute_script")) {
                    String[] parts = line.trim().split("\\s+", 2);
                    String nestedPath = parts.length > 1 ? parts[1] : "";
                    System.out.println("Error: recursive script call detected");
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

    // ---------------- RESPONSE OUTPUT ----------------
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
                    b.getCreationDate().toLocalDate()
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