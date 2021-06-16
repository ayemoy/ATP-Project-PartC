package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Optional;

public class AboutSceneController extends Controller {


    private Stage mainStage;
    private Scene aboutScene;
    private Parent root;
    @FXML
    private Tab aboutTab;
    @FXML
    private Tab algoTab;
    @FXML
    public ImageView aboutImage;

    @FXML
    public Button backButton;



    @Override
    public void update(Observable o, Object arg) {

    }


    public void switchToMain() {
        changeScene("MainScreen.fxml",(Stage)backButton.getScene().getWindow(),"A little bit about us");
    }



    public void switchToAboutScene()
    {
        changeScene("AboutScene.fxml",(Stage)backButton.getScene().getWindow(),"About us and our game algorithms");
    }

    public void switchToHelpScene()
    {
        changeScene("HelpScene.fxml",(Stage)backButton.getScene().getWindow(),"We are here for you!");
    }

    public void switchToPropertiesScene()
    {
        changeScene("PropertiesScene.fxml",(Stage)backButton.getScene().getWindow(),"Your Properties");
    }

    public void switchToStartScene()
    {
        changeScene("MyView.fxml",(Stage)backButton.getScene().getWindow(),"Play and have fun!! :)");
    }


    public void handleNewFile(ActionEvent actionEvent) {
        changeScene("MyView.fxml",(Stage)backButton.getScene().getWindow(),"New Maze");
    }

    public void handleSaveFile(ActionEvent actionEvent) {
        SaveMenuBar("save",(Stage)backButton.getScene().getWindow(),false);
    }

    public void handleLoadFile(ActionEvent actionEvent) {
        LoadMenuBar("load",(Stage)backButton.getScene().getWindow(),false);
    }


    public void handleExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { //want to exit the game
            viewModel.exit();
            Window welcome = backButton.getScene().getWindow();
            ((Stage) welcome).close();
        }
    }



    public void turnMusicOn() throws URISyntaxException
    {
        turnMusicOn("Resources/Music/toallScenes1.mp3");
    }


}
