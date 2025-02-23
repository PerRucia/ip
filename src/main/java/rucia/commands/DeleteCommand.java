package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;
import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private String input;
    private Storage storage;

    /**
     * Constructs a DeleteCommand with the specified input and storage.
     *
     * @param input The input string containing the task number to be deleted.
     * @param storage The storage to save the updated task list.
     */
    public DeleteCommand(String input, Storage storage) {
        this.input = input;
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
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            String taskDescription = taskList.getTask(taskIndex).toString();
            taskList.deleteTask(taskIndex);
            storage.saveToFile(taskList.getTasks());

            message.addMessage("Deleted task - " + taskDescription + ". Cleaning up your mess, I see.");
            message.addMessage("You now have " + taskList.getSize() + " entries in your list.");
            return message.getMessage();

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return Message.showError("Invalid task number. Pay attention!");
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
    }
}