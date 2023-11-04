package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class GameView extends UserView implements MouseListener {

    private final Submarine submarine;

    Font bigSpace, cybergame;

    GameLevel gameLevel;

    Game game;

    float logY = 50;
    float logY2 = 50;

    int musicInt = 0;

    Color textColor = new Color(224, 224, 224);
    Rectangle restartBound, nextLevelBound, menuBound, pauseBound;

    Image gameend = new ImageIcon("data/gameend.jpg").getImage();
    Image oneBackground = new ImageIcon("data/levelonebackground.png").getImage();
    Image twoBackground = new ImageIcon("data/leveltwobackground.png").getImage();
    Image threeBackground = new ImageIcon("data/levelthreebackground.png").getImage();

    Image fuelIcon = new ImageIcon("data/fuelIcon.png").getImage();
    Image heartIcon = new ImageIcon("data/heartIcon.png").getImage();

    Image crystalIcon = new ImageIcon("data/crystal.png").getImage();
    Image octopusIcon = new ImageIcon("data/octopus.png").getImage();
    Image sharkIcon = new ImageIcon("data/shark.png").getImage();
    Image pauseImage = new ImageIcon("data/pause2.png").getImage();
    Music music;

    public GameView(Game game, GameLevel gameLevel, int width, int height, Submarine s) {
        super(gameLevel, width, height);
        submarine = s;
        this.gameLevel = gameLevel;
        this.game = game;

        try {
            InputStream is = getClass().getResourceAsStream("BigSpace-rPKx.ttf");
            bigSpace = Font.createFont(Font.TRUETYPE_FONT, is);
            InputStream us = getClass().getResourceAsStream("Cybergame-Regular Italic.ttf");
            cybergame = Font.createFont(Font.TRUETYPE_FONT, us);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        music = new Music(game);
    }

    // create different backgrounds according to levels
    protected void paintBackground(Graphics2D g) {
        if (gameLevel.level == 1) {
            g.drawImage(oneBackground, 0, 0, 800, 600, this);
        } else if (gameLevel.level == 2) {
            g.drawImage(twoBackground, 0, 0, 800, 600, this);
        } else if (gameLevel.level == 3) {
            g.drawImage(threeBackground, 0, 0, 800, 600, this);
        } else if (!submarine.isAlive | gameLevel.isComplete()) {
            g.drawImage(gameend, 0, 0, 800, 600, this);
        }
    }

    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);

        // some levels have no fuel
        if (gameLevel.hasFuel) {
            g.drawImage(fuelIcon, 190, 0, 50, 50, this);
            g.drawImage(heartIcon, 415, 2, 50, 50, this);
        } else {
            g.drawImage(heartIcon, 295, 2, 50, 50, this);
        }

        //pause button
        pauseBound = new Rectangle(740, 10, 50, 50);
        g.setStroke(new BasicStroke(0));
        g.draw(pauseBound);
        g.drawImage(pauseImage, 740, 10, 50, 50, this);

        // image as objectives
        g.drawImage(crystalIcon, 50, 30, 50, 50, this);
        g.setFont(bigSpace);
        g.setColor(new Color(102, 187, 106));
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20));
        g.drawString("Score: " + submarine.getScore(), 70, 30);
        g.setColor(new Color(255, 255, 0));
        g.drawString(submarine.crystalCount + " / " + gameLevel.goalCountCrystal, 100, 60);
        if (gameLevel.level >= 2) {
            g.drawImage(octopusIcon, 50, 65, 50, 50, this);
            g.drawString(submarine.octopusCount + " / " + gameLevel.goalCountOctopus, 100, 95);
        }
        if (gameLevel.level == 3) {
            g.drawImage(sharkIcon, 48, 100, 50, 50, this);
            g.drawString(submarine.sharkCount + " / " + gameLevel.goalCountShark, 100, 130);
        }

        // what happens when submarine dies
        if (!submarine.isAlive) {
            game.gameLevel = 4;
            checkMusic();
            g.drawImage(gameend, 0, 0, 800, 600, this);
            g.setFont(bigSpace.deriveFont(Font.PLAIN, 60));
            g.setColor(new Color(255, 0, 0));
            g.drawString("Oh no, you died !", 30, 150);
            g.setFont(bigSpace.deriveFont(Font.PLAIN, 40));
            g.setColor(new Color(181, 125, 125));
            g.drawString("Your Score: " + submarine.getScore(), 100, 200);
            g.setStroke(new BasicStroke(0));
            restartBound = new Rectangle(100, 270, 270, 40);
            g.draw(restartBound);
            // create restart button
            if (restartBound.contains(getMousePosition())) {
                g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                g.setColor(new Color(128, 128, 128));
                g.drawString("Restart", 100, 310);
            } else {
                g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                g.setColor(textColor);
                g.drawString("Restart", 100, 310);
            }
        }

        // if game is completed
        if (gameLevel.isComplete()) {
            g.drawImage(gameend, 0, 0, 800, 600, this);
            if (gameLevel.level < 3) {
                g.setFont(bigSpace.deriveFont(Font.PLAIN, 60));
                g.setColor(new Color(255, 0, 0));
                g.drawString("Congratulations !", 30, 150);
                g.setFont(bigSpace.deriveFont(Font.PLAIN, 40));
                g.setColor(new Color(181, 125, 125));
                g.drawString("Your Score: " + submarine.getScore(), 100, 200);
                g.setStroke(new BasicStroke(0));
                restartBound = new Rectangle(100, 270, 270, 40);
                g.draw(restartBound);
                // option to restart
                if (restartBound.contains(getMousePosition())) {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(new Color(128, 128, 128));
                    g.drawString("Restart level", 100, 310);
                } else {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(textColor);
                    g.drawString("Restart level", 100, 310);
                }
                // option to go next level
                nextLevelBound = new Rectangle(100, 310, 210, 40);
                g.draw(nextLevelBound);
                if (nextLevelBound.contains(getMousePosition())) {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(new Color(128, 128, 128));
                    g.drawString("Next Level", 100, 350);
                } else {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(textColor);
                    g.drawString("Next Level", 100, 350);
                }
            } else {
                g.setFont(bigSpace.deriveFont(Font.PLAIN, 60));
                g.setColor(new Color(255, 0, 0));
                g.drawString("You finished the game !", 30, 150);
                g.setFont(bigSpace.deriveFont(Font.PLAIN, 40));
                g.setColor(new Color(181, 125, 125));
                g.drawString("Your Score: " + submarine.getScore(), 100, 200);
                g.setStroke(new BasicStroke(0));
                restartBound = new Rectangle(100, 270, 270, 40);
                g.draw(restartBound);
                if (restartBound.contains(getMousePosition())) {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(new Color(128, 128, 128));
                    g.drawString("Restart", 100, 310);
                } else {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(textColor);
                    g.drawString("Restart", 100, 310);
                }
                // option to go back to menu
                menuBound = new Rectangle(100, 310, 210, 40);
                if (menuBound.contains(getMousePosition())) {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(new Color(128, 128, 128));
                    g.drawString("Menu", 100, 350);
                } else {
                    g.setFont(cybergame.deriveFont(Font.PLAIN, 45));
                    g.setColor(textColor);
                    g.drawString("Menu", 100, 350);
                }
            }
        }

        // killfeed for collectibles
        for (Collectibles c : gameLevel.getCollectibles()) {
            if (c.logTrigger && (submarine.isAlive | gameLevel.isComplete())) {
                g.setFont(bigSpace.deriveFont(Font.PLAIN, 15));
                g.setColor(new Color(204, 204, 0));
                g.drawString(c.getName() + " has been captured.", 600, logY);
                g.drawString(c.getPoints() + " points earned.", 600, logY + 12);
                logY -= 0.35f;
                if (logY <= 20) {
                    c.logTrigger = false;
                    logY = 50;
                }
            }
        }

        // killfeed for enemies
        for (Enemies e : gameLevel.getEnemies()) {
            if (e.logTrigger && (submarine.isAlive | gameLevel.isComplete())) {
                g.drawString(e.getName() + " damaged you.", 600, logY2);
                logY2 -= 0.35f;
                if (logY2 <= 20) {
                    e.logTrigger = false;
                    logY2 = 50;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (restartBound != null && restartBound.contains(e.getPoint())) {
            restartBound = null;
            nextLevelBound = null;
            menuBound = null;
            gameLevel.restart();
            music.playMusic();
        }
        if (nextLevelBound != null && nextLevelBound.contains(e.getPoint())) {
            restartBound = null;
            nextLevelBound = null;
            menuBound = null;
            game.goToNextLevel();
            music.playMusic();
        }
        if (menuBound != null && menuBound.contains(e.getPoint())) {
            restartBound = null;
            nextLevelBound = null;
            menuBound = null;
            game.createMenu();
        }
        //pause button triggers
        if (pauseBound.contains(e.getPoint())) {
            gameLevel.stop();
            // Pause button clicked, show pause menu
            JFrame pauseFrame = new JFrame("Pause");
            pauseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create buttons for pause menu
            JButton saveAndExitButton = new JButton("Save & Exit Game");
            JButton resumeGameButton = new JButton("Resume Game");
            JButton backToMenuButton = new JButton("Back to Menu");

            // Add action listeners to buttons
            saveAndExitButton.addActionListener(e1 -> {
                if (Game.player == null) {
                    // Player name not set, show name window and save game
                    JFrame playerNameFrame = new JFrame("Player Name");
                    playerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Create player name label and text field
                    JLabel nameLabel = new JLabel("Enter player name:");
                    JTextField nameField = new JTextField(20);

                    // Create save button to save player name and exit game
                    JButton saveButton = new JButton("Save and Exit");
                    saveButton.addActionListener(e2 -> {
                        Game.player = nameField.getText();
                        try {
                            GameSaverLoader.save(gameLevel);
                            FileWriter writer = new FileWriter("playerName.txt");
                            writer.write(Game.player);
                            writer.close();
                        } catch (IOException ex) {
                            System.out.println("error saving");
                        }
                        System.exit(0);
                    });

                    // Create panel and add components
                    JPanel panel = new JPanel();
                    panel.add(nameLabel);
                    panel.add(nameField);
                    panel.add(saveButton);
                    playerNameFrame.add(panel);
                    playerNameFrame.pack();
                    playerNameFrame.setLocationRelativeTo(null);
                    playerNameFrame.setVisible(true);
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the game as \"" + Game.player + "\"?", "Save Game", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            GameSaverLoader.save(gameLevel);
                        } catch (IOException ex) {
                            System.out.println("error saving");
                        }
                        System.exit(0);
                    } else {
                        JFrame playerNameFrame = new JFrame("Player Name");
                        playerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        // Create player name label and text field
                        JLabel nameLabel = new JLabel("Enter player name:");
                        JTextField nameField = new JTextField(20);

                        // Create save button to save player name and exit game
                        JButton saveButton = new JButton("Save and Exit");
                        saveButton.addActionListener(e2 -> {
                            String playerName = nameField.getText();
                            if (playerName.isEmpty()) {
                                JOptionPane.showMessageDialog(playerNameFrame, "Please enter a valid player name.");
                            } else {
                                Game.player = playerName;
                                try {
                                    GameSaverLoader.save(gameLevel);
                                    FileWriter writer = new FileWriter("playerName.txt");
                                    writer.write(Game.player);
                                    writer.close();
                                } catch (IOException ex) {
                                    System.out.println("error saving");
                                }
                                playerNameFrame.dispose();
                                System.exit(0);
                            }
                        });
                        JPanel panel = new JPanel();
                        panel.add(nameLabel);
                        panel.add(nameField);
                        panel.add(saveButton);
                        playerNameFrame.add(panel);
                        playerNameFrame.pack();
                        playerNameFrame.setLocationRelativeTo(null);
                        playerNameFrame.setVisible(true);
                        // Player name set, save game and exit
                    }
                }
            });

            resumeGameButton.addActionListener(e1 -> {
                // Resume game, just dispose of pause menu
                pauseFrame.dispose();
                gameLevel.start();
            });

            backToMenuButton.addActionListener(e1 -> {
                // Show confirmation dialog
                int result = JOptionPane.showConfirmDialog(pauseFrame, "Do you want to save your game?", "Save and Exit", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (Game.player == null) {
                        JFrame playerNameFrame = new JFrame("Player Name");
                        playerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        JLabel nameLabel = new JLabel("Enter player name: ");
                        JTextField nameField = new JTextField(20);

                        JButton saveButton = new JButton("Save");
                        saveButton.addActionListener(e2 -> {
                            Game.player = nameField.getText();
                            try {
                                GameSaverLoader.save(gameLevel);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            playerNameFrame.dispose();
                            game.createMenu();
                        });

                        JPanel panel = new JPanel();
                        panel.add(nameLabel);
                        panel.add(nameField);
                        panel.add(saveButton);
                        playerNameFrame.add(panel);
                        playerNameFrame.pack();
                        playerNameFrame.setLocationRelativeTo(null);
                        playerNameFrame.setVisible(true);
                    } else {
                        int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the game as \"" + Game.player + "\"?", "Save Game", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                GameSaverLoader.save(gameLevel);
                            } catch (IOException ex) {
                                System.out.println("error saving");
                            }
                            pauseFrame.dispose();
                            game.createMenu();
                        } else {
                            JFrame playerNameFrame = new JFrame("Player Name");
                            playerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            // Create player name label and text field
                            JLabel nameLabel = new JLabel("Enter player name:");
                            JTextField nameField = new JTextField(20);

                            // Create save button to save player name and exit game
                            JButton saveButton = new JButton("Save and Exit");
                            saveButton.addActionListener(e2 -> {
                                String playerName = nameField.getText();
                                if (playerName.isEmpty()) {
                                    JOptionPane.showMessageDialog(playerNameFrame, "Please enter a valid player name.");
                                } else {
                                    Game.player = playerName;
                                    try {
                                        GameSaverLoader.save(gameLevel);
                                        FileWriter writer = new FileWriter("playerName.txt");
                                        writer.write(Game.player);
                                        writer.close();
                                    } catch (IOException ex) {
                                        System.out.println("error saving");
                                    }
                                    playerNameFrame.dispose();
                                    pauseFrame.dispose();
                                    game.createMenu();
                                }
                            });
                            JPanel panel = new JPanel();
                            panel.add(nameLabel);
                            panel.add(nameField);
                            panel.add(saveButton);
                            playerNameFrame.add(panel);
                            playerNameFrame.pack();
                            playerNameFrame.setLocationRelativeTo(null);
                            playerNameFrame.setVisible(true);
                            // Player name set, save game and exit
                        }
                    }
                } else if (result == JOptionPane.NO_OPTION) {
                    pauseFrame.dispose();
                    game.createMenu();
                }
            });

            // Create panel and add buttons
            JPanel pausePanel = new JPanel();
            pausePanel.add(saveAndExitButton);
            pausePanel.add(resumeGameButton);
            pausePanel.add(backToMenuButton);

            pauseFrame.add(pausePanel);
            pauseFrame.pack();
            pauseFrame.setLocationRelativeTo(null);
            pauseFrame.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void checkMusic() {
        if (musicInt != Game.gameLevel) {
            musicInt = Game.gameLevel;
            music.playMusic();
        }
    }
}

