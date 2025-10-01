package org.kdepo.games.ploshchadka.model.base.geometry;

import java.util.Objects;

public class Sphere {

    private double x;

    private double y;

    private double z;

    private double radius;

    public Sphere() {

    }

    public Sphere(double x, double y, double z, double radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(x, sphere.x) == 0
                && Double.compare(y, sphere.y) == 0
                && Double.compare(z, sphere.z) == 0
                && Double.compare(radius, sphere.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, radius);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", radius=" + radius +
                '}';
    }
}
