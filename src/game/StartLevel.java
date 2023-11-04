package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

import javax.swing.*;
public class StartLevel implements StepListener {
    Game game;
    JFrame frame;
    GameLevel l;
    GameView view;

    public StartLevel(Game g, JFrame f, GameLevel l) {
        game = g;
        frame = f;
        this.l = l;

        Music music = new Music(game);
        music.playMusic();

        view = new GameView(game, l, 800, 600, l.getSubmarine());
        view.addMouseListener(view);
        l.addStepListener(this);

        SubmarineHandler controller = new SubmarineHandler(l.getSubmarine());
        view.addKeyListener(controller);
        l.addStepListener(controller);

        GiveFocus giveFocus = new GiveFocus(view);
        view.addMouseListener(giveFocus);
        l.addStepListener(l);

        CollectiblesNature collectiblesNature = new CollectiblesNature(l, view, l.getSubmarine());
        view.addMouseListener(collectiblesNature);
        l.addStepListener(collectiblesNature);

        EnemiesNature enemiesNature = new EnemiesNature(l, l.getSubmarine());
        l.addStepListener(enemiesNature);
        l.getSubmarine().addCollisionListener(enemiesNature);

        Bullet shooting = new Bullet(l, view, l.getSubmarine());
        view.addKeyListener(shooting);

        HealthBar healthBar = new HealthBar(l);
        l.addStepListener(healthBar);

        if (l.hasFuel) {
            FuelBar fuelBar = new FuelBar(l);
            l.addStepListener(fuelBar);
        }

//        DebugViewer debug = new DebugViewer(l, 800,600);
//        debug.setVisible(true);

        frame.add(view);
        view.setBounds(0, 0, 800, 600);
        frame.repaint();

        view.requestFocus();
        l.start();
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if ((l.isComplete() | !l.getSubmarine().isAlive )&& l.isRunning()) {
            l.stop();
            System.out.println("game stopped");
        }
    }
}
