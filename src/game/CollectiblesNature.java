package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CollectiblesNature implements MouseListener, StepListener {
    private GameLevel world;
    private GameView view;
    private Submarine submarine;

    //angle used for movements
    private int angle = 0;
    private int count = 0;

    public CollectiblesNature(GameLevel w, GameView v, Submarine s) {
        world = w;
        view = v;
        submarine = s;
    }

    public void mousePressed(MouseEvent e) {
        Point mousePoint = e.getPoint();

        //transform mouse coordinates into world coordinates
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        for (Collectibles k : world.getCollectibles()) {
            // using mouse clicks to collect the collectibles
            if (k.contains(worldPoint)) {
                k.destroy();
                if (k instanceof Jellyfish j) {
                    if (Music.soundEffectsAllowed) {
                        GameLevel.getPickUpSound().play();
                    }
                    // jellyfish restores fuel
                    submarine.setFuel(submarine.getFuel() + Jellyfish.getFuel());
                    if (submarine.getFuel() > 100) {
                        submarine.setFuel(submarine.getFuel() - (submarine.getFuel() % 100));
                    }
                    j.logTrigger = true;
                    System.out.println("Fuel added. Total Fuel: " + submarine.getFuel());
                } else if (k instanceof Mermaid m) {
                    if (Music.soundEffectsAllowed) {
                        GameLevel.getPickUpSound().play();
                    }
                    // mermaid restores health
                    submarine.setHealth(submarine.getHealth() + Mermaid.getPotion());
                    if (submarine.getHealth() > 100) {
                        submarine.setHealth(submarine.getHealth() - (submarine.getHealth() % 100));
                    }
                    System.out.println("Submarine healed. Health: " + submarine.getHealth());
                    submarine.setScore(submarine.getScore() + k.getPoints());
                    m.logTrigger = true;
                } else if (k instanceof Crystal c) {
                    if (Music.soundEffectsAllowed) {
                        GameLevel.getCrystalSound().play();
                    }
                    // crystal is game objectives
                    submarine.setScore(submarine.getScore() + k.getPoints());
                    submarine.crystalCount++;
                    c.logTrigger = true;
                }
                System.out.println("Total Score: " + submarine.getScore());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // the mechanism for collectible items' movements
        count++;
        float speedMultiplier = (float) Math.random() + 1;
        for (Collectibles c : world.getCollectibles()) {
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