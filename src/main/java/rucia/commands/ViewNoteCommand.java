// src/main/java/rucia/commands/ViewNoteCommand.java
package rucia.commands;

import rucia.tasks.TaskList;
import rucia.ui.Message;

public class ViewNoteCommand implements Command {
    private final int noteIndex;

    public ViewNoteCommand(String input) {
        this.noteIndex = Integer.parseInt(input) - 1;
    }

    public String execute(TaskList taskList, Message message) {
        try {
            message.addMessage("Viewing Note: " + taskList.getNote(noteIndex).toFullString());
            return message.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return Message.showError("Invalid note number. Please try again.");
        }
    }
}