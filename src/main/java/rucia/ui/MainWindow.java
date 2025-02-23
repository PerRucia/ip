package rucia.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rucia.driver.Rucia;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Rucia rucia;

    private static final Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/user.png"));
    private static final Image RUCIA_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/rucia2.png"));
    private static final int EXIT_DELAY = 1500; // Delay before exiting the application
    private static final int RESPONSE_DELAY = 400; // Delay before Rucia responds

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        VBox.setVgrow(dialogContainer, Priority.ALWAYS); // Ensure dialog container stretches

        // Ensure VBox expands when ScrollPane resizes
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
    }

    /**
     * Displays the welcome message from Rucia.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = rucia.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getRuciaDialog(welcomeMessage, RUCIA_IMAGE)
        );
    }

    /** Injects the Rucia instance */
    public void setRucia(Rucia r) {
        assert r != null : "Rucia instance should not be null";
        rucia = r;
        showWelcomeMessage();
    }

    /**
     * Handles user input by creating dialog boxes for user input and Rucia's response.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.isEmpty() : "User input should not be null or empty";
        String response = rucia.getResponse(input);

        // Create the user dialog box and add it immediately
        DialogBox userDialog = DialogBox.getUserDialog(input, USER_IMAGE);
        dialogContainer.getChildren().add(userDialog);
        userInput.clear();

        // Create a PauseTransition before adding Rucia's response
        PauseTransition botDelay = new PauseTransition(Duration.millis(RESPONSE_DELAY));
        botDelay.setOnFinished(event -> {
            Platform.runLater(() -> {
                DialogBox ruciaDialog = DialogBox.getRuciaDialog(response, RUCIA_IMAGE);
                dialogContainer.getChildren().add(ruciaDialog);
            });
        });

        botDelay.play();

        // Delay exit if user says "bye"
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition exitDelay = new PauseTransition(Duration.millis(EXIT_DELAY + RESPONSE_DELAY));
            exitDelay.setOnFinished(event -> Platform.exit());
            exitDelay.play();
        }
    }
}