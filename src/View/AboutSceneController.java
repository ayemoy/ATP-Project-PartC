package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Observable;

public class AboutSceneController extends Controller {


    private Stage mainStage;
    private Scene aboutScene;
    private Parent root;
    @FXML
    private Tab aboutTab;
    @FXML
    private Tab algoTab;
    @FXML
    private TabPane tabPane;

    @FXML
    public Button backButton;




//    public void switchMainScreen(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
//        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        aboutScene = new Scene(root);
//        mainStage.setScene(aboutScene);
//        mainStage.show();
//    }


    @Override
    public void update(Observable o, Object arg) {

    }


    public void switchToMain() {
        changeScene("MainScreen.fxml",(Stage)backButton.getScene().getWindow(),"A little bit about us");
    }



/*
    public void handleNewFile(ActionEvent actionEvent) throws IOException
    {
        NewMenuBarButton(actionEvent);
    }

    public void handleSaveFile(ActionEvent actionEvent)
    {
        SaveMenuBarButton(actionEvent);
    }

    public void handleLoadFile(ActionEvent actionEvent)
    {
        LoadMenuBarButton(actionEvent);
    }



    public void handleAbout(ActionEvent actionEvent) throws IOException
    {
        switchAboutScene(actionEvent);
    }

    public void handleHelp(ActionEvent actionEvent) throws IOException
    {
        switchHelpScene(actionEvent);
    }

    public void handleProperties(ActionEvent actionEvent) throws IOException
    {
        switchPropertiesScene(actionEvent);
    }


    public void handleExitController(ActionEvent actionEvent) throws IOException
    {
        handleExit(actionEvent);
    }

*/



}
