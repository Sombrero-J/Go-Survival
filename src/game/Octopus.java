package game;

import city.cs.engine.*;

public class Octopus extends Enemies {

    private static final Shape OctopusShape = new PolygonShape(0.08f, 1.74f, 1.43f, 0.83f, 1.94f, -0.7f, 1.2f, -1.57f, -0.46f, -1.21f, -1.46f, 0.41f, -0.41f, 1.67f);
    private static final BodyImage octopusImage = new BodyImage("data/octopus.png", 5f);

    public Octopus(World world) {
        super(world, OctopusShape);
        this.damage = 15;
        this.health = 10;
        logTrigger = false;
        this.addImage(octopusImage);
    }

    @Override
    public String getName() {
        return "Octopus";
    }
}
