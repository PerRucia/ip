package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Message;
import utils.Storage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The FindCommand class implements the Command interface and is responsible for
 * finding tasks that contain specific keywords in their description.
 *
 * <p>This class allows for multiple keywords to be specified, and it will search
 * for tasks that contain any of the provided keywords. If no matching tasks are found,
 * an appropriate message is displayed to the user.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * FindCommand findCommand = new FindCommand("keyword1", "keyword2");
 * findCommand.execute(taskList, message);
 * }
 * </pre>
 */
public class FindCommand implements Command {
    private String[] keywords;

    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    /**
     * Executes the find command by searching for tasks that contain any of the specified keywords
     * in their description. Displays the matching tasks or an appropriate message if no tasks are found.
     * @param taskList The current task list to search for matching tasks.
     * @param message  The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> {
                    for (String keyword : keywords) {
                        if (task.getDescription().contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            message.addMessage("No tasks found with the keywords: " + String.join(", ", keywords));
        } else {
            message.addMessage("Tasks found with the keywords: " + String.join(", ", keywords));
            for (Task task : matchingTasks) {
                message.addMessage(task.toString());
            }
        }
        return message.getMessage();
    }
}