package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

/**
 * Represents a command to display help information to the user.
 * Lists all available commands with brief descriptions.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command by displaying a list of available commands
     * along with their descriptions to the user.
     *
     * @param taskList The current task list (not used in this command).
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {;
        String helpMessage = "Looks like you need help after all huh?:\n"
                + "1. bye - Exit the application\n"
                + "2. add <task description> - Add a new task\n"
                + "3. deadline <task> /by <date> - Add a new deadline task\n"
                + "4. event <task> /from <start> /to <end> - Add a new event task\n"
                + "5. note <title> | <description> - Add a new note\n"
                + "6. list - List all tasks\n"
                + "7. mark <task number> - Mark a task as done\n"
                + "8. unmark <task number> - Mark a task as not done\n"
                + "9. delete <task number> - Delete a task\n"
                + "10. list_day <dd/MM/yyyy> - List tasks for a specific day\n"
                + "11. find <keyword> - Find tasks containing the keyword\n"
                + "12. help or ? - Show this help message";
        message.addMessage(helpMessage);
        return message.getMessage();
    }
}