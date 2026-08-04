package network;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 2L;

    private CommandType commandType;
    private Object argument;
    private UserCredentials credentials;

    public Request(CommandType commandType, Object argument, UserCredentials credentials) {
        this.commandType = commandType;
        this.argument = argument;
        this.credentials = credentials;
    }

    public Request(CommandType commandType, Object argument) {
        this(commandType, argument, null);
    }

    public CommandType getCommandType() { return commandType; }
    public Object getArgument() { return argument; }
    public UserCredentials getCredentials() { return credentials; }
    public void setCredentials(UserCredentials credentials) { this.credentials = credentials; }
}
