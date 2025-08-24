package org.kdepo.games.ploshchadka.model.base.geometry;

import java.util.Objects;

public class VirtualRectangle {

    /**
     * Top left corner
     */
    protected double x;

    /**
     * Top left corner
     */
    protected double y;

    protected double width;
    protected double height;

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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VirtualRectangle that = (VirtualRectangle) o;
        return Double.compare(x, that.x) == 0
                && Double.compare(y, that.y) == 0
                && Double.compare(width, that.width) == 0
                && Double.compare(height, that.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
