import managers.CollectionManager;
import managers.FileManager;
import network.Server;

/**
 * The type Server main.
 */
public class ServerMain {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 1. Read file path from environment variable
        String filePath = System.getenv("MUSIC_COLLECTION_FILE");
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Environment variable MUSIC_COLLECTION_FILE not set");
            return;
        }

        // 2. Create managers
        FileManager fileManager = new FileManager(filePath);
        CollectionManager collectionManager = new CollectionManager(fileManager);

        // 3. Load collection from XML
        collectionManager.load();

        // 4. Create server
        Server server = new Server(collectionManager);

        // 5. Shutdown hook (auto-save)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nServer shutting down... saving data");
            collectionManager.save();
            System.out.println("Data saved successfully.");
        }));

        // 6. Start server
        System.out.println("Server starting...");
        server.start();
    }
}