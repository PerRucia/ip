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
                + "1. add <task description> - Add a new task\n"
                + "2. deadline <task> /by <date> - Add a new deadline task\n"
                + "3. event <task> /from <start> /to <end> - Add a new event task\n"
                + "4. list - List all tasks\n"
                + "5. mark <task number> - Mark a task as done\n"
                + "6. unmark <task number> - Mark a task as not done\n"
                + "7. delete <task number> - Delete a task\n"
                + "8. list_day <dd/MM/yyyy> - List tasks for a specific day\n"
                + "9. clear - Deletes all tasks\n"
                + "10. find <keyword> - Find tasks containing the keyword\n"
                + "11. help notes - Show notes-related commands\n"
                + "12. bye - Exit the application\n"
                + "13. help or ? - Show this help message";
        message.addMessage(helpMessage);
        return message.getMessage();
    }
}