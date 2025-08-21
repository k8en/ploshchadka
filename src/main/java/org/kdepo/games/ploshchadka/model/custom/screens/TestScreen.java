package org.kdepo.games.ploshchadka.model.custom.screens;

import org.kdepo.games.ploshchadka.model.base.screens.AbstractScreen;
import org.kdepo.games.ploshchadka.model.custom.Ball;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestScreen extends AbstractScreen {

    private final Random random;

    private final List<Ball> ballList;

    public TestScreen() {
        random = new Random(new Date().getTime());
        ballList = new ArrayList<>();
    }

    @Override
    public void update() {
        double nextX;
        double nextY;
        for (Ball ball : ballList) {
            nextX = ball.getCenterX() + ball.getVectorX() * ball.getSpeed();
            nextY = ball.getCenterY() + ball.getVectorY() * ball.getSpeed();

            if (nextX < 0) {
                nextX = 0;
                double vectorX = ball.getVectorX();
                vectorX = -vectorX;
                ball.setVectorX(vectorX);
            }
            if (nextX > 1024) {
                nextX = 1024;
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
            if (nextY > 960) {
                nextY = 960;
                double vectorY = ball.getVectorY();
                vectorY = -vectorY;
                ball.setVectorY(vectorY);
            }

            ball.setCenterX(nextX);
            ball.setCenterY(nextY);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1024, 960);

        g.setColor(Color.WHITE);
        for (Ball ball : ballList) {
            g.drawOval(
                    (int) ball.getCenterX(),
                    (int) ball.getCenterY(),
                    ball.getRadius(),
                    ball.getRadius()
            );
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Ball ball = new Ball();

        int radius = random.nextInt(20);

        ball.setCenterX(e.getX());
        ball.setCenterY(e.getY());
        ball.setRadius(radius);
        ball.setSpeed(radius);

        int direction = random.nextInt(360);
        ball.setVectorX(Math.cos(direction));
        ball.setVectorY(Math.sin(direction));

        ball.setSpeed(random.nextDouble());

        ballList.add(ball);
        System.out.println("Clicked");
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

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
