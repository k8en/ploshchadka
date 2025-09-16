package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationsController;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DrawableObject {

    private double runSpeed;
    private double dashSpeed;

    private AnimationsController animationsController;

    private FaceDirection faceDirection;
    private PlayerState playerState;

    private double kickReadiness;
    private double kickReadinessRestoreSpeed;

    private int freezeTicks;

    private int radius;

    private boolean isControllingTheBall;

    public Player() {
        id = 1;

        // Player state parameters
        faceDirection = FaceDirection.RIGHT;
        playerState = PlayerState.STAND;
        runSpeed = 1.8;
        dashSpeed = runSpeed * 1.6;

        kickReadiness = 100d;
        kickReadinessRestoreSpeed = 3d;

        freezeTicks = 0;

        // Rendering parameters
        animationsController = new AnimationsController();

        // Prepare frames images
        BufferedImage imageFrame01 = FileUtils.loadImage("frame01.png");
        BufferedImage imageFrame01m = FileUtils.loadImage("frame01m.png");
        BufferedImage imageFrame02 = FileUtils.loadImage("frame02.png");
        BufferedImage imageFrame02m = FileUtils.loadImage("frame02m.png");
        BufferedImage imageFrame03 = FileUtils.loadImage("frame03.png");
        BufferedImage imageFrame03m = FileUtils.loadImage("frame03m.png");
        BufferedImage imageFrame04 = FileUtils.loadImage("frame04.png");
        BufferedImage imageFrame04m = FileUtils.loadImage("frame04m.png");
        BufferedImage imageFrame05 = FileUtils.loadImage("frame05.png");
        BufferedImage imageFrame05m = FileUtils.loadImage("frame05m.png");
        BufferedImage imageFrame06 = FileUtils.loadImage("frame06.png");
        BufferedImage imageFrame06m = FileUtils.loadImage("frame06m.png");
        BufferedImage imageFrame07 = FileUtils.loadImage("frame07.png");
        BufferedImage imageFrame07m = FileUtils.loadImage("frame07m.png");
        BufferedImage imageFrame08 = FileUtils.loadImage("frame08.png");
        BufferedImage imageFrame08m = FileUtils.loadImage("frame08m.png");

        // Prepare animations
        AnimationFrame[] standRightFrames = new AnimationFrame[1];
        standRightFrames[0] = new AnimationFrame(0, imageFrame01, 999);
        Animation standRightAnimation = new Animation(Constants.AnimationName.STAND_RIGHT, standRightFrames, 0);
        animationsController.addAnimation(standRightAnimation);

        AnimationFrame[] runRightFrames = new AnimationFrame[2];
        runRightFrames[0] = new AnimationFrame(0, imageFrame02, 8);
        runRightFrames[1] = new AnimationFrame(1, imageFrame01, 8);
        Animation runRightAnimation = new Animation(Constants.AnimationName.RUN_RIGHT, runRightFrames, 0);
        animationsController.addAnimation(runRightAnimation);

        AnimationFrame[] standLeftFrames = new AnimationFrame[1];
        standLeftFrames[0] = new AnimationFrame(0, imageFrame01m, 999);
        Animation standLeftAnimation = new Animation(Constants.AnimationName.STAND_LEFT, standLeftFrames, 0);
        animationsController.addAnimation(standLeftAnimation);

        AnimationFrame[] runLeftFrames = new AnimationFrame[2];
        runLeftFrames[0] = new AnimationFrame(0, imageFrame02m, 8);
        runLeftFrames[1] = new AnimationFrame(1, imageFrame01m, 8);
        Animation runLeftAnimation = new Animation(Constants.AnimationName.RUN_LEFT, runLeftFrames, 0);
        animationsController.addAnimation(runLeftAnimation);

        AnimationFrame[] kickRightFrames = new AnimationFrame[1];
        kickRightFrames[0] = new AnimationFrame(0, imageFrame03, 12);
        Animation kickRightAnimation = new Animation(Constants.AnimationName.KICK_RIGHT, kickRightFrames, 0);
        animationsController.addAnimation(kickRightAnimation);

        AnimationFrame[] kickLeftFrames = new AnimationFrame[1];
        kickLeftFrames[0] = new AnimationFrame(0, imageFrame03m, 12);
        Animation kickLeftAnimation = new Animation(Constants.AnimationName.KICK_LEFT, kickLeftFrames, 0);
        animationsController.addAnimation(kickLeftAnimation);

        AnimationFrame[] powerKickRightFrames = new AnimationFrame[3];
        powerKickRightFrames[0] = new AnimationFrame(0, imageFrame04, 6);
        powerKickRightFrames[1] = new AnimationFrame(1, imageFrame05, 6);
        powerKickRightFrames[2] = new AnimationFrame(2, imageFrame06, 6);
        Animation powerKickRightAnimation = new Animation(Constants.AnimationName.POWER_KICK_RIGHT, powerKickRightFrames, 0);
        animationsController.addAnimation(powerKickRightAnimation);

        AnimationFrame[] powerKickLeftFrames = new AnimationFrame[3];
        powerKickLeftFrames[0] = new AnimationFrame(0, imageFrame04m, 6);
        powerKickLeftFrames[1] = new AnimationFrame(1, imageFrame05m, 6);
        powerKickLeftFrames[2] = new AnimationFrame(2, imageFrame06m, 6);
        Animation powerKickLeftAnimation = new Animation(Constants.AnimationName.POWER_KICK_LEFT, powerKickLeftFrames, 0);
        animationsController.addAnimation(powerKickLeftAnimation);

        // Resolve animation based on state parameters
        animationsController.setCurrentAnimation(standRightAnimation);

        // Virtual position parameters
        this.centerX = -50;
        this.centerY = 0;
        this.centerZ = 0;

        // Sprite position based on virtual position parameters
        this.x = centerX - animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getWidth() * 1.0 / 2;
        this.y = centerY - animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getHeight() - centerZ;
        this.width = animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getWidth();
        this.height = animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getHeight();

        isControllingTheBall = false;
        radius = 16;
    }

    @Override
    public void setCenterX(double centerX) {
        this.centerX = centerX;
        this.x = centerX - animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getWidth() * 1.0 / 2;
    }

    @Override
    public void setCenterY(double centerY) {
        this.centerY = centerY;
        this.y = centerY - animationsController.getCurrentAnimation().getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()].getFrameImage().getHeight() - centerZ;
    }

    public AnimationsController getAnimationsController() {
        return animationsController;
    }

    public void setAnimationsController(AnimationsController animationsController) {
        this.animationsController = animationsController;
    }

    public double getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
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
        animationsController.animate();
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

        BufferedImage frameImage = animationsController.getCurrentAnimationFrameImage();

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
