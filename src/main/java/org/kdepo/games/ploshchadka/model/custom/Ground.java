package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.tiles.TileMap;

import java.awt.*;

public class Ground {

    private TileMap tileMap;

    public Ground() {
        tileMap = new TileMap();
        tileMap.load("training_ground.map");

        tileMap.setTileWidth(32);
        tileMap.setTileHeight(32);
        tileMap.setMapWidth(32 * tileMap.getTiles()[0].length);
        tileMap.setMapHeight(32 * tileMap.getTiles().length);

        tileMap.setX(-tileMap.getMapWidth() / 2);
        tileMap.setY(-tileMap.getMapHeight() / 2);
    }

    public void draw(Graphics g, VirtualCamera camera) {
        tileMap.draw(g, camera);
    }
}
