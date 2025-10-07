package org.kdepo.games.ploshchadka.model.base;

import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VirtualObject that = (VirtualObject) o;
        return id == that.id
                && Double.compare(centerX, that.centerX) == 0
                && Double.compare(centerY, that.centerY) == 0
                && Double.compare(centerZ, that.centerZ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, centerX, centerY, centerZ);
    }

    @Override
    public String toString() {
        return "VirtualObject{" +
                "id=" + id +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", centerZ=" + centerZ +
                "} " + super.toString();
    }
}
