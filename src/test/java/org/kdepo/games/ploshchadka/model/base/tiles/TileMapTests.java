package org.kdepo.games.ploshchadka.model.base.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileMapTests {

    @Test
    void getTileColumn() {
        int x = 0;
        int y = 0;
        int tileWidth = 32;
        int tileHeight = 32;
        int columns = 10;
        int rows = 10;

        TileMap tileMap = new TileMap();
        tileMap.setX(x);
        tileMap.setY(y);
        tileMap.setTileWidth(tileWidth);
        tileMap.setTileHeight(tileHeight);
        tileMap.setMapWidth(tileWidth * columns);
        tileMap.setMapHeight(tileHeight * rows);

        Assertions.assertEquals(0, tileMap.getTileColumn(0), "At the left border");
        Assertions.assertEquals(0, tileMap.getTileColumn(1));

        Assertions.assertEquals(1, tileMap.getTileColumn(32), "At the single tile width");
        Assertions.assertEquals(-1, tileMap.getTileColumn(tileWidth * columns), "At the tile map width");

        Assertions.assertEquals(-1, tileMap.getTileColumn(-1), "Out of bounds at left");
        Assertions.assertEquals(-1, tileMap.getTileColumn(-0.1), "Out of bounds at left");
        Assertions.assertEquals(-1, tileMap.getTileColumn(tileWidth * columns), "Out of bounds at right");
        Assertions.assertEquals(-1, tileMap.getTileColumn(tileWidth * columns + 0.1), "Out of bounds at right");
    }

    @Test
    void getTileColumn2() {
        int x = -160;
        int y = -160;
        int tileWidth = 32;
        int tileHeight = 32;
        int columns = 10;
        int rows = 10;

        TileMap tileMap = new TileMap();
        tileMap.setX(x);
        tileMap.setY(y);
        tileMap.setTileWidth(tileWidth);
        tileMap.setTileHeight(tileHeight);
        tileMap.setMapWidth(tileWidth * columns);
        tileMap.setMapHeight(tileHeight * rows);

        Assertions.assertEquals(-1, tileMap.getTileColumn(160), "Border test");
    }
}
