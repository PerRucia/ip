package rucia.commands;

import rucia.tasks.TaskList;
import rucia.tasks.Deadline;
import rucia.ui.Message;
import rucia.utils.Storage;
import rucia.utils.TaskStorageUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task to the task list.
 * Handles parsing of date-time input, creation of the deadline task,
 * updating the task list, and saving the tasks to persistent storage.
 */
public class DeadlineCommand implements Command {
    private String taskDescription;
    private String dateTimeString;
    private Storage storage;

    /**
     * Formatter to parse date-time strings in the format "dd/MM/yyyy HHmm".
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a DeadlineCommand with the specified task description, date-time string, and storage handler.
     *
     * @param taskDescription The description of the deadline task.
     * @param dateTimeString  The deadline date and time in string format.
     * @param storage         The storage handler to save the task.
     */
    public DeadlineCommand(String taskDescription, String dateTimeString, Storage storage) {
        this.taskDescription = taskDescription;
        this.dateTimeString = dateTimeString;
        this.storage = storage;
    }

    /**
     * Executes the deadline command by adding a new deadline task to the task list,
     * saving the updated list to storage, and displaying appropriate messages to the user.
     *
     * @param taskList The current task list to which the new deadline task will be added.
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        try {
            if (dateTimeString.length() == 10) {
                dateTimeString += " 1200";  // Default time if only date is provided
            }
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString.trim(), DATE_TIME_FORMATTER);
            taskList.addTask(new Deadline(taskDescription, dateTime.toEpochSecond(ZoneOffset.UTC)));
            TaskStorageUtil.saveEntries(taskList, storage);

            message.addMessage("Added Deadline task - " + taskDescription + " (by: " +
                    dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")");
            message.addMessage("You now have " + taskList.getTaskSize() + " entries in your list. " +
                    "Impressive, I guess.");
            return message.getMessage();

        } catch (DateTimeParseException e) {
            return Message.showError("Invalid date format. Use: dd/MM/yyyy HHmm " +
                    "(e.g., 02/03/2019 1800). It's not that hard.");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
    }
}