package View;

import ViewModel.MyViewModel;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javafx.geometry.Pos.CENTER;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


//do all function of the maze play screen
//like: init, displayMaze, Zoom, solveMaze, generateMaze.......

public class MyViewController implements IView , Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }


    @Override
    public void changeScene(String fxmlPath, Stage stage, String title) {

    }

    @Override
    public void handleLoadAndSave(String loadOrSave, Stage stage, boolean changeScene) {

    }

    @Override
    public void showAlert(String title, String message) {

    }

    @Override
    public void showErrorAlert(String title, String message) {

    }
}
