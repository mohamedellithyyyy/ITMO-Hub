package commands;

import managers.CollectionManager;
import models.MusicBand;

public class RemoveHeadCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        MusicBand band = collectionManager.removeHead();
        if (band == null) {
            System.out.println("Collection is empty");
            return;
        }
        System.out.println("Removed: " + band);
    }

    @Override
    public String getDescription() {
        return "Show and remove first element";
    }
}