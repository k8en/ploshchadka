package org.kdepo.games.ploshchadka.utils;

import org.kdepo.games.ploshchadka.model.base.geometry.OrthogonalPolygon;
import org.kdepo.games.ploshchadka.model.base.geometry.Sphere;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;

public class ReflectionUtils {

    public static void reflectVector(Vector3D vector, Sphere sphere, OrthogonalPolygon polygon) {
        // Calculate distance between sphere center and polygon plane based on orthogonal axis
        double sphereToPlaneDistance;
        if (polygon.getOrthogonalAxis() == OrthogonalPolygon.X_AXIS) {
            sphereToPlaneDistance = Math.abs(sphere.getX() - polygon.getOrthogonalCoordinate());
        } else if (polygon.getOrthogonalAxis() == OrthogonalPolygon.Y_AXIS) {
            sphereToPlaneDistance = Math.abs(sphere.getY() - polygon.getOrthogonalCoordinate());
        } else if (polygon.getOrthogonalAxis() == OrthogonalPolygon.Z_AXIS) {
            sphereToPlaneDistance = Math.abs(sphere.getZ() - polygon.getOrthogonalCoordinate());
        } else {
            throw new IllegalArgumentException("Orthogonal coordinate is not calculated for " + polygon);
        }

        // If distance is more than sphere radius then there cannot be any reflection
        if (sphereToPlaneDistance > sphere.getRadius()) {
            throw new IllegalArgumentException("Reflection cannot be calculated for " + vector + " " + sphere + " " + polygon);
        }

        // Check based on section and axis
        if (polygon.getOrthogonalAxis() == OrthogonalPolygon.X_AXIS) {
            if (sphere.getY() <= polygon.getA().getY()
                    && sphere.getY() >= polygon.getC().getY()
                    && sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()) {
                // Section 0
                if (ReflectionUtils.isApproaching(
                        sphere.getX(), sphere.getY(), vector.getZ(),
                        vector,
                        polygon.getOrthogonalAxis(), polygon.getOrthogonalCoordinate())
                ) {
                    vector.setX(-vector.getX());
                }
            }

            if (sphere.getY() <= polygon.getA().getY()
                    && sphere.getY() >= polygon.getB().getY()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 1
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), sphere.getY(), polygon.getA().getZ()
                );
            } else if (sphere.getY() < polygon.getB().getY()
                    && sphere.getZ() > polygon.getB().getZ()) {
                // Section 2
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), polygon.getB().getY(), polygon.getB().getZ()
                );
            } else if (sphere.getZ() <= polygon.getB().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()
                    && sphere.getY() < polygon.getB().getY()) {
                // Section 3
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), polygon.getB().getY(), sphere.getZ()
                );
            } else if (sphere.getY() < polygon.getC().getY()
                    && sphere.getZ() < polygon.getC().getZ()) {
                // Section 4
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getC().getX(), polygon.getC().getY(), polygon.getC().getZ()
                );
            } else if (sphere.getY() <= polygon.getD().getY()
                    && sphere.getY() >= polygon.getC().getY()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 5
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getD().getX(), sphere.getY(), polygon.getD().getZ()
                );
            } else if (sphere.getY() > polygon.getD().getY()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Sector 6
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getD().getX(), polygon.getD().getY(), polygon.getD().getZ()
                );
            } else if (sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getD().getZ()
                    && sphere.getY() > polygon.getA().getY()) {
                // Sector 7
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), polygon.getA().getY(), sphere.getZ()
                );
            } else if (sphere.getY() > polygon.getA().getY()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Sector 8
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), polygon.getA().getY(), polygon.getA().getZ()
                );
            } else {
                throw new RuntimeException("Sector is not resolved for " + sphere + " and " + polygon);
            }

            //return sphereToPlaneDistance <= sphere.getRadius();
            System.out.println("Vector is not updated for X");

        } else if (polygon.getOrthogonalAxis() == OrthogonalPolygon.Y_AXIS) {
            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()) {
                // Section 0
                if (ReflectionUtils.isApproaching(
                        sphere.getX(), sphere.getY(), vector.getZ(),
                        vector,
                        polygon.getOrthogonalAxis(), polygon.getOrthogonalCoordinate())
                ) {
                    vector.setY(-vector.getY());
                }
            }

            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getB().getX()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 1
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        sphere.getX(), polygon.getA().getY(), polygon.getA().getZ()
                );
            } else if (sphere.getX() > polygon.getB().getX()
                    && sphere.getZ() > polygon.getB().getZ()) {
                // Section 2
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), polygon.getB().getY(), polygon.getB().getZ()
                );
            } else if (sphere.getZ() <= polygon.getB().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()
                    && sphere.getX() > polygon.getB().getX()) {
                // Section 3
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), polygon.getB().getY(), sphere.getZ()
                );
            } else if (sphere.getX() > polygon.getC().getX()
                    && sphere.getZ() < polygon.getC().getZ()) {
                // Section 4
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getC().getX(), polygon.getC().getY(), polygon.getC().getZ()
                );
            } else if (sphere.getX() >= polygon.getD().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 5
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        sphere.getX(), polygon.getD().getY(), polygon.getD().getZ()
                );
            } else if (sphere.getX() < polygon.getD().getX()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Sector 6
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getD().getX(), polygon.getD().getY(), polygon.getD().getZ()
                );
            } else if (sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getD().getZ()
                    && sphere.getX() < polygon.getA().getX()) {
                // Sector 7
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), polygon.getA().getY(), sphere.getZ()
                );
            } else if (sphere.getX() < polygon.getA().getX()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Sector 8
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), polygon.getA().getY(), polygon.getA().getZ()
                );
            } else {
                throw new RuntimeException("Sector is not resolved for " + sphere + " and " + polygon);
            }

            //return sphereToPlaneDistance <= sphere.getRadius();
            System.out.println("Vector is not updated for Y");

        } else if (polygon.getOrthogonalAxis() == OrthogonalPolygon.Z_AXIS) {
            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getY() >= polygon.getA().getY()
                    && sphere.getY() <= polygon.getC().getY()) {
                // Section 0
                if (ReflectionUtils.isApproaching(
                        sphere.getX(), sphere.getY(), vector.getZ(),
                        vector,
                        polygon.getOrthogonalAxis(), polygon.getOrthogonalCoordinate())
                ) {
                    vector.setZ(-vector.getZ());
                }
            }

            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getB().getX()
                    && sphere.getY() < polygon.getA().getY()) {
                // Section 1
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        sphere.getX(), polygon.getA().getY(), polygon.getA().getZ()
                );
            } else if (sphere.getX() > polygon.getB().getX()
                    && sphere.getY() < polygon.getB().getY()) {
                // Section 2
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), polygon.getB().getY(), polygon.getB().getZ()
                );
            } else if (sphere.getY() >= polygon.getB().getY()
                    && sphere.getY() <= polygon.getC().getY()
                    && sphere.getX() > polygon.getB().getX()) {
                // Section 3
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getB().getX(), sphere.getY(), polygon.getB().getZ()
                );
            } else if (sphere.getX() > polygon.getC().getX()
                    && sphere.getY() > polygon.getC().getY()) {
                // Section 4
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getC().getX(), polygon.getC().getY(), polygon.getC().getZ()
                );
            } else if (sphere.getX() >= polygon.getD().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getY() > polygon.getD().getY()) {
                // Section 5
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        sphere.getX(), polygon.getD().getY(), polygon.getD().getZ()
                );
            } else if (sphere.getX() < polygon.getD().getX()
                    && sphere.getY() > polygon.getD().getY()) {
                // Sector 6
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getD().getX(), polygon.getD().getY(), polygon.getD().getZ()
                );
            } else if (sphere.getY() >= polygon.getA().getY()
                    && sphere.getY() <= polygon.getD().getY()
                    && sphere.getX() < polygon.getA().getX()) {
                // Sector 7
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), sphere.getY(), polygon.getA().getZ()
                );
            } else if (sphere.getX() < polygon.getA().getX()
                    && sphere.getY() < polygon.getA().getY()) {
                // Sector 8
                sphereToPlaneDistance = MathUtils.getDistance3D(
                        sphere.getX(), sphere.getY(), sphere.getZ(),
                        polygon.getA().getX(), polygon.getA().getY(), polygon.getA().getZ()
                );
            } else {
                throw new RuntimeException("Sector is not resolved for " + sphere + " and " + polygon);
            }

            //return sphereToPlaneDistance <= sphere.getRadius();
            System.out.println("Vector is not updated for Z");

        } else {
            throw new IllegalArgumentException("Orthogonal coordinate is not calculated for " + polygon);
        }

    }

    public static boolean isApproaching(double vectorStartX, double vectorStartY, double vectorStartZ, Vector3D vector, int axisToCheck, double axisStartCoordinate) {
        double vectorStartCoordinate;
        double vectorAxisToCheck;

        if (OrthogonalPolygon.X_AXIS == axisToCheck) {
            vectorStartCoordinate = vectorStartX;
            vectorAxisToCheck = vector.getX();

        } else if (OrthogonalPolygon.Y_AXIS == axisToCheck) {
            vectorStartCoordinate = vectorStartY;
            vectorAxisToCheck = vector.getY();

        } else if (OrthogonalPolygon.Z_AXIS == axisToCheck) {
            vectorStartCoordinate = vectorStartZ;
            vectorAxisToCheck = vector.getZ();

        } else {
            throw new IllegalArgumentException("Axis to check is not resolved for " + axisToCheck);
        }

        if (vectorStartCoordinate > axisStartCoordinate) {
            return vectorAxisToCheck < 0;
        } else {
            return vectorAxisToCheck > 0;
        }
    }
}
