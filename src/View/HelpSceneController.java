package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public class HelpSceneController extends Controller {


    private Stage mainStage;
    private Scene helpScene;
    private Parent root;
    @FXML
    private Button backButton;

    public void switchToMain() {
        changeScene("MainScreen.fxml",(Stage)backButton.getScene().getWindow(),"A little bit about us");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
