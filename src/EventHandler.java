import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {

    private static GameGrid currentGrid;
    private static Difficulty currentDifficulty;
    private static int[] gamePixels = Minesweeper.getEasyGamePixels();
    private static final Color oceanBackground = new Color(0, 168, 243);

    @Override
    public void actionPerformed(ActionEvent e){
        //if user presses play button on main menu
        if (e.getSource()==Minesweeper.getWelcomeScreenPlayButton()){
            currentDifficulty = Minesweeper.getCurrentDifficulty();
            //generate the grid pattern for the bombs and numbers
            currentGrid = new GameGrid(currentDifficulty);

            Minesweeper.getFrame().setSize(gamePixels[0], gamePixels[1]);
            Minesweeper.getFrame().remove(Minesweeper.getWelcomePanel());
            Minesweeper.getGamePanel().add(Minesweeper.getGameTopText());
            Minesweeper.getFrame().add(Minesweeper.getGamePanel());

            int row = 0;
            int column = 0;
            int numOfSpots = 0;
            int maxColumnIndex = 0;
            switch (Minesweeper.getCurrentDifficulty()){
                case EASY -> {
                    numOfSpots = 80;
                    maxColumnIndex = 7;
                }
                case MEDIUM -> {
                    numOfSpots = 99;
                    maxColumnIndex = 8;
                }
                case HARD -> {
                    numOfSpots = 120;
                    maxColumnIndex = 9;
                }
            }
            for (int i = 0; i < numOfSpots; i++){
                Minesweeper.getButtons()[i].setIcon(null);
                Minesweeper.getGamePanel().add(Minesweeper.getButtons()[i]);
                if (currentGrid.getBombGrid()[row][column]){
                    Minesweeper.getLocations()[i].setIcon(Minesweeper.getBombIcon());
                }else {
                    int numOfSurroundingBombs = currentGrid.getNumberGrid()[row][column];
                    switch (numOfSurroundingBombs){
                        case 0 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getZeroIcon());
                        case 1 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getOneIcon());
                        case 2 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getTwoIcon());
                        case 3 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getThreeIcon());
                        case 4 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getFourIcon());
                        case 5 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getFiveIcon());
                        case 6 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getSixIcon());
                        case 7 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getSevenIcon());
                        case 8 -> Minesweeper.getLocations()[i].setIcon(Minesweeper.getEightIcon());
                    }
                }
                if (column == maxColumnIndex){
                    column = 0;
                    row += 1;
                }else {
                    column += 1;
                }
                Minesweeper.getGamePanel().add(Minesweeper.getLocations()[i]);
            }
            Minesweeper.getGamePanel().revalidate();
            Minesweeper.getGamePanel().repaint();
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();

        //if the user presses the theme button
        }else if (e.getSource()==Minesweeper.getThemesButton()){
            Minesweeper.getFrame().setSize(250, 175);
            Minesweeper.getFrame().remove(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().add(Minesweeper.getThemesPanel());
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();

        //if the user presses the classic theme button
        }else if (e.getSource()==Minesweeper.getClassicButton()){
            if (Minesweeper.getCurrentTheme()!=Theme.CLASSIC){
                Minesweeper.setCurrentTheme(Theme.CLASSIC);
                Minesweeper.getCurrentThemeIndicator().setBounds(60, 50, 20, 20);
                Minesweeper.getWelcomePanel().setBackground(Color.lightGray);
                Minesweeper.getGamePanel().setBackground(Color.lightGray);
                Minesweeper.getThemesPanel().setBackground(Color.lightGray);
                Minesweeper.getDifficultyPanel().setBackground(Color.lightGray);
                Minesweeper.getBombIcon().setImage(Minesweeper.getBombImageClassic());
                Minesweeper.getBombExplosionIcon().setImage(Minesweeper.getBombExplosionImageClassic());
                Minesweeper.getZeroIcon().setImage(Minesweeper.getZeroImageClassic());
                Minesweeper.getOneIcon().setImage(Minesweeper.getOneImageClassic());
                Minesweeper.getTwoIcon().setImage(Minesweeper.getTwoImageClassic());
                Minesweeper.getThreeIcon().setImage(Minesweeper.getThreeImageClassic());
                Minesweeper.getFourIcon().setImage(Minesweeper.getFourImageClassic());
                Minesweeper.getFiveIcon().setImage(Minesweeper.getFiveImageClassic());
                Minesweeper.getSixIcon().setImage(Minesweeper.getSixImageClassic());
                Minesweeper.getSevenIcon().setImage(Minesweeper.getSevenImageClassic());
                Minesweeper.getEightIcon().setImage(Minesweeper.getEightImageClassic());
                Minesweeper.getFlagIcon().setImage(Minesweeper.getFlagImageClassic());
                Minesweeper.getThemesPanel().revalidate();
                Minesweeper.getThemesPanel().repaint();
            }

        //if the user presses the ocean theme button
        }else if (e.getSource()==Minesweeper.getOceanButton()){
            if (Minesweeper.getCurrentTheme()!=Theme.OCEAN){
                Minesweeper.setCurrentTheme(Theme.OCEAN);
                Minesweeper.getCurrentThemeIndicator().setBounds(60, 75, 20, 20);
                Minesweeper.getWelcomePanel().setBackground(oceanBackground);
                Minesweeper.getGamePanel().setBackground(oceanBackground);
                Minesweeper.getThemesPanel().setBackground(oceanBackground);
                Minesweeper.getDifficultyPanel().setBackground(oceanBackground);
                Minesweeper.getBombIcon().setImage(Minesweeper.getBombImageOcean());
                Minesweeper.getBombExplosionIcon().setImage(Minesweeper.getBombExplosionImageOcean());
                Minesweeper.getZeroIcon().setImage(Minesweeper.getZeroImageOcean());
                Minesweeper.getOneIcon().setImage(Minesweeper.getOneImageOcean());
                Minesweeper.getTwoIcon().setImage(Minesweeper.getTwoImageOcean());
                Minesweeper.getThreeIcon().setImage(Minesweeper.getThreeImageOcean());
                Minesweeper.getFourIcon().setImage(Minesweeper.getFourImageOcean());
                Minesweeper.getFiveIcon().setImage(Minesweeper.getFiveImageOcean());
                Minesweeper.getSixIcon().setImage(Minesweeper.getSixImageOcean());
                Minesweeper.getSevenIcon().setImage(Minesweeper.getSevenImageOcean());
                Minesweeper.getEightIcon().setImage(Minesweeper.getEightImageOcean());
                Minesweeper.getFlagIcon().setImage(Minesweeper.getFlagImageOcean());
                Minesweeper.getThemesPanel().revalidate();
                Minesweeper.getThemesPanel().repaint();
            }

        //if the user presses the back button on the themes panel
        }else if (e.getSource()==Minesweeper.getThemesBackButton()){
            Minesweeper.getFrame().setSize(300, 250);
            Minesweeper.getFrame().remove(Minesweeper.getThemesPanel());
            Minesweeper.getFrame().add(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();

        //if the user presses the difficulty button
        }else if (e.getSource()==Minesweeper.getDifficultyButton()) {
            Minesweeper.getFrame().setSize(250, 200);
            Minesweeper.getFrame().remove(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().add(Minesweeper.getDifficultyPanel());
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();

        //if the user presses the easy button
        }else if (e.getSource()==Minesweeper.getEasyButton()){
            if (Minesweeper.getCurrentDifficulty()!=Difficulty.EASY){
                Minesweeper.setCurrentDifficulty(Difficulty.EASY);
                gamePixels = Minesweeper.getEasyGamePixels();
                Minesweeper.getCurrentDifficultyIndicator().setBounds(60, 50, 20, 20);
                Minesweeper.getDifficultyPanel().revalidate();
                Minesweeper.getDifficultyPanel().repaint();
                Minesweeper.getGameTopText().setText("<html>Difficulty: Easy<br># of Bombs: 10</html>");
                Minesweeper.getGameTopText().setBounds(90, 20, 100, 40);
                Minesweeper.makeGameObjects(Difficulty.EASY);
                Minesweeper.getGameOverMessage().setBounds(95, 15, 150, 20);
                Minesweeper.getReturnButton().setBounds(80, 50, 100, 20);
            }

        //if the user presses the medium button
        }else if (e.getSource()==Minesweeper.getMediumButton()){
            if (Minesweeper.getCurrentDifficulty()!=Difficulty.MEDIUM){
                Minesweeper.setCurrentDifficulty(Difficulty.MEDIUM);
                gamePixels = Minesweeper.getMediumGamePixels();
                Minesweeper.getCurrentDifficultyIndicator().setBounds(60, 75, 20, 20);
                Minesweeper.getDifficultyPanel().revalidate();
                Minesweeper.getDifficultyPanel().repaint();
                Minesweeper.getGameTopText().setText("<html>Difficulty: Medium<br># of Bombs: 15</html>");
                Minesweeper.getGameTopText().setBounds(100, 20, 100, 40);
                Minesweeper.makeGameObjects(Difficulty.MEDIUM);
                Minesweeper.getGameOverMessage().setBounds(105, 15, 150, 20);
                Minesweeper.getReturnButton().setBounds(90, 50, 100, 20);
            }

        //if the user presses the hard button
        }else if (e.getSource()==Minesweeper.getHardButton()){
            if (Minesweeper.getCurrentDifficulty()!=Difficulty.HARD){
                Minesweeper.setCurrentDifficulty(Difficulty.HARD);
                gamePixels = Minesweeper.getHardGamePixels();
                Minesweeper.getCurrentDifficultyIndicator().setBounds(60, 100, 20, 20);
                Minesweeper.getDifficultyPanel().revalidate();
                Minesweeper.getDifficultyPanel().repaint();
                Minesweeper.getGameTopText().setText("<html>Difficulty: Hard<br># of Bombs: 20</html>");
                Minesweeper.getGameTopText().setBounds(115, 20, 100, 40);
                Minesweeper.makeGameObjects(Difficulty.HARD);
                Minesweeper.getGameOverMessage().setBounds(120, 15, 150, 20);
                Minesweeper.getReturnButton().setBounds(105, 50, 100, 20);
            }

        //if the user presses the back button on the difficulty panel
        }else if (e.getSource()==Minesweeper.getDifficultyBackButton()) {
            Minesweeper.getFrame().setSize(300, 250);
            Minesweeper.getFrame().remove(Minesweeper.getDifficultyPanel());
            Minesweeper.getFrame().add(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();

        //if the user presses the exit button on the main menu
        }else if (e.getSource()==Minesweeper.getExitButton()){
            Minesweeper.getFrame().dispose();

        //if the user presses the main menu button
        }else if (e.getSource()==Minesweeper.getReturnButton()){
            returnToHome();

        //else game guess
        }else {
            int row = 0;
            int column = 0;
            int numOfSpots = 0;
            int maxColumnIndex = 0;
            switch (Minesweeper.getCurrentDifficulty()){
                case EASY -> {
                    numOfSpots = 80;
                    maxColumnIndex = 7;
                }
                case MEDIUM -> {
                    numOfSpots = 99;
                    maxColumnIndex = 8;
                }
                case HARD -> {
                    numOfSpots = 120;
                    maxColumnIndex = 9;
                }
            }
            //loop to check which of the buttons on the grid was clicked
            for (int i=0; i<numOfSpots; i++){
                if (e.getSource()==Minesweeper.getButtons()[i]){
                    Minesweeper.getGamePanel().remove(Minesweeper.getButtons()[i]);
                    Minesweeper.getGamePanel().revalidate();
                    Minesweeper.getGamePanel().repaint();
                    //check for a guess on a bomb tile
                    if (currentGrid.getBombGrid()[row][column]){
                        Minesweeper.getLocations()[i].setIcon(Minesweeper.getBombExplosionIcon());
                        gameOverMessage();
                    }else {
                        //check for a guess on an empty tile
                        if (currentGrid.getNumberGrid()[row][column] == 0){
                            recursiveClearEmptySpots(i);
                        }
                        //logic to check if the player won after making a guess (based on if there are 10 buttons left)
                        int buttonCount = 0;
                        boolean gameWon = true;
                        JButton[] remainingButtons = new JButton[10];
                        for (JButton button : Minesweeper.getButtons()){
                            if (button.getParent() == Minesweeper.getGamePanel()){
                                if (buttonCount < 10){
                                    remainingButtons[buttonCount] = button;
                                }else {
                                    gameWon = false;
                                    break;
                                }
                                buttonCount += 1;
                            }
                        }
                        if (gameWon){
                            for (JButton buttonToRemove : remainingButtons){
                                Minesweeper.getGamePanel().remove(buttonToRemove);
                            }
                            winnerMessage();
                        }
                        break;
                    }
                }
                if (column==maxColumnIndex){
                    column = 0;
                    row += 1;
                }else {
                    column += 1;
                }
            }
        }
    }

    /**
     * Modifies game panel if the user reveal a bomb
     */
    private void gameOverMessage(){
        Minesweeper.getGamePanel().remove(Minesweeper.getGameTopText());
        Minesweeper.getGamePanel().add(Minesweeper.getGameOverMessage());
        Minesweeper.getGamePanel().add(Minesweeper.getReturnButton());
        int numOfSpots = 0;
        switch (Minesweeper.getCurrentDifficulty()){
            case EASY -> numOfSpots = 80;
            case MEDIUM -> numOfSpots = 99;
            case HARD -> numOfSpots = 120;
        }
        for (int i=0; i<numOfSpots; i++){
            Minesweeper.getGamePanel().remove(Minesweeper.getButtons()[i]);
        }

    }

    /**
     * Modifies game panel if the user solves the puzzle
     */
    private void winnerMessage(){
        Minesweeper.getGamePanel().add(Minesweeper.getWinnerMessage());
        Minesweeper.getGamePanel().add(Minesweeper.getReturnButton());
    }

    /**
     * Returns to welcome panel
     */
    private void returnToHome(){
        Minesweeper.getFrame().setSize(300, 250);
        Minesweeper.getFrame().remove(Minesweeper.getGamePanel());
        Minesweeper.getFrame().add(Minesweeper.getWelcomePanel());
        Minesweeper.getFrame().revalidate();
        Minesweeper.getFrame().repaint();
        Minesweeper.getGamePanel().removeAll();
    }

    /**
     * Helper function for recursiveEmptySpots - This function checks whether surrounding tiles need to be removed
     * @param surroundingTiles int[] - array of surrounding positions that need to be checked
     */
    private void removeButtonAndCheck(int[] surroundingTiles){
        //loop through tiles to check
        for (int surroundingLocation : surroundingTiles){
            //check if button is still uncovered on grid
            if (Minesweeper.getButtons()[surroundingLocation].getParent()==Minesweeper.getGamePanel()){
                Minesweeper.getGamePanel().remove(Minesweeper.getButtons()[surroundingLocation]);
                //check if revealed space is also an empty tile
                if (Minesweeper.getLocations()[surroundingLocation].getIcon().equals(Minesweeper.getZeroIcon())){
                    recursiveClearEmptySpots(surroundingLocation);
                }
            }
        }
    }

    /**
     * Recursively remove spots that are adjacent to empty tiles
     * @param tileLocation int - index position representing the tile location
     */
    private void recursiveClearEmptySpots(int tileLocation){
        int divider = 0;
        switch (currentDifficulty){
            case EASY -> divider = 8;
            case MEDIUM -> divider = 9;
            case HARD -> divider = 10;
        }
        int column = 0;
        int row = tileLocation/divider;
        for (int i=0; i<divider; i++){
            if ((tileLocation-i)%divider==0){
                column = i;
            }
        }
        //determine which surrounding spaces are eligible spaces to check
        switch (currentGrid.getCaseGrid()[row][column]){
            case TOP_LEFT -> {
                int[] surroundingTiles = {0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {1, 8, 9};
                    case MEDIUM -> surroundingTiles = new int[] {1, 9, 10};
                    case HARD -> surroundingTiles = new int[] {1, 10, 11};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case TOP_RIGHT -> {
                int[] surroundingTiles = {0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {6, 14, 15};
                    case MEDIUM -> surroundingTiles = new int[] {7, 16, 17};
                    case HARD -> surroundingTiles = new int[] {8, 18, 19};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM_RIGHT -> {
                int[] surroundingTiles = {0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {70, 71, 78};
                    case MEDIUM -> surroundingTiles = new int[] {88, 89, 97};
                    case HARD -> surroundingTiles = new int[] {108, 109, 118};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM_LEFT -> {
                int[] surroundingTiles = {0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {64, 65, 73};
                    case MEDIUM -> surroundingTiles = new int[] {81, 82, 91};
                    case HARD -> surroundingTiles = new int[] {100, 101, 111};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case TOP -> {
                int[] surroundingTiles = {0, 0, 0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {tileLocation-1, tileLocation+1, tileLocation+7,
                            tileLocation+8, tileLocation+9};
                    case MEDIUM -> surroundingTiles = new int[] {tileLocation-1, tileLocation+1, tileLocation+8,
                            tileLocation+9, tileLocation+10};
                    case HARD -> surroundingTiles = new int[] {tileLocation-1, tileLocation+1, tileLocation+9,
                            tileLocation+10, tileLocation+11};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case RIGHT -> {
                int[] surroundingTiles = {0, 0, 0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-1,
                            tileLocation+7, tileLocation+8};
                    case MEDIUM -> surroundingTiles = new int[] {tileLocation-10, tileLocation-9, tileLocation-1,
                            tileLocation+8, tileLocation+9};
                    case HARD -> surroundingTiles = new int[] {tileLocation-11, tileLocation-10, tileLocation-1,
                            tileLocation+9, tileLocation+10};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM -> {
                int[] surroundingTiles = {0, 0, 0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-7,
                            tileLocation-1, tileLocation+1};
                    case MEDIUM -> surroundingTiles = new int[] {tileLocation-10, tileLocation-9, tileLocation-8,
                            tileLocation-1, tileLocation+1};
                    case HARD -> surroundingTiles = new int[] {tileLocation-11, tileLocation-10, tileLocation-9,
                            tileLocation-1, tileLocation+1};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case LEFT -> {
                int[] surroundingTiles = {0, 0, 0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {tileLocation-8, tileLocation-7, tileLocation+1,
                            tileLocation+8, tileLocation+9};
                    case MEDIUM -> surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation+1,
                            tileLocation+9, tileLocation+10};
                    case HARD -> surroundingTiles = new int[] {tileLocation-10, tileLocation-9, tileLocation+1,
                            tileLocation+10, tileLocation+11};
                }
                removeButtonAndCheck(surroundingTiles);
            }
            case NORMAL -> {
                int[] surroundingTiles = {0, 0, 0, 0, 0};
                switch (currentDifficulty){
                    case EASY -> surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-7,
                            tileLocation-1, tileLocation+1, tileLocation+7, tileLocation+8, tileLocation+9};
                    case MEDIUM -> surroundingTiles = new int[] {tileLocation-10, tileLocation-9, tileLocation-8,
                            tileLocation-1, tileLocation+1, tileLocation+8, tileLocation+9, tileLocation+10};
                    case HARD -> surroundingTiles = new int[] {tileLocation-11, tileLocation-10, tileLocation-9,
                            tileLocation-1, tileLocation+1, tileLocation+9, tileLocation+10, tileLocation+11};
                }
                removeButtonAndCheck(surroundingTiles);

            }
        }
    }
}
