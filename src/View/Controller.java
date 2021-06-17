package View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.Observer;
import ViewModel.MyViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.stage.Window;

/*this class implement IView and observer
it is like Aview of rotem in the tirgul
in this class we will write function that every controller in every other class will need to lemamesh.
every class need to lemamesh the function : change the scene, load and save maze cuz the menu go with us during all scenes.
every scene need to handle Error and Message alerts
*/

public abstract class Controller implements IView, Observer {


    MyViewModel viewModel = MyViewModel.getInstance();
    private boolean sound=true;//natasha


    //public static FileChooser SaveFileChooser = new FileChooser();
    //public static FileChooser LoadFileChooser = new FileChooser();



    public void changeScene(String fxmlPath, Stage stage, String title)
    {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            root = fxmlLoader.load();
            viewModel.addObserver(fxmlLoader.getController());
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
            viewModel.stopPlayTheMusic();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void turnMusicOn(String path) throws URISyntaxException// natasha
    {
        if(sound)
        {
            String str = path;
            viewModel.playTheMusic(str);
            this.sound=false;
        }
        else{
            viewModel.stopPlayTheMusic();
            this.sound=true;
        }
    }


    //this func show to user messages
    public void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    //this func show to the user alert so he can confirmation things
    public void showConformationAlert(String message, Stage stage)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        Optional<ButtonType> result = alert.showAndWait();
    }

    @Override
    //in every scene we want be able show alert to user so we do it here
    //in every scene we can choose later what to write to the user
    public void showErrorAlert(String titleWindow, String ErrorToShow)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleWindow);
        alert.setContentText(ErrorToShow);
        alert.show();
    }


    //______________________________help function to thr menu bar_______________________________________________________

    public void menuBarProperties()
    {
        Stage propStage = new Stage();
        changeScene("../View/Properties.fxml",propStage,"Your Properties");

    }

    public void menuBarAbout()
    {
        Stage propStage = new Stage();
        changeScene("../View/AboutScene.fxml",propStage,"About us and our game algorithms");

    }

    public void menuBarHelp()
    {
        Stage propStage = new Stage();
        changeScene("../View/HelpScene.fxml",propStage,"We are here for you!");

    }


    public void LoadMenuBar(String loadOrSave, Stage stage, boolean changeScene)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("maze files","*.maze");
        fc.getExtensionFilters().add(filter);
        if (loadOrSave.equals("Load"))
        {
            fc.setTitle("Load Maze");
            File file = fc.showOpenDialog(stage);
            if (file != null)
            {
                if (changeScene)
                    changeScene("MyView.fxml",stage,"Load Maze");
                viewModel.loadMaze(file.getPath());
            }
            else
                showAlert("Load Maze","No path selected, Please try again.");
        }
    }


    public void SaveMenuBar(String loadOrSave, Stage stage, boolean changeScene)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("maze files","*.maze");
        fc.getExtensionFilters().add(filter);
        if (viewModel.getIntMazeArrayMVM() != null)
        {
            fc.setTitle("Save Maze");
            File file = fc.showSaveDialog(stage);
            if (file != null)
            {
                viewModel.saveMaze(file.getPath());
            }
            else
            {
                showAlert("Save Maze","No path selected, Please try again.");
            }
        }
        else
        {
            showErrorAlert("Oh, No!","There is no maze to save. Please generate an new maze first.");
        }

    }





}
