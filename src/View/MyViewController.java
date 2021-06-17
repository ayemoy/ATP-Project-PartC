package View;
import ViewModel.MyViewModel;
import algorithms.search.Solution;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import static javafx.geometry.Pos.CENTER;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;


//do all function of the maze play screen
//like: init, displayMaze, Zoom, solveMaze, generateMaze.......

public class MyViewController extends Controller implements IView , Initializable {

    //private MyViewModel viewModel; //save object of viewModel like we need to do in MVVM
    public boolean showSolution; //boolean that help us know in we need to show the solution
    public MazeDisplayer mazeDisplayer;
    @FXML
    private Solution solution;

    private boolean sound=true;//natasha
    public String AlertClipString = new File("Resources/Music/winVideo.mp4").getAbsolutePath();



    @FXML
    public SplitPane splitPane;
    @FXML
    public AnchorPane leftSide;
    @FXML
    public Button play;
    @FXML
    public Button back;
    @FXML
    public Button save;
    @FXML
    public Button solve;
    @FXML
    public Button hide;
    @FXML
    public TextField RowField;
    @FXML
    public TextField ColField;
    @FXML
    public MenuBar menuBar;
    @FXML
    public ImageView imageLeft;
    @FXML
    public ImageView imageRight;


    @FXML
    public AnchorPane rightSide;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Pane pane;


    @FXML
    public Label labelPlayerRow;
    @FXML
    public Label labelPlayerCol;

    private StringProperty updatePlayerPositionRow = new SimpleStringProperty();
    private StringProperty updatePlayerPositionCol = new SimpleStringProperty();


    //_____________function for mouse drag______________________________
    //public MyViewController(MazeDisplayer mazeDisplayer) { //constructor
      //  this.mazeDisplayer = mazeDisplayer;
    //}

    public Boolean CellPlayer=false;


//________________________________________

    @Override
    //this function do all updates so when the maze window open, the function run and do all we need
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        RowField.clear();
        ColField.clear();

        //labelPlayerRow.textProperty().bind(updatePlayerPositionRow);
        //labelPlayerCol.textProperty().bind(updatePlayerPositionCol);

        fitDisplaySizes();
        viewModel.stopPlayTheMusic();
        try
        {
        viewModel.playTheMusic("Resources/Music/remix.mp3");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }







/*
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
*/


    private void fitDisplaySizes()
    {
        rightSide.widthProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinWidth(rightSide.getWidth() - 10);
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });
        rightSide.heightProperty().addListener((obs, oldVal, newVal) -> {
            pane.setMinHeight(rightSide.getHeight());
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
            mazeDisplayer.setHeight(pane.getHeight() - 10);
            if (viewModel.getIntMazeArrayMVM() != null)
                mazeDisplayer.draw();
        });

    }



    public void handleSaveFile(ActionEvent actionEvent)
    {
        SaveMenuBar("save", (Stage) back.getScene().getWindow(), false);
    }










    //this function chek if the number of roes and number of columns that the uset insert to the text field is valid
    private boolean checkIfValidRowsCols(String userNumStr) {
        String regex = "\\d+";
        if (userNumStr.matches(regex)) {
            int val = Integer.valueOf(userNumStr);
            if (val >= 2 && val <= 500)
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

            solve.setDisable(false);
            mazeDisplayer.setMazeSolution(null);
            mazeDisplayer.setIfMazeSolved(false);
            pane.requestFocus();

        }
        else
        {
            showErrorAlert("Invalid Input", "Invalid Number!/n" + "Please enter numbers between 2 to 500");
        }
    }


    //______________________handlerssssssss_______________________________________________
    //this function is controll the back button
    public void switchToMain()
    {
        changeScene("MainScreen.fxml",(Stage)back.getScene().getWindow(),"A little bit about us");
    }
//__________________________________________________________________________________________

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MyViewModel) {
            if (arg == "Update") {
                mazeDisplayer.setMazeSolution(null);
                mazeDisplayer.setIfMazeSolved(false);
                mazeDisplayer.setIntMazeArray(viewModel.getIntMazeArrayMVM());
                mazeDisplayer.setGoalPosition(viewModel.getGoalRow(), viewModel.getGoalCol());
                mazeDisplayer.setPlayerPosition(viewModel.getCurrentRow(), viewModel.getCurrentCol());
                mazeDisplayer.drawMaze(mazeDisplayer.getIntMazeArray());

                setUpdatePlayerPositionRow(viewModel.getCurrentRow() + "");
                setUpdatePlayerPositionCol(viewModel.getCurrentCol() + "");
                //this.zoom(mazeDisplayer); todo maybe we need this line for zoom func

                solve.setDisable(false);
                hide.setDisable(true);

            }
            else if (arg == "Load incorrect file type")
            {
                showErrorAlert("File Problem" ,"You tried to upload an unsuitable file type or a file that does not contain a maze. Please reload a file with .maze extension only.");
            }
            else if (arg == "Move") {
                if (viewModel.isIfWonTheGame())
                {
                    mazeDisplayer.setPlayerPosition(viewModel.getCurrentRow(),viewModel.getCurrentCol());
                    ButtonType newGameButtonType = new ButtonType("New Game" , ButtonBar.ButtonData.LEFT);
                    ButtonType noButtonType = new ButtonType("No");
                    Alert a = new Alert(Alert.AlertType.NONE,"",newGameButtonType,noButtonType);
                    a.getDialogPane().setStyle("-fx-background-color: black;");
                    Button newGameButton = (Button) a.getDialogPane().lookupButton(newGameButtonType);
                    newGameButton.setAlignment(Pos.CENTER);
                    a.setTitle("YOU WIN !!!");
                    a.setHeaderText(null);
                    Media vid =  new Media(new File(AlertClipString).toURI().toString());
                    MediaPlayer player = new MediaPlayer(vid);
                    player.setVolume(0.9);
                    MediaView mediaView = new MediaView(player);
                    mediaView.setFitHeight(400);
                    mediaView.setFitWidth(600);
                    VBox content = new VBox(0.001, mediaView);
                    player.setOnEndOfMedia(new Runnable() {
                        @Override
                        public void run() {
                            player.seek(Duration.ZERO);
                        }
                    });
                    content.setAlignment(Pos.CENTER);
                    a.getDialogPane().setContent(content);
                    MediaPlayer.Status MusicStatus = MyViewModel.getInstance().getPlayMusic().getStatus();
                    a.setOnShowing(e -> {MyViewModel.getInstance().getPlayMusic().pause();player.play();});
                    a.getDialogPane().setPrefHeight(420);
                    a.getDialogPane().setPrefWidth(610);
                    a.getDialogPane().getScene().getWindow().setOnCloseRequest(e-> {a.close();});
                    Optional<ButtonType> result = a.showAndWait();
                    if(!result.isPresent()){
                        player.stop();
                        //MusicSwitchCase(MusicStatus);
                    }
                    else if(result.get() == newGameButtonType){
                        player.stop();
                        //MusicSwitchCase(MusicStatus);
                        handleNewFile(new ActionEvent());
                    }
                    else if(result.get() == noButtonType) {
                        player.stop();
                        //MusicSwitchCase(MusicStatus);
                    }
                    /*
                    //________________________________________

                    //___________________________________
                    viewModel.stopPlayTheMusic();
                    Stage stage = new Stage();
                    stage.setTitle("C O N G R A T U L A T I O N S ! ! !");
                    VBox layout = new VBox();
                    //HBox H = new HBox(5);
                    //H.setAlignment(CENTER);
                    layout.setAlignment(CENTER);
                    Button close = new Button();
                    close.setText("CLOSE");
                    //H.getChildren().add(close);
                    layout.spacingProperty().setValue(10);
                    //Image im = new Image("/pictures/giphy.gif"); original
                    Image im = new Image("/pictures/win.jpg");
                    ImageView image = new ImageView((Element) im);
                    //layout.getChildren().add(image);
                   // layout.getChildren().add(H);
                    Scene scene = new Scene(layout, 494, 365);
                    //scene.getStylesheets().add(getClass().getResource("/View/MainStyle.css").toExternalForm());
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                    stage.show();
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                        }
                    });
                    try{
                        viewModel.playTheMusic("/pictures/ops.mp3");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }*/
                }
                else {
                    mazeDisplayer.setPlayerPosition(viewModel.getCurrentRow(), viewModel.getCurrentCol());
                    setUpdatePlayerPositionRow(viewModel.getCurrentRow() + "");
                    setUpdatePlayerPositionCol(viewModel.getCurrentCol() + "");
                }
            }
            else if (arg == "Solve") {
                mazeDisplayer.drawSolution(viewModel.getMazeSolution());
            }
            else if (arg == "Save")
            {
                showAlert("Save Maze", "Your maze was successfully saved");
            }

        }
    }




    public void zoom(ScrollEvent event){
        if(viewModel.getIntMazeArrayMVM() != null){
            if (event.isControlDown()) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if(deltaY < 0){
                    zoomFactor = 2 - zoomFactor;
                }

                rightSide.setScaleX(rightSide.getScaleX() * zoomFactor);
                rightSide.setScaleY(rightSide.getScaleY() * zoomFactor);

            }
        }
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


    public void turnMusicOn() throws URISyntaxException {
        turnMusicOn("Resources/Music/remix.mp3");
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





//______________________________FUNCTION FOR mouse drag____________________________________


    public void MoveWithDrag(MouseEvent mouseEvent) {

        double canvasHeight = mazeDisplayer.getHeight();
        double canvasWidth = mazeDisplayer.getWidth();
        int row = mazeDisplayer.intMazeArray.length;
        int col = mazeDisplayer.intMazeArray[0].length;
        double cellHeight = canvasHeight / row;
        double cellWidth = canvasWidth / col;



        if (mazeDisplayer.getIntMazeArray() == null) {
            System.out.println("please create maze");
            return;
        }
        if (!mazeDisplayer.ifMazeSolved) {
            int mouseX = (int) ((mouseEvent.getSceneX()-233)/ cellWidth);
            int mouseY = (int) ((mouseEvent.getSceneY()) / cellHeight);

            if (mazeDisplayer.getPlayerCol() == mouseX && mazeDisplayer.getPlayerRow() == mouseY) {
                CellPlayer = true;
            }
            if (CellPlayer) {
                viewModel.movePlayerWithMouseDrag(mouseX, mouseY);
                mazeDisplayer.setPlayerPosition(mazeDisplayer.playerRow, mazeDisplayer.playerCol);
            }
            //playerWonHandle();
        }

    }

        public void freeMouse(MouseEvent  mouseEvent)
        {
            CellPlayer = false;
        }

//________________________________________-video win scene_________________________________











    //////////////______________________///////////MENU BAR////////////////////////////////



    public void handleNewFile(ActionEvent actionEvent) {
        changeScene("MyView.fxml",(Stage)back.getScene().getWindow(),"New Maze");
    }

    public void handleSaveMenuFile(ActionEvent actionEvent) {
        SaveMenuBar("save",(Stage)back.getScene().getWindow(),false);
    }

    public void handleLoadFile(ActionEvent actionEvent) {
        LoadMenuBar("Load",(Stage)back.getScene().getWindow(),false);
    }


    public void handleExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { //want to exit the game
            viewModel.exit();
            Window welcome = back.getScene().getWindow();
            ((Stage) welcome).close();
        }
    }
    public void switchToAboutScene()
    {
        changeScene("AboutScene.fxml",(Stage)back.getScene().getWindow(),"About us and our game algorithms");
    }

    public void switchToHelpScene()
    {
        changeScene("HelpScene.fxml",(Stage)back.getScene().getWindow(),"We are here for you!");
    }

    public void switchToPropertiesScene()
    {
        changeScene("PropertiesScene.fxml",(Stage)back.getScene().getWindow(),"Your Properties");
    }
/////////////////////////////////MENUBAR///////////////////////////////////////////////


}