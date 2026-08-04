import auth.LoginManager;
import console.CommandBuilder;
import console.ConsoleReader;
import network.Client;
import network.ServerConnection;

public class ClientMain {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        LoginManager loginManager = new LoginManager();
        CommandBuilder commandBuilder = new CommandBuilder(consoleReader, loginManager);
        ServerConnection connection = new ServerConnection();
        Client client = new Client(commandBuilder, connection, loginManager);
        client.start();
    }
}
