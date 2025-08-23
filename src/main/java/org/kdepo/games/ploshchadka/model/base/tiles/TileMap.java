package org.kdepo.games.ploshchadka.model.base.tiles;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TileMap {

    /**
     * Top left corner
     */
    private double x;

    /**
     * Top left corner
     */
    private double y;

    /**
     * Single tile width
     */
    private int tileWidth;

    /**
     * Single tile height
     */
    private int tileHeight;

    /**
     * Tile map width
     */
    private double mapWidth;

    /**
     * Tile map height
     */
    private double mapHeight;

    /**
     * Tile map data
     */
    private Tile[][] tiles;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public double getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(double mapWidth) {
        this.mapWidth = mapWidth;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(double mapHeight) {
        this.mapHeight = mapHeight;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void load(String mapName) {
        // Load used ids
        Integer[][] tileIds = FileUtils.readFileToArray("maps\\" + mapName);
        System.out.println("Loaded tile ids" + tileIds[0].length + "x" + tileIds.length);

        // Load tiles configurations
        String pathToImageMap = "tiles\\tiles.cfg";
        InputStream inputStream = TileMap.class.getClassLoader().getResourceAsStream(pathToImageMap);
        if (inputStream == null) {
            throw new RuntimeException("File not found: " + pathToImageMap);
        }

        Map<Integer, BufferedImage> imageMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String configLine;
            while ((configLine = reader.readLine()) != null) {
                String[] configValues = configLine.split("=");
                Integer id = Integer.parseInt(configValues[0]);

                String fileName = configValues[1];
                BufferedImage tileImage = FileUtils.loadImage("tiles\\" + fileName);

                imageMap.put(id, tileImage);
                System.out.println("Found tile image: " + id + ", " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Prepare tile map
        tiles = new Tile[tileIds.length][tileIds[0].length];
        for (int row = 0; row < tileIds.length; row++) {
            for (int column = 0; column < tileIds[0].length; column++) {
                Tile tile = new Tile();
                tile.setImage(imageMap.get(tileIds[row][column]));
                tiles[row][column] = tile;
            }
        }
    }

    public void draw(Graphics g) {
        Tile tile = null;
        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[row].length; column++) {
                tile = tiles[row][column];
                if (tile != null) {
                    g.drawImage(
                            tile.getImage(),
                            (int) (x + column * tileWidth),
                            (int) (y + row * tileHeight),
                            null
                    );
                }
            }
        }
    }

    public void draw(Graphics g, VirtualCamera camera) {
        int tileX1;
        int tileX2;

        if (this.x + this.mapWidth <= camera.getX()) {
            // 1 Tile map at the left, camera at the right - no intersection - no draw
            tileX1 = -1;
            tileX2 = -1;
//            Console.addMessage("TileMap at the left, camera at the right - no intersection - no draw");

        } else if (this.x < camera.getX() && this.x + this.mapWidth > camera.getX() && this.x + this.mapWidth < camera.getX() + camera.getWidth()) {
            // 2 Tile map at the left, camera at the right - partial intersection
            tileX1 = getTileColumn(camera.getX());
            tileX2 = tiles[0].length - 1;
//            Console.addMessage("TileMap(" + this.x + "," + this.y + "," + this.mapWidth + "," + this.mapHeight + ") at the left, Camera(" + camera.getX() + "," + camera.getY() + "," + camera.getWidth() + "," + camera.getHeight() + ") at the right - partial intersection");

        } else if (this.x >= camera.getX() && this.x + this.mapWidth <= camera.getX() + camera.getWidth()) {
            // 3 Tile map can be seen by camera, tile map width is less than camera width
            tileX1 = 0;
            tileX2 = tiles[0].length - 1;
//            Console.addMessage("TileMap can be seen by camera, tile map width is less than camera width");

        } else if (this.x < camera.getX() && this.x + this.mapWidth > camera.getX() + camera.getWidth()) {
            // 4 Tile map can be seen by camera, tile map width is wider than camera width
            tileX1 = getTileColumn(camera.getX());
            tileX2 = getTileColumn(camera.getX() + camera.getWidth());
//            Console.addMessage("TileMap can be seen by camera, tile map width is wider than camera width");

        } else if (this.x > camera.getX() && this.x <= camera.getX() + camera.getWidth() && this.getX() + this.mapWidth > camera.getX() + camera.getWidth()) {
            // 5 Tile map at the right, camera at the left - partial intersection
            tileX1 = 0;
            tileX2 = getTileColumn(camera.getX() + camera.getWidth());
//            Console.addMessage("TileMap at the right, camera at the left - partial intersection");

        } else if (this.x > camera.getX() + camera.getWidth()) {
            // 6 Tile map at the right, camera at the left - no intersection - no draw
            tileX1 = -1;
            tileX2 = -1;
//            Console.addMessage("TileMap at the right, camera at the left - no intersection - no draw");

        } else {
            // 7 This should never happen
            tileX1 = -1;
            tileX2 = -1;

            String debugString = "ERROR on TileMap: x=" + this.x + ", y=" + this.y + ", mapWidth=" + this.mapWidth + ", mapHeight=" + this.mapHeight;
//            Console.addMessage(debugString);
        }

        if (tileX1 == -1 && tileX2 == -1) {
            return;
        }

        int tileY1;
        int tileY2;

        if (this.y + this.mapHeight <= camera.getY()) {
            // 1 Tile map at the top, camera at the bottom - no intersection - no draw
            tileY1 = -1;
            tileY2 = -1;

        } else if (this.y < camera.getY() && this.y + this.mapHeight > camera.getY() && this.y + this.mapHeight < camera.getY() + camera.getHeight()) {
            // 2 Tile map at the top, camera at the bottom - partial intersection
            tileY1 = getTileRow(camera.getY());
            tileY2 = tiles.length - 1;

        } else if (this.y >= camera.getY() && this.y + this.mapHeight <= camera.getY() + camera.getHeight()) {
            // 3 Tile map can be seen by camera, tile map height is less than camera height
            tileY1 = 0;
            tileY2 = tiles.length - 1;

        } else if (this.y < camera.getY() && this.y + this.mapHeight > camera.getY() + camera.getHeight()) {
            // 4 Tile map can be seen by camera, tile map height is wider than camera height
            tileY1 = getTileRow(camera.getY());
            tileY2 = getTileRow(camera.getY() + camera.getHeight());

        } else if (this.y > camera.getY() && this.y <= camera.getY() + camera.getHeight() && this.getY() + this.mapHeight > camera.getY() + camera.getHeight()) {
            // 5 Tile map at the bottom, camera at the top - partial intersection
            tileY1 = 0;
            tileY2 = getTileRow(camera.getY() + camera.getHeight());

        } else if (this.y > camera.getY() + camera.getHeight()) {
            // Tile map at the bottom, camera at the top - no intersection - no draw
            tileY1 = -1;
            tileY2 = -1;

        } else {
            // This should never happen
            tileY1 = -1;
            tileY2 = -1;
        }

        if (tileY1 == -1 && tileY2 == -1) {
            return;
        }

        Console.addMessage("TileMap tileX1=" + tileX1 + ", tileY1=" + tileY1 + ", tileX2=" + tileX2 + ", tileY2=" + tileY2);

        // Calculate screen offset for the first visible tile
        double screenOffsetX = (this.x) - camera.getX();
        double screenOffsetY = (this.y) - camera.getY();

//        Console.addMessage("TileMap screenOffsetX=" + screenOffsetX + ", screenOffsetY=" + screenOffsetY);

        Tile tile = null;
        for (int row = tileY1; row <= tileY2; row++) {
            for (int column = tileX1; column <= tileX2; column++) {
                tile = tiles[row][column];
                if (tile != null) {
                    g.drawImage(
                            tile.getImage(),
                            (int) (screenOffsetX + column * tileWidth),
                            (int) (screenOffsetY + row * tileHeight),
                            null
                    );
                }
            }
        }
    }

    public int getTileColumn(double x) {
        if (x < this.x || x >= this.x + this.mapWidth) {
            return -1;
        }
        double localX = x - this.x;
        return (int) Math.floor(localX / tileWidth);
    }

    public int getTileRow(double y) {
        if (y < this.y || y >= this.y + this.mapHeight) {
            return -1;
        }
        double localY = y - this.y;
        return (int) Math.floor(localY / tileHeight);
    }
}
