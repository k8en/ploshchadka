package org.kdepo.games.ploshchadka.model.custom.characters;

import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractCharacter extends DrawableObject {

    /**
     * To control all character animations
     */
    protected AnimationsController animationsController;

    protected FaceDirection faceDirection;
    protected CharacterState characterState;

    /**
     * Character cylinder height for collision purposes
     */
    protected int characterHeight;

    /**
     * Character cylinder radius for collision purposes
     */
    protected int characterRadius;

    @Override
    public void setCenterX(double centerX) {
        // Set object center
        this.centerX = centerX;

        // Adjust picture position according to the object center
        this.x = centerX - animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getWidth() * 1.0 / 2;
    }

    @Override
    public void setCenterY(double centerY) {
        // Set object center
        this.centerY = centerY;

        // Adjust picture position according to the object center
        this.y = centerY - animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getHeight() - centerZ;
    }

    @Override
    public void draw(Graphics g, VirtualCamera camera) {
        double screenOffsetX = camera.getScreenOffsetX(this.x);
        double screenOffsetY = camera.getScreenOffsetY(this.y);

        BufferedImage frameImage = animationsController.getCurrentAnimationFrameImage();

        g.drawImage(
                frameImage,
                (int) screenOffsetX, (int) (screenOffsetY - centerZ),
                null
        );

//        if (true) {
//            g.setColor(Color.MAGENTA);
//            g.drawRect((int) screenOffsetX, (int) screenOffsetY, (int) this.width, (int) this.height);
//
//            double screenOffsetCenterX = camera.getScreenOffsetX(this.centerX);
//            double screenOffsetCenterY = camera.getScreenOffsetY(this.centerY);
//
//            g.setColor(Color.RED);
//            g.drawOval((int) screenOffsetCenterX - characterRadius, (int) screenOffsetCenterY - characterRadius, characterRadius * 2, characterRadius * 2);
//            g.drawLine((int) screenOffsetCenterX, (int) screenOffsetCenterY - 64, (int) screenOffsetCenterX, (int) screenOffsetCenterY);
//        }
    }

    public AnimationsController getAnimationsController() {
        return animationsController;
    }

    public void setAnimationsController(AnimationsController animationsController) {
        this.animationsController = animationsController;
    }

    public FaceDirection getFaceDirection() {
        return faceDirection;
    }

    public void setFaceDirection(FaceDirection faceDirection) {
        this.faceDirection = faceDirection;
    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public void setCharacterHeight(int characterHeight) {
        this.characterHeight = characterHeight;
    }

    public int getCharacterRadius() {
        return characterRadius;
    }

    public void setCharacterRadius(int characterRadius) {
        this.characterRadius = characterRadius;
    }

    protected abstract void initRenderingParameters(String currentAnimationName);

    protected void initPositionParameters(double centerX, double centerY, double centerZ) {
        // Virtual position parameters
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;

        // Sprite position based on virtual position parameters
        this.x = this.centerX - animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getWidth() * 1.0 / 2;

        this.y = this.centerY - animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getHeight() - this.centerZ;

        this.width = animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getWidth();

        this.height = animationsController.getCurrentAnimation()
                .getAnimationFrames()[animationsController.getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getHeight();
    }
}
