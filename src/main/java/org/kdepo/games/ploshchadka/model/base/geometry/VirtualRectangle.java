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

    public VirtualRectangle() {

    }

    public VirtualRectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void addToX(double delta) {
        this.x = this.x + delta;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addToY(double delta) {
        this.y = this.y + delta;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void addToWidth(double delta) {
        this.width = this.width + delta;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void addToHeight(double delta) {
        this.height = this.height + delta;
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

    @Override
    public String toString() {
        return "VirtualRectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
