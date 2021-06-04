package View;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface IView {

    //void changeScene(String fxmlPath, Stage stage, String title);
    //void handleLoadAndSave(String loadOrSave, Stage stage, boolean changeScene);
    //void showAlert(String title, String message);
    void showErrorAlert(String title, String message);
    //void displayMaze();
}
