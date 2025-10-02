package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.tiles.TileMap;

import java.awt.*;

public class Ground {

    private TileMap tileMap;

    private int maxHeight;

    private double friction;
    private double gravity;

    public Ground() {
        tileMap = new TileMap();
        tileMap.load("training_ground.map");

        tileMap.setTileWidth(32);
        tileMap.setTileHeight(32);
        tileMap.setMapWidth(tileMap.getTileWidth() * tileMap.getTiles()[0].length);
        tileMap.setMapHeight(tileMap.getTileHeight() * tileMap.getTiles().length);

        tileMap.setX(-tileMap.getMapWidth() / 2);
        tileMap.setY(-tileMap.getMapHeight() / 2 - 32);

        friction = 0.02d;
        gravity = 0.0024d;

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
