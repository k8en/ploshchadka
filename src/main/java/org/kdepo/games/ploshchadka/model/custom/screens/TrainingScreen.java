package org.kdepo.games.ploshchadka.model.custom.screens;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.Ploshchadka;
import org.kdepo.games.ploshchadka.ai.CharactersController;
import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.VirtualObject;
import org.kdepo.games.ploshchadka.model.base.geometry.OrthogonalPolygon;
import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector2D;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;
import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;
import org.kdepo.games.ploshchadka.model.base.screens.AbstractScreen;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.Ground;
import org.kdepo.games.ploshchadka.model.custom.characters.FaceDirection;
import org.kdepo.games.ploshchadka.model.custom.game.Ball;
import org.kdepo.games.ploshchadka.model.custom.game.CrossbarSegment;
import org.kdepo.games.ploshchadka.model.custom.game.GameState;
import org.kdepo.games.ploshchadka.model.custom.game.GoalKeeper;
import org.kdepo.games.ploshchadka.model.custom.game.Goalpost;
import org.kdepo.games.ploshchadka.model.custom.game.MatchInfo;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.Team;
import org.kdepo.games.ploshchadka.utils.CollisionUtils;
import org.kdepo.games.ploshchadka.utils.MathUtils;
import org.kdepo.games.ploshchadka.utils.ReflectionUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainingScreen extends AbstractScreen {

    private final Ploshchadka ploshchadka;

    private final VirtualCamera camera;
    private final VirtualRectangle screenMovementBounds;

    private GameState gameState;

    private final Ball ball;

    private final Ground ground;

    // Goalpost (right) rendering
    private final Goalpost goalpostRight1;
    private final CrossbarSegment crossbarSegmentRight1;
    private final CrossbarSegment crossbarSegmentRight2a;
    private final CrossbarSegment crossbarSegmentRight2b;
    private final CrossbarSegment crossbarSegmentRight2c;
    private final CrossbarSegment crossbarSegmentRight3;
    private final Goalpost goalpostRight2;

    // Goalpost (right) geometry
    private final OrthogonalPolygon goalpostRight1Plane;
    private final OrthogonalPolygon goalpostRight2Plane;
    private final OrthogonalPolygon goalpostRightTopPlane;
    private final OrthogonalPolygon goalpostRightBackPlane;

    // Game participants control
    CharactersController charactersController;

    // To collect controls from keyboard
    private Controls humanControls;

    // To prepare controls based on algo calculations
    private Controls aiControls;

    private MatchInfo matchInfo;

    private Team teamLeft;
    private Team teamRight;

    // All players list
    private List<Player> players;

    // All goalkeepers list
    private List<GoalKeeper> goalKeepers;

    // List of objects to sort and render
    private final List<DrawableObject> renderList;

    // List of objects to check for collision with ball
    private final List<OrthogonalPolygon> geometryList;

    public TrainingScreen(Ploshchadka ploshchadka) {
        this.ploshchadka = ploshchadka;

        gameState = GameState.PLAY;

        ball = new Ball(0, 0, 14, 14);

        ground = new Ground();

        // Prepare goalpost (right) rendering parameters
        goalpostRight1 = new Goalpost(965, -69 + 16);
        crossbarSegmentRight1 = new CrossbarSegment(974, -48 + 16, CrossbarSegment.SEGMENT_TOP);
        crossbarSegmentRight2a = new CrossbarSegment(974, -24 + 16, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight2b = new CrossbarSegment(974, 0 + 16, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight2c = new CrossbarSegment(974, 24 + 16, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight3 = new CrossbarSegment(974, 48 + 16, CrossbarSegment.SEGMENT_BOTTOM);
        goalpostRight2 = new Goalpost(965, 49 + 16);

        // Prepare goalpost (right) geometry parameters
        Point3D a1 = new Point3D(goalpostRight1.getX(), -60 + 16, goalpostRight1.getHeight());
        Point3D b1 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), -60 + 16, goalpostRight1.getHeight());
        Point3D c1 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), -60 + 16, 0);
        Point3D d1 = new Point3D(goalpostRight1.getX(), -60 + 16, 0);
        Point3D a2 = new Point3D(goalpostRight1.getX(), 60 + 16, goalpostRight1.getHeight());
        Point3D b2 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), 60 + 16, goalpostRight1.getHeight());
        Point3D c2 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), 60 + 16, 0);
        Point3D d2 = new Point3D(goalpostRight1.getX(), 60 + 16, 0);

        goalpostRight1Plane = new OrthogonalPolygon(a1, b1, c1, d1);
        goalpostRight2Plane = new OrthogonalPolygon(a2, b2, c2, d2);
        goalpostRightTopPlane = new OrthogonalPolygon(a1, b1, b2, a2);
        goalpostRightBackPlane = new OrthogonalPolygon(b2, b1, c1, c2);

        // Collect geometry into single list
        geometryList = new ArrayList<>();
        geometryList.add(goalpostRight1Plane);
        geometryList.add(goalpostRight2Plane);
        geometryList.add(goalpostRightTopPlane);
        geometryList.add(goalpostRightBackPlane);

        // Initialize characters controller
        charactersController = CharactersController.getInstance();

        humanControls = new Controls();
        aiControls = new Controls();

        // Prepare players
        players = new ArrayList<>();

        // Add human-controlled player
        Player humanPlayer = new Player(1, Constants.AnimationName.STAND_RIGHT, -50, 0, 0);
        humanPlayer.setTeamId(Constants.Team.TEST_LEFT);
        humanPlayer.setHumanControls(true);
        players.add(humanPlayer);

        // Add teammate for human-controlled player
        Player teammatePlayer = new Player(3, Constants.AnimationName.STAND_RIGHT, 50, -50, 0);
        teammatePlayer.setTeamId(Constants.Team.TEST_LEFT);
        teammatePlayer.setHumanControls(false);
        players.add(teammatePlayer);

        // Prepare goal keepers
        goalKeepers = new ArrayList<>();

        GoalKeeper goalKeeper = new GoalKeeper(2, Constants.AnimationName.STAND_LEFT, 900, 0, 0);
        goalKeeper.setTeamId(Constants.Team.TEST_RIGHT);

        goalKeepers.add(goalKeeper);

        // Setup teams
        teamLeft = new Team();
        teamLeft.setTeamId(Constants.Team.TEST_LEFT);
        teamLeft.getPlayers().add(humanPlayer);
        teamLeft.getPlayers().add(teammatePlayer);

        teamRight = new Team();
        teamRight.setTeamId(Constants.Team.TEST_RIGHT);
        teamRight.setGoalKeeper(goalKeeper);

        matchInfo = new MatchInfo();
        matchInfo.setTeamAtLeftSide(teamLeft.getTeamId());
        matchInfo.setTeamAtRightSide(teamRight.getTeamId());

        // Prepare camera
        camera = new VirtualCamera(Constants.ScreenSize.WIDTH, Constants.ScreenSize.HEIGHT);
        camera.setCameraCenter(0.0d, 0.0d);

        // Set camera bounds
        camera.getMovementBounds().addToX(150);
        camera.getMovementBounds().addToY(150);
        camera.getMovementBounds().addToWidth(-300);
        camera.getMovementBounds().addToHeight(-300);

        screenMovementBounds = new VirtualRectangle();
        screenMovementBounds.setX(ground.getX());
        screenMovementBounds.setY(ground.getY());
        screenMovementBounds.setWidth(ground.getWidth());
        screenMovementBounds.setHeight(ground.getHeight());
        System.out.println("Screen movement bounds = " + screenMovementBounds);

        renderList = new ArrayList<>();
    }

    @Override
    public void update() {
        // Clear all previous messages
        Console.clear();

        updateBall(ball, ground, geometryList);

        // Update players
        for (Player player : players) {
            Team teammates = teamLeft.getTeamId() == player.getTeamId() ? teamLeft : teamRight;
            Team opponents = teamLeft.getTeamId() == player.getTeamId() ? teamRight : teamLeft;

            if (player.isHumanControls()) {
                charactersController.processPlayerState(player, humanControls, ball, teammates);
            } else {
                aiControls = charactersController.resolvePlayerControls(aiControls, gameState, matchInfo, ball, player, teammates, opponents);
                charactersController.processPlayerState(player, aiControls, ball, teammates);
            }
        }

        // Update goalKeepers
        for (GoalKeeper goalKeeper : goalKeepers) {
            Team teammates = teamLeft.getTeamId() == goalKeeper.getTeamId() ? teamLeft : teamRight;
            Team opponents = teamLeft.getTeamId() == goalKeeper.getTeamId() ? teamRight : teamLeft;

            aiControls = charactersController.resolveGoalKeeperControls(aiControls, gameState, matchInfo, ball, goalKeeper, teammates, opponents);
            updateGoalKeeper(goalKeeper, aiControls, ball);
        }

        // Update camera parameters
        updateCamera(camera, screenMovementBounds, ball);

        // Prepare render list ordered by Y coordinate
        renderList.clear(); // TODO should we clear this every time?

        renderList.addAll(players);
        renderList.addAll(goalKeepers);

        renderList.add(ball);
        renderList.add(goalpostRight1);
        renderList.add(crossbarSegmentRight1);
        renderList.add(crossbarSegmentRight2a);
        renderList.add(crossbarSegmentRight2b);
        renderList.add(crossbarSegmentRight2c);
        renderList.add(crossbarSegmentRight3);
        renderList.add(goalpostRight2);
        renderList.sort(Comparator.comparing(VirtualObject::getCenterY));

        // Output debug information
        //Console.addMessage("Camera: x=" + camera.getX() + ", y=" + camera.getY() + ", width=" + camera.getWidth() + ", height=" + camera.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        // Clear all with selected color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ploshchadka.getScreenWidth(), ploshchadka.getScreenHeight());

        // Draw tiled ground
        ground.draw(g, camera);

        // Draw sorted objects
        for (DrawableObject object : renderList) {
            object.draw(g, camera);
        }

        // Draw camera bounds for debug purposes
//        g.setColor(Color.ORANGE);
//        g.drawRect(
//                (int) camera.getScreenOffsetX(camera.getMovementBounds().getX()),
//                (int) camera.getScreenOffsetY(camera.getMovementBounds().getY()),
//                (int) camera.getMovementBounds().getWidth(),
//                (int) camera.getMovementBounds().getHeight()
//        );

        // Draw console output
        Console.draw(g);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            humanControls.setButtonRight(true);
        } else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            humanControls.setButtonLeft(true);
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
            humanControls.setButtonUp(true);
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            humanControls.setButtonDown(true);
        } else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            humanControls.setButtonJump(true);
        } else if (KeyEvent.VK_C == e.getKeyCode()) {
            humanControls.setButtonPowerKick(true);
        } else if (KeyEvent.VK_X == e.getKeyCode()) {
            humanControls.setButtonKick(true);
        } else if (KeyEvent.VK_Z == e.getKeyCode()) {
            humanControls.setButtonPass(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            humanControls.setButtonRight(false);
        } else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            humanControls.setButtonLeft(false);
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
            humanControls.setButtonUp(false);
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            humanControls.setButtonDown(false);
        } else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            humanControls.setButtonJump(false);
        } else if (KeyEvent.VK_C == e.getKeyCode()) {
            humanControls.setButtonPowerKick(false);
        } else if (KeyEvent.VK_X == e.getKeyCode()) {
            humanControls.setButtonKick(false);
        } else if (KeyEvent.VK_Z == e.getKeyCode()) {
            humanControls.setButtonPass(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    public void updateBall(Ball ball, Ground ground, List<OrthogonalPolygon> geometryList) {
        double nextCenterX;
        double nextCenterY;
        double nextCenterZ = -99999;

        if (ball.getControlledBy() != null) {
            // Ball is moved by player
            double targetCenterX = ball.getControlledBy().getCenterX();
            double targetCenterY = ball.getControlledBy().getCenterY();

            if (FaceDirection.RIGHT.equals(ball.getControlledBy().getFaceDirection())) {
                targetCenterX = targetCenterX + ball.getSphere().getRadius();
            } else if (FaceDirection.LEFT.equals(ball.getControlledBy().getFaceDirection())) {
                targetCenterX = targetCenterX - ball.getSphere().getRadius();
            } else {
                throw new RuntimeException("Unknown face direction: " + ball.getControlledBy().getFaceDirection());
            }

            Vector2D targetVector = MathUtils.getVector2D(ball.getSphere().getX(), ball.getSphere().getY(), targetCenterX, targetCenterY);
            if (targetVector.getX() == 0 && targetVector.getY() == 0) {
                return;
            }
            Vector2D targetVectorNormalized = MathUtils.getVector2DNormalized(targetVector);

            // 2.8 is a speed of ball moving to the target position
            nextCenterX = ball.getSphere().getX() + targetVectorNormalized.getX() * 2.8d;
            nextCenterY = ball.getSphere().getY() + targetVectorNormalized.getY() * 2.8d;

            double distance = MathUtils.getDistance2D(targetCenterX, targetCenterY, nextCenterX, nextCenterY);
            if (distance <= 1) {
                nextCenterX = targetCenterX;
                nextCenterY = targetCenterY;
            }

        } else if (ball.getMovementSpeed() > 0) {
            // Ball is moved by its own
            nextCenterX = ball.getSphere().getX() + ball.getMovementVector().getX() * ball.getMovementSpeed();
            nextCenterY = ball.getSphere().getY() + ball.getMovementVector().getY() * ball.getMovementSpeed();
            nextCenterZ = ball.getSphere().getZ() + ball.getMovementVector().getZ() * ball.getMovementSpeed();

        } else {
            // No ball movement
            Console.addMessage("Ball center(" + ball.getCenterX() + "," + ball.getCenterY() + "," + ball.getCenterZ() + ") - no movement");
            return;
        }

        // Check for the left side bounds
        if (nextCenterX < ground.getX()) {
            nextCenterX = ground.getX();
            double nextVectorX = -ball.getMovementVector().getX();
            ball.getMovementVector().setX(nextVectorX);
        }
        // Check for the right side bounds
        if (nextCenterX > ground.getX() + ground.getWidth()) {
            nextCenterX = ground.getX() + ground.getWidth();
            double nextVectorX = -ball.getMovementVector().getX();
            ball.getMovementVector().setX(nextVectorX);
        }

        // Check for the far side bounds
        if (nextCenterY < ground.getY()) {
            nextCenterY = ground.getY();
            double nextVectorY = -ball.getMovementVector().getY();
            ball.getMovementVector().setY(nextVectorY);
        }
        // Check for the near side bounds
        if (nextCenterY > ground.getY() + ground.getHeight()) {
            nextCenterY = ground.getY() + ground.getHeight();
            double nextVectorY = -ball.getMovementVector().getY();
            ball.getMovementVector().setY(nextVectorY);
        }

        // Check for the ceiling side bounds
        if (nextCenterZ > ground.getMaxHeight()) {
            nextCenterZ = ground.getMaxHeight();
            double nextVectorZ = -ball.getMovementVector().getZ();
            ball.getMovementVector().setZ(nextVectorZ);
        }
        // Check for the floor side bounds
        if (nextCenterZ - ball.getSphere().getRadius() < 0) {
            nextCenterZ = ball.getSphere().getRadius();
            double nextSpeed = ball.getMovementSpeed() - ball.getMovementSpeed() / 1.85;
            if (nextSpeed < 0.8) {
                nextSpeed = 0;
            }
            ball.setMovementSpeed(nextSpeed);

            double nextVectorZ = -ball.getMovementVector().getZ();
            ball.getMovementVector().setZ(nextVectorZ);
        }

        // Calculate ball sprite rotation
        double ballDistance = ball.getBallDistance() + MathUtils.getDistance2D(ball.getSphere().getX(), ball.getSphere().getY(), nextCenterX, nextCenterY);
        if (ballDistance >= ball.getBallDistanceForSpin()) {
            ballDistance = ballDistance - ball.getBallDistanceForSpin();
            if (ball.getMovementVector().getX() >= 0) {
                ball.setNextFrame();
            } else {
                ball.setPreviousFrame();
            }
        }
        ball.setBallDistance(ballDistance);

        ball.setCenterX(nextCenterX);
        ball.setCenterY(nextCenterY);
        ball.setCenterZ(nextCenterZ);

        // Apply gravity
        if (ball.getSphere().getZ() - ball.getSphere().getRadius() > 0) {
            double vectorZ = ball.getMovementVector().getZ() - ground.getGravity();
            ball.getMovementVector().setZ(vectorZ);
        } else if (ball.getSphere().getZ() - ball.getSphere().getRadius() == 0) {
            // Apply friction
            double speed = ball.getMovementSpeed() - ground.getFriction();
            if (speed < 0) {
                speed = 0;
                ball.getMovementVector().setX(0);
                ball.getMovementVector().setY(0);
                ball.getMovementVector().setZ(0);
            }
            ball.setMovementSpeed(speed);
        }

        // Check collisions
        for (OrthogonalPolygon polygon : geometryList) {
            if (CollisionUtils.intersects(ball.getSphere(), polygon)) {
                Vector3D reflectedVector = ReflectionUtils.reflectVector(ball.getMovementVector(), ball.getSphere(), polygon);
                ball.setMovementVector(reflectedVector);
                break; // ?
            }
        }

        Console.addMessage("Ball center(" + ball.getCenterX() + "," + ball.getCenterY() + "," + ball.getCenterZ() + ") Sphere center(" + ball.getSphere().getX() + "," + ball.getSphere().getY() + "," + ball.getSphere().getZ() + ")");
    }

    private void updateGoalKeeper(GoalKeeper goalKeeper, Controls aiControls, Ball ball) {
    }

    public void updateCamera(VirtualCamera camera, VirtualRectangle cameraMovementBounds, VirtualObject target) {
        // Check if we need to adjust camera position according to target position
        if (camera.getMovementBounds().getX() > target.getCenterX()) {
            double delta = target.getCenterX() - camera.getMovementBounds().getX();
            camera.addToX(delta);
        }
        if (camera.getMovementBounds().getX() + camera.getMovementBounds().getWidth() < target.getCenterX()) {
            double delta = target.getCenterX() - camera.getMovementBounds().getX() - camera.getMovementBounds().getWidth();
            camera.addToX(delta);
        }
        if (camera.getMovementBounds().getY() > target.getCenterY()) {
            double delta = target.getCenterY() - camera.getMovementBounds().getY();
            camera.addToY(delta);
        }
        if (camera.getMovementBounds().getY() + camera.getMovementBounds().getHeight() < target.getCenterY()) {
            double delta = target.getCenterY() - camera.getMovementBounds().getY() - camera.getMovementBounds().getHeight();
            camera.addToY(delta);
        }

        // Check if camera is out of world bounds
        if (camera.getX() < cameraMovementBounds.getX()) {
            camera.setX(cameraMovementBounds.getX());
        } else if (camera.getX() + camera.getWidth() >= cameraMovementBounds.getX() + cameraMovementBounds.getWidth()) {
            camera.setX(cameraMovementBounds.getX() + cameraMovementBounds.getWidth() - camera.getWidth() - 1);
        }
        if (camera.getY() < cameraMovementBounds.getY()) {
            camera.setY(cameraMovementBounds.getY());
        } else if (camera.getY() + camera.getHeight() >= cameraMovementBounds.getY() + cameraMovementBounds.getHeight()) {
            camera.setY(cameraMovementBounds.getY() + cameraMovementBounds.getHeight() - camera.getHeight() - 1);
        }
    }
}
