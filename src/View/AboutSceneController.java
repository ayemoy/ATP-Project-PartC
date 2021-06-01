package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class AboutSceneController {


    private Stage mainStage;
    private Scene aboutScene;
    private Parent root;



    public void switchMainScreen(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        aboutScene = new Scene(root);
        mainStage.setScene(aboutScene);
        mainStage.show();
    }

}
