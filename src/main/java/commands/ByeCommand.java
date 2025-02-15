// src/main/java/commands/ByeCommand.java

package commands;

import tasks.TaskList;
import ui.Message;

/**
 * The ByeCommand class implements the Command interface and is used to handle the "bye" command.
 * This command signals the application to exit.
 */
public class ByeCommand implements Command {

    /**
     * Executes the "bye" command, which shows the exit message and terminates the application.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        return Message.showExit();
    }
}