package View;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.io.IOException;
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


    private Stage mainStage;
    private Scene mainScene;
    private Parent root;
    @FXML
    private Button exitButton;
    MyModel model = new MyModel();
    //MyViewModel viewModel = new MyViewModel();


    public void switchAboutScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AboutScene.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.show();
    }


    public void switchHelpScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public void switchMazeScene(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("MyView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        root = fxmlLoader.load();
        viewModel.addObserver(fxmlLoader.getController());
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public void handleExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { //want to exit the game
            viewModel.exit();
            Window welcome = exitButton.getScene().getWindow();
            ((Stage) welcome).close();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}



/*
    public void about()
    {
        handleAboutButton();
    }
*/

