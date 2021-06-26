import java.util.*;

public class GameGrid {
    private boolean[][] bombGrid;
    private int[][] numberGrid;
    private SpecialCase[][] caseGrid;

    /**
     * Constructs a GameGrid object
     */
    public GameGrid(Difficulty currentDifficulty){
        switch (currentDifficulty){
            case EASY -> {
                bombGrid = new boolean[10][8];
                numberGrid = new int[10][8];
                caseGrid = new SpecialCase[10][8];
            }
            case MEDIUM -> {
                bombGrid = new boolean[11][9];
                numberGrid = new int[11][9];
                caseGrid = new SpecialCase[11][9];
            }
            case HARD -> {
                bombGrid = new boolean[12][10];
                numberGrid = new int[12][10];
                caseGrid = new SpecialCase[12][10];
            }
        }
        generateAndRandomizeBombs(currentDifficulty);
        determineClues();
    }

    /**
     * Generate the random placement of bombs on the grid
     */
    private void generateAndRandomizeBombs(Difficulty currentDifficulty){
        //populate the bombGrid with adequate bomb locations
        switch (currentDifficulty){
            //EASY has 10 bombs
            case EASY -> {
                for (int row=0; row<bombGrid.length; row++){
                    bombGrid[row] = new boolean[] {true, false, false, false, false, false, false, false};
                }
            }
            //MEDIUM has 15 bombs
            case MEDIUM -> {
                for (int row=0; row<bombGrid.length; row++){
                    if (row<=3){
                        bombGrid[row] = new boolean[] {true, true, false, false, false, false, false, false, false};
                    }else {
                        bombGrid[row] = new boolean[]{true, false, false, false, false, false, false, false, false};
                    }
                }
            }
            //HARD has 20 bombs
            case HARD -> {
                for (int row=0; row<bombGrid.length; row++){
                    if (row<=7) {
                        bombGrid[row] = new boolean[]{true, true, false, false, false, false, false, false, false, false};
                    }else {
                        bombGrid[row] = new boolean[]{true, false, false, false, false, false, false, false, false, false};
                    }
                }
            }
        }
        //randomize the location of the bombs
        for (int i=0; i<bombGrid.length; i++){
            for (int j=0; j<bombGrid[i].length; j++){
                int iPrime = (int)(Math.random()*bombGrid.length);
                int jPrime = (int)(Math.random()*bombGrid[i].length);

                boolean temp = bombGrid[i][j];
                bombGrid[i][j] = bombGrid[iPrime][jPrime];
                bombGrid[iPrime][jPrime] = temp;
            }
        }
    }

    /**
     * Determine the number of bombs surrounding each space
     */
    private void determineClues(){
        //determine the number of bombs surrounding the current spot (-1 if it is a bomb itself)
        for (int row=0; row<numberGrid.length; row++){
            for (int column=0; column<numberGrid[row].length; column++){
                boolean topRow = false;
                boolean bottomRow = false;
                boolean leftColumn = false;
                boolean rightColumn = false;
                SpecialCase caseCheck;
                int surroundingBombs = 0;

                //if current position is a bomb
                if (bombGrid[row][column]){
                    surroundingBombs = -1;
                }else {
                    //edge cases
                    if (row == 0) {
                        topRow = true;
                    }else if (row == numberGrid.length - 1) {
                        bottomRow = true;
                    }
                    if (column == 0) {
                        leftColumn = true;
                    }else if (column == numberGrid[row].length - 1) {
                        rightColumn = true;
                    }

                    //check which case the current position is
                    if (leftColumn && topRow) {
                        caseCheck = SpecialCase.TOP_LEFT;
                    }else if (topRow && rightColumn) {
                        caseCheck = SpecialCase.TOP_RIGHT;
                    }else if (bottomRow && rightColumn) {
                        caseCheck = SpecialCase.BOTTOM_RIGHT;
                    }else if (bottomRow && leftColumn) {
                        caseCheck = SpecialCase.BOTTOM_LEFT;
                    }else if (bottomRow) {
                        caseCheck = SpecialCase.BOTTOM;
                    }else if (topRow) {
                        caseCheck = SpecialCase.TOP;
                    }else if (rightColumn) {
                        caseCheck = SpecialCase.RIGHT;
                    }else if (leftColumn) {
                        caseCheck = SpecialCase.LEFT;
                    } else {
                        caseCheck = SpecialCase.NORMAL;
                    }
                    caseGrid[row][column] = caseCheck;
                    //determines the number of surrounding bombs
                    switch (caseCheck) {
                        case TOP_LEFT -> {
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                        }
                        case TOP -> {
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                        }
                        case TOP_RIGHT -> {
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                        }
                        case RIGHT -> {
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                        }
                        case BOTTOM_RIGHT -> {
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                        }
                        case BOTTOM -> {
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                        }
                        case BOTTOM_LEFT -> {
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                        }
                        case LEFT -> {
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                        }
                        case NORMAL -> {
                            if (bombGrid[row - 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row - 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column - 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column + 1]) {
                                surroundingBombs += 1;
                            }
                            if (bombGrid[row + 1][column]) {
                                surroundingBombs += 1;
                            }
                        }
                    }
                }
                //assign the corresponding position in the the numberGrid with the number of surrounding bombs
                numberGrid[row][column]=surroundingBombs;
            }
        }
    }

    /**
     * Accessor for bomb grid array
     * @return boolean[][] - 2-D array containing location of bombs
     */
    public boolean[][] getBombGrid(){
        return bombGrid;
    }

    /**
     * Accessor for number grid array
     * @return int[][] - 2-D array containing location of clues
     */
    public int[][] getNumberGrid(){
        return numberGrid;
    }

    /**
     * Accessor for case grid array
     * @return SpecialCase[][] - 2-D array containing the location type of the tiles
     */
    public SpecialCase[][] getCaseGrid(){
        return caseGrid;
    }

    /**
     * Outputs the pattern of bombs and clues to the console - Bug Testing
     */
    public void output(){
        for (boolean[] booleans : bombGrid) {
            System.out.println(Arrays.toString(booleans));
        }
        System.out.println();
        for (int[] ints : numberGrid) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }
}
