package game;

import city.cs.engine.*;

public class Seaweed extends Walker {

    GameLevel world;

    public Seaweed(GameLevel world, float HalfHeight) {
        super(world);
        this.world = world;
        GhostlyFixture gs = new GhostlyFixture(this, new BoxShape(0.5f, HalfHeight));
        Sensor sensor = new Sensor(this, new BoxShape(0.5f, HalfHeight));
        sensor.addSensorListener(world.getSubmarine());
    }
}