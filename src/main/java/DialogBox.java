import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        int size = 50;
        // Set the avatar’s width/height
        displayPicture.setFitWidth(size);
        displayPicture.setFitHeight(size);
        displayPicture.setPreserveRatio(true);


        // Clip the ImageView into a circle
        Circle clip = new Circle(25, 25, 20); // centerX, centerY, radius
        displayPicture.setClip(clip);

        // Give some spacing between avatar and text
        this.setSpacing(10);

        // Style the label like a bubble
        dialog.setStyle(
                "-fx-background-color: #3C3F41; "
                        + "-fx-text-fill: #FFFFFF; "
                        + "-fx-background-radius: 10; "
                        + "-fx-padding: 10;"
        );
    }

    /**
     * Returns a DialogBox with the specified text and image for the user.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox with the specified text and image for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        // Maybe a different color for the user bubble
        db.dialog.setStyle(
                "-fx-background-color: #0061a8; "
                        + "-fx-text-fill: #FFFFFF; "
                        + "-fx-background-radius: 10; "
                        + "-fx-padding: 10;"
        );
        return db;
    }

    /**
     * Returns a DialogBox with the specified text and image for Rucia.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox with the specified text and image for Rucia.
     */
    public static DialogBox getRuciaDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
