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
import javafx.beans.Observable;


public class MyModel implements IModel, Observable {

    private static MyModel myModel;
    private Server generateServer;
    private Server solveServer;
    private Maze maze;
    private int[][] intMazeArray;
    private int playerRow;
    private int playerCol;
    private int goalRow;
    private int goalCol;
    private boolean ifwonGame;
    private ArrayList<AState> mazeSolutionSteps;
    private ArrayList<int[]> finalSolution;
    //public MediaPlayer mediaPlayer;





    private MyModel() //constructor
    {
        Server.Configuration("MazeGenerator","MyMazeGenerator");
        Server.Configuration("SearchingAlgorithm","Depth First Search");
        Server.Configuration("ThreadPoolSize","3");
        generateServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        solveServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        generateServer.start();
        //LOG.info("Generate maze server started");
        solveServer.start();
        //LOG.info("Solve maze server started");
    }

    //singleton
    public static MyModel getInstance() {
        if (myModel == null) {
            myModel = new MyModel();
        }
        return myModel;
    }




    @Override
    public void generateMaze(int row, int col) {
        CommunicateWithServer_MazeGenerating(row, col);
        //initMaze(maze);
        //LOG.info("A new maze has been created. Maze dimensions - " + row + " X " + col);
       // setChanged();
        //notifyObservers("generate");

    }

    @Override
    public int[][] getMaze() {
        return new int[0][];
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


    //this function given in part B
    private void CommunicateWithServer_MazeGenerating(int row, int col) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, (IClientStrategy) (inFromServer, outToServer) -> {
                try {
                    //LOG.info("User info " + InetAddress.getLocalHost() + "requests to generate a maze");
                    //LOG.info("A maze creation request was accepted! Generating maze using " +
                    //Server.getConfigurations("MazeGenerator"));
                    ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                    ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                    toServer.flush();
                    int[] mazeDimensions = new int[]{row, col};
                    toServer.writeObject(mazeDimensions);
                    toServer.flush();
                    byte[] compressedMaze = (byte[]) fromServer.readObject();
                    InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                    byte[] decompressedMaze = new byte[(row * col) + 24];
                    is.read(decompressedMaze);
                    maze = new Maze(decompressedMaze);
                } catch (Exception e) {
                    //LOG.error("Exception: ", e);
                    e.printStackTrace();
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            //LOG.error("Unknown Host Exception: ", e);
            e.printStackTrace();
        }
    }





    //_________________________ SETTERS & GETTERS _____________________________________________________


    public void setMaze(Maze maze) { this.maze = maze; }
    public int[][] getInMazeArray() { return intMazeArray; }
    public void setInMazeArray(int[][] inMazeArray) { this.intMazeArray = inMazeArray; }
    public int getPlayerRow() { return playerRow; }
    public void setPlayerRow(int playerRow) { this.playerRow = playerRow; }
    public int getPlayerCol() { return playerCol; }
    public void setPlayerCol(int playerCol) { this.playerCol = playerCol; }
    public ArrayList<AState> getMazeSolutionSteps() { return mazeSolutionSteps; }
    public void setMazeSolutionSteps(ArrayList<AState> mazeSolutionSteps) { this.mazeSolutionSteps = mazeSolutionSteps; }
    public ArrayList<int[]> getFinalSolution() { return finalSolution; }
    public void setFinalSolution(ArrayList<int[]> finalSolution) { this.finalSolution = finalSolution; }

    //_____________________function the MyViewModel use to communicate with model__________________________
    public int[][] getMazeArray() {return intMazeArray; }
    public int getCurrentRow() { return playerRow; }
    public int getCurrentCol() { return playerCol;}
    public int getGoalRow() { return goalRow; }
    public int getGoalCol() { return goalCol;}
    public boolean mazeSolution() { return ifwonGame;}
    public ArrayList<int[]> getMazeSolution() { return finalSolution;}
}
