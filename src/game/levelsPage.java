package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class levelsPage implements ActionListener {

    JFrame frame;
    Font font;
    Game game;

    public levelsPage(JFrame jFrame, Font font, Game game) {
        frame = jFrame;
        this.font = font;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getContentPane().removeAll();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("data/menubackground.jpg"));
        background.setBounds(0, 0, 800, 600);
        frame.setContentPane(background);

        JLabel label = new JLabel("created by: Chan Weng");
        frame.add(label);
        label.setBounds(20, 540, 200, 20);

        JLabel labelVersion = new JLabel("version: 2.0");
        frame.add(labelVersion);
        labelVersion.setBounds(700, 540, 200, 20);

        JLabel title = new JLabel("Go Survival!");
        title.setBounds(280, 100, 500, 40);

        try {
            InputStream is = getClass().getResourceAsStream("BigSpace-rPKx.ttf");
            assert is != null;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException f) {
            f.printStackTrace();
        }

        title.setFont(font.deriveFont(50f));
        frame.add(title);

        JButton levelOne = new JButton("Level One");
        JButton levelTwo = new JButton("Level Two");
        JButton levelThree = new JButton("Level Three");

        frame.add(levelOne);
        levelOne.setBounds(230, 250, 100, 100);
        levelOne.addActionListener(p -> {
            frame.getContentPane().removeAll();
            LevelOne levelOneBoard = new LevelOne(game);
            StartLevel levelOneGame = new StartLevel(game, frame, levelOneBoard);
        });
        frame.add(levelTwo);
        levelTwo.setBounds(350, 250, 100, 100);
        levelTwo.addActionListener(p -> {
            String levelName = GameSaverLoader.checkStats();
            assert levelName != null;
            if (levelName.equals("Level 1")) {
                JOptionPane.showMessageDialog(frame, Game.player + " has not yet reached this level.","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else if (levelName.equals("Player not defined.")) {
                JOptionPane.showMessageDialog(frame, "Please sign in player in Settings page.","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else if (levelName.equals("No saved game found")) {
                JOptionPane.showMessageDialog(frame, "No saved game found for \"" + Game.player + "\"","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else {
                frame.getContentPane().removeAll();
                LevelTwo levelTwoBoard = new LevelTwo(game);
                StartLevel levelTwoGame = new StartLevel(game, frame, levelTwoBoard);
            }
        });

        frame.add(levelThree);
        levelThree.setBounds(470, 250, 100, 100);
        levelThree.addActionListener(p -> {
            String levelName = GameSaverLoader.checkStats();
            assert levelName != null;
            if (levelName.equals("Level 1") | levelName.equals("Level 2")) {
                JOptionPane.showMessageDialog(frame, Game.player + " has not yet reached this level.","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else if (levelName.equals("Player not defined.")) {
                JOptionPane.showMessageDialog(frame, "Please sign in player in Settings page.","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else if (levelName.equals("No saved game found")) {
                JOptionPane.showMessageDialog(frame, "No saved game found for \"" + Game.player + "\"","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            } else {
                frame.getContentPane().removeAll();
                LevelThree levelThreeBoard = new LevelThree(game);
                StartLevel levelThreeGame = new StartLevel(game, frame, levelThreeBoard);
            }
        });

        JButton back = new JButton("back");
        frame.add(back);
        back.setBounds(360, 355, 80, 30);
        back.addActionListener(d -> {
            frame.getContentPane().removeAll();
            game.createMenu();
        });
    }
}
