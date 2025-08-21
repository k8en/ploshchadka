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
    }
}
