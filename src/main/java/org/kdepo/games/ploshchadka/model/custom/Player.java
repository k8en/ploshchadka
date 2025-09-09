package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends DrawableObject {

    private double runSpeed;

    private final Map<String, Animation> animationMap;
    private Animation currentAnimation;

    private FaceDirection faceDirection;
    private PlayerState playerState;

    private double kickReadiness;
    private double kickReadinessRestoreSpeed;

    private int freezeTicks;

    private boolean isControllingTheBall;
    private int radius;

    public Player() {
        // Player state parameters
        faceDirection = FaceDirection.RIGHT;
        playerState = PlayerState.STAND;
        runSpeed = 1.8;

        kickReadiness = 100d;
        kickReadinessRestoreSpeed = 3d;

        freezeTicks = 0;

        // Rendering parameters
        animationMap = new HashMap<>();

        BufferedImage imageFrame01 = FileUtils.loadImage("frame01.png");
        BufferedImage imageFrame01m = FileUtils.loadImage("frame01m.png");
        BufferedImage imageFrame02 = FileUtils.loadImage("frame02.png");
        BufferedImage imageFrame02m = FileUtils.loadImage("frame02m.png");
        BufferedImage imageFrame03 = FileUtils.loadImage("frame03.png");
        BufferedImage imageFrame03m = FileUtils.loadImage("frame03m.png");

        AnimationFrame[] standRightFrames = new AnimationFrame[1];
        standRightFrames[0] = new AnimationFrame(0, imageFrame01, 999);
        Animation standRightAnimation = new Animation(Constants.AnimationName.STAND_RIGHT, standRightFrames, 0);
        animationMap.put(Constants.AnimationName.STAND_RIGHT, standRightAnimation);

        AnimationFrame[] runRightFrames = new AnimationFrame[2];
        runRightFrames[0] = new AnimationFrame(0, imageFrame02, 8);
        runRightFrames[1] = new AnimationFrame(1, imageFrame01, 8);
        Animation runRightAnimation = new Animation(Constants.AnimationName.RUN_RIGHT, runRightFrames, 0);
        animationMap.put(Constants.AnimationName.RUN_RIGHT, runRightAnimation);

        AnimationFrame[] standLeftFrames = new AnimationFrame[1];
        standLeftFrames[0] = new AnimationFrame(0, imageFrame01m, 999);
        Animation standLeftAnimation = new Animation(Constants.AnimationName.STAND_LEFT, standLeftFrames, 0);
        animationMap.put(Constants.AnimationName.STAND_LEFT, standLeftAnimation);

        AnimationFrame[] runLeftFrames = new AnimationFrame[2];
        runLeftFrames[0] = new AnimationFrame(0, imageFrame02m, 8);
        runLeftFrames[1] = new AnimationFrame(1, imageFrame01m, 8);
        Animation runLeftAnimation = new Animation(Constants.AnimationName.RUN_LEFT, runLeftFrames, 0);
        animationMap.put(Constants.AnimationName.RUN_LEFT, runLeftAnimation);

        AnimationFrame[] kickRightFrames = new AnimationFrame[1];
        kickRightFrames[0] = new AnimationFrame(0, imageFrame03, 999);
        Animation kickRightAnimation = new Animation(Constants.AnimationName.KICK_RIGHT, kickRightFrames, 0);
        animationMap.put(Constants.AnimationName.KICK_RIGHT, kickRightAnimation);

        AnimationFrame[] kickLeftFrames = new AnimationFrame[1];
        kickLeftFrames[0] = new AnimationFrame(0, imageFrame03m, 999);
        Animation kickLeftAnimation = new Animation(Constants.AnimationName.KICK_LEFT, kickLeftFrames, 0);
        animationMap.put(Constants.AnimationName.KICK_LEFT, kickLeftAnimation);

        // Resolve animation based on state parameters
        currentAnimation = standRightAnimation;

        // Virtual position parameters
        this.centerX = -50;
        this.centerY = 0;
        this.centerZ = 0;

        // Sprite position based on virtual position parameters
        this.x = centerX - currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getWidth() * 1.0 / 2;
        this.y = centerY - currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getHeight() - centerZ;
        this.width = currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getWidth();
        this.height = currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getHeight();

        isControllingTheBall = true;
        radius = 16;
    }

    @Override
    public void setCenterX(double centerX) {
        this.centerX = centerX;
        this.x = centerX - currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getWidth() * 1.0 / 2;
    }

    @Override
    public void setCenterY(double centerY) {
        this.centerY = centerY;
        this.y = centerY - currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage().getHeight() - centerZ;
    }

    public double getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public String getCurrentAnimationName() {
        return currentAnimation.getAnimationName();
    }

    public void setCurrentAnimationByName(String animationName) {
        currentAnimation = animationMap.get(animationName);
        if (currentAnimation == null) {
            throw new RuntimeException("Animation not found: " + animationName);
        } else {
            System.out.println("current animation set to " + currentAnimation.getAnimationName());
        }
        currentAnimation.reset();
    }

    public int getCurrentAnimationFrameNumber() {
        return currentAnimation.getCurrentFrameNumber();
    }

    public FaceDirection getFaceDirection() {
        return faceDirection;
    }

    public void setFaceDirection(FaceDirection faceDirection) {
        this.faceDirection = faceDirection;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void animate() {
        if (currentAnimation.isFrameDisplayCompleted()) {
            currentAnimation.nextFrame();
        } else {
            currentAnimation.update();
        }
    }

    public void update() {
        if (kickReadiness < 100d) {
            kickReadiness = kickReadiness + kickReadinessRestoreSpeed;
            if (kickReadiness > 100d) {
                kickReadiness = 100d;
            }
        }

        if (freezeTicks != 0) {
            freezeTicks--;
        }
    }

    @Override
    public void draw(Graphics g, VirtualCamera camera) {
        double screenOffsetX = camera.getScreenOffsetX(this.x);
        double screenOffsetY = camera.getScreenOffsetY(this.y) - centerZ;

        BufferedImage frameImage = currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage();

        g.drawImage(
                frameImage,
                (int) screenOffsetX, (int) screenOffsetY,
                null
        );

        if (true) {
            g.setColor(Color.MAGENTA);
            g.drawRect((int) screenOffsetX, (int) screenOffsetY, (int) this.width, (int) this.height);

            double screenOffsetCenterX = camera.getScreenOffsetX(this.centerX);
            double screenOffsetCenterY = camera.getScreenOffsetY(this.centerY);

            g.setColor(Color.RED);
            g.drawOval((int) screenOffsetCenterX - radius, (int) screenOffsetCenterY - radius, radius * 2, radius * 2);
        }
    }

    public boolean isFreezed() {
        return freezeTicks > 0;
    }

    public boolean isReadyToKick() {
        return kickReadiness == 100d;
    }

    public void startKick() {
        kickReadiness = 0;
        freezeTicks = 10;
    }

    public boolean isControllingTheBall() {
        return isControllingTheBall;
    }

    public void setControllingTheBall(boolean isControllingTheBall) {
        this.isControllingTheBall = isControllingTheBall;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
