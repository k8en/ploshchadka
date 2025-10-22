package org.kdepo.games.ploshchadka.model.custom.game;

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

    // Ball geometry
    private final Sphere sphere;

    // Ball movement
    private Vector3D movementVector;
    private double movementSpeed;

    // Ball spin
    private double ballDistance;
    private double ballDistanceForSpin;

    // Ball rendering
    private final AnimationFrame[] animationFrames;
    private int currentFrameNumber;

    // Ball shadow
    private final BufferedImage shadowImage;

    private GameParticipant controlledBy;

    // Ball debug
    private final StringBuilder debugInfoBuilder;
    private final DecimalFormat decimalFormat;

    public Ball(double centerX, double centerY, double centerZ, double radius) {
        // Rendering parameters
        BufferedImage ballSprites = FileUtils.loadImage("animations/balls/ball.png");
        shadowImage = FileUtils.loadImage("images/misc/shadow.png");

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
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        sphere = new Sphere(this.centerX, this.centerY, this.centerZ, radius);

        // Sprite position based on virtual position parameters
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
        this.y = centerY - animationFrames[currentFrameNumber].getFrameImage().getHeight();
        this.width = animationFrames[currentFrameNumber].getFrameImage().getWidth();
        this.height = animationFrames[currentFrameNumber].getFrameImage().getHeight();

        // Movement parameters
        movementVector = new Vector3D(0.0d, 0.0d, 0.0d);
        movementSpeed = 0.0d;

        // Ball spin parameters
        ballDistance = 0;
        ballDistanceForSpin = 8;

        // For debug purposes
        debugInfoBuilder = new StringBuilder();
        decimalFormat = new DecimalFormat("#0.00");

        System.out.println("Ball initialized with center at (" + centerX + "," + centerY + "," + centerZ + ") " + sphere);
    }

    @Override
    public void setCenterX(double centerX) {
        // Set object center
        this.centerX = centerX;

        // Set ball geometry center
        sphere.setX(centerX);

        // Adjust picture position according to the object center
        this.x = centerX - animationFrames[currentFrameNumber].getFrameImage().getWidth() * 1.0 / 2;
    }

    @Override
    public void setCenterY(double centerY) {
        // Set object center
        this.centerY = centerY;

        // Set ball geometry center
        sphere.setY(centerY);

        // Adjust picture position according to the object center
        this.y = centerY - centerZ - animationFrames[currentFrameNumber].getFrameImage().getHeight() * 1.0 / 2;
    }

    @Override
    public void setCenterZ(double centerZ) {
        // Set object center
        this.centerZ = centerZ;

        // Set ball geometry center
        sphere.setZ(centerZ);

        // Adjust sprite rendering position
        this.y = centerY - centerZ - animationFrames[currentFrameNumber].getFrameImage().getHeight() * 1.0 / 2;
    }

    @Override
    public void draw(Graphics g, VirtualCamera camera) {
        double screenOffsetX = camera.getScreenOffsetX(this.x);
        double screenOffsetY = camera.getScreenOffsetY(this.y);

        // Draw shadow if ball in the air
        if (sphere.getZ() > 0) {
            double screenOffsetShadowX = screenOffsetX + animationFrames[currentFrameNumber].getFrameImage().getWidth() / 2 - shadowImage.getWidth() / 2;
            double screenOffsetShadowY = screenOffsetY + animationFrames[currentFrameNumber].getFrameImage().getHeight() - shadowImage.getHeight() / 2 + sphere.getZ() - sphere.getRadius();
            g.drawImage(
                    shadowImage,
                    (int) screenOffsetShadowX, (int) screenOffsetShadowY,
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
//        if (true) {
//            g.setColor(Color.MAGENTA);
//            g.drawRect((int) screenOffsetX, (int) screenOffsetY, (int) this.width, (int) this.height);
//
//            double screenOffsetCenterX = screenOffsetX + animationFrames[currentFrameNumber].getFrameImage().getWidth() / 2 - sphere.getRadius();
//            double screenOffsetCenterY = screenOffsetY + animationFrames[currentFrameNumber].getFrameImage().getHeight() + sphere.getZ() - sphere.getRadius() * 2;
//
//            g.setColor(Color.RED);
//            g.drawOval(
//                    (int) (screenOffsetCenterX),
//                    (int) (screenOffsetCenterY),
//                    (int) (sphere.getRadius() * 2),
//                    (int) (sphere.getRadius() * 2)
//            );
//        }
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

    public double getBallDistance() {
        return ballDistance;
    }

    public void setBallDistance(double ballDistance) {
        this.ballDistance = ballDistance;
    }

    public double getBallDistanceForSpin() {
        return ballDistanceForSpin;
    }

    public void setBallDistanceForSpin(double ballDistanceForSpin) {
        this.ballDistanceForSpin = ballDistanceForSpin;
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

    public GameParticipant getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(Player player) {
        this.controlledBy = player;
    }

    public String getDebugInfo() {
        debugInfoBuilder.setLength(0);

        debugInfoBuilder.append("Ball (");
        debugInfoBuilder.append(decimalFormat.format(centerX));
        debugInfoBuilder.append(" ").append(decimalFormat.format(centerY));
        debugInfoBuilder.append(" ").append(decimalFormat.format(centerZ));

        debugInfoBuilder.append(") vector (");
        debugInfoBuilder.append(decimalFormat.format(movementVector.getX()));
        debugInfoBuilder.append(" ").append(decimalFormat.format(movementVector.getY()));
        debugInfoBuilder.append(" ").append(decimalFormat.format(movementVector.getZ()));

        debugInfoBuilder.append(") sphere (");
        debugInfoBuilder.append(decimalFormat.format(sphere.getX()));
        debugInfoBuilder.append(" ").append(decimalFormat.format(sphere.getY()));
        debugInfoBuilder.append(" ").append(decimalFormat.format(sphere.getZ()));

        debugInfoBuilder.append(")");

        return debugInfoBuilder.toString();
    }
}
