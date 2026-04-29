package network;

/**
 * The enum Command type.
 */
public enum CommandType {

    /**
     * Help command type.
     */
    HELP,
    /**
     * Info command type.
     */
    INFO,
    /**
     * Show command type.
     */
    SHOW,
    /**
     * Add command type.
     */
    ADD,
    /**
     * Update command type.
     */
    UPDATE,

    /**
     * Remove by id command type.
     */
    REMOVE_BY_ID,
    /**
     * Clear command type.
     */
    CLEAR,
    /**
     * Execute script command type.
     */
    EXECUTE_SCRIPT,
    /**
     * Exit command type.
     */
    EXIT,

    /**
     * Head command type.
     */
    HEAD,
    /**
     * Remove head command type.
     */
    REMOVE_HEAD,
    /**
     * Remove lower command type.
     */
    REMOVE_LOWER,

    /**
     * Sum of number of participants command type.
     */
    SUM_OF_NUMBER_OF_PARTICIPANTS,

    /**
     * Filter starts with name command type.
     */
    FILTER_STARTS_WITH_NAME,

    /**
     * Print unique number of participants command type.
     */
    PRINT_UNIQUE_NUMBER_OF_PARTICIPANTS
}