package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.tiles.TileMap;

import java.awt.*;

public class Ground {

    private final TileMap tileMap;

    private final int maxHeight;

    private final double friction;
    private final double gravity;

    public Ground() {
        tileMap = new TileMap();
        tileMap.load("training_ground.map");

        int tileWidth = 32;  // TODO make it configurable / readable
        int tileHeight = 32;

        tileMap.setTileWidth(tileWidth);
        tileMap.setTileHeight(tileHeight);
        tileMap.setMapWidth(tileMap.getTileWidth() * tileMap.getTiles()[0].length);
        tileMap.setMapHeight(tileMap.getTileHeight() * tileMap.getTiles().length);

        // Align center tile map to ball start position
        tileMap.setX(-tileMap.getMapWidth() / 2);
        tileMap.setY(-tileMap.getMapHeight() / 2 - tileHeight / 2);

        // Limitation for Z coordinate
        maxHeight = 10000;

        friction = 0.04d;
        gravity = 0.024d;

        System.out.println("Ground top left at " + tileMap.getX() + " " + tileMap.getY() + " width = " + tileMap.getMapWidth() + " height = " + tileMap.getMapHeight());
    }

    public double getX() {
        return tileMap.getX();
    }

    public double getY() {
        return tileMap.getY();
    }

    public double getWidth() {
        return tileMap.getMapWidth();
    }

    public double getHeight() {
        return tileMap.getMapHeight();
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public double getFriction() {
        return friction;
    }

    public double getGravity() {
        return gravity;
    }

    public void draw(Graphics g, VirtualCamera camera) {
        tileMap.draw(g, camera);
    }
}
