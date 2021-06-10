package Model;

import ViewModel.MyViewModel;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

import Server.*;
import algorithms.search.Solution;
//import javafx.beans.Observable;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import javafx.beans.*;

//import java.util.Observable;



public class MyModel extends Observable implements IModel {

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
    private Position currentPosition;
    //_____________________________________________________________





    public MyModel() //constructor
    {
        Configurations.setMazeAlgorithm("MyMazeGenerator");
        Configurations.setSearchingAlgorithm("Depth First Search");
        Configurations.setThread(5);

        generateServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        solveServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        generateServer.start();
        //LOG.info("Generate maze server started");
        solveServer.start();
        //LOG.info("Solve maze server started");
    }

    //singleton
    public static MyModel getInstance()
    {
        if (myModel == null) {
            myModel = new MyModel();
        }
        return myModel;
    }


    @Override
    public void generateMaze(int row, int col)
    {
        GenerateAndCommunicateWithServer(row, col);
        initMaze(maze);
        //LOG.info("A new maze has been created. Maze dimensions - " + row + " X " + col);
        setChanged();
        notifyObservers("generate");

    }


    private void initMaze(Maze newMaze)
    {
        intMazeArray = maze.getIntMaze();
        playerRow = maze.getStartPosition().getRowIndex();
        playerCol = maze.getStartPosition().getColumnIndex();
        goalRow = maze.getGoalPosition().getRowIndex();
        goalCol = maze.getGoalPosition().getColumnIndex();
        ifwonGame = false;
        mazeSolutionSteps = null;
        finalSolution = null;
    }



    @Override
    public void solveMaze()
    {
        finalSolution = new ArrayList<>();
        SolveAndCommunicateWithServer();
        //updates sol
        for (AState state:mazeSolutionSteps)
        {
            int[] currPosState = new int[2];
            String x = state.getStateName();
            x.
            currPosState[0] = state.getStateName();
            mazeSolutionS
            currPosState[1] = state.getColState();
            finalSolution.add(currPosState);
        }
        //LOG.info("A solution for the maze was created. Solution number of steps - " + mazeSolutionSteps.size());
        setChanged();
        notifyObservers("solve");
    }

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
    public void addObserver(MyViewModel viewModel) { super.addObserver(viewModel); }


    //this function given in part B
    private void GenerateAndCommunicateWithServer(int row, int col) {
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




    private void SolveAndCommunicateWithServer()
    {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, (IClientStrategy) (inFromServer, outToServer) -> {
                try {
                    //LOG.info("User info " + InetAddress.getLocalHost() + "requests to solve a maze");
                    //LOG.info("A maze resolution request was accepted! Solving maze using " +
                    Configurations.getSearchingAlgorithm();
                    ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                    ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                    toServer.flush();
                    maze.setStartPosition(currentPosition);
                    toServer.writeObject(maze);
                    toServer.flush();
                    Solution mazeSolution = (Solution)fromServer.readObject();
                    mazeSolutionSteps = mazeSolution.getSolutionPath();
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

    public void setPlayerCurrentPosition()
    {
        this.currentPosition.setRowIndex(this.playerRow);
        this.currentPosition.setColIndex(this.playerCol);
    }
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
    public boolean ifWonGame() { return ifwonGame;}
    public ArrayList<int[]> getMazeSolution() { return finalSolution;}
}

