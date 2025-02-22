package rucia.driver;

import rucia.utils.CommandIdentifier;
import rucia.utils.CommandParser;
import rucia.utils.Storage;
import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.commands.Command;

import java.io.IOException;

/**
 * Rucia is a personal assistant chatbot that helps users with basic commands.
 * This class handles greeting the user, managing tasks, providing help, and exiting when requested.
 * The main command-processing logic is encapsulated in {@code getResponse}, which processes a single user command
 * and returns a response string.
 */
public class Rucia {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Rucia instance and initializes the UI, storage, and task list.
     */
    public Rucia() {
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Processes a single user command and returns the chatbot's response.
     * <p>
     * The method identifies and parses the input command, then executes it.
     * It is assumed that {@code Command.execute} has been refactored to return a response string.
     * If an error occurs during parsing or execution, the error message is returned.
     * </p>
     *
     * @param input The command input string provided by the user.
     * @return A response message generated after processing the command.
     */
    public String getResponse(String input) {
        String commandType = CommandIdentifier.identify(input);
        try {
            Command command = CommandParser.parse(input, commandType, storage);
            return command.execute(taskList, new Message());
        } catch (IllegalArgumentException e) {
            return Message.showError(e.getMessage());
        }
    }

    /**
     * Returns a welcome message to be displayed when the user starts the application.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return Message.showWelcome();
    }
}
