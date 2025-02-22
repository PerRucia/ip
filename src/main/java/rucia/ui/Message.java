package rucia.ui;

/**
 * The Message class contains static methods to generate messages for JavaFX GUI.
 */
public class Message {
    private String message = "";

    public Message() {}

    /**
     * Adds a new message to the existing message string.
     *
     * @param newMessage The new message to be added.
     */
    public void addMessage(String newMessage) {
        assert newMessage != null : "New message should not be null";
        if (message.isEmpty()) {
            message = newMessage;
        }
        else {
            message += "\n" + newMessage;
        }
    }

    /**
     * Retrieves the current message string.
     *
     * @return The current message string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns a welcome message to be displayed when the user starts the application.
     *
     * @return The welcome message.
     */
    public static String showWelcome() {
        return """
                Hello! I'm Rucia
                How can I assist you today?
                Send "Help" or "?" for instructions on how to use me!""";
    }

    /**
     * Returns an exit message to be displayed when the user exits the application.
     *
     * @return The exit message.
     */
    public static String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message to be displayed when an error occurs.
     *
     * @param errorMessage The error message to be displayed.
     * @return The formatted error message.
     */
    public static String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }
}