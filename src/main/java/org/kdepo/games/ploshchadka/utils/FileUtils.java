package org.kdepo.games.ploshchadka.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    public static Integer[][] readFileToArray(String filePath) {
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new RuntimeException("File not found: " + filePath);
        }

        List<String[]> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строку по запятым и убираем пробелы
                String[] numbers = line.split("\\s*,\\s*", -1);
                lines.add(numbers);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (lines.isEmpty()) {
            return new Integer[0][0];
        }

        int rows = lines.size();
        int cols = lines.get(0).length;

        // Check that all lines has the same length
        for (int i = 1; i < rows; i++) {
            if (lines.get(i).length != cols) {
                throw new IllegalArgumentException("Lines length mismatch");
            }
        }

        Integer[][] result = new Integer[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] currentLine = lines.get(row);
            for (int col = 0; col < cols; col++) {
                String idAsStr = currentLine[col].trim();
                if (idAsStr.isEmpty()) {
                    continue;
                }
                result[row][col] = Integer.parseInt(idAsStr);
            }
        }

        return result;
    }
}
