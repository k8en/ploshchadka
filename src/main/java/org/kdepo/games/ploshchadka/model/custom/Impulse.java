package org.kdepo.games.ploshchadka.model.custom;

import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;

import java.util.Objects;

public class Impulse {

    private Vector3D direction;

    private double speed;

    public Impulse() {

    }

    public Impulse(Vector3D direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Impulse impulse = (Impulse) o;
        return Double.compare(speed, impulse.speed) == 0 && Objects.equals(direction, impulse.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, speed);
    }

    @Override
    public String toString() {
        return "Impulse{" +
                "direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
