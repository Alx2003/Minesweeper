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

    //Class level GUI objects
    private static JFrame frame;
    private static JPanel welcomePanel;
    private static JButton welcomeScreenPlayButton;
    private static JButton themesButton;
    private static JButton difficultyButton;

    private static JPanel gamePanel;
    private static JButton[] buttons;
    private static JLabel[] locations;

    private static JPanel themesPanel;
    private static JButton classicButton;
    private static JButton oceanButton;
    private static JButton themesBackButton;

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
        themesPanel.add(classicButton);
        themesPanel.add(oceanButton);
        themesPanel.add(themesBackButton);

        //difficultyPanel objects

        //gamePanel objects
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.lightGray);

        //buttons representing each grid space & labels representing bomb/number locations
        buttons = new JButton[80];
        locations = new JLabel[80];
        int row = 0;
        int column = 0;
        for (int i=0; i<80; i++){
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
            if (column==7){
                column = 0;
                row += 1;
            }else {
                column += 1;
            }
        }

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
    //Accessor and mutator for currentTheme
    public static Theme getCurrentTheme(){
        return currentTheme;
    }
    public static void setCurrentTheme(Theme newTheme){
        currentTheme = newTheme;
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

    public static JPanel getThemesPanel(){
        return themesPanel;
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

    public static JPanel getGamePanel(){
        return gamePanel;
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
