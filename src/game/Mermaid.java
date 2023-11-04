package game;

import city.cs.engine.*;

public class Mermaid extends Collectibles {
    private static final int potion = 10;

    private static final Shape mermaidShape = new PolygonShape(0.59f, 2.47f,
            1.94f, 1.89f, 1.02f, -1.88f, -2.05f, -1.85f, -2.09f, 0.16f, -0.86f, 2.38f, 0.15f, 2.48f);
    private static final BodyImage mermaidImage = new BodyImage("data/mermaid.png", 4f);

    public Mermaid(World world) {
        super(world, mermaidShape, 30);
        addImage(mermaidImage);
        logTrigger = false;
    }

    public static int getPotion() {
        return potion;
    }

    @Override
    public String getName() {
        return "Mermaid";
    }
}
