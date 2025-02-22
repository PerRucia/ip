// src/main/java/utils/TaskStorageUtil.java
package rucia.utils;

import rucia.tasks.TaskList;
import java.io.IOException;

public class TaskStorageUtil {

    /**
     * Saves the current tasks in the task list to persistent storage.
     *
     * @param taskList The task list containing tasks to be saved.
     * @param storage  The storage handler to save the task list.
     * @throws IOException if an I/O error occurs during saving.
     */
    public static void saveTasks(TaskList taskList, Storage storage) throws IOException {
        storage.saveToFile(taskList.getTasks());
    }
}