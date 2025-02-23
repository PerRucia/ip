package rucia.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucia.tasks.TaskList;
import rucia.ui.Message;
import rucia.utils.Storage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandTest {
    private TaskList taskList;
    private Storage storage;
    private Message message;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = mock(Storage.class); // Mock Storage to prevent actual file writes
        message = new Message();
    }

    @Test
    void execute_addCommand_addsTaskAndSavesToStorage() throws IOException {
        AddCommand command = new AddCommand("Buy groceries", storage);
        String response = command.execute(taskList, message);

        assertEquals(1, taskList.getTaskSize());
        assertTrue(response.contains("Added ToDo task - Buy groceries"));
        verify(storage, times(1)).saveToFile(taskList);
    }

    @Test
    void execute_deleteCommand_deletesTaskAndSavesToStorage() throws IOException {
        new AddCommand("Test Task", storage).execute(taskList, message);
        DeleteCommand command = new DeleteCommand("delete 1", storage);

        String response = command.execute(taskList, message);

        assertEquals(0, taskList.getTaskSize());
        assertTrue(response.contains("Deleted task"));
        verify(storage, times(2)).saveToFile(taskList);
    }

    @Test
    void execute_markCommand_marksTaskAsDone() throws IOException {
        new AddCommand("Test Task", storage).execute(taskList, message);
        MarkCommand command = new MarkCommand("mark 1", storage);

        String response = command.execute(taskList, message);

        assertTrue(taskList.isTaskDone(0));
        assertTrue(response.contains("Marked task as done"));
        verify(storage, times(2)).saveToFile(taskList);
    }

    @Test
    void execute_unmarkCommand_marksTaskAsNotDone() throws IOException {
        new AddCommand("Test Task", storage).execute(taskList, message);
        new MarkCommand("mark 1", storage).execute(taskList, message);
        UnmarkCommand command = new UnmarkCommand("unmark 1", storage);

        String response = command.execute(taskList, message);

        assertFalse(taskList.isTaskDone(0));
        assertTrue(response.contains("Unmarked task as not done"));
        verify(storage, times(3)).saveToFile(taskList);
    }

    @Test
    void execute_deadlineCommand_addsDeadlineTask() throws IOException {
        DeadlineCommand command = new DeadlineCommand("Finish report", "15/03/2025 1800", storage);

        String response = command.execute(taskList, message);

        assertEquals(1, taskList.getTaskSize());
        assertTrue(response.contains("Added Deadline task - Finish report"));
        verify(storage, times(1)).saveToFile(taskList);
    }

    @Test
    void execute_eventCommand_addsEventTask() throws IOException {
        EventCommand command = new EventCommand("Team Meeting", "15/03/2025 1000", "15/03/2025 1200", storage);

        String response = command.execute(taskList, message);

        assertEquals(1, taskList.getTaskSize());
        assertTrue(response.contains("Added Event task - Team Meeting"));
        verify(storage, times(1)).saveToFile(taskList);
    }

    @Test
    void execute_findCommand_findsMatchingTasks() {
        new AddCommand("Finish homework", storage).execute(taskList, message);
        new AddCommand("Buy groceries", storage).execute(taskList, message);

        FindCommand command = new FindCommand("homework");
        String response = command.execute(taskList, message);

        assertTrue(response.contains("Tasks found with the keywords: homework"));
    }

    @Test
    void execute_listCommand_showsAllTasks() {
        new AddCommand("Test Task", storage).execute(taskList, message);
        ListCommand command = new ListCommand();

        String response = command.execute(taskList, message);

        assertTrue(response.contains("Here are your tasks."));
    }

    @Test
    void execute_listDayCommand_listsTasksOnSpecificDay() {
        new DeadlineCommand("Project deadline", "15/03/2025 1800", storage).execute(taskList, message);
        ListDayCommand command = new ListDayCommand("list_day 15/03/2025");

        String response = command.execute(taskList, message);

        assertTrue(response.contains("Tasks for 2025-03-15"));
    }

    @Test
    void execute_noteCommand_addsNote() {
        NoteCommand command = new NoteCommand("Meeting Notes", "Discussed project plan", storage);

        String response = command.execute(taskList, message);

        assertEquals(1, taskList.getNoteSize());
        assertTrue(response.contains("Meeting Notes"));
    }

    @Test
    void execute_notesCommand_listsAllNotes() {
        new NoteCommand("Meeting Notes", "Discussed project plan", storage).execute(taskList, message);
        ListNotesCommand command = new ListNotesCommand();
        String response = command.execute(taskList, message);

        assertTrue(response.contains("Here are your notes:"));
    }

    @Test
    void execute_clearCommand_clearsAllTasks() throws IOException {
        new AddCommand("Task 1", storage).execute(taskList, message);
        new AddCommand("Task 2", storage).execute(taskList, message);
        ClearCommand command = new ClearCommand(storage);

        String response = command.execute(taskList, message);

        assertEquals(0, taskList.getTaskSize());
        assertTrue(response.contains("Cleared all tasks."));
        verify(storage, times(3)).saveToFile(taskList);
    }

    @Test
    void execute_byeCommand_returnsExitMessage() {
        ByeCommand command = new ByeCommand();
        String response = command.execute(taskList, message);

        assertTrue(response.contains("Finally! Bye."));
    }

    @Test
    void execute_helpCommand_displaysHelpMessage() {
        HelpCommand command = new HelpCommand();
        String response = command.execute(taskList, message);

        assertTrue(response.contains("Looks like you need help after all huh?"));
    }

    @Test
    void execute_helpNotesCommand_displaysNotesHelpMessage() {
        HelpNotesCommand command = new HelpNotesCommand();
        String response = command.execute(taskList, message);

        assertTrue(response.contains("It's just note-taking, how hard can it be?:"));
    }

    @Test
    void execute_deleteNoteCommand_deletesNote() throws IOException {
        new NoteCommand("Meeting Notes", "Discussed project plan", storage).execute(taskList, message);
        DeleteNoteCommand command = new DeleteNoteCommand("delete_note 1", storage);

        String response = command.execute(taskList, message);

        assertEquals(0, taskList.getNoteSize());
        assertTrue(response.contains("Deleted note"));
        verify(storage, times(2)).saveToFile(taskList);
    }

    @Test
    void execute_viewNoteCommand_viewNote() {
        new NoteCommand("Meeting Notes", "Discussed project plan", storage).execute(taskList, message);
        ViewNoteCommand command = new ViewNoteCommand("1");

        String response = command.execute(taskList, message);

        assertTrue(response.contains("Meeting Notes"));
    }

    @Test
    void execute_cheerCommand_displaysMotivationalQuote() {
        CheerCommand command = new CheerCommand();
        String response = command.execute(taskList, message);

        assertNotNull(response);
    }
}
