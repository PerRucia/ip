package rucia.commands;

import rucia.tasks.Deadline;
import rucia.tasks.Event;
import rucia.tasks.Task;
import rucia.tasks.TaskList;
import rucia.ui.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to list tasks scheduled for a specific day.
 * Filters and displays deadlines and events that occur on the specified date.
 */
public class ListDayCommand implements Command {
    private LocalDate date;

    /**
     * Constructs a ListDayCommand with the specified date input.
     * Parses the date from the user input.
     *
     * @param input The user input containing the date in dd/MM/yyyy format.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public ListDayCommand(String input) {
        try {
            String dateString = input.substring(8).trim();
            this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use: list_day <dd/MM/yyyy>");
        }
    }

    /**
     * Executes the list day command by filtering tasks scheduled for the specified date.
     * Displays the list of matching tasks or a message if no tasks are found.
     *
     * @param taskList The current task list containing tasks to be filtered.
     * @param message  The message object to display messages on JavaFX.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        List<Task> tasksForDay = taskList.getTasks().stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        LocalDateTime byDateTime = ((Deadline) task).getByDateTime();
                        return byDateTime.toLocalDate().equals(date);
                    } else if (task instanceof Event) {
                        LocalDateTime fromDateTime = ((Event) task).getFromDateTime();
                        LocalDateTime toDateTime = ((Event) task).getToDateTime();
                        return !fromDateTime.toLocalDate().isAfter(date) && !toDateTime.toLocalDate().isBefore(date);
                    }
                    return false;
                })
                .collect(Collectors.toList());

        if (tasksForDay.isEmpty()) {
            message.addMessage("No tasks found for " + date);
        } else {
            message.addMessage("Tasks for " + date + ":");
            for (Task task : tasksForDay) {
                message.addMessage(task.toString());
            }
        }
        return message.getMessage();
    }
}