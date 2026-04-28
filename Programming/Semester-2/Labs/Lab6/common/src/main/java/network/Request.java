import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private CommandType commandType;
    private Object argument;

    public Request(CommandType commandType, Object argument) {
        this.commandType = commandType;
        this.argument = argument;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Object getArgument() {
        return argument;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }
}