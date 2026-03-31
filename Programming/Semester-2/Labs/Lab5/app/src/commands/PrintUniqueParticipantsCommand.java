package commands;

import managers.CollectionManager;

import java.util.List;

public class PrintUniqueParticipantsCommand implements Command {
    private CollectionManager collectionManager;

    public PrintUniqueParticipantsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        List<Long> unique = collectionManager.uniqueParticipants();
        if (unique.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        unique.forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Print unique numberOfParticipants values";
    }
}