package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {

    private double centerX;
    private double centerY;
    private int radius;
    private double vectorX;
    private double vectorY;
    private double speed;

    private final AnimationFrame[] animationFrames;
    private int currentFrameNumber;

    public Ball() {
        // Render parameters
        BufferedImage ballSprites = FileUtils.loadImage("ball.png");

        int framesTotal = 6;
        int frameWidth = 28;
        int frameHeight = 28;
        animationFrames = new AnimationFrame[framesTotal];
        for (int frameNumber = 0; frameNumber < framesTotal; frameNumber++) {
            BufferedImage frameImage = ballSprites.getSubimage(frameWidth * frameNumber, 0, frameWidth, frameHeight);
            AnimationFrame animationFrame = new AnimationFrame();
            animationFrame.setFrameImage(frameImage);
            animationFrame.setFrameNumber(frameNumber);
            animationFrame.setTicksToDisplay(1);
            animationFrames[frameNumber] = animationFrame;
        }
        currentFrameNumber = 0;

        // Position parameters
        centerX = 0;
        centerY = 0;
        radius = 14;

        // Movement parameters
        vectorX = 0.0d;
        vectorY = 0.0d;
        speed = 0.0d;

        System.out.println("Ball initialized with center at (" + centerX + "," + centerY + ")");
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getVectorX() {
        return vectorX;
    }

    public void setVectorX(double vectorX) {
        this.vectorX = vectorX;
    }

    public double getVectorY() {
        return vectorY;
    }

    public void setVectorY(double vectorY) {
        this.vectorY = vectorY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setNextFrame() {
        currentFrameNumber = currentFrameNumber + 1;
        if (currentFrameNumber >= animationFrames.length) {
            currentFrameNumber = 0;
        }
    }

    public void setPreviousFrame() {
        currentFrameNumber = currentFrameNumber - 1;
        if (currentFrameNumber < 0) {
            currentFrameNumber = animationFrames.length - 1;
        }
    }

    public void draw(Graphics g, VirtualCamera camera) {
        int onScreenX = (int) camera.getScreenOffsetX(centerX - radius);
        int onScreenY = (int) camera.getScreenOffsetY(centerY - radius);

        g.drawImage(
                animationFrames[currentFrameNumber].getFrameImage(),
                onScreenX, onScreenY,
                null
        );
    }
}
