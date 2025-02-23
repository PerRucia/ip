// src/main/java/tasks/Task.java
package rucia.tasks;

/**
 * Represents an abstract Task that contains common properties and behaviors
 * shared by all task types such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts the Task to a formatted string suitable for file storage.
     *
     * @return A string representing the task in a file-friendly format.
     */
    public String toFileString() {
        return String.format("%s | %d | %s", getType(), isDone ? 1 : 0, description);
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type.
     */
    protected abstract String getType();

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the Task for display.
     *
     * @return A string with the task type, status, and description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), isDone ? "X" : " ", description);
    }
}