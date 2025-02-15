import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rucia.Rucia;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ruciaImage = new Image(this.getClass().getResourceAsStream("/images/rucia.png"));

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
                DialogBox.getRuciaDialog(welcomeMessage, ruciaImage)
        );
    }

    /** Injects the Rucia instance */
    public void setRucia(Rucia r) {
        rucia = r;
        showWelcomeMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rucia's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rucia.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRuciaDialog(response, ruciaImage)
        );
        userInput.clear();
    }
}
