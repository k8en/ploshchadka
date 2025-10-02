package org.kdepo.games.ploshchadka.model.custom.screens;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.Ploshchadka;
import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationPlayMode;
import org.kdepo.games.ploshchadka.model.base.geometry.*;
import org.kdepo.games.ploshchadka.model.base.screens.AbstractScreen;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.model.custom.*;
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

    private Player player;

    private boolean isButtonUp;
    private boolean isButtonRight;
    private boolean isButtonDown;
    private boolean isButtonLeft;
    private boolean isButtonKick;
    private boolean isButtonPowerKick;

    // List of objects to sort and render
    private final List<DrawableObject> renderList;

    // List of objects to check for collision with ball
    private final List<OrthogonalPolygon> geometryList;

    public TrainingScreen(Ploshchadka ploshchadka) {
        this.ploshchadka = ploshchadka;

        ball = new Ball(0, 0, 14, 14);

        ground = new Ground();

        // Prepare goalpost (right) rendering parameters
        goalpostRight1 = new Goalpost(965, -69);
        crossbarSegmentRight1 = new CrossbarSegment(974, -48, CrossbarSegment.SEGMENT_TOP);
        crossbarSegmentRight2a = new CrossbarSegment(974, -24, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight2b = new CrossbarSegment(974, 0, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight2c = new CrossbarSegment(974, 24, CrossbarSegment.SEGMENT_MIDDLE);
        crossbarSegmentRight3 = new CrossbarSegment(974, 48, CrossbarSegment.SEGMENT_BOTTOM);
        goalpostRight2 = new Goalpost(965, 49);

        // Prepare goalpost (right) geometry parameters
        Point3D a1 = new Point3D(goalpostRight1.getX(), -60, goalpostRight1.getHeight());
        Point3D b1 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), -60, goalpostRight1.getHeight());
        Point3D c1 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), -60, 0);
        Point3D d1 = new Point3D(goalpostRight1.getX(), -60, 0);
        Point3D a2 = new Point3D(goalpostRight1.getX(), 60, goalpostRight1.getHeight());
        Point3D b2 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), 60, goalpostRight1.getHeight());
        Point3D c2 = new Point3D(goalpostRight1.getX() + goalpostRight1.getWidth(), 60, 0);
        Point3D d2 = new Point3D(goalpostRight1.getX(), 60, 0);

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

        player = new Player();

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

        isButtonUp = false;
        isButtonRight = false;
        isButtonDown = false;
        isButtonLeft = false;
        isButtonKick = false;
        isButtonPowerKick = false;

        renderList = new ArrayList<>();
    }

    @Override
    public void update() {
        // Clear all previous messages
        Console.clear();

        updateBall(ball, ground, geometryList);

        updatePlayer(player, ball);

        updateCamera(camera, screenMovementBounds, ball);

        // Prepare render list ordered by Y coordinate
        renderList.clear();
        renderList.add(player);
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
            isButtonRight = true;
        } else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            isButtonLeft = true;
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
            isButtonUp = true;
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            isButtonDown = true;
        } else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            isButtonKick = true;
        } else if (KeyEvent.VK_C == e.getKeyCode()) {
            isButtonPowerKick = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            isButtonRight = false;
        } else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            isButtonLeft = false;
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
            isButtonUp = false;
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            isButtonDown = false;
        } else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            isButtonKick = false;
        } else if (KeyEvent.VK_C == e.getKeyCode()) {
            isButtonPowerKick = false;
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
        // Update ball state
        if (ball.getMovementSpeed() > 0) {
            double nextCenterX;
            double nextCenterY;
            double nextCenterZ;

            nextCenterX = ball.getSphere().getX() + ball.getMovementVector().getX() * ball.getMovementSpeed();
            nextCenterY = ball.getSphere().getY() + ball.getMovementVector().getY() * ball.getMovementSpeed();
            nextCenterZ = ball.getSphere().getZ() + ball.getMovementVector().getZ() * ball.getMovementSpeed();

            // Check for the left side
            if (nextCenterX < ground.getX()) {
                nextCenterX = ground.getX();
                double nextVectorX = -ball.getMovementVector().getX();
                ball.getMovementVector().setX(nextVectorX);
            }
            // Check for the right side
            if (nextCenterX > ground.getX() + ground.getWidth()) {
                nextCenterX = ground.getX() + ground.getWidth();
                double nextVectorX = -ball.getMovementVector().getX();
                ball.getMovementVector().setX(nextVectorX);
            }

            // Check for the far side
            if (nextCenterY < ground.getY()) {
                nextCenterY = ground.getY();
                double nextVectorY = -ball.getMovementVector().getY();
                ball.getMovementVector().setY(nextVectorY);
            }
            // Check for the near side
            if (nextCenterY > ground.getY() + ground.getHeight()) {
                nextCenterY = ground.getY() + ground.getHeight();
                double nextVectorY = -ball.getMovementVector().getY();
                ball.getMovementVector().setY(nextVectorY);
            }

            // Check for the ceiling side
            if (nextCenterZ > ground.getMaxHeight()) {
                nextCenterZ = ground.getMaxHeight();
                double nextVectorZ = -ball.getMovementVector().getZ();
                ball.getMovementVector().setZ(nextVectorZ);
            }
            // Check for the floor side
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

            double ballDistance = ball.getBallDistance() + MathUtils.getDistance2D(ball.getSphere().getX(), ball.getSphere().getY(), nextCenterX, nextCenterY);
            if (ballDistance >= ball.getBallDistanceForSpin()) {
                ballDistance = ballDistance - ball.getBallDistanceForSpin();
                ball.setBallDistance(ballDistance);
                if (ball.getMovementVector().getX() >= 0) {
                    ball.setNextFrame();
                } else {
                    ball.setPreviousFrame();
                }
            }

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

            if (ball.getControlledBy() != null && ball.getSphere().getZ() > ball.getSphere().getRadius()) {
                ball.setControlledBy(null);
            }

            // Check collisions
            for (OrthogonalPolygon polygon : geometryList) {
                if (CollisionUtils.intersects(ball.getSphere(), polygon)) {
                    Vector3D reflectedVector = ReflectionUtils.reflectVector(ball.getMovementVector(), ball.getSphere(), polygon);
                    ball.setMovementVector(reflectedVector);
                    break; // ?
                }
            }
        }

        //Console.addMessage(ball.getDebugInfo());
    }

    public void updatePlayer(Player player, Ball ball) {
        if (PlayerState.STAND.equals(player.getPlayerState())) {
            if (isButtonKick) {
                // Ball KICK intent while STAND
                if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                    // Player is controlling the ball
                    if (player.isReadyToKick()) {
                        // Player is ready to kick the ball
                        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);

                        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);

                        } else {
                            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                        }

                        player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                        player.getAnimationsController().setPlayCompleted(false);
                        player.getAnimationsController().resetCurrentFrameNumber();
                        player.getAnimationsController().setTicksPassed(0);

                        player.setPlayerState(PlayerState.KICK);
                        player.startKick();

                        // Hit the ball
                        if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                            if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {

                            } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {

                            } else {
                                throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                            }
                            ball.getMovementVector().setX(0);
                            ball.getMovementVector().setY(0);
                            ball.getMovementVector().setZ(1);
                            ball.setMovementSpeed(2);
                        }
                    }

                } else {
                    // Player is not controlling the ball

                }

            } else if (isButtonPowerKick) {
                // Ball POWER KICK intent while STAND
                if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                    // Player is controlling the ball
                    if (player.isReadyToKick()) {
                        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_RIGHT);

                        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_LEFT);

                        } else {
                            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                        }

                        player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                        player.getAnimationsController().setPlayCompleted(false);
                        player.getAnimationsController().resetCurrentFrameNumber();
                        player.getAnimationsController().setTicksPassed(0);

                        player.setPlayerState(PlayerState.POWER_KICK);
                    }

                } else {
                    // Player is not controlling the ball

                }

            } else if (isButtonUp && !isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move UP intent while STAND
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setPlayerState(PlayerState.RUN);

                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (isButtonUp && isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move UP + RIGHT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.RIGHT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move RIGHT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.RIGHT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                checkBallControl(player, ball);

            } else if (!isButtonUp && isButtonRight && isButtonDown && !isButtonLeft) {
                // Move DOWN + RIGHT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.RIGHT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && isButtonDown && !isButtonLeft) {
                // Move DOWN intent while STAND
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setPlayerState(PlayerState.RUN);

                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && isButtonDown && isButtonLeft) {
                // Move DOWN + LEFT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.LEFT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && !isButtonDown && isButtonLeft) {
                // Move LEFT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.LEFT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                checkBallControl(player, ball);

            } else if (isButtonUp && !isButtonRight && !isButtonDown && isButtonLeft) {
                // Move UP + LEFT intent while STAND
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);
                player.setFaceDirection(FaceDirection.LEFT);
                player.setPlayerState(PlayerState.RUN);

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else {
                checkBallControl(player, ball);
            }

        } else if (PlayerState.RUN.equals(player.getPlayerState())) {
            if (isButtonKick) {
                // Ball KICK intent while RUN
                if (player.isReadyToKick()) {
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);

                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);

                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }

                    player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                    player.getAnimationsController().setPlayCompleted(false);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);

                    player.setPlayerState(PlayerState.KICK);
                    player.startKick();

                    // Hit the ball
                    if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                            ball.getMovementVector().setX(0.7);

                        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                            ball.getMovementVector().setX(-0.7);

                        } else {
                            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                        }

                        ball.getMovementVector().setY(0);
                        ball.getMovementVector().setZ(0.7);
                        ball.setMovementSpeed(6);
                    }
                }

            } else if (isButtonPowerKick) {
                // Ball POWER KICK intent while RUN
                if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                    // Player is controlling the ball
                    if (player.isReadyToKick()) {
                        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_RIGHT);

                        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_LEFT);

                        } else {
                            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                        }

                        player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                        player.getAnimationsController().setPlayCompleted(false);
                        player.getAnimationsController().resetCurrentFrameNumber();
                        player.getAnimationsController().setTicksPassed(0);

                        player.setPlayerState(PlayerState.POWER_KICK);
                    }

                } else {
                    // Player is not controlling the ball

                }

            } else if (isButtonUp && !isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move UP intent while RUN
                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (isButtonUp && isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move UP + RIGHT intent while RUN
                if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.RIGHT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && isButtonRight && !isButtonDown && !isButtonLeft) {
                // Move RIGHT intent while RUN
                if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.RIGHT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                checkBallControl(player, ball);

            } else if (!isButtonUp && isButtonRight && isButtonDown && !isButtonLeft) {
                // Move DOWN + RIGHT intent while RUN
                if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.RIGHT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() + player.getRunSpeed();
                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && isButtonDown && !isButtonLeft) {
                // Move DOWN intent while RUN
                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && isButtonDown && isButtonLeft) {
                // Move DOWN + LEFT intent while RUN
                if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.LEFT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                double nextCenterY = player.getCenterY() + player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else if (!isButtonUp && !isButtonRight && !isButtonDown && isButtonLeft) {
                // Move LEFT intent while RUN
                if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.LEFT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                checkBallControl(player, ball);

            } else if (isButtonUp && !isButtonRight && !isButtonDown && isButtonLeft) {
                // Move UP + LEFT intent while RUN
                if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.setFaceDirection(FaceDirection.LEFT);
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);
                }

                double nextCenterX = player.getCenterX() - player.getRunSpeed();
                double nextCenterY = player.getCenterY() - player.getRunSpeed();
                player.setCenterX(nextCenterX);
                player.setCenterY(nextCenterY);
                checkBallControl(player, ball);

            } else {
                // Switch to STAND for other cases
                if (Constants.AnimationName.RUN_LEFT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);

                } else if (Constants.AnimationName.RUN_RIGHT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setPlayerState(PlayerState.STAND);
                checkBallControl(player, ball);

            }

            // Adjust height for running
            if (Constants.AnimationName.RUN_LEFT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())
                    || Constants.AnimationName.RUN_RIGHT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
                int frameNumber = player.getAnimationsController().getCurrentFrameNumber();
                if (frameNumber == 0) {
                    player.setCenterZ(2);
                } else {
                    player.setCenterZ(0);
                }
            } else {
                player.setCenterZ(0);
            }

        } else if (PlayerState.KICK.equals(player.getPlayerState())) {
            // Process KICK state
            // Check if animation is completed - switch to the next animation
            if (player.getAnimationsController().isPlayCompleted()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setPlayerState(PlayerState.STAND);
            }

        } else if (PlayerState.POWER_KICK.equals(player.getPlayerState())) {
            // Process POWER_KICK state
            // Check if animation is completed - switch to the next animation
            if (player.getAnimationsController().isPlayCompleted()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setPlayerState(PlayerState.STAND);

                System.out.println("Completed");

            } else {
                // Animation is not completed
                int frameNumber = player.getAnimationsController().getCurrentFrameNumber();
                if (frameNumber == 2) {
                    // Hit the ball with the last frame
                    System.out.println("Power kick on ball");
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                        ball.getMovementVector().setX(0.7);
                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                        ball.getMovementVector().setX(-0.7);
                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }
                    ball.getMovementVector().setY(0);
                    ball.getMovementVector().setZ(0.7);
                    ball.setMovementSpeed(8);
                    ball.setControlledBy(null);

                    player.startKick();
                }

                System.out.println("Not completed");

            }
        }

        // Update frame ticks
        player.animate();

        // Update internal parameters
        player.update();

        Console.addMessage("Player center(" + player.getCenterX() + "," + player.getCenterY() + "," + player.getCenterZ() + ") state=" + player.getPlayerState());
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

    private void checkBallControl(Player player, Ball ball) {
        // Get distance between player and ball
        double distanceToBall = MathUtils.getDistance2D(player.getCenterX(), player.getCenterY(), ball.getCenterX(), ball.getCenterY());

        if (ball.getControlledBy() == null) {
            // If ball is not controlled by any player
            if (distanceToBall <= player.getRadius() + ball.getSphere().getRadius()) {
                // If distance between player and ball is enough to acquire control
                ball.setControlledBy(player);
            }
        } else if (player.getId() == ball.getControlledBy().getId()) {
            // If ball is controlled by player
            if (distanceToBall > player.getRadius() + ball.getSphere().getRadius()) {
                // If ball is too far from player - need to adjust distance
                Vector2D vector2D = MathUtils.getVector2D(ball.getCenterX(), ball.getCenterY(), player.getCenterX(), player.getCenterY());
                Vector2D vector2DNormalized = MathUtils.getVector2DNormalized(vector2D.getX(), vector2D.getY());
                ball.getMovementVector().setX(vector2DNormalized.getX());
                ball.getMovementVector().setY(vector2DNormalized.getY());
                ball.getMovementVector().setZ(0);
                ball.setMovementSpeed(3d);
            }
        }
    }
}
