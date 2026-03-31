package commands;

import managers.CollectionManager;

public class SaveCommand implements Command {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.save();
        System.out.println("Collection saved");
    }

    @Override
    public String getDescription() {
        return "Save collection to file";
    }
}