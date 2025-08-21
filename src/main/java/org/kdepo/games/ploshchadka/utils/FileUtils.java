package org.kdepo.games.ploshchadka.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static BufferedImage loadImage(String path) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException("Image not found: " + path);
            }
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage getImageMirroredHorizontally(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage mirroredImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = mirroredImage.createGraphics();

        // Draw the image flipped horizontally
        // Arguments: image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer
        // (dx1, dy1) and (dx2, dy2) define the destination rectangle
        // (sx1, sy1) and (sx2, sy2) define the source rectangle
        g.drawImage(originalImage, width, 0, 0, height, 0, 0, width, height, null);
        g.dispose();

        return mirroredImage;
    }
}
