package game;

public class LevelTwo extends GameLevel {

    public LevelTwo(Game game) {
        super(game);
        level = 2;
        game.gameLevel = 2;
        canShoot = true;
        hasFuel = false;
        goalCountCrystal = 3;
        goalCountOctopus = 3;
    }

    @Override
    public boolean isComplete() {
        return (submarine.crystalCount >= goalCountCrystal && submarine.octopusCount >= goalCountOctopus);
    }

    @Override
    String getLevelName() {
        return "Level 2";
    }
}
