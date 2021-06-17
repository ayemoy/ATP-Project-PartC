package Model;

import View.MazeDisplayer;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    public MediaPlayer playMusic;

    ///\choose player
    private CharacterChoose mainCharacter = new CharacterChoose();


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
    public void generateMaze(int row, int col) //this function will generate the maze and send it to my view model and then to Myviewcontroller
    {
        GenerateAndCommunicateWithServer(row, col);
        initTheMaze(maze);

        //LOG.info("A new maze has been created. Maze dimensions - " + row + " X " + col);
        setChanged();
        notifyObservers("Generate");

    }


    private void initTheMaze(Maze newMaze) //get all the information about the maze and init it
    {
        ifwonGame = false;

        goalRow = maze.getGoalPosition().getRowIndex();
        goalCol = maze.getGoalPosition().getColumnIndex();


        intMazeArray = maze.getIntMaze();

        playerRow = maze.getStartPosition().getRowIndex();
        playerCol = maze.getStartPosition().getColumnIndex();
        currentPosition = new Position(playerRow,playerCol);

        mazeSolutionSteps = null;
        finalSolution = null;
    }



    @Override
    public void solveTheGameMaze()
    {
        finalSolution = new ArrayList<>();
        SolveAndCommunicateWithServer(); //call the server that handle with maze solving and communicate with it
        // after connect to the server , we updates the solution
        for (AState state:mazeSolutionSteps) {
            int[] currPosState = new int[2];
            String[] splitedIndexes = state.getStateName().replace("{","").replace("}","").split(",");
            int indexRow = Integer.parseInt(splitedIndexes[0]);
            int indexCol = Integer.parseInt(splitedIndexes[1]);

            currPosState[0] = indexRow;
            currPosState[1] = indexCol;
            finalSolution.add(currPosState);
        }
        //LOG.info("A solution for the maze was created. Solution number of steps - " + mazeSolutionSteps.size());
        setChanged();
        notifyObservers("Solve");
    }



    /*
      wherePlayerMove = 1 -> Up
       wherePlayerMove = 2 -> Down
       wherePlayerMove = 3 -> Left
       wherePlayerMove = 4 -> Right
        wherePlayerMove = 5 -> Up Left
        wherePlayerMove = 6 -> Up Right
       wherePlayerMove = 7 -> Down Left
       wherePlayerMove = 8 -> Down Right
             */
    @Override
    public void updateTheCharacterLocation(int wherePlayerMove) //this function update all the time the cuurent location of the character player and keep updating the new location that user move to
    {

        switch(wherePlayerMove) {
            case 1: //if the character move up
                if (ifCharacterCanMove(playerRow-1, playerCol))
                    playerRow--;
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 2: //if the character move down
                if (ifCharacterCanMove(playerRow+1, playerCol))
                    playerRow++;
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 3: //if the character move left
                if (ifCharacterCanMove(playerRow, playerCol-1))
                    playerCol--;
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 4: //if the character move right
                if (ifCharacterCanMove(playerRow, playerCol+1))

                    playerCol++;
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 5: //if the character move up left
                if (ifCharacterCanMove(playerRow-1, playerCol-1))
                {
                    playerRow--;
                    playerCol--;
                }
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 6: //if the character move up right
                if (ifCharacterCanMove(playerRow-1, playerCol+1))
                {
                    playerRow--;
                    playerCol++;
                }
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 7: // if the character move down left
                if (ifCharacterCanMove(playerRow+1, playerCol-1))
                {
                    playerRow++;
                    playerCol--;
                }
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
            case 8: // if the character move down right
                if (ifCharacterCanMove(playerRow+1, playerCol+1))
                {
                    playerRow++;
                    playerCol++;
                }
                else
                    playMusicWhenPlayerCantMove("Resources/Music/ops.mp3");
                break;
        }
        // now we check if the character get to the goal position and if the user won the game
        if (playerRow == goalRow && playerCol == goalCol)
        {
            ifwonGame = true;
            //LOG.info("The user won the game");
        }
        //now we notify to observer that the user move the character and set the changes that it makes in the game
        setChanged();
        notifyObservers("Move");
    }




    //this function check if the player can move to any side
    public boolean ifCharacterCanMove(int rowUserWantToMove, int colUserWantToMove) {
        if (rowUserWantToMove < 0 || rowUserWantToMove >= intMazeArray.length ||
                colUserWantToMove < 0 || colUserWantToMove >= intMazeArray[0].length || intMazeArray[rowUserWantToMove][colUserWantToMove] == 1)
            return false;
        return true;
    }

    //the user hear this sound when the character do invalid step in the maze
    protected void playMusicWhenPlayerCantMove(String pathOfMusic) {
        String musicFile = pathOfMusic;
        Media sound = new Media(new java.io.File(musicFile).toURI().toString());
        playMusic = new MediaPlayer(sound);
        playMusic.play();
    }



    @Override
    public void stopServers() //this function stop the communication with the server and user
    {
        generateServer.stop();
        //LOG.info("Generate maze server stopped");
        solveServer.stop();
        //LOG.info("Solve maze server stopped");
    }


    //when user want to save the maze in the game into a file, he click on Save Button and save it
    @Override
    public void saveTheUserMazeToFile(String filePath)
    {
        try {
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(maze);
            output.flush();
            output.close();
            file.close();
           //LOG.info("The maze has been successfully saved to the disk");
            setChanged();
            notifyObservers("Save");
        }
        catch (IOException e)
        {
            //LOG.error("IO Exception: ", e);
        }
    }

    @Override
    public void loadUserMaze(String filePath)
    {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream input = new ObjectInputStream(file);
            maze = (Maze)input.readObject();
            initTheMaze(maze);
            //LOG.info("A maze has been successfully uploaded from the disk");
            setChanged();
            notifyObservers("Load");
            file.close();
        }
        catch (IOException|ClassNotFoundException e)
        {
            setChanged();
            notifyObservers("Load incorrect file type");
            //LOG.error("IO/Class Not Found Exception : Not a maze file");
            //e.printStackTrace();
        }

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
//                    setPlayerCurrentPosition();
                    this.intMazeArray = maze.getIntMaze();
                }
                catch (Exception e)
                {
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
                    Solution mazeSolution = (Solution)fromServer.readObject(); //problem
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




    //_______________________functions for choose player_____________________________________________-


    @Override
    public void setCharacter(String name, String url) {
        this.mainCharacter.setCharacter(name,url);
    }
}

