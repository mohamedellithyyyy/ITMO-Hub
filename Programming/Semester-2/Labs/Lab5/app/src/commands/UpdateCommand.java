package commands;

import managers.CollectionManager;
import managers.InputManager;

public class UpdateCommand implements Command {
    private CollectionManager collectionManager;
    private InputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: update <id>");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collectionManager.getById(id) == null) {
                System.out.println("Band with id " + id + " not found");
                return;
            }
            collectionManager.updateById(id, inputManager.readMusicBand());
            System.out.println("Band updated successfully");
        } catch (NumberFormatException e) {
            System.out.println("Error: id must be a number");
        }
    }

    @Override
    public String getDescription() {
        return "Update band by id";
    }
}