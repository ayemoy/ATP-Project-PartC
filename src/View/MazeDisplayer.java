package View;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MazeDisplayer extends Canvas {


    private int[][] intMazeArray;
    private int playerRow;
    private int playerCol;
    private int playerGoalRow;
    private int playerGoalCol;
    private ArrayList<int[]> mazeSolution;
    private boolean ifMazeSolved;

    // wall and player images:

    private StringProperty wallPic= new SimpleStringProperty("pictures/jellyfish.png");
    private StringProperty playerPic= new SimpleStringProperty("pictures/player.png");
    private StringProperty startPosPic= new SimpleStringProperty("pictures/house.png");
    private StringProperty goalPosPic= new SimpleStringProperty("pictures/krusty.png");
    private StringProperty solPathPic= new SimpleStringProperty("pictures/burger3.png");


    //this function draw the maze itself, the walls and the player
    void draw()
    {
        if(intMazeArray != null)
        {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            int row = intMazeArray.length;
            int col = intMazeArray[0].length;
            double cellHeight = canvasHeight/row;
            double cellWidth = canvasWidth/col;
            GraphicsContext graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0,0, canvasWidth, canvasHeight);
            graphicsContext.strokeRect(0,0, canvasWidth, canvasHeight);
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.setLineWidth(2);
            graphicsContext.stroke();
            double w, h;

            //draw the maze
            Image wallImage = null;
            try {
                wallImage = new Image(new FileInputStream(getWallPic()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no wallPic in here");
            }
            for(int i=0;i<row;i++)
            {
                for(int j=0;j<col;j++)
                {
                    if(intMazeArray[i][j] == 1) //Wall
                    {
                        h = i * cellHeight;
                        w = j * cellWidth;
                        if (wallImage == null) {
                            graphicsContext.fillRect(w, h, cellWidth, cellHeight);
                        }
                        else {
                            graphicsContext.drawImage(wallImage, w, h, cellWidth, cellHeight);
                        }
                    }
                }
            }
            //Draw Solution
            if (ifMazeSolved)
            {
                Image solImage = null;
                try {
                    solImage = new Image(new FileInputStream(getSolPathPic()));
                } catch (FileNotFoundException e) {
                    System.out.println("There is no solPathPic in here");
                }
                double hSol,wSol;
                for (int i=1; i<mazeSolution.size()-1; i++) {
                    hSol = mazeSolution.get(i)[0] * cellHeight;
                    wSol = mazeSolution.get(i)[1] * cellWidth;
                    if (solImage == null)
                        graphicsContext.fillRect(wSol, hSol, cellWidth, cellHeight);
                    else
                        graphicsContext.drawImage(solImage, wSol, hSol, cellWidth, cellHeight);
                }
            }
            //draw the Player
            double h_player = getPlayerRow() * cellHeight;
            double w_player = getPlayerCol() * cellWidth;
            Image playerImage = null;
            try {
                playerImage = new Image(new FileInputStream(getPlayerPic()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no playerPic in here");
            }
            graphicsContext.drawImage(playerImage, w_player, h_player, cellWidth, cellHeight);
            //Target
            double h_target = getPlayerGoalRow() * cellHeight;
            double w_target = getPlayerGoalCol() * cellWidth;
            Image targetImage = null;
            try {
                targetImage = new Image(new FileInputStream(getGoalPosPic()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no goalPic in here");
            }
            graphicsContext.drawImage(targetImage, w_target, h_target, cellWidth, cellHeight);

        }
    }


    //this function chek if we have not solv the maze yet, so we draw it
    public void drawMaze(int [][] maze)
    {
        this.intMazeArray = maze;
        ifMazeSolved = false;
        draw();
    }

    public void drawSolution(ArrayList<int[]> solution)
    {
        ifMazeSolved = true;
        mazeSolution = solution;
        draw();
    }


    //this function is print the solution path and then desolve ddddddddddddddddddddddddddddddddd
//    private void printSolution() {
//        canvasHeight = getHeight();
//        canvasWidth = getWidth();
//        cellHeight = canvasHeight/arrMaze.length;
//        cellWidth = canvasWidth/arrMaze[0].length;
//        Image characterImage = new Image(Main.getViewModel().getCharacterPicPath());
//        Image trophy = new Image("/Images/trophy.png");
//        GraphicsContext graphicsContext = getGraphicsContext2D();
//        int pathSize = solution.getSolutionPath().size();
//        for(int index = 0; index < pathSize; index++) {
//            int rowIndex,colIndex;
//            rowIndex = ((MazeState)solution.getSolutionPath().get(index)).getMyState().getRowIndex();
//            colIndex = ((MazeState)solution.getSolutionPath().get(index)).getMyState().getColumnIndex();
//            graphicsContext.drawImage(index < pathSize-1 ?characterImage:trophy ,colIndex*cellWidth,rowIndex*cellHeight,cellWidth,cellWidth);
//        }
//    }









//__________________________________GETTERS & SETTERS______________________________________________________


    public void setIfMazeSolved(Boolean solved) { this.ifMazeSolved = solved; }
    public ArrayList<int[]> getMazeSolution() { return mazeSolution; }
    public void setMazeSolution(ArrayList<int[]> solution) { this.mazeSolution = solution; }
    public boolean isIfMazeSolved() { return ifMazeSolved; }


    public int getPlayerRow() { return playerRow; }
    public int getPlayerCol() { return playerCol; }
    public void setPlayerPosition(int row, int col)
    {
        this.playerRow = row;
        this.playerCol = col;
        draw();
    }
    public int getPlayerGoalRow() { return playerGoalRow; }
    public int getPlayerGoalCol() { return playerGoalCol; }
    public void setGoalPosition(int row, int col)
    {
        this.playerGoalRow = row;
        this.playerGoalCol = col;
    }
    public int[][] getIntMazeArray() { return intMazeArray; }
    public void setIntMazeArray(int[][] intMaze) { this.intMazeArray = intMaze; }


    public void setPlayerRow(int playerRow) { this.playerRow = playerRow; }
    public void setPlayerCol(int playerCol) { this.playerCol = playerCol; }
    public void setPlayerGoalRow(int playerGoalRow) { this.playerGoalRow = playerGoalRow; }
    public void setPlayerGoalCol(int playerGoalCol) { this.playerGoalCol = playerGoalCol; }



    public String getWallPic() { return wallPic.get(); }
    public StringProperty wallPicProperty() { return wallPic; }
    public void setWallPic(String wallPic) { this.wallPic.set(wallPic); }
    public String getPlayerPic() { return playerPic.get(); }
    public StringProperty playerPicProperty() { return playerPic; }
    public void setPlayerPic(String playerPic) { this.playerPic.set(playerPic); }
    public String getStartPosPic() { return startPosPic.get(); }
    public StringProperty startPosPicProperty() { return startPosPic; }
    public void setStartPosPic(String startPosPic) { this.startPosPic.set(startPosPic); }
    public String getGoalPosPic() { return goalPosPic.get(); }
    public StringProperty goalPosPicProperty() { return goalPosPic; }
    public void setGoalPosPic(String goalPosPic) { this.goalPosPic.set(goalPosPic); }
    public String getSolPathPic() { return solPathPic.get(); }
    public StringProperty solPathPicProperty() { return solPathPic; }
    public void setSolPathPic(String solPathPic) { this.solPathPic.set(solPathPic); }
//______________________________________________________________________________________________________


}
