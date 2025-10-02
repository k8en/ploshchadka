package org.kdepo.games.ploshchadka.model.base;

import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;

public class VirtualObject extends VirtualRectangle {

    /**
     * Should be unique object identifier
     */
    protected int id;

    /**
     * From left to right
     */
    protected double centerX;

    /**
     * From top to bottom
     */
    protected double centerY;

    /**
     * From bottom to top
     */
    protected double centerZ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getCenterZ() {
        return centerZ;
    }

    public void setCenterZ(double centerZ) {
        this.centerZ = centerZ;
    }
}
