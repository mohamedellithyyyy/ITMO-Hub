package commands;

import managers.CollectionManager;

public class ShowCommand implements Command {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.size() == 0) {
            System.out.println("Collection is empty");
            return;
        }
        collectionManager.getAll().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Show all bands";
    }
}