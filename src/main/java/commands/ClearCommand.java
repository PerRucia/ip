package commands;

import tasks.TaskList;
import ui.Message;
import utils.Storage;

import java.io.IOException;

/**
 * The ClearCommand class implements the Command interface and is used to handle the "clear" command.
 * This command clears all tasks from the task list and updates the storage.
 */
public class ClearCommand implements Command {
    private Storage storage;

    /**
     * Constructs a ClearCommand with the specified storage.
     *
     * @param storage The storage to update after clearing tasks.
     */
    public ClearCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes the "clear" command, which clears all tasks from the task list and updates the storage.
     *
     * @param taskList The list of tasks to be cleared.
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        taskList.clearTasks();
        try {
            storage.saveTasksToFile(taskList.getTasks());
            message.addMessage("All tasks have been cleared.");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }

        return message.getMessage();
    }
}