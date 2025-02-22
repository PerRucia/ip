package rucia.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rucia.driver.Rucia;

import java.util.stream.Stream;

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
    private static final Image RUCIA_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/rucia.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        String response = rucia.getResponse(input);
        Stream.of(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getRuciaDialog(response, RUCIA_IMAGE)
        ).forEach(dialogContainer.getChildren()::add);
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}