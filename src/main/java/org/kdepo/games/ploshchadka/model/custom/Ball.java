package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.geometry.Sphere;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Ball extends DrawableObject {

    private final Sphere sphere;

    private Vector3D movementVector;
    private double movementSpeed;

    private final AnimationFrame[] animationFrames;
    private int currentFrameNumber;

    private final BufferedImage shadowImage;

    private Player controlledBy;

    private final StringBuilder debugInfoBuilder;
    private final DecimalFormat decimalFormat;

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
        sphere = new Sphere(this.centerX, this.centerY, 14, 14);

        // Sprite position based on virtual position parameters
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
        this.y = centerY - animationFrames[currentFrameNumber].getFrameImage().getHeight() - centerZ;
        this.width = animationFrames[currentFrameNumber].getFrameImage().getWidth();
        this.height = animationFrames[currentFrameNumber].getFrameImage().getHeight();

        // Movement parameters
        movementVector = new Vector3D(0.0d, 0.0d, 0.0d);
        movementSpeed = 0.0d;

        // For debug purposes
        debugInfoBuilder = new StringBuilder();
        decimalFormat = new DecimalFormat("#0.00");

        System.out.println("Ball initialized with center at (" + centerX + "," + centerY + "," + centerZ + ") " + sphere);
    }

    @Override
    public void setCenterX(double centerX) {
        this.centerX = centerX;
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
        sphere.setX(centerX);
    }

    @Override
    public void setCenterY(double centerY) {
        this.centerY = centerY;
        this.y = centerY - animationFrames[currentFrameNumber].getFrameImage().getHeight() - centerZ;
        sphere.setY(centerY);
    }

    public Sphere getSphere() {
        return sphere;
    }

    public Vector3D getMovementVector() {
        return movementVector;
    }

    public void setMovementVector(Vector3D movementVector) {
        this.movementVector = movementVector;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
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

    public Player getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(Player player) {
        this.controlledBy = player;
    }

    @Override
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
            g.drawOval(
                    (int) (screenOffsetCenterX - sphere.getRadius()),
                    (int) (screenOffsetCenterY - sphere.getRadius()),
                    (int) (sphere.getRadius() * 2),
                    (int) (sphere.getRadius() * 2)
            );
        }
    }

    public String getDebugInfo() {
        debugInfoBuilder.setLength(0);

        debugInfoBuilder.append("Ball ");
        debugInfoBuilder.append(decimalFormat.format(centerX));
        debugInfoBuilder.append(", ").append(decimalFormat.format(centerY));
        debugInfoBuilder.append(", ").append(decimalFormat.format(centerZ));

        debugInfoBuilder.append(" sphere ");
        debugInfoBuilder.append(decimalFormat.format(sphere.getX()));
        debugInfoBuilder.append(", ").append(decimalFormat.format(sphere.getY()));
        debugInfoBuilder.append(", ").append(decimalFormat.format(sphere.getX()));

        return debugInfoBuilder.toString();
    }
}
