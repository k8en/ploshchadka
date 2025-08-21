package org.kdepo.games.ploshchadka.model.base.geometry;

public class VirtualRectangle {

    /**
     * Top left corner
     */
    private double x;

    /**
     * Top left corner
     */
    private double y;

    private double width;
    private double height;

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
}
