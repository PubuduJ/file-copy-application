import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(402);
        primaryStage.setMinWidth(752);
        primaryStage.setTitle("Simple File / Folder Copy-Paste Application");
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
