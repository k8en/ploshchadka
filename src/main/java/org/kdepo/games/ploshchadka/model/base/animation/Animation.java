package org.kdepo.games.ploshchadka.model.base.animation;

public class Animation {

    private String animationName;

    private AnimationFrame[] animationFrames;

    private int currentFrameNumber;

    private int ticksPassed;

    public Animation() {
        currentFrameNumber = 0;
        ticksPassed = 0;
    }

    public Animation(String animationName, AnimationFrame[] animationFrames, int currentFrameNumber) {
        this.animationName = animationName;
        this.animationFrames = animationFrames;
        this.currentFrameNumber = currentFrameNumber;
        ticksPassed = 0;
    }

    public boolean isFrameDisplayCompleted() {
        return ticksPassed >= animationFrames[currentFrameNumber].getTicksToDisplay();
    }

    public void nextFrame() {
        currentFrameNumber = currentFrameNumber + 1;
        if (currentFrameNumber >= animationFrames.length) {
            currentFrameNumber = 0;
        }
        ticksPassed = 0;
    }

    public void reset() {
        currentFrameNumber = 0;
        ticksPassed = 0;
    }

    public void update() {
        ticksPassed = ticksPassed + 1;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public AnimationFrame[] getAnimationFrames() {
        return animationFrames;
    }

    public void setAnimationFrames(AnimationFrame[] animationFrames) {
        this.animationFrames = animationFrames;
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public void setCurrentFrameNumber(int currentFrameNumber) {
        this.currentFrameNumber = currentFrameNumber;
    }
}
