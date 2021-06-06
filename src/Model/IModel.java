package Model;

import ViewModel.MyViewModel;
import javafx.beans.Observable;


public interface IModel {

    void generateMaze(int rows, int cols);
    //int[][] getMaze();
    void solveMaze();
    void updateCharacterLocation(int direction);
    void stopServers();
    void saveMazeToFile(String filePath);
    void loadUserMaze (String filePath);
    void addObserver(MyViewModel viewModel);

    /*ROTEM CODE
    public interface IModel {
        void generateMaze(int rows, int cols);
        int[][] getMaze();
        void updatePlayerLocation(MovementDirection direction);
        int getPlayerRow();
        int getPlayerCol();
        void assignObserver(Observer o);
        void solveMaze();
        Solution getSolution();
    }
    */



}
