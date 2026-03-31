package commands;

import managers.CollectionManager;
import models.MusicBand;

public class HeadCommand implements Command {
    private CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        MusicBand band = collectionManager.head();
        if (band == null) {
            System.out.println("Collection is empty");
            return;
        }
        System.out.println(band);
    }

    @Override
    public String getDescription() {
        return "Show first element";
    }
}