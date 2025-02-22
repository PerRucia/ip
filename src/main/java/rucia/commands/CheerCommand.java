package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * The CheerCommand class implements the Command interface and is responsible for
 * displaying a random motivational quote from a file.
 */
public class CheerCommand implements Command {
    private static final String FILE_PATH = "./data/cheer.txt";

    /**
     * Executes the CheerCommand, which reads a random quote from the file and displays it.
     *
     * @param taskList The task list (not used in this command).
     * @param message The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        try {
            List<String> quotes = Files.readAllLines(Paths.get(FILE_PATH));
            if (quotes.isEmpty()) {
                message.addMessage("No quotes available.");
            } else {
                Random random = new Random();
                String randomQuote = quotes.get(random.nextInt(quotes.size()));
                message.addMessage(randomQuote);
            }
        } catch (IOException e) {
            return Message.showError(e.getMessage());
        }

        return message.getMessage();
    }
}