package game;

import city.cs.engine.*;

public class Crystal extends Collectibles {

    private static final Shape crystalShape = new PolygonShape(0.59f, 1.77f, 1.1f, -0.15f, 0.28f, -1.1f, -0.87f, -0.89f, -1.53f, -0.18f, -0.6f, 1.38f, 0.36f, 1.77f
    );
    private static final BodyImage crystalImage = new BodyImage("data/crystal.png", 5f);

    public Crystal(World world) {
        super(world, crystalShape, 40);
        this.addImage(crystalImage);
        logTrigger = false;
    }

    @Override
    public String getName() {
        return "Crystal";
    }
}
