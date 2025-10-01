package org.kdepo.games.ploshchadka.utils;

import org.kdepo.games.ploshchadka.model.base.geometry.Vector2D;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;

public class MathUtils {

    public static Vector2D getVector2D(double fromX, double fromY, double toX, double toY) {
        return new Vector2D(toX - fromX, toY - fromY);
    }

    public static double getDistance2D(double fromX, double fromY, double toX, double toY) {
        double dX = toX - fromX;
        double dY = toY - fromY;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static double getDistance3D(double fromX, double fromY, double fromZ, double toX, double toY, double toZ) {
        double dX = toX - fromX;
        double dY = toY - fromY;
        double dZ = toZ - fromZ;
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public static Vector2D getVector2DNormalized(double x, double y) {
        double length = Math.sqrt(x * x + y * y);
        return new Vector2D(x / length, y / length);
    }

    public static Vector3D getVector3DNormalized(Vector3D v) {
        double length = Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
        if (length == 0) return v;
        return new Vector3D(v.getX() / length, v.getY() / length, v.getZ() / length);
    }

}
