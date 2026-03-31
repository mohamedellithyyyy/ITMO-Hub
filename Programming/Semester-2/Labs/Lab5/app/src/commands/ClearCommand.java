package commands;

import managers.CollectionManager;

public class ClearCommand implements Command {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clear();
        System.out.println("Collection cleared");
    }

    @Override
    public String getDescription() {
        return "Clear the collection";
    }
}