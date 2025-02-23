package rucia.commands;

import rucia.tasks.Note;
import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;

/**
 * Represents a command to add a new Note to the task list.
 */
public class NoteCommand implements Command {
    private String title;
    private String description;
    private Storage storage;

    /**
     * Constructs a NoteCommand with the specified title and description.
     *
     * @param title The title of the note.
     * @param description The description of the note.
     */
    public NoteCommand(String title, String description, Storage storage) {
        this.title = title;
        this.description = description;
        this.storage = storage;
    }

    /**
     * Executes the note command by adding a new Note to the task list.
     *
     * @param taskList The current task list to add the note to.
     * @param message  The message object to display messages on JavaFX.
     * @return A response message indicating the note has been added.
     */
    @Override
    public String execute(TaskList taskList, Message message) {
        Note note = new Note(title, description);
        taskList.addTask(note);
        message.addMessage("Added Note - " + title);
        message.addMessage("You now have " + taskList.getSize() + " entries in your list." +
                " Impressive, I guess.");
        return message.getMessage();
    }
}