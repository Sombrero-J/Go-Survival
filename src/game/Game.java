package game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Game {

    JFrame frame;
    Font titleFont;
    public static int gameLevel;
    public static String player;
    Music music = new Music(this);

    public Game() {
        gameLevel = 0;

        // create a new frame
        frame = new JFrame("Go Survival");
        frame.setSize(800, 600);
        frame.setLayout(null);

        // play music
        music.playMusic();

        createMenu();

        //load in last player's name from playerName.txt
        try {
            loadPlayerStats();
        } catch (IOException k){
            System.out.println("Error loading player stats: " + k.getMessage());
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // code to get to next level
    public void goToNextLevel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().removeAll();
        if (gameLevel == 1) {
            LevelTwo levelTwoBoard = new LevelTwo(this);
            StartLevel levelTwo = new StartLevel(this, frame, levelTwoBoard);
        } else if (gameLevel == 2) {
            LevelThree levelThreeBoard = new LevelThree(this);
            StartLevel levelThree = new StartLevel(this, frame, levelThreeBoard);
        }
    }

    public void createMenu() {
        gameLevel = 0;
        music.playMusic();

        // background image
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("data/menubackground.jpg"));
        background.setBounds(0, 0, 800, 600);
        frame.setContentPane(background);

        // buttons
        JButton newGameButton = new JButton("New Game");
        JButton levelsButton = new JButton("Levels");
        JButton settingsButton = new JButton("Settings");

        frame.add(newGameButton);
        newGameButton.setBounds(300, 250, 200, 50);

        frame.add(levelsButton);
        levelsButton.setBounds(300, 310, 200, 50);

        frame.add(settingsButton);
        settingsButton.setBounds(300, 370, 200, 50);

        // labels
        JLabel label = new JLabel("created by: Chan Weng");
        frame.add(label);
        label.setBounds(20, 540, 200, 20);

        JLabel labelVersion = new JLabel("version: 2.0");
        frame.add(labelVersion);
        labelVersion.setBounds(700, 540, 200, 20);

        // game title
        JLabel title = new JLabel("Go Survival!");
        title.setBounds(280, 100, 500, 40);

        JButton exitButton = new JButton("Exit Game");
        exitButton.setBounds(325, 430, 150, 30);
        frame.add(exitButton);
        exitButton.addActionListener(e14 -> {
            frame.dispose();
        });

        try {
            InputStream is = getClass().getResourceAsStream("BigSpace-rPKx.ttf");
            assert is != null;
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        // title font
        title.setFont(titleFont.deriveFont(50f));
        frame.add(title);

        newGameButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            LevelOne levelOneBoard = new LevelOne(this);
            StartLevel levelOne = new StartLevel(Game.this, frame, levelOneBoard);
        });


        levelsButton.addActionListener(new levelsPage(frame, titleFont, this));
        settingsButton.addActionListener(new settingsPage(frame, titleFont, this));
    }

    public void loadPlayerStats() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader("playerName.txt");
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                Game.player = line;
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("error loading player name");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Game();
    }
}
