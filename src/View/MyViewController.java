package View;

import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.Solution;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


//do all function of the maze play screen
//like: init, displayMaze, Zoom, solveMaze, generateMaze.......

public class MyViewController extends Controller implements IView , Initializable {

    private MyViewModel viewModel; //save object of viewModel like we need to do in MVVM
    public boolean showSolution; //boolean that help us know in we need to show the solution
    public MazeDisplayer mazeDisplayer;
    private Solution solution;

    private Scene gameScene;
    private Scene mainScene;
    private Stage mainStage;
    private Parent root;

    @FXML
    private SplitPane splitPane;

    @FXML
    private AnchorPane leftSide;
    @FXML
    private Button play;
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Button solve;
    @FXML
    private ToggleButton music;
    @FXML
    private TextField RowField;
    @FXML
    private TextField ColField;
    @FXML
    public MenuBar menuBar;


    @FXML
    private AnchorPane rightSide;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Pane pane;


    @FXML
    public Label labelPlayerRow;
    @FXML
    public Label labelPlayerCol;

    private StringProperty updatePlayerPositionRow = new SimpleStringProperty();
    private StringProperty updatePlayerPositionCol = new SimpleStringProperty();

//________________________________________
/*
    @FXML
    public Label ValidNumberLabel;
    public boolean showSolution;
    private Maze maze;
*/
    @Override
    //this function do all updates so when the maze window open, the function run and do all we need
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        labelPlayerRow.textProperty().bind(updatePlayerPositionRow);
        labelPlayerCol.textProperty().bind(updatePlayerPositionCol);
        adjustDisplaySizes();
        viewModel.pauseMusic();
        try{
            viewModel.playMusic((new Media(getClass().getResource("/Music/SpongeBobNice.mp3").toURI().toString())),200);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    //this func adjusts the sizes of the pane to GridPane
    private void adjustDisplaySizes() {
        borderPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinWidth(borderPane.getWidth()-200);
            if (viewModel.getIntMazeArray() != null)
                mazeDisplayer.draw();
        });
        borderPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinHeight(borderPane.getHeight());
            if (viewModel.getIntMazeArray() != null)
                mazeDisplayer.draw();
        });
        //adjusts the size of the maze displayer to pane
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            mazeDisplayer.setWidth(pane.getWidth());
            if (viewModel.getIntMazeArray() != null)
                mazeDisplayer.draw();
        });
        pane.heightProperty().addListener((obs, oldVal, newVal) -> {
            mazeDisplayer.setHeight(pane.getHeight()-40);
            if (viewModel.getIntMazeArray() != null)
                mazeDisplayer.draw();
        });

    }



    //this function chek if the number of roes and number of columns that the uset insert to the text field is valid
    private boolean chekIfValidRowsCols(String userNumStr)
    {
        String regex = "\\d+";
        if (userNumStr.matches(regex)) {
            int val = Integer.valueOf(userNumStr);
            if (val >= 3 && val <= 500)
                return true;
        }
        return false;
    }




    public void generateMaze()
    {
        showSolution = false;
        //get from user the size of maze that he want set
        String userStrRows = RowField.getText();
        String userStrCols = ColField.getText();

        if (chekIfValidRowsCols(userStrRows) && chekIfValidRowsCols(userStrCols))
        //if number as str is valid, we replace the str in int so we can create the int maze in the right size
        {
            int intRows = Integer.valueOf(userStrRows); //replace the str in int
            int intCols = Integer.valueOf(userStrCols); //replace the str in int

            viewModel.generateMaze(intRows, intCols);

            //ShowSolution.setDisable(false);
           // mazeDisplayer.setSolution(null);
            //mazeDisplayer.setSolved(false);

        }
        else
        {
            showErrorAlert("Invalid Input" , "Invalid Number!/n" + "Please enter numbers between 3 to 500");
        }
    }




    //______________________handlerssssssss_______________________________________________
    //this function is controll the back button
        public void switchMainScreen(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.show();
    }


    //________________getters & setters____________________


    public String getUpdatePlayerPositionRow() { return updatePlayerPositionRow.get(); }
    public StringProperty updatePlayerPositionRowProperty() { return updatePlayerPositionRow; }
    public void setUpdatePlayerPositionRow(String updatePlayerPositionRow) { this.updatePlayerPositionRow.set(updatePlayerPositionRow); }
    public String getUpdatePlayerPositionCol() { return updatePlayerPositionCol.get(); }
    public StringProperty updatePlayerPositionColProperty() { return updatePlayerPositionCol; }
    public void setUpdatePlayerPositionCol(String updatePlayerPositionCol) { this.updatePlayerPositionCol.set(updatePlayerPositionCol); }

    @Override
    public void update(Observable o, Object arg) {

    }

    //__________________________________________________________________________________
}
