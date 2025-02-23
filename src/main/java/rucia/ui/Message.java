package rucia.ui;

/**
 * The Message class contains static methods to generate messages for JavaFX GUI.
 */
public class Message {
    private StringBuilder message = new StringBuilder();

     public Message() {}

    /**
     * Adds a new message to the existing message string.
     *
     * @param newMessage The new message to be added.
     */
    public void addMessage(String newMessage) {
        assert newMessage != null : "New message should not be null";
        if (message.isEmpty()) {
            message.append(newMessage);
        } else {
            message.append("\n").append(newMessage);
        }
    }

     /**
      * Retrieves the current message string.
      *
      * @return The current message string.
      */
     public String getMessage() {
         return message.toString();
     }

     /**
      * Returns a welcome message to be displayed when the user starts the application.
      *
      * @return The welcome message.
      */
     public static String showWelcome() {
         return """
                Oh, it's you again. I'm Rucia.
                What do you want this time?
                Type "Help" or "?" if you can't figure it out yourself.""";
     }

     /**
      * Returns an exit message to be displayed when the user exits the application.
      *
      * @return The exit message.
      */
     public static String showExit() {
         return "Finally! Bye. Don't let the door hit you on the way out.";
     }

     /**
      * Returns an error message to be displayed when an error occurs.
      *
      * @param errorMessage The error message to be displayed.
      * @return The formatted error message.
      */
     public static String showError(String errorMessage) {
         return errorMessage + "\nTry not to mess up next time.";
     }
 }