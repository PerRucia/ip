package rucia.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rucia.driver.Rucia;

public class Main extends Application {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;

    private final Rucia rucia = new Rucia();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null";
            Scene scene = new Scene(ap);
            // Load the dark theme CSS
            scene.getStylesheets().add(Main.class.getResource("/css/darktheme.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRucia(rucia);  // inject the Rucia instance
            stage.setTitle("Rucia ChatBot");
            stage.setWidth(DEFAULT_WIDTH);
            stage.setHeight(DEFAULT_HEIGHT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Consider using a logging framework
        }
    }
}