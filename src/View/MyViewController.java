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

public class MyViewController implements IView , Initializable {

    private MyViewModel viewModel;
    @FXML

    public MazeDisplayer mazeDisplayerCanvas;
    private Solution solution;
    private Scene gameScene;
    private Scene mainScene;
    @FXML
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
    private Pane gamePane;
    @FXML
    private GridPane theGamePane;

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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
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

    //__________________________________________________________________________________
}
