package View;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Observer;
import ViewModel.MyViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import ViewModel.MyViewModel;

/*this class implement IView and observer
in this class we will write function that every controller in every other class will need to lemamesh.
every class need to lemamesh the function : change the scene, load and save maze cuz the menu go with us during all scenes.
every scene need to handle Error and Message alerts
*/

public abstract class Controller implements IView, Observer {
/*
    @Override
    //in this func we change every scene with a path that give, new stage and window title
    public void changeScene(String newSceneFXMLPath, Stage newStage, String WindowTitle)
    {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newSceneFXMLPath));
            root = fxmlLoader.load();
            //viewModel.addObserver(fxmlLoader.getController());
            newStage.setTitle(WindowTitle);
            if (newSceneFXMLPath.equals("AboutUsPage.fxml"))
                newStage.setScene(new Scene(root,768,432));
            else
                newStage.setScene(new Scene(root,900,614));
            newStage.show();
        } catch (IOException e) {
            //e.printStackTrace(); //check?
        }
    }

    @Override
    //in every window we keep the option to save and load a new game
    public void handleLoadAndSave(String loadOrSave, Stage stage, boolean changeScene)
    {

    }

    @Override
    //in every scene we want be able show alert to user so we do it here
    //in every scene we can choose later what to write to the user
    public void showAlert(String titleWindow, String alertToShow)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleWindow);
        alert.setContentText(alertToShow);
        alert.show();
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

    @Override
    public void update(Observable o, Object arg) {

    }


/*
    public void handleAboutButton() {
        Stage aboutStage = new Stage();
        changeScene("AbouScene.fxml",aboutStage,"About");
    }
*/

}
