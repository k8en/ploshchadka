package org.kdepo.games.ploshchadka.utils;

public class TileMapUtils {

    public static int getTileColumnByX(double x, double tileMapX, int tileMapWidth, int tileWidth) {
        if (x < tileMapX) {
            return -1;
        }
        if (x >= tileMapX + tileMapWidth) {
            return -1;
        }

        double localX = x - tileMapX;
        return (int) Math.floor(localX / tileWidth);
    }

    public static int getTileRowByY(double y, double tileMapY, int tileMapHeight, int tileHeight) {
        if (y < tileMapY) {
            return -1;
        }
        if (y >= tileMapY + tileMapHeight) {
            return -1;
        }

        double localY = y - tileMapY;
        return (int) Math.floor(localY / tileHeight);
    }
}
