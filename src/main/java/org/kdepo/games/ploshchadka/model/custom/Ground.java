package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.VirtualCamera;
import org.kdepo.games.ploshchadka.model.base.tiles.Tile;
import org.kdepo.games.ploshchadka.model.base.tiles.TileMap;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground {

    private TileMap tileMap;

    public Ground() {
        int rows = 10;
        int columns = 10;

        BufferedImage image = FileUtils.loadImage("tile_test.png");
        int tileWidth = image.getWidth();
        int tileHeight = image.getHeight();

        tileMap = new TileMap();
        tileMap.setX(-160);
        tileMap.setY(-160);
        tileMap.setTileWidth(tileWidth);
        tileMap.setTileHeight(tileHeight);
        tileMap.setMapWidth(tileWidth * columns);
        tileMap.setMapHeight(tileHeight * rows);

        Tile tile = new Tile();
        tile.setImage(image);

        Tile[][] tiles = new Tile[rows][columns];
        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[0].length; column++) {
                tiles[row][column] = tile;
            }
        }
        tileMap.setTiles(tiles);
    }

    public void draw(Graphics g, VirtualCamera camera) {
        tileMap.draw(g, camera);
    }
}
