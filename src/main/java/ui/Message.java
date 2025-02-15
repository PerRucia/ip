package ui;

/**
 * The Message class contains static methods to generate messages for JavaFX GUI.
 */
public class Message {

    // Constructor
    public Message() {
    }

    public static String showWelcome() {
        return """
                Hello! I'm Rucia
                How can I assist you today?
                Send "Help" or "?" for instructions on how to use me!""";
    }

    public static String showExit() {
        return "Rucia: Bye. Hope to see you again soon!";
    }

    public static String constructAddTaskMessage(String taskDescription, int taskListSize) {
        return "Added task - " + taskDescription + "\n" +
                "You now have " + taskListSize + " task(s) in your list.";
    }

    public static String constructDeadlineMessage(String taskDescription, String formattedDateTime, int taskListSize) {
        return "Added Deadline task - " + taskDescription + " (by: " + formattedDateTime + ")\n" +
                "You now have " + taskListSize + " task(s) in your list.";
    }

    public static String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }
}