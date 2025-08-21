package org.kdepo.games.ploshchadka.model.base;

import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;

public class VirtualCamera {

    private double x;
    private double y;
    private int width;
    private int height;

    public VirtualCamera(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public VirtualCamera(int width, int height) {
        System.out.println(width + " " + height);
        this.width = width;
        this.height = height;
        this.x = -width / 2.0;
        this.y = -height / 2.0;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCameraCenter(double x, double y) {
        this.x = x - width / 2.0;
        this.y = y - height / 2.0;
    }

    public boolean canSee(VirtualRectangle rectangle) {
        return true;
    }

    public double getScreenOffsetX(double x) {
        return x - this.x;
    }

    public double getScreenOffsetY(double y) {
        return y - this.y;
    }
}
