package ViewModel;
import Model.MyModel;
import javafx.scene.media.MediaPlayer;


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




    public MyViewModel() { this.model = MyModel.getInstance(); } //constructor

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
            if (arg == "generate" || arg == "load") {
                intMazeArray = model.getMazeArray();
                currentRow = model.getCurrentRow();
                currentCol = model.getCurrentCol();
                goalCol = model.getGoalRow();
                goalCol = model.getGoalCol();
                //
                mazeSolution = null;

                setChanged();
                notifyObservers("update");
            }
            else if (arg == "load incorrect file type")
            {
                setChanged();
                notifyObservers("load incorrect file type");
            }
            else if (arg == "move") {
                currentRow = model.getCurrentRow();
                currentCol = model.getCurrentCol();
                ifWonTheGame = model.ifWonGame();
                setChanged();
                notifyObservers("move");
            }
            else if (arg == "solve") {
                mazeSolution = model.getMazeSolution();
                setChanged();
                notifyObservers("solve");
            }
            else if (arg == "save") {
                setChanged();
                notifyObservers("save");
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





    //_____________________________GETTERS & SETTERS________________________________________

    public MyModel getModel() { return model; }
    public void setModel(MyModel model) { this.model = model; }
    public int[][] getIntMazeArray() { return intMazeArray; }
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
