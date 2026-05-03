package commands;

import managers.CollectionManager;
import models.MusicBand;
import network.CommandType;
import network.Request;
import network.Response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Command processor.
 */
public class CommandProcessor {

    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Command processor.
     *
     * @param collectionManager the collection manager
     */
    public CommandProcessor(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Execute response.
     *
     * @param request the request
     * @return the response
     */
    public Response execute(Request request) {

        CommandType type = request.getCommandType();
        Object arg = request.getArgument();

        try {
            switch (type) {

                case HELP:
                    return new Response(
                            "add - Add a new band\n" +
                                    "clear - Clear the collection\n" +
                                    "execute_script - Execute commands from a script file. Usage: execute_script <file_name>\n" +
                                    "exit - Exit the program\n" +
                                    "filter_starts_with_name - Filter bands by name prefix. Usage: filter_starts_with_name <prefix>\n" +
                                    "head - Show first element\n" +
                                    "help - Show available commands\n" +
                                    "info - Show collection info\n" +
                                    "print_unique_number_of_participants - Print unique numberOfParticipants values\n" +
                                    "remove_by_id - Remove band by id. Usage: remove_by_id <id>\n" +
                                    "remove_head - Remove and display the first element of the collection\n" +
                                    "remove_lower - Remove all bands lower than the given element\n" +
                                    "show - Show all bands\n" +
                                    "sum_of_number_of_participants - Show sum of numberOfParticipants\n" +
                                    "update - Update band by id. Usage: update <id>",
                            null
                    );

                case INFO:
                    return new Response(collectionManager.info(), null);

                case SHOW:
                    List<MusicBand> sorted = collectionManager.getCollection()
                            .stream()
                            .sorted(Comparator.comparing(b ->
                                    b.getFrontMan() != null && b.getFrontMan().getLocation() != null
                                            ? b.getFrontMan().getLocation().toString()
                                            : ""
                            ))
                            .collect(Collectors.toList());
                    return new Response("Empty", sorted);

                case ADD:
                    return new Response(collectionManager.add(arg), null);

                case UPDATE:
                    return new Response(collectionManager.update(arg), null);

                case REMOVE_BY_ID:
                    return new Response(collectionManager.removeById(arg), null);

                case CLEAR:
                    collectionManager.clear();
                    return new Response("Collection cleared", null);

                case HEAD:
                    return new Response(collectionManager.head(), null);

                case REMOVE_HEAD:
                    return new Response(collectionManager.removeHead(), null);

                case REMOVE_LOWER:
                    return new Response(collectionManager.removeLower(arg), null);

                case SUM_OF_NUMBER_OF_PARTICIPANTS:
                    return new Response(
                            String.valueOf(collectionManager.sumOfParticipants()),
                            null
                    );

                case FILTER_STARTS_WITH_NAME:
                    List<MusicBand> filtered = collectionManager.filterStartsWithName(arg);
                    String filterMsg = filtered.isEmpty() ? "No bands found" : "Filtered result";
                    return new Response(filterMsg, filtered);

                case PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS:
                    return new Response(
                            collectionManager.printUniqueParticipants().toString(),
                            null
                    );

                case EXECUTE_SCRIPT:
                    return new Response("Script executed on server", null);

                case EXIT:
                    return new Response("Client exited", null);

                default:
                    return new Response("Unknown command", null);
            }

        } catch (Exception e) {
            return new Response("Error: " + e.getMessage(), null);
        }
    }
}