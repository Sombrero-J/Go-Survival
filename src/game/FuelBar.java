package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class FuelBar extends StatusBars implements StepListener {
    BodyImage fuelGIF = new BodyImage("data/fuelGIF.gif", 18);
    BodyImage[] imageList = new BodyImage[]{
            new BodyImage("data/fuelFrames/fuel9.png", 18),
            new BodyImage("data/fuelFrames/fuel8.png", 18),
            new BodyImage("data/fuelFrames/fuel7.png", 18),
            new BodyImage("data/fuelFrames/fuel6.png", 18),
            new BodyImage("data/fuelFrames/fuel5.png", 18),
            new BodyImage("data/fuelFrames/fuel4.png", 18),
            new BodyImage("data/fuelFrames/fuel3.png", 18),
            new BodyImage("data/fuelFrames/fuel2.png", 18),
            new BodyImage("data/fuelFrames/fuel1.png", 18),
            new BodyImage("data/fuelFrames/fuel0.png", 18)
    };

    GameLevel world;

    public FuelBar(GameLevel w) {
        super(w);
        world = w;
        bar.setPosition(new Vec2(-5f, 13.57f));
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        count++;
        if (count <= 50) {
            bar.addImage(fuelGIF);
        } else {
            bar.removeAllImages();
            statusIndex = Math.max(0, Math.min(world.getSubmarine().getFuel() / 10, 9));
            if (statusIndex != prevIndex) {
                bar.removeAllImages();
            }
            bar.addImage(imageList[statusIndex]);
            prevIndex = statusIndex;
        }
    }
}

