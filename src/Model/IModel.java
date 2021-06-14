package Model;

import ViewModel.MyViewModel;
import javafx.beans.Observable;


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
