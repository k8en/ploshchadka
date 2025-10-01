package org.kdepo.games.ploshchadka.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kdepo.games.ploshchadka.model.base.geometry.OrthogonalPolygon;
import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.base.geometry.Sphere;

public class CollisionUtilsTests {

    @Test
    void sphereToOrthogonalPolygonIntersectionXAxis() {
        OrthogonalPolygon polygonXAxis = new OrthogonalPolygon(
                new Point3D(0, 1, 1),
                new Point3D(0, -1, 1),
                new Point3D(0, -1, -1),
                new Point3D(0, 1, -1)
        );

        Assertions.assertEquals(OrthogonalPolygon.X_AXIS, polygonXAxis.getOrthogonalAxis());

        // Never intersects (above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 3, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 0, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -3, 1), polygonXAxis));

        // Partially intersects (by radius above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 3, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 0, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -3, 1), polygonXAxis));

        // Partially intersects (center on the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 3, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, 2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 2, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 2, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 0, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 2, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, -2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, -2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, -2, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -3, 1), polygonXAxis));

        // Partially intersects (by radius behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 3, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 0, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 0, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, -1, 1), polygonXAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -3, 1), polygonXAxis));

        // Never intersects (behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 3, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 0, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 0, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, -1, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -1, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, -2, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -2, 1), polygonXAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, -3, 1), polygonXAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -3, 1), polygonXAxis));
    }

    @Test
    void sphereToOrthogonalPolygonIntersectionYAxis() {
        OrthogonalPolygon polygonYAxis = new OrthogonalPolygon(
                new Point3D(-1, 0, 1),
                new Point3D(1, 0, 1),
                new Point3D(1, 0, -1),
                new Point3D(-1, 0, -1)
        );

        Assertions.assertEquals(OrthogonalPolygon.Y_AXIS, polygonYAxis.getOrthogonalAxis());

        // Never intersects (above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 3, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 0, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -3, 1), polygonYAxis));

        // Partially intersects (by radius above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 3, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 0, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -3, 1), polygonYAxis));

        // Partially intersects (center on the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 3, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, 2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, 0, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, 0, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, 0, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, 0, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 0, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, 0, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, 0, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, -2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, -2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, -2, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -3, 1), polygonYAxis));

        // Partially intersects (by radius behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 3, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 0, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 0, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, -1, 1), polygonYAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -3, 1), polygonYAxis));

        // Never intersects (behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 3, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 0, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 0, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, -1, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -1, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, -2, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -2, 1), polygonYAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, -3, 1), polygonYAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -3, 1), polygonYAxis));
    }

    @Test
    void sphereToOrthogonalPolygonIntersectionZAxis() {
        OrthogonalPolygon polygonZAxis = new OrthogonalPolygon(
                new Point3D(-1, -1, 0),
                new Point3D(1, -1, 0),
                new Point3D(1, 1, 0),
                new Point3D(-1, 1, 0)
        );

        Assertions.assertEquals(OrthogonalPolygon.Z_AXIS, polygonZAxis.getOrthogonalAxis());

        // Never intersects (above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 2, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 3, 1), polygonZAxis));

        // Partially intersects (by radius above the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 2, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 1, 1), polygonZAxis));

        // Partially intersects (center on the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, -1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, -1, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, 0, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, 0, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-2, 1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(2, 1, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 2, 0, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 2, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, 0, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, 0, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, 0, 1), polygonZAxis));

        // Partially intersects (by radius behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, -1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, -1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, -1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 0, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 0, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 0, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(-1, 1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(0, 1, -1, 1), polygonZAxis));
        Assertions.assertTrue(CollisionUtils.intersects(new Sphere(1, 1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 2, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, -1, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, -1, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -1, 1), polygonZAxis));

        // Never intersects (behind the plane)
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -3, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -2, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, -1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, -1, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 0, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 0, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 1, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 1, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 2, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 2, -3, 1), polygonZAxis));

        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-3, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-2, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(-1, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(0, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(1, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(2, 3, -3, 1), polygonZAxis));
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(3, 3, -3, 1), polygonZAxis));
    }

    @Test
    void sphereToOrthogonalPolygonIntersection() {
        OrthogonalPolygon polygonAxis = new OrthogonalPolygon(
                new Point3D(928.0, 60.0, 138.0),
                new Point3D(1002.0, 60.0, 138.0),
                new Point3D(1002.0, 60.0, 0.0),
                new Point3D(928.0, 60.0, 0.0)
        );

        Assertions.assertEquals(OrthogonalPolygon.Y_AXIS, polygonAxis.getOrthogonalAxis());
        Assertions.assertFalse(CollisionUtils.intersects(new Sphere(930.0371410967371, 54.58065293274879, 14.0, 14.0), polygonAxis));
    }

}
