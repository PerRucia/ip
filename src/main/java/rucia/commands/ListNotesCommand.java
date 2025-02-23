package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

/**
 * Represents a command to display all tasks in the task list.
 * Lists each task with its corresponding index.
 */
public class ListNotesCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, an appropriate message is shown.
     *
     * @param taskList The current task list containing tasks to be displayed.
     * @param message  The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        if (taskList.getNoteSize() == 0) {
            message.addMessage("Your note list is empty. Don't you have anything to write?");
        } else {
            message.addMessage("Here are your notes:");
            for (int i = 0; i < taskList.getNoteSize(); i++) {
                message.addMessage((i + 1) + ". " + taskList.getNote(i));
            }
        }
        return message.getMessage();
    }
}