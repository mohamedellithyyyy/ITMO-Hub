package commands;

import managers.CollectionManager;
import managers.InputManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private CollectionManager collectionManager;
    private CommandManager commandManager;

    public ExecuteScriptCommand(CollectionManager collectionManager, CommandManager commandManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: execute_script <file_name>");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                System.out.println("> " + line);
                String[] parts = line.split(" ", 2);
                String commandName = parts[0].toLowerCase();
                String[] cmdArgs = parts.length > 1 ? parts[1].split(" ") : new String[0];
                commandManager.executeCommand(commandName, cmdArgs);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading script: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Execute commands from a script file";
    }
}