import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {

    private static GameGrid currentGrid;

    @Override
    public void actionPerformed(ActionEvent e){
        //if user presses play button on main menu
        if (e.getSource()==Minesweeper.getWelcomeScreenPlayButton()){
            //generate the grid pattern for the bombs and numbers
            currentGrid = new GameGrid();
            currentGrid.output();

            //add objects to game panel
            Minesweeper.getFrame().setSize(275, 360);
            Minesweeper.getFrame().remove(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().add(Minesweeper.getGamePanel());

            int row = 0;
            int column = 0;
            for (int i = 0; i < 80; i++){
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
                if (column == 7){
                    column = 0;
                    row += 1;
                }else {
                    column += 1;
                }
                Minesweeper.getGamePanel().add(Minesweeper.getLocations()[i]);
            }

            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();
        //if the user presses the theme button
        }else if (e.getSource()==Minesweeper.getThemesButton()){
            Minesweeper.getFrame().setSize(250, 175);
            Minesweeper.getFrame().remove(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().add(Minesweeper.getThemesPanel());
        //if the user presses the classic theme button
        }else if (e.getSource()==Minesweeper.getClassicButton()){
            if (Minesweeper.getCurrentTheme()!=Theme.CLASSIC){
                Minesweeper.setCurrentTheme(Theme.CLASSIC);
                Minesweeper.getWelcomePanel().setBackground(Color.lightGray);
                Minesweeper.getGamePanel().setBackground(Color.lightGray);
                Minesweeper.getThemesPanel().setBackground(Color.lightGray);
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
            if (Minesweeper.getCurrentTheme() != Theme.OCEAN){
                Minesweeper.setCurrentTheme(Theme.OCEAN);
                Minesweeper.getWelcomePanel().setBackground(new Color(0, 168, 243));
                Minesweeper.getGamePanel().setBackground(new Color(0, 168, 243));
                Minesweeper.getThemesPanel().setBackground(new Color(0, 168, 243));
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
        }else if (e.getSource()==Minesweeper.getThemesBackButton()){
            Minesweeper.getFrame().setSize(300, 250);
            Minesweeper.getFrame().remove(Minesweeper.getThemesPanel());
            Minesweeper.getFrame().add(Minesweeper.getWelcomePanel());
            Minesweeper.getFrame().revalidate();
            Minesweeper.getFrame().repaint();
        //if the user presses the main menu button
        }else if (e.getSource()==Minesweeper.getReturnButton()){
            returnToHome();
        }else {
            int row = 0;
            int column = 0;
            //loop to check which of the buttons on the grid was clicked
            for (int i=0; i<80; i++){
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
                if (column==7){
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
        Minesweeper.getGamePanel().add(Minesweeper.getGameOverMessage());
        Minesweeper.getGamePanel().add(Minesweeper.getReturnButton());
        for (int i=0; i<80; i++){
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
        Minesweeper.getGamePanel().remove(Minesweeper.getGameOverMessage());
        Minesweeper.getGamePanel().remove(Minesweeper.getWinnerMessage());
        Minesweeper.getGamePanel().remove(Minesweeper.getReturnButton());
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
        int column = 0;
        int row = tileLocation/8;
        for (int i=0; i<8; i++){
            if ((tileLocation-i)%8==0){
                column = i;
            }
        }
        //determine which surrounding spaces are eligible spaces to check
        switch (currentGrid.getCaseGrid()[row][column]){
            case TOP_LEFT -> {
                int[] surroundingTiles = new int[] {1, 8, 9};
                removeButtonAndCheck(surroundingTiles);
            }
            case TOP_RIGHT -> {
                int[] surroundingTiles = new int[] {6, 14, 15};
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM_RIGHT -> {
                int[] surroundingTiles = new int[] {70, 71, 78};
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM_LEFT -> {
                int[] surroundingTiles = new int[] {64, 65, 73};
                removeButtonAndCheck(surroundingTiles);
            }
            case TOP -> {
                int[] surroundingTiles = new int[] {tileLocation-1, tileLocation+1, tileLocation+7, tileLocation+8,
                        tileLocation+9};
                removeButtonAndCheck(surroundingTiles);
            }
            case RIGHT -> {
                int[] surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-1, tileLocation+7,
                        tileLocation+8};
                removeButtonAndCheck(surroundingTiles);
            }
            case BOTTOM -> {
                int[] surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-7, tileLocation-1,
                        tileLocation+1};
                removeButtonAndCheck(surroundingTiles);
            }
            case LEFT -> {
                int[] surroundingTiles = new int[] {tileLocation-8, tileLocation-7, tileLocation+1, tileLocation+8,
                        tileLocation+9};
                removeButtonAndCheck(surroundingTiles);
            }
            case NORMAL -> {
                int[] surroundingTiles = new int[] {tileLocation-9, tileLocation-8, tileLocation-7, tileLocation-1,
                        tileLocation+1, tileLocation+7, tileLocation+8, tileLocation+9};
                removeButtonAndCheck(surroundingTiles);
            }
        }
    }
}
