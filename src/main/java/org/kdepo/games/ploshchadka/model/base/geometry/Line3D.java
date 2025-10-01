package org.kdepo.games.ploshchadka.model.base.geometry;

import java.util.Objects;

public class Line3D {

    private Point3D a;

    private Point3D b;

    public Line3D() {

    }

    public Line3D(Point3D a, Point3D b) {
        this.a = a;
        this.b = b;
    }

    public Point3D getA() {
        return a;
    }

    public void setA(Point3D a) {
        this.a = a;
    }

    public Point3D getB() {
        return b;
    }

    public void setB(Point3D b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line3D line3D = (Line3D) o;
        return Objects.equals(a, line3D.a) && Objects.equals(b, line3D.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Line3D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
