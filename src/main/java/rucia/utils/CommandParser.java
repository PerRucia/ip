package rucia.utils;

import rucia.commands.*;

/**
 * Utility class for parsing user input into specific command objects.
 * Determines the appropriate command to execute based on the input and command type.
 */
public class CommandParser {

    private static final int ADD_COMMAND_LENGTH = 3;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int FIND_COMMAND_LENGTH = 4;
    private static final int NOTE_COMMAND_LENGTH = 4;

    /**
     * Parses the user input and returns the corresponding command object.
     *
     * @param input       The user input string.
     * @param commandType The type of command identified from the input.
     * @param storage     The storage handler for saving task-related changes.
     * @return The command object corresponding to the input.
     * @throws IllegalArgumentException if the input format is invalid or the command type is unknown.
     */
    public static Command parse(String input, CommandType commandType, Storage storage) {
        switch (commandType) {
            case BYE:
                return new ByeCommand();
            case ADD:
                String taskDescription = input.substring(ADD_COMMAND_LENGTH).trim();
                if (taskDescription.isEmpty()) {
                    throw new IllegalArgumentException("Task description cannot be empty. Use: add <task " +
                            "description>. It's not rocket science.");
                }
                return new AddCommand(taskDescription, storage);
            case DEADLINE:
                String[] parts = input.substring(DEADLINE_COMMAND_LENGTH).trim().split(" /by ");
                if (parts.length < 2) {
                    throw new IllegalArgumentException("Invalid input format. Use: deadline <task> /by <date>. " +
                            "Seriously, how hard is it?");
                }
                return new DeadlineCommand(parts[0].trim(), parts[1].trim(), storage);
            case EVENT:
                String[] eventParts = input.substring(EVENT_COMMAND_LENGTH).trim().split(" /from ");
                if (eventParts.length < 2) {
                    throw new IllegalArgumentException("Invalid input format. Use: event <task> /from <start> /to " +
                            "<end>. Pay attention!");
                }
                String description = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new IllegalArgumentException("Invalid input format. Use: event <task> /from <start> /to " +
                            "<end>. It's not that complicated.");
                }
                return new EventCommand(description, timeParts[0].trim(), timeParts[1].trim(), storage);
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(input, storage);
            case UNMARK:
                return new UnmarkCommand(input, storage);
            case DELETE:
                return new DeleteCommand(input, storage);
            case LIST_DAY:
                return new ListDayCommand(input);
            case HELP:
                return new HelpCommand();
            case CLEAR:
                return new ClearCommand(storage);
            case FIND:
                String[] keywords = input.substring(FIND_COMMAND_LENGTH).trim().split("\\s+");
                if (keywords.length == 0) {
                    throw new IllegalArgumentException("Keyword(s) cannot be empty. Use: find <keyword1> <keyword2> " +
                            "... Come on, it's not that hard.");
                }
                return new FindCommand(keywords);
            case CHEER:
                return new CheerCommand();
            case NOTE:
                String[] noteParts = input.substring(NOTE_COMMAND_LENGTH).trim().split(" /desc ");
                if (noteParts.length < 2) {
                    throw new IllegalArgumentException("Invalid input format. Use: note <Title> /desc " +
                            "<Description>. Try to keep up.");
                }
                return new NoteCommand(noteParts[0].trim(), noteParts[1].trim(), storage);
            case NOTES:
                return new ListNotesCommand();
            case HELP_NOTES:
                return new HelpNotesCommand();
            default:
                throw new IllegalArgumentException("Unknown command type. Are you even trying?");
        }
    }
}