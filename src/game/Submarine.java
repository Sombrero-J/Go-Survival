package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Submarine extends Walker implements SensorListener {
    private static final Shape SubmarineShape = new PolygonShape(0.48f, 1.54f, 2.16f, 0.11f, 0.67f, -1.44f, -1.8f, -0.94f, -2.45f, -0.06f, -1.67f, 0.97f, -0.04f, 1.52f, 0.25f, 1.54f);
    private static int fuel;
    private static int health;
    private int score;
    private float speed = 8;
    boolean isAlive;
    int crystalCount = 0;
    int octopusCount = 0;
    int sharkCount = 0;

    BodyImage submarine_right =
            new BodyImage("data/subGIF.gif", 6f);

    BodyImage submarine_left =
            new BodyImage("data/subGIF_left.gif", 6f);

    public Submarine(World world) {
        super(world, SubmarineShape);
        score = 0;
        fuel = 100;
        health = 100;
        isAlive = true;
        this.addImage(submarine_right);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Submarine.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        Submarine.fuel = fuel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String getName() {
        return "Submarine";
    }

    public void startWalkingX(float speed) {
        super.startWalking(speed);
    }

    public void startWalkingY(float speed) {
        this.setLinearVelocity(new Vec2(0, speed));
    }

    public void changeRight() {
        removeAllImages();
        addImage(submarine_right);
    }

    public void changeLeft() {
        removeAllImages();
        addImage(submarine_left);
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Submarine) {
            this.setSpeed(4);
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Submarine) {
            this.setSpeed(8);
        }
    }
}