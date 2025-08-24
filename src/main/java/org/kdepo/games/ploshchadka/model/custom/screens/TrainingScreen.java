package org.kdepo.games.ploshchadka.model.custom.screens;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.Ploshchadka;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector2D;
import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;
import org.kdepo.games.ploshchadka.model.base.screens.AbstractScreen;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.model.custom.Ball;
import org.kdepo.games.ploshchadka.model.custom.Ground;
import org.kdepo.games.ploshchadka.model.custom.Player;
import org.kdepo.games.ploshchadka.utils.MathUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Random;

public class TrainingScreen extends AbstractScreen {

    private final Ploshchadka ploshchadka;
    private final Random random;

    private final VirtualCamera camera;
    private final VirtualRectangle screenMovementBounds;

    private final Ball ball;
    private double ballDistance;
    private double ballDistanceForSpin;

    private Ground ground;
    private Player player;

    private double friction;

    public TrainingScreen(Ploshchadka ploshchadka) {
        this.ploshchadka = ploshchadka;
        random = new Random(new Date().getTime());

        ball = new Ball();
        ballDistance = 0;
        ballDistanceForSpin = 0;

        friction = 0.02d;

        ground = new Ground();
        player = new Player();

        camera = new VirtualCamera(Constants.ScreenSize.WIDTH, Constants.ScreenSize.HEIGHT);
        camera.setCameraCenter(0.0d, 0.0d);

        screenMovementBounds = new VirtualRectangle();
        screenMovementBounds.setX(ground.getX());
        screenMovementBounds.setY(ground.getY());
        screenMovementBounds.setWidth(ground.getWidth());
        screenMovementBounds.setHeight(ground.getHeight());
    }

    @Override
    public void update() {
        // Clear all previous messages
        Console.clear();

        if (ball.getSpeed() > 0) {
            double nextX;
            double nextY;

            nextX = ball.getCenterX() + ball.getVectorX() * ball.getSpeed();
            nextY = ball.getCenterY() + ball.getVectorY() * ball.getSpeed();

            if (nextX < 0) {
                nextX = 0;
                double vectorX = ball.getVectorX();
                vectorX = -vectorX;
                ball.setVectorX(vectorX);
            }
            if (nextX > ploshchadka.getScreenWidth()) {
                nextX = ploshchadka.getScreenWidth();
                double vectorX = ball.getVectorX();
                vectorX = -vectorX;
                ball.setVectorX(vectorX);
            }

            if (nextY < 0) {
                nextY = 0;
                double vectorY = ball.getVectorY();
                vectorY = -vectorY;
                ball.setVectorY(vectorY);
            }
            if (nextY > ploshchadka.getScreenHeight()) {
                nextY = ploshchadka.getScreenHeight();
                double vectorY = ball.getVectorY();
                vectorY = -vectorY;
                ball.setVectorY(vectorY);
            }

            ballDistance = ballDistance + MathUtils.getDistance2D(ball.getCenterX(), ball.getCenterY(), nextX, nextY);
            if (ballDistance >= ballDistanceForSpin) {
                ballDistance = ballDistance - ballDistanceForSpin;
                if (ball.getVectorX() >= 0) {
                    ball.setNextFrame();
                } else {
                    ball.setPreviousFrame();
                }
            }

            ball.setCenterX(nextX);
            ball.setCenterY(nextY);

            // Apply friction
            double speed = ball.getSpeed() - friction;
            if (speed < 0) {
                speed = 0;
            }
            //System.out.println("Ball speed: " + speed);
            ball.setSpeed(speed);
        }

        player.animate();
        Console.addMessage("Player center(" + player.getCenterX() + "," + player.getCenterY() + "," + player.getCenterZ() + ")");

        // Check if need to adjust camera position according to player position
        if (camera.getMovementBounds().getX() > player.getCenterX()) {
            double delta = camera.getMovementBounds().getX() - player.getCenterX();
            camera.setX(camera.getX() - delta);
        }
        if (camera.getMovementBounds().getX() + camera.getMovementBounds().getWidth() < player.getCenterX()) {
            double delta = camera.getMovementBounds().getX() + camera.getMovementBounds().getWidth() - player.getCenterX();
            camera.setX(camera.getX() - delta);
        }
        if (camera.getMovementBounds().getY() > player.getCenterY()) {
            double delta = camera.getMovementBounds().getY() - player.getCenterY();
            camera.setY(camera.getY() - delta);
        }
        if (camera.getMovementBounds().getY() + camera.getMovementBounds().getHeight() < player.getCenterY()) {
            double delta = camera.getMovementBounds().getY() + camera.getMovementBounds().getHeight() - player.getCenterY();
            camera.setY(camera.getY() - delta);
        }

        // Check if camera is out of bounds
        if (camera.getX() < screenMovementBounds.getX()) {
            camera.setX(screenMovementBounds.getX());
        } else if (camera.getX() + camera.getWidth() > screenMovementBounds.getX() + screenMovementBounds.getWidth()) {
            camera.setX(screenMovementBounds.getX() + screenMovementBounds.getWidth() - camera.getWidth());
        }
        if (camera.getY() < screenMovementBounds.getY()) {
            camera.setY(screenMovementBounds.getY());
        } else if (camera.getY() + camera.getHeight() > screenMovementBounds.getY() + screenMovementBounds.getHeight()) {
            camera.setY(screenMovementBounds.getY() + screenMovementBounds.getHeight() - camera.getHeight());
        }

        // Output debug information
        Console.addMessage("Camera: x=" + camera.getX() + ", y=" + camera.getY() + ", width=" + camera.getWidth() + ", height=" + camera.getHeight());
        Console.addMessage("TileMap y=" + ground.getY());
    }

    @Override
    public void draw(Graphics g) {
        // Clear all with selected color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ploshchadka.getScreenWidth(), ploshchadka.getScreenHeight());

        // Draw ground tiled image
        ground.draw(g, camera);

        // Draw ball on the ground
        ball.draw(g, camera);

        player.draw(g, camera);

        // For debug purposes
//        g.setColor(Color.WHITE);
//        g.drawLine(0, 0, Constants.ScreenSize.WIDTH, Constants.ScreenSize.HEIGHT);
//        g.drawLine(0, Constants.ScreenSize.HEIGHT, Constants.ScreenSize.WIDTH, 0);

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
        int clickPositionX = e.getX();
        int clickPositionY = e.getY();
        System.out.println("Click at (" + clickPositionX + "," + clickPositionY + ")");

        Vector2D vector2D = MathUtils.getVector2D(ball.getCenterX(), ball.getCenterY(), clickPositionX, clickPositionY);
        System.out.println("Vector is (" + vector2D.getX() + "," + vector2D.getY() + ")");

        Vector2D vector2DNormalized = MathUtils.getVector2DNormalized(vector2D.getX(), vector2D.getY());
        System.out.println("Normal as (" + vector2DNormalized.getX() + "," + vector2DNormalized.getY() + ")");

        ball.setVectorX(vector2DNormalized.getX());
        ball.setVectorY(vector2DNormalized.getY());

        double distance = MathUtils.getDistance2D(clickPositionX, clickPositionY, ball.getCenterX(), ball.getCenterY());
        System.out.println("Distance: " + distance);
        double ballSpeed = distance / 100.0;
        ball.setSpeed(ballSpeed);

        ballDistance = 0;
        //ballDistanceForSpin = random.nextDouble(50);
        ballDistanceForSpin = 12.0d;
        System.out.println("Spin distance: " + ballDistanceForSpin);
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
        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            camera.setX((int) (camera.getX() - 1));
        } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            camera.setX((int) (camera.getX() + 1));
        } else if (KeyEvent.VK_UP == e.getKeyCode()) {
            camera.setY((int) (camera.getY() - 1));
        } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            camera.setY((int) (camera.getY() + 1));
        }

        if (KeyEvent.VK_D == e.getKeyCode()) {
            // Move right
            if (Constants.AnimationName.STAND_RIGHT.equals(player.getCurrentAnimationName())
                    || Constants.AnimationName.STAND_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_RIGHT);
            } else if (Constants.AnimationName.RUN_RIGHT.equals(player.getCurrentAnimationName())) {

            }

            double nextCenterX = player.getCenterX() + player.getRunSpeed();
            player.setCenterX(nextCenterX);

        } else if (KeyEvent.VK_A == e.getKeyCode()) {
            // Move Left
            if (Constants.AnimationName.STAND_LEFT.equals(player.getCurrentAnimationName())
                    || Constants.AnimationName.STAND_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_LEFT);
            } else if (Constants.AnimationName.RUN_LEFT.equals(player.getCurrentAnimationName())) {

            }

            double nextCenterX = player.getCenterX() - player.getRunSpeed();
            player.setCenterX(nextCenterX);

        } else if (KeyEvent.VK_W == e.getKeyCode()) {
            // Move Up
            if (Constants.AnimationName.STAND_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_LEFT);
            } else if (Constants.AnimationName.STAND_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_RIGHT);
            }

            double nextCenterY = player.getCenterY() - player.getRunSpeed();
            player.setCenterY(nextCenterY);

        } else if (KeyEvent.VK_S == e.getKeyCode()) {
            // Move Up
            if (Constants.AnimationName.STAND_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_LEFT);
            } else if (Constants.AnimationName.STAND_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.RUN_RIGHT);
            }

            double nextCenterY = player.getCenterY() + player.getRunSpeed();
            player.setCenterY(nextCenterY);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_D == e.getKeyCode()) {
            if (Constants.AnimationName.RUN_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_RIGHT);
            }
        } else if (KeyEvent.VK_A == e.getKeyCode()) {
            if (Constants.AnimationName.RUN_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_LEFT);
            }
        } else if (KeyEvent.VK_W == e.getKeyCode()) {
            if (Constants.AnimationName.RUN_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_LEFT);
            } else if (Constants.AnimationName.RUN_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_RIGHT);
            }
        } else if (KeyEvent.VK_S == e.getKeyCode()) {
            if (Constants.AnimationName.RUN_LEFT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_LEFT);
            } else if (Constants.AnimationName.RUN_RIGHT.equals(player.getCurrentAnimationName())) {
                player.setCurrentAnimationByName(Constants.AnimationName.STAND_RIGHT);
            }
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
}
