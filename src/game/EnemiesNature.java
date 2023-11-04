package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class EnemiesNature implements CollisionListener, StepListener {

    private GameLevel world;

    private Submarine submarine;

    private int count = 0;
    private int angle = 0;

    public EnemiesNature(GameLevel w, Submarine s) {
        world = w;
        submarine = s;
    }

    @Override
    public void collide(CollisionEvent c) {
        if (c.getOtherBody() instanceof Enemies k && c.getReportingBody() instanceof Submarine) {
            if (Music.soundEffectsAllowed) {
                GameLevel.getDamageSound().play();
            }
            // reduce submarine health
            submarine.setHealth(submarine.getHealth() - k.getDamage());
            k.logTrigger = true;
            c.getOtherBody().destroy();
            // submarine dies
            if (submarine.getHealth() <= 0) {
                System.out.println("Submarine destroyed. Game Ended...");
                submarine.isAlive = false;
            } else {
                System.out.println("You've been damaged. Health: " + submarine.getHealth());
            }
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // enemies movement
        count++;
        float speedMultiplier = (float) Math.random() + 1;

        for (Enemies c : world.getEnemies()) {
            if (count % 45 == 0) {
                float amplitude = (float) Math.random() * 30 - 15;
                float y = amplitude * (float) Math.sin(getNextAngle());
                c.setLinearVelocity(new Vec2(c.getSpeed() * speedMultiplier, y));
            }
        }
    }

    public int getNextAngle() {
        angle = (angle + 1) % 360;
        return angle;
    }
}
