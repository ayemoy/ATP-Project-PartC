package ViewModel;
import Model.MyModel;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer {

    private static MyViewModel myViewModel;
    private MyModel model;
    private int[][] intMazeArray;
    private int goalRow;
    private int goalCol;
    private int currentRow;
    private int currentCol;
    private boolean ifWonTheGame;
    private ArrayList<int[]> mazeSolution;
    private MediaPlayer playMusic;




    public MyViewModel()
    {
        this.model = MyModel.getInstance();
        this.model.addObserver(this);
    } //constructor

    // this function do the singleton
    public static MyViewModel getInstance()
    {
        if (myViewModel == null) {
            myViewModel = new MyViewModel();
        }
        return myViewModel;
    }


    @Override
    //this func gets update if view or model change and update according to it
    public void update(Observable o, Object arg)
    {
        if (o instanceof MyModel) {
            if (arg == "Generate" || arg == "Load") {
                this.intMazeArray = model.getMazeArray();
                this.currentRow = model.getCurrentRow();
                this.currentCol = model.getCurrentCol();
                this.goalRow = model.getGoalRow();
                this.goalCol = model.getGoalCol();
                //
                this.mazeSolution = null;

                setChanged();
                notifyObservers("Update");
            }
            else if (arg == "Load incorrect file type")
            {
                setChanged();
                notifyObservers("Load incorrect file type");
            }
            else if (arg == "Move") {
                currentRow = model.getCurrentRow();
                currentCol = model.getCurrentCol();
                ifWonTheGame = model.ifWonGame();
                setChanged();
                notifyObservers("Move");
            }
            else if (arg == "Solve") {
                mazeSolution = model.getMazeSolution();
                setChanged();
                notifyObservers("Solve");
            }
            else if (arg == "Save") {
                setChanged();
                notifyObservers("Save");
            }
        }

    }



    //this func get a generat maze request from myViewController in View Package and pass it to Model Package
    public void generateMaze(int row, int col)
    {
        mazeSolution = null;
        model.generateMaze(row, col);
    }

    public void exit() {
        model.stopServers();
    }



    //this function get notify from VIEW that the user want to save the maze and pass it to MYMODEL to save it
    public void saveMaze(String path) {
        if (path != null)
            model.saveTheUserMazeToFile(path);
    }

    //load maze
    public void loadMaze(String path) {
        if (path != null)
            model.loadUserMaze(path);
    }


    //move character
    public void moveCharacter(KeyEvent keyEvent) {
        int characterMovePress = -1;
        switch (keyEvent.getCode()) {
            case UP:
                characterMovePress = 1;
                break;
            case DIGIT8:
                characterMovePress = 1;
                break;
            case DOWN:
                characterMovePress = 2;
                break;
            case DIGIT2:
                characterMovePress = 2;
                break;
            case LEFT:
                characterMovePress = 3;
                break;
            case DIGIT4:
                characterMovePress = 3;
                break;
            case RIGHT:
                characterMovePress = 4;
                break;
            case DIGIT6:
                characterMovePress = 4;
                break;
            //Diagonal steps
            case DIGIT7:
                characterMovePress = 5;
                break;
            case DIGIT9:
                characterMovePress = 6;
                break;
            case DIGIT1:
                characterMovePress = 7;
                break;
            case DIGIT3:
                characterMovePress = 8;
                break;
        }
        model.updateTheCharacterLocation(characterMovePress);
    }


    public void solve()
    {
        model.solveTheGameMaze();
    }


    public boolean playTheMusic(String path) throws URISyntaxException
    {//natasha

        Media music = new Media( new File(path).toURI().toString());
        playMusic = new MediaPlayer(music);
        playMusic.setVolume(220);
        playMusic.play();
        return false;

    }
    public void stopPlayTheMusic()
    {
        playMusic.stop();
    }//natasha




    //_____________________________GETTERS & SETTERS________________________________________

    public MyModel getModel() { return model; }
    public void setModel(MyModel model) { this.model = model; }
    public int[][] getIntMazeArrayMVM() { return intMazeArray; }
    public void setIntMazeArray(int[][] mazeArray) { this.intMazeArray = mazeArray; }
    public int getGoalRow() { return goalRow; }
    public void setGoalRow(int goalRow) { this.goalRow = goalRow; }
    public int getGoalCol() { return goalCol; }
    public void setGoalCol(int goalCol) { this.goalCol = goalCol; }
    public int getCurrentRow() { return currentRow; }
    public void setCurrentRow(int currentRow) { this.currentRow = currentRow; }
    public int getCurrentCol() { return currentCol; }
    public void setCurrentCol(int currentCol) { this.currentCol = currentCol; }
    public boolean isIfWonTheGame() { return ifWonTheGame; }
    public void setIfWonTheGame(boolean ifWonTheGame) { this.ifWonTheGame = ifWonTheGame; }
    public ArrayList<int[]> getMazeSolution() { return mazeSolution; }
    public void setMazeSolution(ArrayList<int[]> mazeSolution) { this.mazeSolution = mazeSolution; }
    public MediaPlayer getPlayMusic() { return playMusic; }
    public void setPlayMusic(MediaPlayer playMusic) { this.playMusic = playMusic; }
}
