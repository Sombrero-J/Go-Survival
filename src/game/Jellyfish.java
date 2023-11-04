package game;

import city.cs.engine.*;

public class Jellyfish extends Collectibles {
    private static int fuel = 5;

    private static final Shape jellyfishShape = new PolygonShape(0.7f, 1.97f, 2.09f, -1.44f, -0.41f, -1.3f, -1.22f, 0.39f, -0.61f, 1.94f, 0.51f, 1.97f
    );

    private static final BodyImage jellyfishImage = new BodyImage("data/jellyfish.png", 4f);

    public Jellyfish(World world) {
        super(world, jellyfishShape, 10);
        this.addImage(jellyfishImage);
        logTrigger = false;
    }

    public static int getFuel() {
        return fuel;
    }

    @Override
    public String getName() {
        return "Jellyfish";
    }
}
