package game;

import city.cs.engine.*;

public class Shark extends Enemies {
    private static final Shape SharkShape = new PolygonShape(-1.8f, 0.86f, 2.43f, 0.9f, 2.18f, -0.95f, 0.14f, -1.15f, -1.51f, -0.61f, -1.96f, 0.42f, -1.9f, 0.7f);

    private static final BodyImage sharkImage = new BodyImage("data/shark.png", 5f);

    public Shark(World world) {
        super(world, SharkShape);
        this.damage = 20;
        this.health = 20;
        logTrigger = false;
        this.addImage(sharkImage);
    }

    @Override
    public String getName() {
        return "Shark";
    }
}
