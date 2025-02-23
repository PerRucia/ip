// src/main/java/utils/CommandIdentifier.java
package rucia.utils;

/**
 * Utility class for identifying commands from user input.
 * Determines the type of command based on specific keywords.
 */
public class CommandIdentifier {

    /**
     * Identifies the command type from the given user input.
     *
     * @param input The user input string.
     * @return A string representing the identified command type,
     *         such as "add", "deadline", "event", "list", "mark",
     *         "unmark", "delete", "list_day", or "unknown" if no match is found.
     */
    public static CommandType identify(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.trim().equalsIgnoreCase("help notes")) {
            return CommandType.HELP_NOTES;
        } else if (input.trim().equalsIgnoreCase("help") || input.trim().equals("?")) {
            return CommandType.HELP;
        } else if (input.trim().equalsIgnoreCase("clear")) {
            return CommandType.CLEAR;
        } else if (input.startsWith("add")) {
            return CommandType.ADD;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.trim().equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("delete")) {
            if (input.trim().startsWith("delete_note")) {
                return CommandType.DELETE_NOTE;
            } else {
                return CommandType.DELETE;
            }
        } else if (input.startsWith("list_day")) {
            return CommandType.LIST_DAY;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else if (input.trim().equalsIgnoreCase("cheer")) {
            return CommandType.CHEER;
        } else if (input.startsWith("note")) {
            if (input.trim().equalsIgnoreCase("notes")) {
                return CommandType.NOTES;
            } else {
                return CommandType.NOTE;
            }
        } else {
            return CommandType.UNKNOWN;
        }
    }
}