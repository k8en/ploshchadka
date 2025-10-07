package org.kdepo.games.ploshchadka.model.custom.characters;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationsController;
import org.kdepo.games.ploshchadka.model.custom.FaceDirection;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.image.BufferedImage;

public class GoalKeeper extends GameParticipant {

    public GoalKeeper(int id, String currentAnimationName, double centerX, double centerY, double centerZ) {
        this.id = id;

        // Player state parameters
        faceDirection = FaceDirection.RIGHT;
        characterState = CharacterState.STAND;
//        runSpeed = 1.8;
//        dashSpeed = runSpeed * 1.6;

//        kickReadiness = 100d;
//        kickReadinessRestoreSpeed = 3d;

//        freezeTicks = 0;

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

    @Override
    protected void initRenderingParameters(String currentAnimationName) {
        // Initialize animation controller
        animationsController = new AnimationsController();

        // Prepare frames images
        BufferedImage imageFrame01 = FileUtils.loadImage("goalkeeper_frame_01.png");
        BufferedImage imageFrame01m = FileUtils.loadImage("goalkeeper_frame_01m.png");
        BufferedImage imageFrame02 = FileUtils.loadImage("goalkeeper_frame_02.png");
        BufferedImage imageFrame02m = FileUtils.loadImage("goalkeeper_frame_01m.png");

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

        // Set current active animation
        animationsController.setCurrentAnimation(currentAnimationName);
    }
}
