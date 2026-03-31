package commands;

import managers.CollectionManager;

public class SumOfParticipantsCommand implements Command {
    private CollectionManager collectionManager;

    public SumOfParticipantsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Sum of participants: " + collectionManager.sumOfParticipants());
    }

    @Override
    public String getDescription() {
        return "Show sum of numberOfParticipants";
    }
}