package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

/**
 * Represents a command to display all tasks in the task list.
 * Lists each task with its corresponding index.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, an appropriate message is shown.
     *
     * @param taskList The current task list containing tasks to be displayed.
     * @param message  The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        if (taskList.getTaskSize() == 0) {
            message.addMessage("Your task list is empty. Someone is free...");
        } else {
            message.addMessage("Here are your tasks. Try not to forget them:");
            for (int i = 0; i < taskList.getTaskSize(); i++) {
                message.addMessage((i + 1) + ". " + taskList.getTask(i));
            }
        }
        return message.getMessage();
    }
}