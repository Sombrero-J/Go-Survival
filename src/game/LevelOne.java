package game;

public class LevelOne extends GameLevel{

    public LevelOne(Game game) {
        super(game);
        level = 1;
        game.gameLevel = 1;
        canShoot = false;
        hasFuel = false;
        goalCountCrystal = 3;
    }

    @Override
    public boolean isComplete() {
        return submarine.crystalCount >= goalCountCrystal;
    }

    @Override
    String getLevelName() {
        return "Level 1";
    }
}
