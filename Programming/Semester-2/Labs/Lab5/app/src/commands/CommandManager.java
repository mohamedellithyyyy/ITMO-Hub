package commands;

import managers.CollectionManager;
import managers.FileManager;
import managers.InputManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    private Map<String, Command> commands;
    private Scanner scanner;

    public CommandManager(CollectionManager collectionManager, FileManager fileManager) {
        this.scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager(scanner);
        this.commands = new HashMap<>();

        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(collectionManager, this);

        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, inputManager));
        commands.put("update", new UpdateCommand(collectionManager, inputManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", executeScriptCommand);
        commands.put("exit", new ExitCommand());
        commands.put("head", new HeadCommand(collectionManager));
        commands.put("remove_head", new RemoveHeadCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, inputManager));
        commands.put("sum_of_number_of_participants", new SumOfParticipantsCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commands.put("print_unique_number_of_participants", new PrintUniqueParticipantsCommand(collectionManager));
    }

    public void executeCommand(String commandName, String[] args) {
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command: " + commandName + ". Type 'help'.");
            return;
        }
        command.execute(args);
    }

    public void run() {
        System.out.println("Program started. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            String[] parts = input.split(" ", 2);
            String commandName = parts[0].toLowerCase();
            String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];
            executeCommand(commandName, args);
        }
    }
}