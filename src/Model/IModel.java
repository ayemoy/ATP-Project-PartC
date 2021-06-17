package Model;

import ViewModel.MyViewModel;
import javafx.beans.Observable;


/**
 * in this interface we write what function MyModel need to implement
 * this is the function of the maze..all actions that will create and func the maze
 */
public interface IModel {

    void generateMaze(int rows, int cols);
    void solveTheGameMaze();
    void updateTheCharacterLocation(int direction);
    void stopServers();
    void saveTheUserMazeToFile(String filePath);
    void loadUserMaze (String filePath);
    void addObserver(MyViewModel viewModel);


    //choose player
    void setCharacter(String name, String url);


}
