// src/main/java/utils/Storage.java
package rucia.utils;

import rucia.tasks.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the storage of tasks to and from a file.
 * Provides functionality to save and load tasks in a persistent way.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Creates the file and necessary directories if they do not exist.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath).toString();
        createFileIfNotExists();
    }

    /**
     * Creates the storage file and its parent directories if they do not exist.
     */
    private void createFileIfNotExists() {
        Path path = Paths.get(filePath);
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param taskList The TaskList containing notes and tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveToFile(TaskList taskList) throws IOException {
        List<String> lines = new ArrayList<>();
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Note> notes = taskList.getNotes();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        for (Note note : notes) {
            lines.add(note.toFileString());
        }
        Files.write(Paths.get(filePath), lines);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> loadFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    long byTimestamp = Long.parseLong(parts[3]);
                    task = new Deadline(description, byTimestamp);
                    break;
                case "E":
                    long fromTimestamp = Long.parseLong(parts[3]);
                    long toTimestamp = Long.parseLong(parts[4]);
                    task = new Event(description, fromTimestamp, toTimestamp);
                    break;
                case "N":
                    task = new Note(description, parts[3]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type: " + type);
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }
}