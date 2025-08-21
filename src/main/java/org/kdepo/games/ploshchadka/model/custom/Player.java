package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private final Map<String, Animation> animationMap;
    private Animation currentAnimation;

    public Player() {
        animationMap = new HashMap<>();

        BufferedImage imageFrame01 = FileUtils.loadImage("frame01.png");
        BufferedImage imageFrame02 = FileUtils.loadImage("frame02.png");
        BufferedImage imageFrame01m = FileUtils.loadImage("frame01m.png");
        BufferedImage imageFrame02m = FileUtils.loadImage("frame02m.png");

        AnimationFrame[] standRightFrames = new AnimationFrame[1];
        standRightFrames[0] = new AnimationFrame(0, imageFrame01, 999);
        Animation standRightAnimation = new Animation(Constants.AnimationName.STAND_RIGHT, standRightFrames, 0);
        animationMap.put(Constants.AnimationName.STAND_RIGHT, standRightAnimation);

        AnimationFrame[] runRightFrames = new AnimationFrame[2];
        runRightFrames[0] = new AnimationFrame(0, imageFrame01, 8);
        runRightFrames[1] = new AnimationFrame(1, imageFrame02, 8);
        Animation runRightAnimation = new Animation(Constants.AnimationName.RUN_RIGHT, runRightFrames, 0);
        animationMap.put(Constants.AnimationName.RUN_RIGHT, runRightAnimation);

        AnimationFrame[] standLeftFrames = new AnimationFrame[1];
        standLeftFrames[0] = new AnimationFrame(0, imageFrame01m, 999);
        Animation standLeftAnimation = new Animation(Constants.AnimationName.STAND_LEFT, standLeftFrames, 0);
        animationMap.put(Constants.AnimationName.STAND_LEFT, standLeftAnimation);

        AnimationFrame[] runLeftFrames = new AnimationFrame[2];
        runLeftFrames[0] = new AnimationFrame(0, imageFrame01m, 8);
        runLeftFrames[1] = new AnimationFrame(1, imageFrame02m, 8);
        Animation runLeftAnimation = new Animation(Constants.AnimationName.RUN_LEFT, runLeftFrames, 0);
        animationMap.put(Constants.AnimationName.RUN_LEFT, runLeftAnimation);

        currentAnimation = standRightAnimation;
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

    public void animate() {
        if (currentAnimation.isFrameDisplayCompleted()) {
            currentAnimation.nextFrame();
        } else {
            currentAnimation.update();
        }
    }

    public void draw(Graphics g) {
        int onScreenX = 100;
        int onScreenY = 100;

        BufferedImage frameImage = currentAnimation.getAnimationFrames()[currentAnimation.getCurrentFrameNumber()].getFrameImage();

        g.drawImage(
                frameImage,
                onScreenX, onScreenY,
                null
        );
    }
}
