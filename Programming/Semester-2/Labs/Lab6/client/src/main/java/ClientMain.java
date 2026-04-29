import console.CommandBuilder;
import console.ConsoleReader;
import network.Client;
import network.ServerConnection;

/**
 * The type Client main.
 */
public class ClientMain {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // ---------------- BUILD COMPONENTS ----------------
        ConsoleReader consoleReader = new ConsoleReader();

        CommandBuilder commandBuilder = new CommandBuilder(consoleReader);

        ServerConnection connection = new ServerConnection();

        // ---------------- START CLIENT ----------------
        Client client = new Client(commandBuilder, connection);

        client.start();
    }
}