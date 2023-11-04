package game;

import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public abstract class Enemies extends Walker {

    protected int health;
    private final float speed = -10;
    protected int damage;

    protected boolean logTrigger;

    public Enemies(World world, Shape shape) {
        super(world, shape);
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}