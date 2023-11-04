package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.*;
import java.util.ArrayList;

public class Bullet extends DynamicBody implements KeyListener, CollisionListener {
    GameView view;
    Submarine submarine;
    GameLevel world;
    float bulletSpeed = 15f;
    float radius = 0.2f;
    float bulletDistance = 2f;
    int bulletDamage = 10;

    BodyImage bulletRoundImage = new BodyImage("data/bulletRound.png", 2f);

    ArrayList<DynamicBody> bulletList;

    public Bullet(GameLevel w, GameView v, Submarine s) {
        super(w);
        view = v;
        world = w;
        submarine = s;
        bulletList = new ArrayList<>();
    }

    //create bullets and set their positions and directions
    public void shoot() {
        //converting mouse position to Vec2
        Vec2 worldPoint = view.viewToWorld(view.getMousePosition());

        //creating the bullet
        DynamicBody bullet = new DynamicBody(world, new CircleShape(radius));
        bullet.addCollisionListener(this);
        bullet.addImage(bulletRoundImage);

        // direction = worldPoint - submarinePosition
        Vec2 direction = worldPoint.sub(world.getSubmarine().getPosition());

        // setting the vector of direction to have a magnitude of 1
        direction.normalize();

        //set bullet to Spawn bulletDistance away from submarine while staying on the direction course
        Vec2 bulletPosition = world.getSubmarine().getPosition().add(direction.mul(bulletDistance));
        bullet.setPosition(bulletPosition);

        // multiplies the vector by a value and stores the result in the original vector.
        Vec2 velocity = direction.mulLocal(bulletSpeed);
        bullet.setLinearVelocity(velocity);
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemies k) {
            //destroys bullet
            e.getReportingBody().destroy();
            //reduce health of enemies
            k.setHealth(k.getHealth() - bulletDamage);
            //increase submarine score everytime an enemy is hit
            world.getSubmarine().setScore(world.getSubmarine().getScore() + 30);
            if (k.getHealth() <= 0) {
                if (k instanceof Octopus) {
                    submarine.octopusCount++;
                } else {
                    submarine.sharkCount++;
                }
                if (Music.soundEffectsAllowed) {
                    // play destroyed music
                    GameLevel.getDestroyedSound().play();
                }
                k.destroy();
            } else {
                // play hit sound
                if (Music.soundEffectsAllowed) {
                    GameLevel.getHitSound().play();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            // shoot using Space Bar
            if (world.canShoot) {
                if (Music.soundEffectsAllowed) {
                    GameLevel.getShootingSound().play();
                }
                shoot();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
