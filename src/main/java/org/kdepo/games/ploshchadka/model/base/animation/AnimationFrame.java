package org.kdepo.games.ploshchadka.model.base.animation;

import java.awt.image.BufferedImage;

public class AnimationFrame {

    private int frameNumber;

    private BufferedImage frameImage;

    private int ticksToDisplay;

    public AnimationFrame() {

    }

    public AnimationFrame(int frameNumber, BufferedImage frameImage, int ticksToDisplay) {
        this.frameNumber = frameNumber;
        this.frameImage = frameImage;
        this.ticksToDisplay = ticksToDisplay;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public BufferedImage getFrameImage() {
        return frameImage;
    }

    public void setFrameImage(BufferedImage frameImage) {
        this.frameImage = frameImage;
    }

    public int getTicksToDisplay() {
        return ticksToDisplay;
    }

    public void setTicksToDisplay(int ticksToDisplay) {
        this.ticksToDisplay = ticksToDisplay;
    }
}
