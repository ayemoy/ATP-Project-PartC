package View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.Observer;
import ViewModel.MyViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import javafx.stage.Window;

/*this class implement IView and observer
it is like Aview of rotem in the tirgul
in this class we will write function that every controller in every other class will need to lemamesh.
every class need to lemamesh the function : change the scene, load and save maze cuz the menu go with us during all scenes.
every scene need to handle Error and Message alerts
*/

public abstract class Controller implements IView, Observer {


    MyViewModel viewModel = MyViewModel.getInstance();
    public static Stage primaryStage;
    public static Scene mainScene;
    public static Scene thisScene;
    private Parent root;

    public static FileChooser SaveFileChooser = new FileChooser();
    public static FileChooser LoadFileChooser = new FileChooser();

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


    public void switchAboutScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AboutScene.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public void switchHelpScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void switchPropertiesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

//    public void handleExit(ActionEvent event) throws IOException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) { //want to exit the game
//            viewModel.exit();
//            Window welcome = exitButton.getScene().getWindow();
//            ((Stage) welcome).close();
//        }
//    }



    public void SaveButtonHandle(ActionEvent actionEvent) {
        SaveFileChooser.setInitialDirectory(new File(""));
        Window stage = SolveMazeButton.getScene().getWindow();
        SaveFileChooser.setTitle("Save Your Maze");
        SaveFileChooser.setInitialFileName("MyMaze");
        SaveFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Maze (*.File)","*"));
        File file = SaveFileChooser.showSaveDialog(stage);
        viewModel.SaveMaze(file);

    }
    public void LoadButtonHandle(ActionEvent actionEvent) {
        LoadFileChooser.setInitialDirectory(new File("C:\\Users\\tomer\\IdeaProjects\\ATP-Project-PartC\\src\\Sources\\SavedMaze"));
        Window stage = SolveMazeButton.getScene().getWindow();
        LoadFileChooser.setTitle("Load Your Maze");
        LoadFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Maze (*.File)","*"));
        File file = LoadFileChooser.showOpenDialog(stage);
        viewModel.LoadMaze(file);

    }
    public void NewButtonHandle(ActionEvent actionEvent) {
        if(mazeDisplayerCanvas.getMaze() != null){
            mazeDisplayerCanvas.ClearAllCanvas();
        }
        TextFieldRow.clear();
        TextFieldCol.clear();
        SolveMazeButton.setDisable(true);
        ValidNumberLabel.setVisible(false);
        SaveMazeButton.setVisible(false);
        SolveMazeButton.setText("Solve Maze");
    }




}
