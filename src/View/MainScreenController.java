package View;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Window;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import ViewModel.MyViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import Model.*;
import java.io.IOException;

/*in thid class we will do the function of buttons in the welcome first screen of the game
we extend what the primary controller have and add more function
*/

public class MainScreenController extends Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private boolean sound=true;//natasha


    private Stage mainStage;
    private Scene mainScene;
    private Parent root;
    @FXML
    public Button aboutButton;
    @FXML
    public Button startButton;



    MyModel model = new MyModel();
    //MyViewModel viewModel = new MyViewModel();




    public void switchToAboutScene()
    {
        changeScene("AboutScene.fxml",(Stage)aboutButton.getScene().getWindow(),"About us and our game algorithms");
    }

    public void switchToHelpScene()
    {
        changeScene("HelpScene.fxml",(Stage)aboutButton.getScene().getWindow(),"We are here for you!");
    }

    public void switchToPropertiesScene()
    {
        changeScene("PropertiesScene.fxml",(Stage)aboutButton.getScene().getWindow(),"Your Properties");
    }

    public void switchToStartScene()
    {
        changeScene("MyView.fxml",(Stage)startButton.getScene().getWindow(),"Play and have fun!! :)");
    }







    public void handleNewFile(ActionEvent actionEvent) {
        changeScene("MyView.fxml",(Stage)aboutButton.getScene().getWindow(),"New Maze");
    }

    public void handleSaveFile(ActionEvent actionEvent) {
        SaveMenuBar("save",(Stage)aboutButton.getScene().getWindow(),false);
    }

    public void handleLoadFile(ActionEvent actionEvent) {
        LoadMenuBar("load",(Stage)aboutButton.getScene().getWindow(),false);
    }


    public void handleExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { //want to exit the game
            viewModel.exit();
            Window welcome = aboutButton.getScene().getWindow();
            ((Stage) welcome).close();
        }
    }

/*
    public void turnMusicOn() throws URISyntaxException// natasha
    {
        if(sound)
        {
            String path = "Resources/Music/mainScreenMusic.mp3";
            viewModel.playTheMusic(path);
            this.sound=false;
        }
        else{
            viewModel.stopPlayTheMusic();
            this.sound=true;
        }
    }
*/

    public void turnMusicOn() throws URISyntaxException {
        turnMusicOn("Resources/Music/mainScreenMusic.mp3");
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}

