package org.kdepo.games.ploshchadka.model.custom.game;

import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CrossbarSegment extends DrawableObject {

    public static final int SEGMENT_TOP = 1;
    public static final int SEGMENT_MIDDLE = 2;
    public static final int SEGMENT_BOTTOM = 3;

    private final BufferedImage image;

    public CrossbarSegment(double centerX, double centerY, int segmentNumber) {
        // Prepare image
        if (CrossbarSegment.SEGMENT_TOP == segmentNumber) {
            image = FileUtils.loadImage("images/goal/crossbar_01.png");
        } else if (CrossbarSegment.SEGMENT_MIDDLE == segmentNumber) {
            image = FileUtils.loadImage("images/goal/crossbar_02.png");
        } else if (CrossbarSegment.SEGMENT_BOTTOM == segmentNumber) {
            image = FileUtils.loadImage("images/goal/crossbar_03.png");
        } else {
            throw new RuntimeException("Unknown crossbar segment number " + segmentNumber);
        }

        // Virtual position parameters
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = 66;

        // Sprite position based on virtual position parameters
        this.x = centerX - image.getWidth() * 1.0 / 2;
        this.y = centerY - image.getHeight() - centerZ;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void draw(Graphics g, VirtualCamera camera) {
        double screenOffsetX = camera.getScreenOffsetX(this.x);
        double screenOffsetY = camera.getScreenOffsetY(this.y) - centerZ;

        g.drawImage(
                image,
                (int) screenOffsetX, (int) screenOffsetY,
                null
        );
    }
}
