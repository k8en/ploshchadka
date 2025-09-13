package org.kdepo.games.ploshchadka.model.base.tiles;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.utils.FileUtils;
import org.kdepo.games.ploshchadka.utils.TileMapUtils;

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
        int tileX1 = TileMapUtils.getTileColumnByX(camera.getX(), this.x, (int) this.mapWidth, this.tileWidth);
        if (tileX1 == -1) {
            return;
        }

        int tileX2 = TileMapUtils.getTileColumnByX(camera.getX() + camera.getWidth(), this.x, (int) this.mapWidth, this.tileWidth);
        if (tileX2 == -1) {
            System.out.println("RIGHT SIDE -1 for " + (camera.getX() + camera.getWidth()) + " " + this.x + " " + this.mapWidth + " " + this.tileWidth);
            return;
        }

        int tileY1 = TileMapUtils.getTileRowByY(camera.getY(), this.y, (int) this.mapHeight, this.tileHeight);
        if (tileY1 == -1) {
            return;
        }

        int tileY2 = TileMapUtils.getTileRowByY(camera.getY() + camera.getHeight(), this.y, (int) this.mapHeight, this.tileHeight);
        if (tileY2 == -1) {
            return;
        }

        //Console.addMessage("TileMap tileX1=" + tileX1 + ", tileY1=" + tileY1 + ", tileX2=" + tileX2 + ", tileY2=" + tileY2);

        // Calculate screen offset for the first visible tile
        double screenOffsetX = (this.x) - camera.getX();
        double screenOffsetY = (this.y) - camera.getY();

        //Console.addMessage("TileMap screenOffsetX=" + screenOffsetX + ", screenOffsetY=" + screenOffsetY);

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
}
