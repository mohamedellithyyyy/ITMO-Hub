package commands;

import managers.CollectionManager;
import managers.InputManager;

public class AddCommand implements Command {
    private CollectionManager collectionManager;
    private InputManager inputManager;

    public AddCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.add(inputManager.readMusicBand());
        System.out.println("Band added successfully");
    }

    @Override
    public String getDescription() {
        return "Add a new band";
    }
}