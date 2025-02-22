package rucia.notes;

/**
 * Represents a note that can be added to a task. Contains a title and a description.
 */
public class Note {
    private String title;
    private String description;

    /**
     * Constructs a note with the specified title and description.
     *
     * @param title The title of the note.
     * @param description The description of the note.
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Returns the title of the note.
     *
     * @return The title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the note.
     *
     * @return The description of the note.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the note to a formatted string suitable for file storage.
     *
     * @return A string representing the note in a file-friendly format.
     */
    public String toFileString() {
        return title + " | " + description;
    }

    /**
     * Returns a string representation of the note.
     *
     * @return A string representation of the note.
     */
    @Override
    public String toString() {
        return title + ": " + description;
    }
}
