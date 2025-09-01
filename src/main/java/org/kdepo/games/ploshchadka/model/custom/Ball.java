package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends VirtualObject {

    private int radius;
    private double vectorX;
    private double vectorY;
    private double vectorZ;
    private double speed;

    private final AnimationFrame[] animationFrames;
    private int currentFrameNumber;

    private final BufferedImage shadowImage;

    public Ball() {
        // Rendering parameters
        BufferedImage ballSprites = FileUtils.loadImage("ball.png");
        shadowImage = FileUtils.loadImage("shadow.png");

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

        // Virtual position parameters
        this.centerX = 0;
        this.centerY = 0;
        this.centerZ = 0;
        radius = 14;

        // Sprite position based on virtual position parameters
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
        this.y = centerY - animationFrames[currentFrameNumber].getFrameImage().getHeight() - centerZ;
        this.width = animationFrames[currentFrameNumber].getFrameImage().getWidth();
        this.height = animationFrames[currentFrameNumber].getFrameImage().getHeight();

        // Movement parameters
        vectorX = 0.0d;
        vectorY = 0.0d;
        vectorZ = 0.0d;
        speed = 0.0d;

        System.out.println("Ball initialized with center at (" + centerX + "," + centerY + "," + centerZ + ")");
    }

    @Override
    public void setCenterX(double centerX) {
        this.centerX = centerX;
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
    }

    @Override
    public void setCenterY(double centerY) {
        this.centerY = centerY;
        this.y = centerY - animationFrames[currentFrameNumber].getFrameImage().getHeight() - centerZ;
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

    public double getVectorZ() {
        return vectorZ;
    }

    public void setVectorZ(double vectorZ) {
        this.vectorZ = vectorZ;
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
        double screenOffsetX = camera.getScreenOffsetX(this.x);
        double screenOffsetY = camera.getScreenOffsetY(this.y) - centerZ;

        // Draw shadow if ball in the air
        if (centerZ > 0) {
            double screenOffsetCenterX = camera.getScreenOffsetX(this.centerX) - shadowImage.getWidth() / 2;
            double screenOffsetCenterY = camera.getScreenOffsetY(this.centerY) - shadowImage.getHeight() / 2;
            g.drawImage(
                    shadowImage,
                    (int) screenOffsetCenterX, (int) screenOffsetCenterY,
                    null
            );
        }

        // Draw ball frame
        g.drawImage(
                animationFrames[currentFrameNumber].getFrameImage(),
                (int) screenOffsetX, (int) screenOffsetY,
                null
        );

        // Draw debug info
        if (true) {
            g.setColor(Color.MAGENTA);
            g.drawRect((int) screenOffsetX, (int) screenOffsetY, (int) this.width, (int) this.height);

            double screenOffsetCenterX = camera.getScreenOffsetX(this.centerX);
            double screenOffsetCenterY = camera.getScreenOffsetY(this.centerY);
            g.setColor(Color.RED);
            g.drawLine((int) (screenOffsetCenterX - radius), (int) (screenOffsetCenterY - radius), (int) (screenOffsetCenterX + radius), (int) (screenOffsetCenterY + radius));
            g.drawLine((int) (screenOffsetCenterX - radius), (int) (screenOffsetCenterY + radius), (int) (screenOffsetCenterX + radius), (int) (screenOffsetCenterY - radius));
        }
    }
}
