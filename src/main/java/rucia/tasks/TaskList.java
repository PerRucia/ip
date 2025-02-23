package rucia.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks and notes, providing methods to manage tasks and notes such as adding,
 * deleting, marking as done, and unmarking. Handles common operations on the task list and note list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<Note> notes;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks and notes from a Storage instance.
     *
     * @param list The list of tasks and notes to initialize the TaskList with.
     *
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = new ArrayList<>();
        this.notes = new ArrayList<>();
        for (Task task : list) {
            if (task instanceof Note) {
                notes.add((Note) task);
            } else {
                tasks.add(task);
            }
        }
    }

    // Task-related methods

    /**
     * Adds a task to the task list.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Returns the task at the specified index.
     * @param index The index of the task to return.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Returns the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Marks the task at the specified index as done.
     * @param index
     */
    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     * @param index
     */
    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    /**
     * Checks if the task at the specified index is marked as done.
     *
     * @param index The index of the task to check.
     * @return true if the task is marked as done, false otherwise.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public boolean isTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index).isDone();
        } else {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
    }

    // Note-related methods

    /**
     * Adds a note to the note list.
     * @param note The note to add.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Deletes the note at the specified index.
     * @param index The index of the note to delete.
     * @return The note that was deleted.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Note deleteNote(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid note index");
        }
    }

    /**
     * Returns the note at the specified index.
     * @param index The index of the note to return.
     * @return The note at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Note getNote(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid note index");
        }
    }

    /**
     * Returns the number of notes in the note list.
     * @return The number of notes in the note list.
     */
    public int getNoteSize() {
        return notes.size();
    }

    /**
     * Returns the list of notes.
     * @return The list of notes.
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }
}