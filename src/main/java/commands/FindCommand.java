// src/main/java/commands/FindCommand.java
package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
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
 * findCommand.execute(taskList, ui);
 * }
 * </pre>
 */
public class FindCommand implements Command {
    private String[] keywords;

    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
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
            ui.showMessage("No tasks found with the keywords: " + String.join(", ", keywords));
        } else {
            ui.showMessage("Tasks found with the keywords: " + String.join(", ", keywords));
            for (Task task : matchingTasks) {
                ui.showMessage(task.toString());
            }
        }
    }
}