package Model;

import ViewModel.MyViewModel;


import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.Solution;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import javafx.beans.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
//import java.util.Observable;
//import javafx.beans.Observable;


public class MyModel implements IModel, Observable {
    @Override
    public void generateMaze(int row, int col) {

    }

    @Override
    public void solveMaze() {

    }

    @Override
    public void updateCharacterLocation(int direction) {

    }

    @Override
    public void stopServers() {

    }

    @Override
    public void saveMazeToFile(String filePath) {

    }

    @Override
    public void loadUserMaze(String filePath) {

    }

    @Override
    public void addObserver(MyViewModel viewModel) {

    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }


}
