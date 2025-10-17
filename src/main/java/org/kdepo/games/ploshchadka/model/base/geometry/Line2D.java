package org.kdepo.games.ploshchadka.model.base.geometry;

import java.util.Objects;

public class Line2D {

    private Point2D a;

    private Point2D b;

    public Line2D() {

    }

    public Line2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
    }

    public Point2D getA() {
        return a;
    }

    public void setA(Point2D a) {
        this.a = a;
    }

    public Point2D getB() {
        return b;
    }

    public void setB(Point2D b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Line2D line2D = (Line2D) o;
        return Objects.equals(a, line2D.a) && Objects.equals(b, line2D.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Line2D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
