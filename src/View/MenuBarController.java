package View;

import ViewModel.MyViewModel;
import algorithms.search.Solution;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import View.*;

public class MenuBarController extends Controller {

    @FXML
    private MenuBar menuBar;
    @FXML
    public MenuItem newMenu;
    @FXML
    public MenuItem saveMenu;
    @FXML
    public MenuItem loadMenu;
    @FXML
    public MenuItem propMenu;
    @FXML
    public MenuItem helpMenu;
    @FXML
    public MenuItem aboutMenu;
    @FXML
    public MenuItem exitMenu;
    @FXML
    public Label About;


    public MenuBarController(MenuBar menuBar)
    {
        this.menuBar = menuBar;
    }


    public void handleAbout() {
        menuBarAbout();
    }

    public void handleHelp() {
        menuBarHelp();
    }

    public void handleProperties() {
        menuBarProperties();
    }





    public void handleNewFile(ActionEvent actionEvent) {
        changeScene("MyView.fxml",(Stage)menuBar.getScene().getWindow(),"New Maze");
    }

    public void handleSaveFile(ActionEvent actionEvent) {
        SaveMenuBar("save",(Stage)menuBar.getScene().getWindow(),false);
    }

    public void handleLoadFile(ActionEvent actionEvent) {
        LoadMenuBar("load",(Stage)menuBar.getScene().getWindow(),false);
    }




    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { //want to exit the game
            viewModel.exit();
            Window welcome = About.getScene().getWindow();
            ((Stage)welcome).close();
        }
    }













    @Override
    public void update(Observable o, Object arg)
    {

    }

}