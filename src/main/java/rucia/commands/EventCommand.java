package rucia.commands;

import rucia.tasks.TaskList;
import rucia.tasks.Event;
import rucia.ui.Message;
import rucia.utils.Storage;
import rucia.utils.TaskStorageUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an event task to the task list.
 * Handles parsing of start and end date-time inputs, creation of the event task,
 * updating the task list, and saving the tasks to persistent storage.
 */
public class EventCommand implements Command {
    private String description;
    private String fromDateTimeString;
    private String toDateTimeString;
    private Storage storage;

    /**
     * Formatter to parse date-time strings in the format "dd/MM/yyyy HHmm".
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs an EventCommand with the specified description, start and end date-time strings, and storage handler.
     *
     * @param description        The description of the event.
     * @param fromDateTimeString The start date and time in string format.
     * @param toDateTimeString   The end date and time in string format.
     * @param storage            The storage handler to save the task.
     */
    public EventCommand(String description, String fromDateTimeString, String toDateTimeString, Storage storage) {
        this.description = description;
        this.fromDateTimeString = fromDateTimeString;
        this.toDateTimeString = toDateTimeString;
        this.storage = storage;
    }

    /**
     * Executes the event command by adding a new event task to the task list,
     * saving the updated list to storage, and displaying appropriate messages to the user.
     *
     * @param taskList The current task list to which the new event task will be added.
     * @param message  The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        try {
            if (fromDateTimeString.length() == 10) {
                fromDateTimeString += " 1200";  // Default time if only date is provided
            }
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, DATE_TIME_FORMATTER);

            if (toDateTimeString.length() == 10) {
                toDateTimeString += " 1200";  // Default time if only date is provided
            }
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, DATE_TIME_FORMATTER);

            taskList.addTask(new Event(description, fromDateTime.toEpochSecond(ZoneOffset.UTC),
                    toDateTime.toEpochSecond(ZoneOffset.UTC)));
            TaskStorageUtil.saveEntries(taskList, storage);

            message.addMessage("Added Event task - " + description + " (from: " +
                    fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + " to: " +
                    toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")");
            message.addMessage("You now have " + taskList.getTaskSize() + " task(s) in your list. " +
                    "Impressive, I guess.");
            return message.getMessage();
        } catch (DateTimeParseException e) {
            return Message.showError("Invalid date format. Use: dd/MM/yyyy HHmm (e.g., 02/03/2019 1800)");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
    }
}