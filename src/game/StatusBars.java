package game;

import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class StatusBars extends StaticBody {
    World world;
    protected StaticBody bar;
    protected int statusIndex;

    protected int count = 0;
    protected int prevIndex = 0;

    public StatusBars(World w) {
        super(w);
        world = w;
        bar = new StaticBody(world);
        GhostlyFixture barFixture = new GhostlyFixture(bar, new BoxShape(1, 1));
    }
}
