package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class HealthBar extends StatusBars implements StepListener {

    BodyImage healthGIF = new BodyImage("data/healthGIF.gif", 18);

    BodyImage[] imageList = new BodyImage[]{
            new BodyImage("data/healthFrames/health0.png", 18),
            new BodyImage("data/healthFrames/health1.png", 18),
            new BodyImage("data/healthFrames/health2.png", 18),
            new BodyImage("data/healthFrames/health3.png", 18),
            new BodyImage("data/healthFrames/health4.png", 18),
            new BodyImage("data/healthFrames/health5.png", 18),
            new BodyImage("data/healthFrames/health6.png", 18),
            new BodyImage("data/healthFrames/health7.png", 18),
            new BodyImage("data/healthFrames/health8.png", 18),
            new BodyImage("data/healthFrames/health9.png", 18)
    };

    GameLevel world;

    public HealthBar(GameLevel w) {
        super(w);
        world = w;
        if (world.hasFuel) {
            bar.setPosition(new Vec2(6f, 13.57f));
        } else {
            bar.setPosition(new Vec2(0f, 13.57f));
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        count++;
        if (count <= 50) {
            bar.addImage(healthGIF);
        } else {
            bar.removeAllImages();
            statusIndex = Math.max(0, Math.min(world.getSubmarine().getHealth() / 10 - 1, 9));
            if (statusIndex != prevIndex) {
                bar.removeAllImages();
            }
            bar.addImage(imageList[statusIndex]);
            prevIndex = statusIndex;
        }
    }
}
