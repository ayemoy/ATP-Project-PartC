package View;

import javafx.stage.Stage;

public interface IView {

    void changeScene(String fxmlPath, Stage stage, String title);
    void handleLoadAdSave(String loadOrSave, Stage stage, boolean changeScene);
    void showAlert(String title, String message);
    void showErrorAlert(String message);
    void displayMaze();
}
