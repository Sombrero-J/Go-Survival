package game;

import javax.swing.*;
import java.io.*;

public class GameSaverLoader {

    // static method to save stats
    static void save(GameLevel g) throws IOException {
        boolean playerRecordExists = false;
        boolean append = true;
        FileWriter writer = null;
        BufferedReader reader;
        try {
            File file = new File("Game Score");
            // if file doesn't exist, create a new file
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null && !playerRecordExists){
                String[] tokens = line.split(",");
                // check if player has saved records in the past
                if (tokens.length > 0 && tokens[0].equals(Game.player)) {
                    playerRecordExists = true;
                }
                line = reader.readLine();
            }
            reader.close();
            if (playerRecordExists){
                // update the particular player's records if found past records
                File tempFile = new File("temp.txt");
                FileWriter tempWriter = new FileWriter(tempFile);

                reader = new BufferedReader(new FileReader(file));
                line = reader.readLine();
                while (line != null) {
                    String[] tokens = line.split(",");
                    if (tokens.length > 0 && !tokens[0].equals(Game.player)) {
                        tempWriter.write(line + "\n");
                    }
                    line = reader.readLine();
                }

                reader.close();
                tempWriter.close();

                // replace the original file with the temporary one
                File originalFile = new File("Game Score");
                if (originalFile.delete()) {
                    tempFile.renameTo(new File("Game Score"));
                } else {
                    System.out.println("Error: failed to delete original file.");
                }
            }
            writer = new FileWriter("Game Score", append);
            writer.write(Game.player + "," + g.getLevelName() + "," + g.getSubmarine().crystalCount + "," + g.getSubmarine().sharkCount + "," + g.getSubmarine().octopusCount + "," + g.getSubmarine().getFuel() + "," + g.getSubmarine().getHealth() + ","+  g.getSubmarine().getScore() + "\n");
            System.out.println("record saved for: " + Game.player);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    static void load(String player, Game game) throws IOException {
        boolean playerRecordExists = false;
        FileReader fr = null;
        BufferedReader reader = null;
        String levelName = null;
        int crystalCount = 0;
        int sharkCount = 0;
        int octopusCount = 0;
        int fuelAmount = 0;
        int healthAmount = 0;
        int score = 0;
        try {
            File file = new File("Game Score");
            // if file doesn't exist, create a new file
            if (!file.exists()) {
                file.createNewFile();
            }
            fr = new FileReader("Game Score");
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null && !playerRecordExists) {
                String[] tokens = line.split(",");
                // search for the particular player's record
                if (tokens.length > 0 && tokens[0].equals(player)) {
                    String playerName = tokens[0];
                    levelName = tokens[1];
                    crystalCount = Integer.parseInt(tokens[2]);
                    sharkCount = Integer.parseInt(tokens[3]);
                    octopusCount = Integer.parseInt(tokens[4]);
                    fuelAmount = Integer.parseInt(tokens[5]);
                    healthAmount = Integer.parseInt(tokens[6]);
                    score = Integer.parseInt(tokens[7]);
                    System.out.println("Name: " + playerName + "Level: " + levelName + ", Crystal: " + crystalCount + ", Shark: " + sharkCount + ", Octopus: " + octopusCount + ", Fuel: " + fuelAmount + ", Health: " + healthAmount + "Score: " + score);
                    playerRecordExists = true;
                }
                line = reader.readLine();
            }
            reader.close();
            // if player exists, load game
            if (playerRecordExists) {
                switch (levelName) {
                    case "Level 1":
                        game.frame.getContentPane().removeAll();
                        game.frame.getContentPane().removeAll();
                        LevelOne levelOneBoard = new LevelOne(game);
                        levelOneBoard.submarine.crystalCount = crystalCount;
                        levelOneBoard.submarine.sharkCount = sharkCount;
                        levelOneBoard.submarine.octopusCount = octopusCount;
                        levelOneBoard.submarine.setFuel(fuelAmount);
                        levelOneBoard.submarine.setHealth(healthAmount);
                        levelOneBoard.submarine.setScore(score);
                        StartLevel levelOne = new StartLevel(game, game.frame, levelOneBoard);
                        break;
                    case "Level 2":
                        game.frame.getContentPane().removeAll();
                        game.frame.getContentPane().removeAll();
                        LevelTwo levelTwoBoard = new LevelTwo(game);
                        levelTwoBoard.submarine.crystalCount = crystalCount;
                        levelTwoBoard.submarine.sharkCount = sharkCount;
                        levelTwoBoard.submarine.octopusCount = octopusCount;
                        levelTwoBoard.submarine.setFuel(fuelAmount);
                        levelTwoBoard.submarine.setHealth(healthAmount);
                        levelTwoBoard.submarine.setScore(score);
                        StartLevel levelTwoGame = new StartLevel(game, game.frame, levelTwoBoard);
                        break;
                    case "Level 3":
                        game.frame.getContentPane().removeAll();
                        game.frame.getContentPane().removeAll();
                        LevelThree levelThreeBoard = new LevelThree(game);
                        levelThreeBoard.submarine.crystalCount = crystalCount;
                        levelThreeBoard.submarine.sharkCount = sharkCount;
                        levelThreeBoard.submarine.octopusCount = octopusCount;
                        levelThreeBoard.submarine.setFuel(fuelAmount);
                        levelThreeBoard.submarine.setHealth(healthAmount);
                        levelThreeBoard.submarine.setScore(score);
                        StartLevel levelThreeGame = new StartLevel(game, game.frame, levelThreeBoard);
                        break;
                }
            } else {
                // if player doesn't exist, pop a warning
                JOptionPane.showMessageDialog(new JFrame(), "No saved game found for \"" + Game.player + "\"","Oops",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("data/warning.png"));
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }


    // method for checking if the player has reached a certain level
    static String checkStats(){
        String fileName = "Game Score";
        if (Game.player == null) {
            return "Player not defined.";
        }
        BufferedReader reader = null;
        try {
            File file = new File("Game Score");
            if (!file.exists()) {
                return "No saved game found";
            }
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 0 && tokens[0].equals(Game.player)) {
                    return tokens[1];
                }
                line = reader.readLine();
            }
            // player not found in file
            return "No saved game found";
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
