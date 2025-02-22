package rucia.tasks;

/**
 * Represents a Note with a title and description.
 * Extends the Task class to reuse common task properties and behaviors.
 */
public class Note extends Task {
    private String title;

    /**
     * Constructs a Note with the specified title and description.
     *
     * @param title The title of the note.
     * @param description The description of the note.
     */
    public Note(String title, String description) {
        super(description);
        this.title = title;
    }

    /**
     * Retrieves the title of the note.
     *
     * @return The note title.
     */
    private String getTitle() {
        return title;
    }

    /**
     * Retrieves the title of the note.
     *
     * @return The note title.
     */
    @Override
    public String getDescription() {
        return getTitle();
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type.
     */
    @Override
    protected String getType() {
        return "N";
    }

    /**
     * Converts the Note to a formatted string suitable for file storage.
     *
     * @return A string representing the note in a file-friendly format.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %s | %s", getType(), title, description);
    }

    /**
     * Returns a string representation of the Note for display.
     *
     * @return A string with the note type, title, and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s: %s", getType(), title, description);
    }
}