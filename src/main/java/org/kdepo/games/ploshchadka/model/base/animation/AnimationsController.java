package org.kdepo.games.ploshchadka.model.base.animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationsController {

    private final Map<String, Animation> animations;
    private Animation currentAnimation;
    private int currentFrameNumber;
    private int ticksPassed;

    private AnimationPlayMode playMode;
    private boolean isPlayCompleted;

    private AnimationPlayDirection playDirection;

    public AnimationsController() {
        animations = new HashMap<>();
        currentFrameNumber = 0;
        ticksPassed = 0;
        playMode = AnimationPlayMode.LOOP;
        isPlayCompleted = false;
        playDirection = AnimationPlayDirection.NORMAL;
    }

    public AnimationsController(Map<String, Animation> animations, Animation currentAnimation) {
        this.animations = animations;
        this.currentAnimation = currentAnimation;
        currentFrameNumber = 0;
        playMode = AnimationPlayMode.LOOP;
        isPlayCompleted = false;
        playDirection = AnimationPlayDirection.NORMAL;
    }

    public void addAnimation(Animation animation) {
        animations.put(animation.getAnimationName(), animation);
    }

    public Animation getAnimation(String animationName) {
        return animations.get(animationName);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void setCurrentAnimation(String animationName) {
        currentAnimation = animations.get(animationName);
        if (currentAnimation == null) {
            throw new RuntimeException("Animation not found: " + animationName);
        } else {
            System.out.println("Current animation is set to " + currentAnimation.getAnimationName());
        }
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public void setCurrentFrameNumber(int currentFrameNumber) {
        this.currentFrameNumber = currentFrameNumber;
    }

    public void resetCurrentFrameNumber() {
        if (AnimationPlayDirection.NORMAL.equals(playDirection)) {
            currentFrameNumber = 0;
        } else if (AnimationPlayDirection.REVERS.equals(playDirection)) {
            currentFrameNumber = currentAnimation.getAnimationFrames().length - 1;
        }
    }

    public int getTicksPassed() {
        return ticksPassed;
    }

    public void setTicksPassed(int ticksPassed) {
        this.ticksPassed = ticksPassed;
    }

    public AnimationPlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(AnimationPlayMode playMode) {
        this.playMode = playMode;
    }

    public boolean isPlayCompleted() {
        return isPlayCompleted;
    }

    public void setPlayCompleted(boolean playCompleted) {
        isPlayCompleted = playCompleted;
    }

    public AnimationPlayDirection getPlayDirection() {
        return playDirection;
    }

    public void setPlayDirection(AnimationPlayDirection playDirection) {
        this.playDirection = playDirection;
    }

    public void animate() {
        if (isPlayCompleted) {
            return;
        }

        ticksPassed = ticksPassed + 1;

        AnimationFrame animationFrame = currentAnimation.getAnimationFrames()[currentFrameNumber];

        // Check if current frame displaying is completed
        if (ticksPassed > animationFrame.getTicksToDisplay()) {

            // Check what play mode is used
            if (AnimationPlayMode.SINGLE.equals(playMode)) {

                // Check what play direction is used
                if (AnimationPlayDirection.NORMAL.equals(playDirection)) {

                    // Check if this was the last frame for the selected play direction
                    if (currentFrameNumber >= currentAnimation.getAnimationFrames().length - 1) {
                        // Stop any updates in case of single mode
                        isPlayCompleted = true;

                    } else {
                        // Go to the next frame
                        currentFrameNumber++;
                        // Reset ticks counter
                        ticksPassed = 0;
                    }

                } else if (AnimationPlayDirection.REVERS.equals(playDirection)) {

                    // Check if this was the last frame for the selected play direction
                    if (currentFrameNumber <= 0) {
                        // Stop any updates in case of single mode
                        isPlayCompleted = true;

                    } else {
                        // Go to the next frame
                        currentFrameNumber--;
                        // Reset ticks counter
                        ticksPassed = 0;
                    }

                } else {
                    throw new RuntimeException("Unknown animation play direction: " + playDirection);
                }

            } else if (AnimationPlayMode.LOOP.equals(playMode)) {

                // Check what play direction is used
                if (AnimationPlayDirection.NORMAL.equals(playDirection)) {

                    // Check if this was the last frame for the selected play direction
                    if (currentFrameNumber >= currentAnimation.getAnimationFrames().length - 1) {
                        // Go to the first frame
                        currentFrameNumber = 0;
                    } else {
                        //System.out.println("ANIMATE number++");
                        // Go to the next frame
                        currentFrameNumber++;
                    }

                    // Reset ticks counter
                    ticksPassed = 0;

                } else if (AnimationPlayDirection.REVERS.equals(playDirection)) {

                    // Check if this was the last frame for the selected play direction
                    if (currentFrameNumber <= 0) {
                        // Go to the first frame (revers)
                        currentFrameNumber = currentAnimation.getAnimationFrames().length - 1;
                    } else {
                        // Go to the next frame
                        currentFrameNumber--;
                    }

                    // Reset ticks counter
                    ticksPassed = 0;

                } else {
                    throw new RuntimeException("Unknown animation play direction: " + playDirection);
                }

            } else {
                throw new RuntimeException("Unknown animation play mode: " + playMode);
            }
        }
    }

    public BufferedImage getCurrentAnimationFrameImage() {
        return currentAnimation.getAnimationFrames()[currentFrameNumber].getFrameImage();
    }
}
