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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


//do all function of the maze play screen
//like: init, displayMaze, Zoom, solveMaze, generateMaze.......

public class MyViewController extends Controller implements IView , Initializable {

    //private MyViewModel viewModel; //save object of viewModel like we need to do in MVVM
    public boolean showSolution; //boolean that help us know in we need to show the solution
    @FXML
    public MazeDisplayer mazeDisplayer;
    @FXML
    private Solution solution;


    private Scene gameScene;
    private Scene mainScene;
    private Stage mainStage;
    private Parent root;
    private boolean sound=true;//natasha



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
    private Button hide;
    @FXML
    private TextField RowField;
    @FXML
    private TextField ColField;
    @FXML
    public MenuBarController menuBar;


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

    @Override
    //this function do all updates so when the maze window open, the function run and do all we need
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RowField.clear();
        ColField.clear();

        labelPlayerRow.textProperty().bind(updatePlayerPositionRow);
        labelPlayerCol.textProperty().bind(updatePlayerPositionCol);
        fitDisplaySizes();
        //viewModel.pauseMusic();
        //try
        //{

        //viewModel.playMusic((new Media(getClass().getResource("/Music/SpongeBobNice.mp3").toURI().toString())),200);
        //}
        //catch (URISyntaxException e) {
        //e.printStackTrace();
        //}
    }


    //this func adjusts the sizes of the pane to GridPane
    private void fitDisplaySizes() {
        borderPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinWidth(borderPane.getWidth() - 200);
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });
        borderPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinHeight(borderPane.getHeight());
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });
        //adjusts the size of the maze displayer to pane
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            mazeDisplayer.setWidth(pane.getWidth());
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });
        pane.heightProperty().addListener((obs, oldVal, newVal) -> {
            mazeDisplayer.setHeight(pane.getHeight() - 40);
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });

    }


    //this function chek if the number of roes and number of columns that the uset insert to the text field is valid
    private boolean checkIfValidRowsCols(String userNumStr) {
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

        if (checkIfValidRowsCols(userStrRows) && checkIfValidRowsCols(userStrCols))
        //if number as str is valid, we replace the str in int so we can create the int maze in the right size
        {
            int intRows = Integer.valueOf(userStrRows); //replace the str in int
            int intCols = Integer.valueOf(userStrCols); //replace the str in int

            viewModel.generateMaze(intRows, intCols);

            //ShowSolution.setDisable(false);
            //mazeDisplayer.setMazeSolution(null);
            //mazeDisplayer.setIfMazeSolved(false);

        } else {
            showErrorAlert("Invalid Input", "Invalid Number!/n" + "Please enter numbers between 3 to 500");
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


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MyViewModel) {
            if (arg == "update") {
                mazeDisplayer.setMazeSolution(null);
                mazeDisplayer.setIfMazeSolved(false);
                mazeDisplayer.setIntMazeArray(viewModel.getIntMazeArrayMVM());
                mazeDisplayer.setGoalPosition(viewModel.getGoalRow(), viewModel.getGoalCol());
                mazeDisplayer.setPlayerPosition(viewModel.getCurrentRow(), viewModel.getCurrentCol());
                mazeDisplayer.drawMaze(mazeDisplayer.getIntMazeArray());

                setUpdatePlayerPositionRow(viewModel.getCurrentRow() + "");
                setUpdatePlayerPositionCol(viewModel.getCurrentCol() + "");
                this.zoom(mazeDisplayer);

                //ShowSolution.setDisable(false);
                //HideSolution.setDisable(true);

            }
            else if (arg == "load incorrect file type")
            {
                //showErrorAlert("You tried to upload an unsuitable file type or a file that does not contain a maze. Please reload a file with .maze extension only.");
            }
            else if (arg == "move") {
                if (viewModel.isIfWonTheGame())
                {
                   // viewModel.pauseMusic();
                    Stage stage = new Stage();
                    stage.setTitle("C O N G R A T U L A T I O N S ! ! !");
                    VBox layout = new VBox();
                    HBox H = new HBox(5);
                    //H.setAlignment(CENTER);
                    //layout.setAlignment(CENTER);
                    Button close = new Button();
                    close.setText("CLOSE");
                    H.getChildren().add(close);
                    layout.spacingProperty().setValue(10);
                   // Image im = new Image("/Images/giphy.gif");
                    //ImageView image = new ImageView(im);
                    //layout.getChildren().add(image);
                    layout.getChildren().add(H);
                    Scene scene = new Scene(layout, 494, 365);
                    scene.getStylesheets().add(getClass().getResource("/View/MainStyle.css").toExternalForm());
                    stage.setScene(scene);
                    //stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                    stage.show();
                    //close button
                    //close.setOnAction(new EventHandler<ActionEvent>() {
                        //@Override
                        //public void handle(ActionEvent event) {
                            //stage.close();
                        //}
                    //});
                   // try{
                        //viewModel.playMusic((new Media(getClass().getResource("/Music/SpongeBobFlute.mp3").toURI().toString())),200);
                   // } catch (URISyntaxException e) {
                       // e.printStackTrace();
                   // }
                }
                else {
                    mazeDisplayer.setPlayerPosition(viewModel.getCurrentRow(), viewModel.getCurrentCol());
                    setUpdatePlayerPositionRow(viewModel.getCurrentRow() + "");
                    setUpdatePlayerPositionCol(viewModel.getCurrentCol() + "");
                }
            }
            else if (arg == "solve") {
                mazeDisplayer.drawSolution(viewModel.getMazeSolution());
            }
            else if (arg == "save") {
               // showAlert("Save Maze", "Your maze was successfully saved");
            }

        }
    }



    private void zoom(MazeDisplayer pane) {
        pane.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        if (event.isControlDown()) {
                            double zoomFactor = 1.05;
                            double deltaY = event.getDeltaY();

                            if (deltaY < 0) {
                                zoomFactor = 0.95;
                            }
                            pane.setScaleX(pane.getScaleX() * zoomFactor);
                            pane.setScaleY(pane.getScaleY() * zoomFactor);
                            event.consume();
                        }
                    }
                });
    }


    public void solveMaze()
    {
        viewModel.solve();
        hide.setDisable(false);
        solve.setDisable(true);
    }

    public void hideSolution() {
        mazeDisplayer.drawMaze(mazeDisplayer.getIntMazeArray());
        hide.setDisable(true);
        solve.setDisable(false);
    }

    //move character
    public void keyPressed(KeyEvent keyEvent) {
        viewModel.moveCharacter(keyEvent);
        keyEvent.consume();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        mazeDisplayer.requestFocus();
    }



    public void turnMusicOn()// natasha
    {
        if(sound)
        {
            String path = "Resources/Music/remix.mp3";
            viewModel.playTheMusic(path);
            this.sound=false;
        }
        else{
            viewModel.stopPlayTheMusic();
            this.sound=true;
        }
    }




    //________________getters & setters____________________


    public String getUpdatePlayerPositionRow() {
        return updatePlayerPositionRow.get();
    }

    public StringProperty updatePlayerPositionRowProperty() {
        return updatePlayerPositionRow;
    }

    public void setUpdatePlayerPositionRow(String updatePlayerPositionRow) {
        this.updatePlayerPositionRow.set(updatePlayerPositionRow);
    }

    public String getUpdatePlayerPositionCol() {
        return updatePlayerPositionCol.get();
    }

    public StringProperty updatePlayerPositionColProperty() {
        return updatePlayerPositionCol;
    }

    public void setUpdatePlayerPositionCol(String updatePlayerPositionCol) {
        this.updatePlayerPositionCol.set(updatePlayerPositionCol);
    }

}
