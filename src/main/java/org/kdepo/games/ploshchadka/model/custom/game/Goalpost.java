package org.kdepo.games.ploshchadka.model.custom.game;

import org.kdepo.games.ploshchadka.model.base.DrawableObject;
import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goalpost extends DrawableObject {

    private final BufferedImage image;

    public Goalpost(double centerX, double centerY) {
        // Prepare image
        image = FileUtils.loadImage("images/goal/goalpost.png");

        // Virtual position parameters
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = 0;

        // Sprite position based on virtual position parameters
        this.x = centerX - image.getWidth() * 1.0 / 2;
        this.y = centerY - image.getHeight() - centerZ;
        this.width = image.getWidth();
        this.height = image.getHeight();
        System.out.println("Top left " + x + " " + y + " bottom right " + (x + width) + " " + (y + height));
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
