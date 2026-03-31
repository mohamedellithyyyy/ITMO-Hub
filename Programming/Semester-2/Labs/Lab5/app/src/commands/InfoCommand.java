package commands;

import managers.CollectionManager;

public class InfoCommand implements Command {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.println(collectionManager.getInfo());
    }

    @Override
    public String getDescription() {
        return "Show collection info";
    }
}