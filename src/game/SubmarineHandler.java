package game;

import city.cs.engine.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SubmarineHandler implements KeyListener, StepListener {
    Submarine submarine;
    private int count = 0;

    public SubmarineHandler(Submarine a) {
        submarine = a;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            submarine.startWalkingX(-submarine.getSpeed());
            submarine.changeLeft();
        } else if (code == KeyEvent.VK_D) {
            submarine.startWalkingX(submarine.getSpeed());
            submarine.changeRight();
        } else if (code == KeyEvent.VK_W) {
            submarine.startWalkingY(submarine.getSpeed());
        } else if (code == KeyEvent.VK_S) {
            submarine.startWalkingY(-submarine.getSpeed());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            submarine.startWalkingX(0);
        } else if (code == KeyEvent.VK_D) {
            submarine.startWalkingX(0);
        } else if (code == KeyEvent.VK_W) {
            submarine.startWalkingY(0);
        } else if (code == KeyEvent.VK_S) {
            submarine.startWalkingY(0);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        count++;
        if (count % 40 == 0) {
            submarine.setFuel(submarine.getFuel() - 1);
        }

        if (submarine.getFuel() <= 0) {
            submarine.isAlive = false;
        }
    }
}
