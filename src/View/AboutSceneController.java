package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class AboutSceneController {


    private Stage mainStage;
    private Scene aboutScene;
    private Parent root;
    @FXML
    private Tab aboutTab;
    @FXML
    private Tab algoTab;
    @FXML
    private TabPane tabPane;




    public void switchMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        aboutScene = new Scene(root);
        mainStage.setScene(aboutScene);
        mainStage.show();
    }

/*
    public void switchToAlgoTab(ActionEvent event) throws IOException {

        tabPane.getSelectionModel().select(algoTab);
    }

    public void switchToAboutTab(ActionEvent event) throws IOException {

        tabPane.getSelectionModel().select(aboutTab);
    }
    */




}
