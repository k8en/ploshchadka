package org.kdepo.games.ploshchadka.model.base.geometry;

import java.util.Objects;

public class OrthogonalPolygon {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    public static final int Z_AXIS = 2;

    /**
     * Top left corner
     */
    private Point3D a;

    /**
     * Top right corner
     */
    private Point3D b;

    /**
     * Bottom right corner
     */
    private Point3D c;

    /**
     * Bottom left corner
     */
    private Point3D d;

    private int orthogonalAxis;
    private double orthogonalCoordinate;

    public OrthogonalPolygon() {

    }

    public OrthogonalPolygon(Point3D a, Point3D b, Point3D c, Point3D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        // Calculate orthogonality axis
        if (a.getX() == b.getX() && a.getX() == c.getX() && a.getX() == d.getX()) {
            orthogonalAxis = OrthogonalPolygon.X_AXIS;
            orthogonalCoordinate = a.getX();
        } else if (a.getY() == b.getY() && a.getY() == c.getY() && a.getY() == d.getY()) {
            orthogonalAxis = OrthogonalPolygon.Y_AXIS;
            orthogonalCoordinate = a.getY();
        } else if (a.getZ() == b.getZ() && a.getZ() == c.getZ() && a.getZ() == d.getZ()) {
            orthogonalAxis = OrthogonalPolygon.Z_AXIS;
            orthogonalCoordinate = a.getZ();
        } else {
            throw new IllegalArgumentException(
                    "Cannot resolve orthogonality for "
                            + "a=[" + a.getX() + " " + a.getY() + " " + a.getZ() + "], "
                            + "b=[" + b.getX() + " " + b.getY() + " " + b.getZ() + "], "
                            + "c=[" + c.getX() + " " + c.getY() + " " + c.getZ() + "], "
                            + "d=[" + d.getX() + " " + d.getY() + " " + d.getZ() + "], "
            );
        }
    }

    public Point3D getA() {
        return a;
    }

    public Point3D getB() {
        return b;
    }

    public Point3D getC() {
        return c;
    }

    public Point3D getD() {
        return d;
    }

    public int getOrthogonalAxis() {
        return orthogonalAxis;
    }

    public double getOrthogonalCoordinate() {
        return orthogonalCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrthogonalPolygon that = (OrthogonalPolygon) o;
        return orthogonalAxis == that.orthogonalAxis
                && Double.compare(orthogonalCoordinate, that.orthogonalCoordinate) == 0
                && Objects.equals(a, that.a)
                && Objects.equals(b, that.b)
                && Objects.equals(c, that.c)
                && Objects.equals(d, that.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, orthogonalAxis, orthogonalCoordinate);
    }

    @Override
    public String toString() {
        return "OrthogonalPolygon2{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", orthogonalAxis=" + orthogonalAxis +
                ", orthogonalCoordinate=" + orthogonalCoordinate +
                '}';
    }
}
