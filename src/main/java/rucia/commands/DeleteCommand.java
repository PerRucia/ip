package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;
import rucia.utils.TaskStorageUtil;
import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * Handles the removal of a task by its index and updates the storage accordingly.
 */
public class DeleteCommand implements Command {
    private int taskIndex;
    private Storage storage;

    /**
     * Constructs a DeleteCommand with the specified input string and storage handler.
     * Extracts the task index from the input.
     *
     * @param input   The user input containing the task number to delete.
     * @param storage The storage handler to update the task list after deletion.
     * @throws IllegalArgumentException if the input does not contain a valid task number.
     */
    public DeleteCommand(String input, Storage storage) {
        try {
            this.taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a valid task number.");
        }
        this.storage = storage;
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated list to storage, and displaying appropriate messages to the user.
     *
     * @param taskList The current task list from which the task will be deleted.
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
            return Message.showError("Invalid task index.");
        }
        String taskDescription = taskList.getTask(taskIndex).getDescription();
        taskList.deleteTask(taskIndex);
        try {
            TaskStorageUtil.saveTasks(taskList, storage);
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }

        message.addMessage("Deleted entry - " + taskDescription);
        message.addMessage("You now have " + taskList.getSize() + " entries in your list.");

        return message.getMessage();
    }
}