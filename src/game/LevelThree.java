package game;

public class LevelThree extends GameLevel {

    public LevelThree(Game game) {
        super(game);
        level = 3;
        Game.gameLevel = 3;
        canShoot = true;
        hasFuel = true;
        goalCountCrystal = 3;
        goalCountOctopus = 3;
        goalCountShark = 3;
    }

    @Override
    public boolean isComplete() {
        return (submarine.crystalCount >= goalCountCrystal && submarine.octopusCount >= goalCountOctopus && submarine.sharkCount >= goalCountShark);
    }

    @Override
    String getLevelName() {
        return "Level 3";
    }
}
