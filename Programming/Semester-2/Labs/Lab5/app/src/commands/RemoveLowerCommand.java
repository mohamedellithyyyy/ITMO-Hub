package commands;

import managers.CollectionManager;
import managers.InputManager;

public class RemoveLowerCommand implements Command {
    private CollectionManager collectionManager;
    private InputManager inputManager;

    public RemoveLowerCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.removeLower(inputManager.readMusicBand());
        System.out.println("Bands removed successfully");
    }

    @Override
    public String getDescription() {
        return "Remove all bands lower than given";
    }
}