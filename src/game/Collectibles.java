package game;

import city.cs.engine.*;

public abstract class Collectibles extends Walker {

    // this is for the kill feed (log)
    protected boolean logTrigger;

    //every collectibles have different points
    protected int points;

    //every collectibles have different speed
    private static final int speed = -10;

    public Collectibles(World world, Shape shape, int points) {
        super(world);
        this.points = points;

        // collectibles are GhostlyFixture
        GhostlyFixture gf = new GhostlyFixture(this, shape);
    }

    public int getPoints() {
        return points;
    }
    public int getSpeed() {
        return speed;
    }
}