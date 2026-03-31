package commands;

import managers.CollectionManager;
import models.MusicBand;

import java.util.List;

public class FilterStartsWithNameCommand implements Command {
    private CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: filter_starts_with_name <prefix>");
            return;
        }
        List<MusicBand> result = collectionManager.filterStartsWith(args[0]);
        if (result.isEmpty()) {
            System.out.println("No bands found");
            return;
        }
        result.forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Filter bands by name prefix";
    }
}