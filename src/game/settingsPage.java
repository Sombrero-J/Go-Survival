package game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Hashtable;

public class settingsPage implements ActionListener, ItemListener, ChangeListener {

    JFrame frame;
    Font font;
    Game game;
    JCheckBox music, soundEffects, fullScreenCheckBox;
    JSlider musicSlider, soundEffectsSlider;
    Music musicClass;

    static int minSound = 1;
    static int maxSound = 20;
    static int initSound = 10;

    static int minSE = 1;
    static int maxSE = 20;
    static int initSE = 10;

    public settingsPage(JFrame jFrame, Font font, Game game) {
        frame = jFrame;
        this.font = font;
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        musicClass = new Music(game);
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());
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

        music = new JCheckBox("Music");
        music.setBounds(340, 190, 100, 15);
        frame.add(music);
        music.addItemListener(this);
        music.setSelected(true);
        soundEffects = new JCheckBox("Sound Effects");
        soundEffects.setBounds(330, 300, 200, 15);
        frame.add(soundEffects);
        soundEffects.addItemListener(this);
        soundEffects.setSelected(true);

        musicSlider = new JSlider(JSlider.HORIZONTAL,
                minSound, maxSound, initSound);
        musicSlider.addChangeListener(this);
        musicSlider.setBounds(180, 190, 400, 100);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(minSound, new JLabel("Soft"));
        labelTable.put(maxSound, new JLabel("Loud"));
        musicSlider.setLabelTable(labelTable);

        musicSlider.setMajorTickSpacing(10);
        musicSlider.setMinorTickSpacing(1);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);

        musicSlider.addChangeListener(e1 -> {
            JSlider source = (JSlider) e1.getSource();
            if (!source.getValueIsAdjusting()) {
                Music.volume = (double) source.getValue() / 10;
                musicClass.playMusic();
            }
        });

        soundEffectsSlider = new JSlider(JSlider.HORIZONTAL,
                minSE, maxSE, initSE);
        soundEffectsSlider.addChangeListener(this);
        soundEffectsSlider.setBounds(180, 300, 400, 100);

        Hashtable<Integer, JLabel> labelTableTwo = new Hashtable<>();
        labelTableTwo.put(minSE, new JLabel("Soft"));
        labelTableTwo.put(maxSE, new JLabel("Loud"));
        soundEffectsSlider.setLabelTable(labelTableTwo);

        soundEffectsSlider.setMajorTickSpacing(10);
        soundEffectsSlider.setMinorTickSpacing(1);
        soundEffectsSlider.setPaintTicks(true);
        soundEffectsSlider.setPaintLabels(true);

        soundEffectsSlider.addChangeListener(e1 -> {
            JSlider source = (JSlider) e1.getSource();
            if (!source.getValueIsAdjusting()) {
                Music.SEvolume = (double) source.getValue() / 10;
            }
        });

        frame.add(musicSlider);
        frame.add(soundEffectsSlider);

        JButton back = new JButton("back");
        frame.add(back);
        back.setBounds(360, 500, 80, 30);
        back.addActionListener(d -> {
            frame.getContentPane().removeAll();
            game.createMenu();
        });

        JButton playerNameButton = new JButton(Game.player == null ? "add player" : Game.player);
        playerNameButton.setBounds(320, 400, 150, 30);
        frame.add(playerNameButton);
        playerNameButton.addActionListener(e13 -> {
            // Create new window for entering player name
            JFrame playerNameFrame = new JFrame("Player Name");
            playerNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create player name label and text field
            JLabel nameLabel = new JLabel("Enter player name:");
            JTextField nameField = new JTextField(20);

            // Create save button to save player name
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e12 -> {
                Game.player = nameField.getText();
                playerNameButton.setText(Game.player);
                try {
                    FileWriter writer = new FileWriter("playerName.txt");
                    writer.write(Game.player);
                    writer.close();
                } catch (IOException p) {
                    p.printStackTrace();
                }
                playerNameFrame.dispose();
            });
            JPanel panel = new JPanel();
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(saveButton);
            playerNameFrame.add(panel);
            playerNameFrame.pack();
            playerNameFrame.setLocationRelativeTo(null);
            playerNameFrame.setVisible(true);
        });

        JButton loadButton = new JButton("load last saved");
        loadButton.setBounds(350,440,100,20);
        frame.add(loadButton);
        loadButton.addActionListener(d->{
            try {
                GameSaverLoader.load(Game.player, game);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();

        if (source == music) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                Music.musicAllowed = false;
                musicClass.playMusic();
            } else {
                Music.musicAllowed = true;
                musicClass.playMusic();
            }
        } else if (source == soundEffects) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                Music.soundEffectsAllowed = false;
            } else {
                Music.soundEffectsAllowed = true;
            }
        }


    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
