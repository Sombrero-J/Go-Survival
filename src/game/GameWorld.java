//package game;
//
//import city.cs.engine.*;
//import city.cs.engine.Shape;
//import org.jbox2d.common.Vec2;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GameWorld extends World implements ActionListener, StepListener, CollisionListener {
//    Submarine submarine;
//    ArrayList<ArrayList> collectiblesList, enemiesList;
//
//    ArrayList<Seaweed> seaweedList;
//    Collectibles mermaid, crystal, jellyfish;
//    Enemies shark, octopus;
//    Timer spawnTimerCollectibles, despawnTimerCollectibles, spawnTimerEnemies;
//
//    private int count = 0;
//
//    Shape topWallShape = new BoxShape(20, 1);
//    Shape leftWallShape = new BoxShape(1, 15);
//
//    Vec2 submarinePosition = new Vec2(-18, 0);
//
//    BodyImage rockImage = new BodyImage("data/rock.png", 10f);
//    BodyImage mushroomImage = new BodyImage("data/mushroom.png", 5f);
//
//    public GameWorld() {
//        super(120);
//        setGravity(0);
//        collectiblesList = new ArrayList<>();
//        enemiesList = new ArrayList<>();
//        seaweedList = new ArrayList<>();
//
//        submarine = new Submarine(this);
//        submarine.setPosition(submarinePosition);
//
//        spawnTimerCollectibles = new Timer(1500, this);
//        spawnTimerCollectibles.start();
//        despawnTimerCollectibles = new Timer(5000, e -> {
//            if (collectiblesList.size() > 1) {
//                for (Object obj : collectiblesList.get(0)) {
//                    if (obj instanceof Collectibles x) {
//                        x.destroy();
//                    }
//                }
//                collectiblesList.remove(0);
//            }
//        });
//        spawnTimerEnemies = new Timer(2500, e -> {
//            spawnTwoEnemies();
//        });
//        spawnTimerEnemies.start();
//        spawnTimerEnemies.setInitialDelay(1000);
//
//        StaticBody topWall = new StaticBody(this, topWallShape);
//        topWall.setPosition(new Vec2(0, 16));
//        topWall.addCollisionListener(this);
//        StaticBody bottomWall = new StaticBody(this, topWallShape);
//        bottomWall.setPosition(new Vec2(0, -16));
//        bottomWall.addCollisionListener(this);
//        StaticBody leftWall = new StaticBody(this, leftWallShape);
//        leftWall.setPosition(new Vec2(-21.5f, 0));
//        leftWall.addCollisionListener(this);
//
//        StaticBody rock = new StaticBody(this);
//        PolygonShape rockLeft = new PolygonShape(-5.0f, 3.48f, -8.0f, 0.64f, -7.84f, -1.64f, -0.68f, -3.24f, 1.04f, 0.8f, -0.68f, 2.96f, -2.96f, 3.52f);
//        PolygonShape rockRight = new PolygonShape(1.08f, 0.84f, -0.68f, -3.24f, 3.56f, -3.64f, 4.84f, -2.24f, 4.56f, -0.6f, 3.48f, 0.4f, 1.64f, 0.88f);
//        SolidFixture rockOne = new SolidFixture(rock, rockLeft);
//        SolidFixture rockTwo = new SolidFixture(rock, rockRight);
//        rock.addImage(rockImage);
//        rock.setPosition(new Vec2(-16, -12));
//
//        StaticBody mushroom = new StaticBody(this);
//        PolygonShape head = new PolygonShape(-1.88f, 1.62f, -0.96f, 1.88f, 0.98f, 1.12f, 2.0f, -0.02f, 1.76f, -1.12f, 0.22f, -0.94f, -2.02f, 0.84f);
//        PolygonShape foot = new PolygonShape(-1.66f, 0.36f, -0.08f, -0.72f, 0.68f, -1.34f, -1.08f, -2.42f, -1.78f, -2.4f, -1.88f, -1.06f, -1.86f, 0.16f);
//        SolidFixture headOne = new SolidFixture(mushroom, head);
//        SolidFixture footOne = new SolidFixture(mushroom, foot);
//        mushroom.addImage(mushroomImage);
//        mushroom.setPosition(new Vec2(-9, -13));
//    }
//
//    public Submarine getSubmarine() {
//        return submarine;
//    }
//
//    public List<Collectibles> getCollectibles() {
//        List<Collectibles> allCollectibles = new ArrayList<>();
//        for (ArrayList collectiblesInsideList : collectiblesList) {
//            allCollectibles.addAll(collectiblesInsideList);
//        }
//        return allCollectibles;
//    }
//
//    List<Enemies> getEnemies() {
//        List<Enemies> allEnemies = new ArrayList<>();
//        for (ArrayList enemiesInsideList : enemiesList) {
//            allEnemies.addAll(enemiesInsideList);
//        }
//        return allEnemies;
//    }
//
//
//    public void spawnTwoCollectibles() {
//        ArrayList<Collectibles> collectiblesInsideList = new ArrayList<>();
//        for (int k = 0; k < 2; k++) {
//            float i = (float) Math.random() * 3;
//            float x = (float) Math.random() + 25;
//            float y = (float) Math.random() * 20 - 10;
//            if (i < 1) {
//                mermaid = new Mermaid(this);
//                mermaid.setPosition(new Vec2(x, y));
//                collectiblesInsideList.add(mermaid);
//            } else if (i < 2) {
//                crystal = new Crystal(this);
//                crystal.setPosition(new Vec2(x, y));
//                collectiblesInsideList.add(crystal);
//            } else {
//                jellyfish = new Jellyfish(this);
//                jellyfish.setPosition(new Vec2(x, y));
//                collectiblesInsideList.add(jellyfish);
//            }
//        }
//        collectiblesList.add(collectiblesInsideList);
//    }
//
//    public void spawnTwoEnemies() {
//        ArrayList<Enemies> enemiesInsideList = new ArrayList<>();
//        for (int k = 0; k < 2; k++) {
//            float i = (float) Math.random() * 3;
//            float x = (float) Math.random() + 25;
//            float y = (float) Math.random() * 20 - 10;
//            if (i < 1) {
//                shark = new Shark(this);
//                shark.setPosition(new Vec2(x, y));
//                enemiesInsideList.add(shark);
//            } else {
//                octopus = new Octopus(this);
//                octopus.setPosition(new Vec2(x, y));
//                enemiesInsideList.add(octopus);
//            }
//        }
//        enemiesList.add(enemiesInsideList);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        spawnTwoCollectibles();
//        if (!despawnTimerCollectibles.isRunning()) {
//            despawnTimerCollectibles.start();
//        }
//    }
//
//    @Override
//    public void preStep(StepEvent stepEvent) {
//
//    }
//
//    @Override
//    public void postStep(StepEvent stepEvent) {
//        count++;
//        if (count % 90 == 0) {
//            float y = (float) Math.random() * 8;
//            Seaweed seaweed = new Seaweed(this, y);
//            seaweedList.add(seaweed);
//            float x = (float) Math.random() * 5 + 20;
//            if (x > 22.5) {
//                seaweed.addImage(new BodyImage("data/seaweedDown.png", y * 2));
//                seaweed.setPosition(new Vec2(x, y - 15));
//            } else {
//                seaweed.addImage(new BodyImage("data/seaweedUp.png", y * 2));
//                seaweed.setPosition(new Vec2(x, 15 - y));
//            }
//            seaweed.startWalking(-5);
//        }
//    }
//
//    @Override
//    public void collide(CollisionEvent e) {
//        if (e.getOtherBody() instanceof Submarine) {
//            submarine.setPosition(submarinePosition);
//        } else {
//            e.getOtherBody().destroy();
//        }
//    }
//
//    public void restart() {
//        submarine.isAlive = true;
//        submarine.setScore(0);
//        submarine.setPoints(0);
//        submarine.setHealth(100);
//        submarine.setFuel(100);
//        submarine.setPosition(submarinePosition);
//        for (Enemies e : getEnemies()) {
//            e.destroy();
//        }
//        for (Collectibles c : getCollectibles()) {
//            c.destroy();
//        }
//        for (Seaweed s : seaweedList) {
//            s.destroy();
//        }
//        start();
//    }
//}