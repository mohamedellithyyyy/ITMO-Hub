package network;
import java.io.Serializable;

/**
 * The type Request.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private CommandType commandType;
    private Object argument;

    /**
     * Instantiates a new Request.
     *
     * @param commandType the command type
     * @param argument    the argument
     */
    public Request(CommandType commandType, Object argument) {
        this.commandType = commandType;
        this.argument = argument;
    }

    /**
     * Gets command type.
     *
     * @return the command type
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Gets argument.
     *
     * @return the argument
     */
    public Object getArgument() {
        return argument;
    }

    /**
     * Sets command type.
     *
     * @param commandType the command type
     */
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Sets argument.
     *
     * @param argument the argument
     */
    public void setArgument(Object argument) {
        this.argument = argument;
    }
}