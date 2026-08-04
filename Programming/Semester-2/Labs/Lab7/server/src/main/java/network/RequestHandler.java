package network;

import commands.CommandProcessor;
import database.MusicBandDAO;
import database.UserDAO;
import managers.CollectionManager;

public class RequestHandler {
    private final UserDAO userDAO = new UserDAO();
    private final MusicBandDAO musicBandDAO;
    private final CollectionManager collectionManager;

    public RequestHandler(CollectionManager collectionManager, MusicBandDAO musicBandDAO) {
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
    }

    public Response handle(Request request) {
        // LOGIN
        if (request.getCommandType() == CommandType.LOGIN) {
            String[] creds = (String[]) request.getArgument();
            UserCredentials auth = userDAO.authenticate(creds[0], creds[1]);
            if (auth != null) {
                // Store the validated credentials (the client will send them again in next requests)
                request.setCredentials(auth);
                return new Response("Login successful", null, auth.getUserId());
            }
            return new Response("Invalid username or password", null);
        }

        // REGISTER
        if (request.getCommandType() == CommandType.REGISTER) {
            String[] creds = (String[]) request.getArgument();
            boolean ok = userDAO.register(creds[0], creds[1]);
            if (ok) {
                return new Response("Registration successful. Please login.", null);
            }
            return new Response("Username already exists", null);
        }

        // For all other commands: re‑authenticate using the credentials from the request
        UserCredentials creds = request.getCredentials();
        if (creds == null || creds.getUsername() == null || creds.getPassword() == null) {
            return new Response("Not authenticated. Please LOGIN first.", null);
        }

        // Validate credentials against DB
        UserCredentials validated = userDAO.authenticate(creds.getUsername(), creds.getPassword());
        if (validated == null) {
            return new Response("Authentication failed. Please login again.", null);
        }

        // Use the validated userId for the command
        creds.setUserId(validated.getUserId());
        creds.setAuthenticated(true);
        request.setCredentials(creds);

        return new CommandProcessor(collectionManager, musicBandDAO).execute(request);
    }
}