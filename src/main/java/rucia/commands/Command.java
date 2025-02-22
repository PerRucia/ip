// src/main/java/commands/Command.java

package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

/**
 * The Command interface defines the structure for all command classes.
 * Each command must implement the execute method to perform its specific action.
 */
public interface Command {
    /**
     * Executes the command with the given task list and user interface.
     *
     * @param taskList The list of tasks to be manipulated by the command.
     * @param message The message object to display messages on JavaFX.
     */
    String execute(TaskList taskList, Message message);
}