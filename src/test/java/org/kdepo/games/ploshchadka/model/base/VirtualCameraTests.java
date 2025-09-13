package org.kdepo.games.ploshchadka.model.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VirtualCameraTests {

    @Test
    void setCameraCenter() {
        VirtualCamera camera = new VirtualCamera(100, 100);

        camera.setCameraCenter(0, 0);
        Assertions.assertEquals(-50, camera.getX());
        Assertions.assertEquals(-50, camera.getY());
        Assertions.assertEquals(-50, camera.getMovementBounds().getX());
        Assertions.assertEquals(-50, camera.getMovementBounds().getY());

        camera.setCameraCenter(100, 100);
        Assertions.assertEquals(50, camera.getX());
        Assertions.assertEquals(50, camera.getY());
        Assertions.assertEquals(50, camera.getMovementBounds().getX());
        Assertions.assertEquals(50, camera.getMovementBounds().getY());
    }

    @Test
    void addXY() {
        VirtualCamera camera = new VirtualCamera(100, 100);

        camera.addToX(20);
        Assertions.assertEquals(-30, camera.getX());
        Assertions.assertEquals(-30, camera.getMovementBounds().getX());

        camera.addToY(20);
        Assertions.assertEquals(-30, camera.getY());
        Assertions.assertEquals(-30, camera.getMovementBounds().getY());

        camera.addToX(-20);
        Assertions.assertEquals(-50, camera.getX());
        Assertions.assertEquals(-50, camera.getMovementBounds().getX());

        camera.addToY(-20);
        Assertions.assertEquals(-50, camera.getY());
        Assertions.assertEquals(-50, camera.getMovementBounds().getY());
    }

    @Test
    void addXY2() {
        VirtualCamera camera = new VirtualCamera(100, 100);

        camera.getMovementBounds().setWidth(80);
        camera.getMovementBounds().setHeight(80);
        camera.getMovementBounds().setX(-40);
        camera.getMovementBounds().setY(-40);

        camera.addToX(20);
        Assertions.assertEquals(-30, camera.getX());
        Assertions.assertEquals(-20, camera.getMovementBounds().getX());

        camera.addToY(20);
        Assertions.assertEquals(-30, camera.getY());
        Assertions.assertEquals(-20, camera.getMovementBounds().getY());

        camera.addToX(-20);
        Assertions.assertEquals(-50, camera.getX());
        Assertions.assertEquals(-40, camera.getMovementBounds().getX());

        camera.addToY(-20);
        Assertions.assertEquals(-50, camera.getY());
        Assertions.assertEquals(-40, camera.getMovementBounds().getY());
    }
}
