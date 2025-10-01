package org.kdepo.games.ploshchadka.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kdepo.games.ploshchadka.model.base.geometry.OrthogonalPolygon;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;

public class ReflectionUtilsTests {

    @Test
    void isApproaching() {
        Assertions.assertTrue(ReflectionUtils.isApproaching(1, 0, 0, new Vector3D(-1, 0, 0), OrthogonalPolygon.X_AXIS, 0));
        Assertions.assertFalse(ReflectionUtils.isApproaching(-1, 0, 0, new Vector3D(-1, 0, 0), OrthogonalPolygon.X_AXIS, 0));

        Assertions.assertTrue(ReflectionUtils.isApproaching(0, 1, 0, new Vector3D(0, -1, 0), OrthogonalPolygon.Y_AXIS, 0));
        Assertions.assertFalse(ReflectionUtils.isApproaching(0, -1, 0, new Vector3D(0, -1, 0), OrthogonalPolygon.Y_AXIS, 0));

        Assertions.assertTrue(ReflectionUtils.isApproaching(0, 0, 1, new Vector3D(0, 0, -1), OrthogonalPolygon.Z_AXIS, 0));
        Assertions.assertFalse(ReflectionUtils.isApproaching(0, 0, -1, new Vector3D(0, 0, -1), OrthogonalPolygon.Z_AXIS, 0));
    }

}
