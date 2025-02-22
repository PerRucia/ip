package rucia.notes;

import java.util.ArrayList;

/**
 * Represents a list of notes that can be added, deleted, and viewed by the user.
 */
public class NoteList {
    private ArrayList<Note> notes;

    /**
     * Constructs a NoteList object with an empty list of notes.
     */
    public NoteList() {
        this.notes = new ArrayList<>();
    }

    /**
     * Adds a note to the list of notes.
     *
     * @param note The note to be added.
     */
    public void add(Note note) {
        notes.add(note);
    }

    /**
     * Deletes a note from the list of notes.
     *
     * @param index The index of the note to be deleted.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void delete(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid note index");
        }
    }

    /**
     * Returns the note at the specified index.
     *
     * @param index The index of the note to be retrieved.
     * @return The note at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Note get(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid note index");
        }
    }`
}
