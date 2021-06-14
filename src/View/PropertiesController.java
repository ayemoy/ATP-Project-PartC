package View;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import Server.*;

import java.util.Observable;

public class PropertiesController extends Controller {

    @FXML
    private MenuItem spongebobChar;

    @FXML
    private MenuItem patricChar;
    @FXML
    private MenuItem krabChar;

    @FXML
    private MenuItem garyChar;

    @FXML
    private MenuItem BFS;

    @FXML
    private MenuItem DFS;

    @FXML
    private MenuItem BestFS;

    @FXML
    private Button backButton;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private MenuButton chooseCharacter;
    @FXML
    private MenuButton chooseAlgo;


    public StringProperty imageString;
    private String name;


    @Override
    public void update(Observable o, Object arg) {

    }



    public void mouseClicked(ActionEvent actionEvent)
    {

        Object source = actionEvent.getSource();

        if (source == cancelButton){
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            showAlert("Information", "Maze Properties have not been changed.");
            stage.close();
        }

        if (source == BFS || source == DFS || source == BestFS){
            String solverText = ((MenuItem)source).getText();
            chooseAlgo.setText(solverText);
        }
        if (source == spongebobChar || source == patricChar || source == garyChar || source == krabChar){
            String solverText = ((MenuItem)source).getText();
            chooseCharacter.setText(solverText);
        }
        if (source == okButton)
        {
            Configurations.setSearchingAlgorithm(chooseAlgo.getText());
            showConformationAlert("The Properties have been changed, Please start a new game in order to load the chosen settings.", (Stage)okButton.getScene().getWindow());
            //String charecter = chooseCharacter.getItems().toString();
            String character = chooseCharacter.getText();
            viewModel.setCharacterImage(character);
        }


    }

    public void switchToMain() {
        changeScene("MainScreen.fxml",(Stage)backButton.getScene().getWindow(),"A little bit about us");
    }


}
