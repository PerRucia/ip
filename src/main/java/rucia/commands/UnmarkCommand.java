package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;
import rucia.utils.TaskStorageUtil;
import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the task list.
 * Handles updating the task status, saving changes to storage,
 * and notifying the user.
 */
public class UnmarkCommand implements Command {
    private int taskIndex;
    private Storage storage;

    /**
     * Constructs an UnmarkCommand with the specified task index from user input
     * and initializes the storage handler.
     *
     * @param input   The user input containing the task number to unmark as not done.
     * @param storage The storage handler to save the updated task list.
     * @throws IllegalArgumentException if the input does not contain a valid task number.
     */
    public UnmarkCommand(String input, Storage storage) {
        try {
            this.taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a valid task number.");
        }
        this.storage = storage;
    }

    /**
     * Executes the unmark command by unmarking the specified task as not done,
     * saving the updated task list to storage, and displaying a confirmation message.
     * If the task is already unmarked, an appropriate message is shown.
     *
     * @param taskList The current task list containing tasks to be updated.
     * @param message  The message object to display messages.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
            return Message.showError("Invalid task index. It's just a number, come on.");
        }
        if (!taskList.isTaskDone(taskIndex)) {
            return Message.showError("Task is already unmarked. Even a goldfish has a better memory.");
        }
        taskList.unmarkTask(taskIndex);
        try {
            TaskStorageUtil.saveTasks(taskList, storage);
            message.addMessage("Unmarked task as not done - " + taskList.getTask(taskIndex));
            message.addMessage("You're back to square one.");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
        return message.getMessage();
    }
}