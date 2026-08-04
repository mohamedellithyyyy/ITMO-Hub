import database.DatabaseManager;
import database.MusicBandDAO;
import managers.CollectionManager;
import network.Server;

public class ServerMain {
    public static void main(String[] args) {
        // Init DB connection
        DatabaseManager.getInstance();
        MusicBandDAO bandDAO = new MusicBandDAO();
        CollectionManager collectionManager = new CollectionManager();
        // Load all bands from DB into memory
        collectionManager.loadFromDB(bandDAO.loadAllBands());

        Server server = new Server(collectionManager, bandDAO);
        System.out.println("Server starting...");
        server.start();
    }
}