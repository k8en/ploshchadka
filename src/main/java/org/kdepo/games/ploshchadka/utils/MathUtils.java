package org.kdepo.games.ploshchadka.utils;

import org.kdepo.games.ploshchadka.model.base.geometry.Vector2D;

public class MathUtils {

    public static Vector2D getVector2D(double fromX, double fromY, double toX, double toY) {
        return new Vector2D(toX - fromX, toY - fromY);
    }

    public static double getDistance2D(double fromX, double fromY, double toX, double toY) {
        double dX = toX - fromX;
        double dY = toY - fromY;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static Vector2D getVector2DNormalized(double x, double y) {
        double distance = Math.sqrt(x * x + y * y);
        return new Vector2D(x / distance, y / distance);
    }

}
