package commands;

import database.MusicBandDAO;
import managers.CollectionManager;
import models.MusicBand;
import network.*;

import java.util.Comparator;
import java.util.List;

public class CommandProcessor {
    private final CollectionManager collectionManager;
    private final MusicBandDAO musicBandDAO;

    public CommandProcessor(CollectionManager collectionManager, MusicBandDAO musicBandDAO) {
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
    }

    public Response execute(Request request) {
        CommandType type = request.getCommandType();
        Object arg = request.getArgument();
        int userId = request.getCredentials().getUserId();

        try {
            switch (type) {
                case HELP:
                    String helpText =
                            "Available commands:\n" +
                                    "  add                            - Add a new music band\n" +
                                    "  clear                          - Remove all your bands\n" +
                                    "  execute_script <file>          - Execute commands from script file\n" +
                                    "  exit                           - Exit the client\n" +
                                    "  filter_starts_with_name <name> - Show bands whose name starts with prefix\n" +
                                    "  head                           - Show the first band in collection (peek)\n" +
                                    "  help                           - Show this help message\n" +
                                    "  info                           - Show collection info (type, size, init date)\n" +
                                    "  print_unique_number_of_participants - Show unique participant counts\n" +
                                    "  remove_by_id <id>              - Remove your band by ID\n" +
                                    "  remove_head                    - Remove and show your first band (must be yours)\n" +
                                    "  remove_lower                   - Remove all your bands with fewer participants\n" +
                                    "  show                           - Show all bands (sorted by location)\n" +
                                    "  sum_of_number_of_participants  - Show sum of participants of all bands\n" +
                                    "  update <id>                    - Update your band by ID";
                    return new Response(helpText, null);
                case INFO:
                    return new Response(collectionManager.info(), null);
                case SHOW:
                    List<MusicBand> all = collectionManager.getAll();
                    all.sort(Comparator.comparing(b -> b.getFrontMan() == null ? "" :
                            b.getFrontMan().getLocation() == null ? "" : b.getFrontMan().getLocation().toString()));
                    return new Response("All bands", all);
                case ADD:
                    MusicBand newBand = (MusicBand) arg;
                    int generatedId = musicBandDAO.insertBand(newBand, userId);
                    if (generatedId > 0) {
                        newBand.setId(generatedId);
                        newBand.setOwnerId(userId);
                        newBand.setOwnerUsername(request.getCredentials().getUsername());
                        collectionManager.add(newBand);
                        return new Response("Added successfully with ID " + generatedId, null);
                    }
                    return new Response("Failed to add band", null);
                case UPDATE:
                    UpdateRequest updReq = (UpdateRequest) arg;
                    MusicBand updated = updReq.getBand();
                    updated.setId(updReq.getId());
                    boolean updatedOk = musicBandDAO.updateBand(updated, userId);
                    if (updatedOk) {
                        collectionManager.update(updated);
                        return new Response("Updated successfully", null);
                    }
                    return new Response("Update failed: not found or not your band", null);
                case REMOVE_BY_ID:
                    int id = (int) arg;
                    boolean deleted = musicBandDAO.deleteBand(id, userId);
                    if (deleted) {
                        collectionManager.removeById(id);
                        return new Response("Removed successfully", null);
                    }
                    return new Response("Remove failed: not found or not your band", null);
                case CLEAR:
                    musicBandDAO.clearByUser(userId);
                    collectionManager.clearByUser(userId);
                    return new Response("All your bands cleared", null);
                case HEAD:
                    MusicBand head = collectionManager.head();
                    return new Response(head == null ? "Collection is empty" : head.toString(), null);
                case REMOVE_HEAD:
                    MusicBand headBand = collectionManager.head();
                    if (headBand == null) {
                        return new Response("Collection is empty", null);
                    }
                    // Verify ownership
                    if (headBand.getOwnerId() != userId) {
                        return new Response("Cannot remove head: it belongs to another user", null);
                    }
                    // Remove from DB first
                    if (musicBandDAO.deleteBand(headBand.getId(), userId)) {
                        collectionManager.pollHead();  // remove from memory
                        return new Response("Removed head: " + headBand, null);
                    }
                    return new Response("Failed to remove head from database", null);
                case REMOVE_LOWER:
                    MusicBand lowerBand = (MusicBand) arg;
                    // Delete from DB (already filters by owner)
                    boolean dbDeleted = musicBandDAO.deleteLowerBands(lowerBand, userId);
                    // Remove from memory (now passes userId to check ownership)
                    boolean memRemoved = collectionManager.removeLower(lowerBand, userId);
                    if (dbDeleted || memRemoved) {
                        return new Response("Lower elements removed", null);
                    }
                    return new Response("No lower elements found", null);
                case SUM_OF_NUMBER_OF_PARTICIPANTS:
                    return new Response(String.valueOf(collectionManager.sumOfParticipants()), null);
                case FILTER_STARTS_WITH_NAME:
                    String prefix = (String) arg;
                    List<MusicBand> filtered = collectionManager.filterStartsWithName(prefix);
                    return new Response(filtered.isEmpty() ? "No bands found" : "Filtered result", filtered);
                case PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS:
                    return new Response(collectionManager.printUniqueParticipants().toString(), null);
                case EXECUTE_SCRIPT:
                    return new Response("Script execution on server not supported", null);
                case EXIT:
                    return new Response("Goodbye", null);
                default:
                    return new Response("Unknown command", null);
            }
        } catch (Exception e) {
            return new Response("Error: " + e.getMessage(), null);
        }
    }
}