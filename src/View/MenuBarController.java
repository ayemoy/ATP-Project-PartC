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


    public MenuBarController(MenuBar menuBar)
    {
        this.menuBar = menuBar;
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



//    public void handleAbout(ActionEvent actionEvent) throws IOException
//    {
//        switchAboutScene(actionEvent);
//    }

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
    @Override
    public void update(Observable o, Object arg)
    {

    }

}