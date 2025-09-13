package org.kdepo.games.ploshchadka.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileMapUtilsTests {

    @Test
    void getTileColumnByX() {

        Assertions.assertEquals(-1, TileMapUtils.getTileColumnByX(-1, 0, 100, 10));
        Assertions.assertEquals(0, TileMapUtils.getTileColumnByX(0, 0, 100, 10));
        Assertions.assertEquals(0, TileMapUtils.getTileColumnByX(1, 0, 100, 10));
        Assertions.assertEquals(1, TileMapUtils.getTileColumnByX(10, 0, 100, 10));
        Assertions.assertEquals(9, TileMapUtils.getTileColumnByX(99, 0, 100, 10));
        Assertions.assertEquals(-1, TileMapUtils.getTileColumnByX(100, 0, 100, 10));
        Assertions.assertEquals(-1, TileMapUtils.getTileColumnByX(101, 0, 100, 10));

    }

    @Test
    void getTileRowByY() {

        Assertions.assertEquals(-1, TileMapUtils.getTileRowByY(-1, 0, 100, 10));
        Assertions.assertEquals(0, TileMapUtils.getTileRowByY(0, 0, 100, 10));
        Assertions.assertEquals(0, TileMapUtils.getTileRowByY(1, 0, 100, 10));
        Assertions.assertEquals(1, TileMapUtils.getTileRowByY(10, 0, 100, 10));
        Assertions.assertEquals(9, TileMapUtils.getTileRowByY(99, 0, 100, 10));
        Assertions.assertEquals(-1, TileMapUtils.getTileRowByY(100, 0, 100, 10));
        Assertions.assertEquals(-1, TileMapUtils.getTileRowByY(101, 0, 100, 10));

    }

}
