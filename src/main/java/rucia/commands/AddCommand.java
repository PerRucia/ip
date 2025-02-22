// src/main/java/commands/AddCommand.java
package rucia.commands;

import rucia.tasks.TaskList;
import rucia.tasks.ToDo;
import rucia.ui.Message;
import rucia.utils.Storage;
import java.io.IOException;

/**
 * Represents a command to add a ToDo task.
 */
public class AddCommand implements Command {
    private String taskDescription;
    private Storage storage;

    /**
     * Constructs an AddCommand with the specified task description and storage.
     *
     * @param taskDescription The description of the task to be added.
     * @param storage The storage to save the task.
     */
    public AddCommand(String taskDescription, Storage storage) {
        this.taskDescription = taskDescription;
        this.storage = storage;
    }

    /**
     * Executes the command to add a ToDo task to the task list and save it to storage.
     *
     * @param taskList The task list to add the task to.
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        taskList.addTask(new ToDo(taskDescription));
        try {
            storage.saveToFile(taskList.getTasks());
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }
        message.addMessage("Added ToDo task - " + taskDescription);
        message.addMessage("You now have " + taskList.getSize() + " entries in your list.");

        return message.getMessage();
    }
}