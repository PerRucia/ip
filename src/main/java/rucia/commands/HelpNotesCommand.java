package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

/**
 * Represents a command to display help information to the user.
 * Lists all available commands with brief descriptions.
 */
public class HelpNotesCommand implements Command {

    /**
     * Executes the help command by displaying a list of available commands
     * along with their descriptions to the user.
     *
     * @param taskList The current task list (not used in this command).
     * @param message The message object to display messages on JavaFX.
     */
    public String execute(TaskList taskList, Message message) {;
        String helpMessage = "It's just note-taking, how hard can it be?:\n"
                + "1. note <title> /desc <description> - Add a new note\n"
                + "2. notes - List all notes\n"
                + "3. delete_note <note number> - Delete a note\n"
                + "4. view_note <note number> - View details about a note\n";
        message.addMessage(helpMessage);
        return message.getMessage();
    }
}