package org.kdepo.games.ploshchadka.model.base;

import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;

public class VirtualCamera extends VirtualRectangle {

    private VirtualRectangle movementBounds;

    public VirtualCamera(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.movementBounds = new VirtualRectangle();
        movementBounds.setX(x);
        movementBounds.setY(y);
        movementBounds.setWidth(width);
        movementBounds.setHeight(height);
    }

    public VirtualCamera(int width, int height) {
        System.out.println(width + " " + height);
        this.width = width;
        this.height = height;
        this.x = -width / 2.0;
        this.y = -height / 2.0;

        this.movementBounds = new VirtualRectangle();
        movementBounds.setX(this.x);
        movementBounds.setY(this.y);
        movementBounds.setWidth(this.width);
        movementBounds.setHeight(this.height);
    }

    @Override
    public void setX(double x) {
        double delta = x - this.x;
        super.setX(x);
        movementBounds.addToX(delta);
    }

    @Override
    public void addToX(double delta) {
        super.addToX(delta);
        movementBounds.addToX(delta);
    }

    @Override
    public void setY(double y) {
        double delta = y - this.y;
        super.setY(y);
        movementBounds.addToY(delta);
    }

    @Override
    public void addToY(double delta) {
        super.addToY(delta);
        movementBounds.addToY(delta);
    }

    public VirtualRectangle getMovementBounds() {
        return movementBounds;
    }

    public void setMovementBounds(VirtualRectangle movementBounds) {
        this.movementBounds = movementBounds;
    }

    public void setCameraCenter(double x, double y) {
        this.x = x - width / 2.0;
        this.y = y - height / 2.0;
        movementBounds.setX(x - movementBounds.getWidth() / 2);
        movementBounds.setY(y - movementBounds.getHeight() / 2);
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
