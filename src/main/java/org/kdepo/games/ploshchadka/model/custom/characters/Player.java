package org.kdepo.games.ploshchadka.model.custom.characters;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationsController;
import org.kdepo.games.ploshchadka.model.custom.FaceDirection;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.image.BufferedImage;

public class Player extends GameParticipant {

    private boolean isHumanControls;

    private double runSpeed;
    private double dashSpeed;

    private double kickReadiness;
    private double kickReadinessRestoreSpeed;

    private int freezeTicks;

    public Player(int id, String currentAnimationName, double centerX, double centerY, double centerZ) {
        this.id = id;

        // Player state parameters
        faceDirection = FaceDirection.RIGHT;
        characterState = CharacterState.STAND;
        runSpeed = 1.8;
        dashSpeed = runSpeed * 1.6;

        kickReadiness = 100d;
        kickReadinessRestoreSpeed = 3d;

        freezeTicks = 0;

        // Initialize animation controller, prepare images, frames and animations
        initRenderingParameters(currentAnimationName);

        // Init object and sprite position
        initPositionParameters(centerX, centerY, centerZ);

        // Character collision parameters
        characterHeight = 64;
        characterRadius = 16;

        // Is not controlling the ball by default
        isControllingTheBall = false;
    }

    public double getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
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

    public boolean isReadyToKick() {
        return kickReadiness == 100d;
    }

    public void startKick() {
        kickReadiness = 0;
    }

    @Override
    protected void initRenderingParameters(String currentAnimationName) {
        // Initialize animation controller
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

        // Set current active animation
        animationsController.setCurrentAnimation(currentAnimationName);
    }

    public boolean isHumanControls() {
        return isHumanControls;
    }

    public void setHumanControls(boolean humanControls) {
        isHumanControls = humanControls;
    }
}
