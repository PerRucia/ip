package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;
import rucia.utils.TaskStorageUtil;
import java.io.IOException;

/**
 * Represents a command to mark a task as done in the task list.
 * Handles updating the task status, saving changes to storage,
 * and notifying the user.
 */
public class MarkCommand implements Command {
    private int taskIndex;
    private Storage storage;

    /**
     * Constructs a MarkCommand with the specified task index from user input
     * and initializes the storage handler.
     *
     * @param input   The user input containing the task number to mark as done.
     * @param storage The storage handler to save the updated task list.
     * @throws IllegalArgumentException if the input does not contain a valid task number.
     */
    public MarkCommand(String input, Storage storage) {
        try {
            this.taskIndex = Integer.parseInt(input.substring(4).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a valid task number.");
        }
        this.storage = storage;
    }

    /**
     * Executes the mark command by marking the specified task as done,
     * saving the updated task list to storage, and displaying a confirmation message.
     * If the task is already marked as done, an appropriate message is shown.
     *
     * @param taskList The current task list containing tasks to be updated.
     * @param message  The message object to display messages.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
            return Message.showError("Invalid task index. It's not rocket science.");
        }
        if (taskList.isTaskDone(taskIndex)) {
            return Message.showError("Task is already marked as done. Are you forgetful or what?");
        }
        taskList.markTask(taskIndex);
        try {
            TaskStorageUtil.saveTasks(taskList, storage);
            message.addMessage("Marked task as done - " + taskList.getTask(taskIndex));
            message.addMessage("Finally, some progress.");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
        return message.getMessage();
    }
}