package commands;

import managers.CollectionManager;

public class RemoveByIdCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: remove_by_id <id>");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collectionManager.removeById(id)) {
                System.out.println("Band removed successfully");
            } else {
                System.out.println("Band with id " + id + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: id must be a number");
        }
    }

    @Override
    public String getDescription() {
        return "Remove band by id";
    }
}