import managers.CollectionManager;
import managers.FileManager;
import commands.CommandManager;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        CollectionManager collectionManager = new CollectionManager(fileManager);
        collectionManager.load();

        CommandManager commandManager = new CommandManager(collectionManager, fileManager);
        commandManager.run();
    }
}