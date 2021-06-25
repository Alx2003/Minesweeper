/*
 * Program: Minesweeper
 * Description: This program runs a minesweeper game using the Swing library. There are two visual themes to the program
 *              and there are three difficulties that increase the size of the grid and the number of bombs. The program
 *              uses recursion clear all surrounding tiles when the player makes a guess on a spot with no surrounding
 *              bombs.
 * Developer: Alexander Lay
 * Date: June 24, 2021
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Minesweeper {
    
    private static Theme currentTheme;
    private static Difficulty currentDifficulty;

    private static final int[] easyGamePixels = new int[] {275, 360};
    private static final int[] mediumGamePixels = new int[] {300, 385};
    private static final int[] hardGamePixels = new int[] {325, 410};

    //Class level GUI objects
    private static JFrame frame;
    private static JPanel welcomePanel;
    private static JButton welcomeScreenPlayButton;
    private static JButton themesButton;
    private static JButton difficultyButton;

    private static JPanel gamePanel;
    private static JLabel gameTopText;
    private static JButton[] buttons;
    private static JLabel[] locations;

    private static JPanel themesPanel;
    private static JLabel currentThemeIndicator;
    private static JButton classicButton;
    private static JButton oceanButton;
    private static JButton themesBackButton;

    private static JPanel difficultyPanel;
    private static JLabel currentDifficultyIndicator;
    private static JButton easyButton;
    private static JButton mediumButton;
    private static JButton hardButton;
    private static JButton difficultyBackButton;

    private static BufferedImage bombImageClassic;
    private static BufferedImage bombExplosionImageClassic;
    private static BufferedImage zeroImageClassic;
    private static BufferedImage oneImageClassic;
    private static BufferedImage twoImageClassic;
    private static BufferedImage threeImageClassic;
    private static BufferedImage fourImageClassic;
    private static BufferedImage fiveImageClassic;
    private static BufferedImage sixImageClassic;
    private static BufferedImage sevenImageClassic;
    private static BufferedImage eightImageClassic;
    private static BufferedImage flagImageClassic;

    private static BufferedImage bombImageOcean;
    private static BufferedImage bombExplosionImageOcean;
    private static BufferedImage zeroImageOcean;
    private static BufferedImage oneImageOcean;
    private static BufferedImage twoImageOcean;
    private static BufferedImage threeImageOcean;
    private static BufferedImage fourImageOcean;
    private static BufferedImage fiveImageOcean;
    private static BufferedImage sixImageOcean;
    private static BufferedImage sevenImageOcean;
    private static BufferedImage eightImageOcean;
    private static BufferedImage flagImageOcean;

    private static ImageIcon bombIcon;
    private static ImageIcon bombExplosionIcon;
    private static ImageIcon zeroIcon;
    private static ImageIcon oneIcon;
    private static ImageIcon twoIcon;
    private static ImageIcon threeIcon;
    private static ImageIcon fourIcon;
    private static ImageIcon fiveIcon;
    private static ImageIcon sixIcon;
    private static ImageIcon sevenIcon;
    private static ImageIcon eightIcon;
    private static ImageIcon flagIcon;

    private static JLabel gameOverMessage;
    private static JLabel winnerMessage;
    private static JButton returnButton;

    public static void main(String[] args) throws IOException {

        currentTheme = Theme.CLASSIC;
        currentDifficulty = Difficulty.EASY;

        //welcomePanel objects
        welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(Color.lightGray);
        JLabel welcomeScreenText = new JLabel("Welcome to Minesweeper!");
        welcomeScreenText.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeScreenText.setBounds(30, 25, 225, 40);
        welcomeScreenPlayButton = new JButton("PLAY");
        welcomeScreenPlayButton.setBounds(110, 100, 65, 20);
        welcomeScreenPlayButton.addActionListener(new EventHandler());
        themesButton = new JButton("Themes");
        themesButton.setBounds(100, 125, 80, 20);
        themesButton.addActionListener(new EventHandler());
        difficultyButton = new JButton("Difficulty");
        difficultyButton.setBounds(90, 150, 100, 20);
        difficultyButton.addActionListener(new EventHandler());

        //welcomePanel configurations
        frame = new JFrame();
        frame.setTitle("Minesweeper!");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(welcomePanel);
        welcomePanel.add(welcomeScreenText);
        welcomePanel.add(welcomeScreenPlayButton);
        welcomePanel.add(themesButton);
        welcomePanel.add(difficultyButton);

        //themesPanel objects
        themesPanel = new JPanel();
        themesPanel.setLayout(null);
        themesPanel.setBackground(Color.lightGray);
        JLabel themesText = new JLabel("Themes:");
        themesText.setFont(new Font("Arial", Font.BOLD, 14));
        themesText.setBounds(85, 20, 80, 20);
        currentThemeIndicator = new JLabel("►");
        currentThemeIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        currentThemeIndicator.setBounds(60, 50, 20, 20);
        classicButton = new JButton("Classic");
        classicButton.setBounds(75, 50, 80, 20);
        classicButton.addActionListener(new EventHandler());
        oceanButton = new JButton("<html><font color='#00a8f3'>Ocean</font></html>");
        oceanButton.setBounds(75, 75, 80, 20);
        oceanButton.addActionListener(new EventHandler());
        themesBackButton = new JButton("Back");
        themesBackButton.setBounds(75, 110, 80, 20);
        themesBackButton.addActionListener(new EventHandler());
        themesPanel.add(themesText);
        themesPanel.add(currentThemeIndicator);
        themesPanel.add(classicButton);
        themesPanel.add(oceanButton);
        themesPanel.add(themesBackButton);

        //difficultyPanel objects
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        difficultyPanel.setBackground(Color.lightGray);
        JLabel difficultiesText = new JLabel("Difficulties:");
        difficultiesText.setFont(new Font("Arial", Font.BOLD, 14));
        difficultiesText.setBounds(75, 20, 80, 20);
        currentDifficultyIndicator = new JLabel("►");
        currentDifficultyIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        currentDifficultyIndicator.setBounds(60, 50, 20, 20);
        easyButton = new JButton("Easy");
        easyButton.setBounds(75, 50, 80, 20);
        easyButton.addActionListener(new EventHandler());
        mediumButton = new JButton("Medium");
        mediumButton.setBounds(75, 75, 80, 20);
        mediumButton.addActionListener(new EventHandler());
        hardButton = new JButton("Hard");
        hardButton.setBounds(75, 100, 80, 20);
        hardButton.addActionListener(new EventHandler());
        difficultyBackButton = new JButton("Back");
        difficultyBackButton.setBounds(75, 135, 80, 20);
        difficultyBackButton.addActionListener(new EventHandler());
        difficultyPanel.add(difficultiesText);
        difficultyPanel.add(currentDifficultyIndicator);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(difficultyBackButton);

        //gamePanel objects
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.lightGray);
        gameTopText = new JLabel("<html>Difficulty: Easy<br># of Bombs: 10</html>");
        gameTopText.setBounds(90, 20, 100, 40);
        gamePanel.add(gameTopText);

        //initial setup for game on launch
        makeGameObjects(currentDifficulty);

        //images representing each spot
        bombImageClassic = ImageIO.read(new File("src/resources/classic/bomb.png"));
        bombExplosionImageClassic = ImageIO.read(new File("src/resources/classic/bombExplosion.png"));
        zeroImageClassic = ImageIO.read(new File("src/resources/classic/zero.png"));
        oneImageClassic = ImageIO.read(new File("src/resources/classic/one.png"));
        twoImageClassic = ImageIO.read(new File("src/resources/classic/two.png"));
        threeImageClassic = ImageIO.read(new File("src/resources/classic/three.png"));
        fourImageClassic = ImageIO.read(new File("src/resources/classic/four.png"));
        fiveImageClassic = ImageIO.read(new File("src/resources/classic/five.png"));
        sixImageClassic = ImageIO.read(new File("src/resources/classic/six.png"));
        sevenImageClassic = ImageIO.read(new File("src/resources/classic/seven.png"));
        eightImageClassic = ImageIO.read(new File("src/resources/classic/eight.png"));
        flagImageClassic = ImageIO.read(new File("src/resources/classic/flag.png"));

        bombImageOcean = ImageIO.read(new File("src/resources/ocean/bomb.png"));
        bombExplosionImageOcean = ImageIO.read(new File("src/resources/ocean/bombExplosion.png"));
        zeroImageOcean = ImageIO.read(new File("src/resources/ocean/zero.png"));
        oneImageOcean = ImageIO.read(new File("src/resources/ocean/one.png"));
        twoImageOcean = ImageIO.read(new File("src/resources/ocean/two.png"));
        threeImageOcean = ImageIO.read(new File("src/resources/ocean/three.png"));
        fourImageOcean = ImageIO.read(new File("src/resources/ocean/four.png"));
        fiveImageOcean = ImageIO.read(new File("src/resources/ocean/five.png"));
        sixImageOcean = ImageIO.read(new File("src/resources/ocean/six.png"));
        sevenImageOcean = ImageIO.read(new File("src/resources/ocean/seven.png"));
        eightImageOcean = ImageIO.read(new File("src/resources/ocean/eight.png"));
        flagImageOcean = ImageIO.read(new File("src/resources/ocean/flag.png"));

        bombIcon = new ImageIcon(bombImageClassic);
        bombExplosionIcon = new ImageIcon(bombExplosionImageClassic);
        zeroIcon = new ImageIcon(zeroImageClassic);
        oneIcon = new ImageIcon(oneImageClassic);
        twoIcon = new ImageIcon(twoImageClassic);
        threeIcon = new ImageIcon(threeImageClassic);
        fourIcon = new ImageIcon(fourImageClassic);
        fiveIcon = new ImageIcon(fiveImageClassic);
        sixIcon = new ImageIcon(sixImageClassic);
        sevenIcon = new ImageIcon(sevenImageClassic);
        eightIcon = new ImageIcon(eightImageClassic);
        flagIcon = new ImageIcon((flagImageClassic));

        //GUI objects for when the player loses
        gameOverMessage = new JLabel("<html><font color='red'>GAME OVER!</font></html>");
        gameOverMessage.setBounds(95, 15, 150, 20);
        winnerMessage = new JLabel("<html><font color='green'>CONGRATS YOU WON!</font></html>");
        winnerMessage.setBounds(70, 15, 150, 20);
        returnButton = new JButton("Main Menu");
        returnButton.setBounds(80, 50, 100, 20);
        returnButton.addActionListener(new EventHandler());
    }

    //Buttons representing each grid space & labels representing bomb/number locations
    public static void makeGameObjects(Difficulty currentDifficulty){
        int numOfSpots = 0;
        int maxColumnIndex = 0;
        switch (currentDifficulty){
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
        buttons = new JButton[numOfSpots];
        locations = new JLabel[numOfSpots];
        int row = 0;
        int column = 0;
        for (int i=0; i<numOfSpots; i++){
            buttons[i] = new JButton();
            buttons[i].setBounds(35+(column*24), 80+(row*24), 21, 21);
            buttons[i].addActionListener(new EventHandler());
            int currentLocation = i;
            //logic for placing and removing flags
            buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)){
                        if (buttons[currentLocation].getIcon()==flagIcon){
                            buttons[currentLocation].setIcon(null);
                        }else {
                            buttons[currentLocation].setIcon(flagIcon);
                        }
                    }
                }
                public void mousePressed(MouseEvent e) { }
                public void mouseReleased(MouseEvent e) { }
                public void mouseEntered(MouseEvent e) { }
                public void mouseExited(MouseEvent e) { }
            });
            locations[i] = new JLabel();
            locations[i].setBounds(35+(column*24), 80+(row*24), 21, 21);
            if (column==maxColumnIndex){
                column = 0;
                row += 1;
            }else {
                column += 1;
            }
        }
    }

    //Accessor and mutator for currentTheme and currentDifficulty
    public static Theme getCurrentTheme(){
        return currentTheme;
    }
    public static void setCurrentTheme(Theme newTheme){
        currentTheme = newTheme;
    }
    public static Difficulty getCurrentDifficulty(){
        return currentDifficulty;
    }
    public static void setCurrentDifficulty(Difficulty newDifficulty){
        currentDifficulty = newDifficulty;
    }

    public static int[] getEasyGamePixels(){
        return easyGamePixels;
    }
    public static int[] getMediumGamePixels() {
        return mediumGamePixels;
    }
    public static int[] getHardGamePixels() {
        return hardGamePixels;
    }

    //Accessors for GUI objects
    public static JFrame getFrame(){
        return frame;
    }
    public static JPanel getWelcomePanel(){
        return welcomePanel;
    }
    public static JButton getWelcomeScreenPlayButton(){
        return welcomeScreenPlayButton;
    }
    public static JButton getThemesButton(){
        return themesButton;
    }
    public static JButton getDifficultyButton() {
        return difficultyButton;
    }

    public static JPanel getThemesPanel(){
        return themesPanel;
    }
    public static JLabel getCurrentThemeIndicator() {
        return currentThemeIndicator;
    }
    public static JButton getClassicButton(){
        return classicButton;
    }
    public static JButton getOceanButton(){
        return oceanButton;
    }
    public static JButton getThemesBackButton(){
        return themesBackButton;
    }

    public static JPanel getDifficultyPanel() {
        return difficultyPanel;
    }
    public static JLabel getCurrentDifficultyIndicator(){
        return currentDifficultyIndicator;
    }
    public static JButton getEasyButton() {
        return easyButton;
    }
    public static JButton getMediumButton() {
        return mediumButton;
    }
    public static JButton getHardButton(){
        return hardButton;
    }
    public static JButton getDifficultyBackButton(){
        return difficultyBackButton;
    }

    public static JPanel getGamePanel(){
        return gamePanel;
    }
    public static JLabel getGameTopText() {
        return gameTopText;
    }
    public static JButton[] getButtons(){
        return buttons;
    }
    public static JLabel[] getLocations(){
        return locations;
    }
    
    public static BufferedImage getBombImageClassic(){
        return bombImageClassic;
    }
    public static BufferedImage getBombExplosionImageClassic(){
        return bombExplosionImageClassic;
    }
    public static BufferedImage getZeroImageClassic(){
        return zeroImageClassic;
    }
    public static BufferedImage getOneImageClassic(){
        return oneImageClassic;
    }
    public static BufferedImage getTwoImageClassic(){
        return twoImageClassic;
    }
    public static BufferedImage getThreeImageClassic(){
        return threeImageClassic;
    }
    public static BufferedImage getFourImageClassic(){
        return fourImageClassic;
    }
    public static BufferedImage getFiveImageClassic(){
        return fiveImageClassic;
    }
    public static BufferedImage getSixImageClassic(){
        return sixImageClassic;
    }
    public static BufferedImage getSevenImageClassic(){
        return sevenImageClassic;
    }
    public static BufferedImage getEightImageClassic(){
        return eightImageClassic;
    }
    public static BufferedImage getFlagImageClassic(){
        return flagImageClassic;
    }

    public static BufferedImage getBombImageOcean(){
        return bombImageOcean;
    }
    public static BufferedImage getBombExplosionImageOcean(){
        return bombExplosionImageOcean;
    }
    public static BufferedImage getZeroImageOcean(){
        return zeroImageOcean;
    }
    public static BufferedImage getOneImageOcean(){
        return oneImageOcean;
    }
    public static BufferedImage getTwoImageOcean(){
        return twoImageOcean;
    }
    public static BufferedImage getThreeImageOcean(){
        return threeImageOcean;
    }
    public static BufferedImage getFourImageOcean(){
        return fourImageOcean;
    }
    public static BufferedImage getFiveImageOcean(){
        return fiveImageOcean;
    }
    public static BufferedImage getSixImageOcean(){
        return sixImageOcean;
    }
    public static BufferedImage getSevenImageOcean(){
        return sevenImageOcean;
    }
    public static BufferedImage getEightImageOcean(){
        return eightImageOcean;
    }
    public static BufferedImage getFlagImageOcean() {
        return flagImageOcean;
    }

    public static ImageIcon getBombIcon(){
        return bombIcon;
    }
    public static ImageIcon getBombExplosionIcon(){
        return bombExplosionIcon;
    }
    public static ImageIcon getZeroIcon(){
        return zeroIcon;
    }
    public static ImageIcon getOneIcon(){
        return oneIcon;
    }
    public static ImageIcon getTwoIcon(){
        return twoIcon;
    }
    public static ImageIcon getThreeIcon(){
        return threeIcon;
    }
    public static ImageIcon getFourIcon(){
        return fourIcon;
    }
    public static ImageIcon getFiveIcon(){
        return fiveIcon;
    }
    public static ImageIcon getSixIcon(){
        return sixIcon;
    }
    public static ImageIcon getSevenIcon(){
        return sevenIcon;
    }
    public static ImageIcon getEightIcon(){
        return eightIcon;
    }
    public static ImageIcon getFlagIcon(){
        return flagIcon;
    }

    public static JLabel getGameOverMessage(){
        return gameOverMessage;
    }
    public static JLabel getWinnerMessage(){
        return winnerMessage;
    }
    public static JButton getReturnButton(){
        return returnButton;
    }
}
