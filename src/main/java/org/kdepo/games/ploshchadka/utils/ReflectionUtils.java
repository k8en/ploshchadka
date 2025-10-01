package org.kdepo.games.ploshchadka.utils;

import org.kdepo.games.ploshchadka.model.base.geometry.*;

public class ReflectionUtils {

    public static Vector3D reflectVector(Vector3D vector, Sphere sphere, OrthogonalPolygon polygon) {
        System.out.println("Reflect vector calculation started for " + vector + " " + sphere + " " + polygon);

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
                    System.out.println("Sector x0-r");
                    vector.setX(-vector.getX());
                    return vector;
                }
            }

            if (sphere.getY() <= polygon.getA().getY()
                    && sphere.getY() >= polygon.getB().getY()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 1
                System.out.println("Sector x1-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getB()));

            } else if (sphere.getY() < polygon.getB().getY()
                    && sphere.getZ() > polygon.getB().getZ()) {
                // Section 2
                System.out.println("Sector x2-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getB());

            } else if (sphere.getZ() <= polygon.getB().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()
                    && sphere.getY() < polygon.getB().getY()) {
                // Section 3
                System.out.println("Sector x3-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getB(), polygon.getC()));

            } else if (sphere.getY() < polygon.getC().getY()
                    && sphere.getZ() < polygon.getC().getZ()) {
                // Section 4
                System.out.println("Sector x4-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getC());

            } else if (sphere.getY() <= polygon.getD().getY()
                    && sphere.getY() >= polygon.getC().getY()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 5
                System.out.println("Sector x5-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getD(), polygon.getC()));

            } else if (sphere.getY() > polygon.getD().getY()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 6
                System.out.println("Sector x6-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getD());

            } else if (sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getD().getZ()
                    && sphere.getY() > polygon.getA().getY()) {
                // Section 7
                System.out.println("Sector x7-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getD()));

            } else if (sphere.getY() > polygon.getA().getY()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 8
                System.out.println("Sector x8-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getA());

            } else {
                throw new RuntimeException("Section is not resolved for " + sphere + " and " + polygon);
            }

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
                    System.out.println("Sector y0-r");
                    vector.setY(-vector.getY());
                    return vector;
                }
            }

            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getB().getX()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 1
                System.out.println("Sector y1-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getB()));

            } else if (sphere.getX() > polygon.getB().getX()
                    && sphere.getZ() > polygon.getB().getZ()) {
                // Section 2
                System.out.println("Sector y2-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getB());

            } else if (sphere.getZ() <= polygon.getB().getZ()
                    && sphere.getZ() >= polygon.getC().getZ()
                    && sphere.getX() > polygon.getB().getX()) {
                // Section 3
                System.out.println("Sector y3-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getB(), polygon.getC()));

            } else if (sphere.getX() > polygon.getC().getX()
                    && sphere.getZ() < polygon.getC().getZ()) {
                // Section 4
                System.out.println("Sector y4-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getC());

            } else if (sphere.getX() >= polygon.getD().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 5
                System.out.println("Sector y5-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getD(), polygon.getC()));

            } else if (sphere.getX() < polygon.getD().getX()
                    && sphere.getZ() < polygon.getD().getZ()) {
                // Section 6
                System.out.println("Sector y6-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getD());

            } else if (sphere.getZ() <= polygon.getA().getZ()
                    && sphere.getZ() >= polygon.getD().getZ()
                    && sphere.getX() < polygon.getA().getX()) {
                // Section 7
                System.out.println("Sector y7-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getD()));

            } else if (sphere.getX() < polygon.getA().getX()
                    && sphere.getZ() > polygon.getA().getZ()) {
                // Section 8
                System.out.println("Sector y8-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getA());

            } else {
                throw new RuntimeException("Section is not resolved for " + sphere + " and " + polygon);
            }

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
                    System.out.println("Sector z0-r");
                    vector.setZ(-vector.getZ());
                    return vector;
                }
            }

            if (sphere.getX() >= polygon.getA().getX()
                    && sphere.getX() <= polygon.getB().getX()
                    && sphere.getY() < polygon.getA().getY()) {
                // Section 1
                System.out.println("Sector z1-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getB()));

            } else if (sphere.getX() > polygon.getB().getX()
                    && sphere.getY() < polygon.getB().getY()) {
                // Section 2
                System.out.println("Sector z2-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getB());

            } else if (sphere.getY() >= polygon.getB().getY()
                    && sphere.getY() <= polygon.getC().getY()
                    && sphere.getX() > polygon.getB().getX()) {
                // Section 3
                System.out.println("Sector z3-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getB(), polygon.getC()));

            } else if (sphere.getX() > polygon.getC().getX()
                    && sphere.getY() > polygon.getC().getY()) {
                // Section 4
                System.out.println("Sector z4-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getC());

            } else if (sphere.getX() >= polygon.getD().getX()
                    && sphere.getX() <= polygon.getC().getX()
                    && sphere.getY() > polygon.getD().getY()) {
                // Section 5
                System.out.println("Sector z5-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getD(), polygon.getC()));

            } else if (sphere.getX() < polygon.getD().getX()
                    && sphere.getY() > polygon.getD().getY()) {
                // Section 6
                System.out.println("Sector z6-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getD());

            } else if (sphere.getY() >= polygon.getA().getY()
                    && sphere.getY() <= polygon.getD().getY()
                    && sphere.getX() < polygon.getA().getX()) {
                // Section 7
                System.out.println("Sector z7-r");
                return ReflectionUtils.reflectFromLine(sphere, vector, new Line3D(polygon.getA(), polygon.getD()));

            } else if (sphere.getX() < polygon.getA().getX()
                    && sphere.getY() < polygon.getA().getY()) {
                // Section 8
                System.out.println("Sector z8-r");
                return ReflectionUtils.reflectFromPoint(sphere, vector, polygon.getA());

            } else {
                throw new RuntimeException("Section is not resolved for " + sphere + " and " + polygon);
            }

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

    /**
     * Calculates moving sphere reflection in case of collision with point
     *
     * @param sphere         moving sphere
     * @param movementVector normalized movement vector
     * @param point          point for collision
     * @return reflected normalized movement vector
     */
    public static Vector3D reflectFromPoint(Sphere sphere, Vector3D movementVector, Point3D point) {
        // Normal for a point collision is a vector from the point to the center of the sphere
        Vector3D normal = new Vector3D(
                sphere.getX() - point.getX(),
                sphere.getY() - point.getY(),
                sphere.getZ() - point.getZ()
        );

        normal = MathUtils.getVector3DNormalized(normal);

        return calculateReflection(movementVector, normal);
    }

    /**
     * Calculates moving sphere reflection in case of collision with line
     *
     * @param sphere         moving sphere
     * @param movementVector normalized movement vector
     * @param line           line (strictly parallel to one of the coordinate axes)
     * @return reflected normalized movement vector
     */
    public static Vector3D reflectFromLine(Sphere sphere, Vector3D movementVector, Line3D line) {
        int lineAxis = getLineAxis(line);

        Vector3D normal = calculateLineNormal(sphere, line, lineAxis);

        return calculateReflection(movementVector, normal);
    }

    private static Vector3D calculateLineNormal(Sphere sphere, Line3D line, int lineAxis) {
        Vector3D normal = new Vector3D();

        switch (lineAxis) {
            case OrthogonalPolygon.X_AXIS:
                // Is parallel to X, normal in YZ-plane
                double distY = sphere.getY() - line.getA().getY();
                double distZ = sphere.getZ() - line.getA().getZ();
                normal = new Vector3D(0, distY, distZ);
                break;

            case OrthogonalPolygon.Y_AXIS:
                // Is parallel to Y, normal in XZ-plane
                double distX = sphere.getX() - line.getA().getX();
                double distZ2 = sphere.getZ() - line.getA().getZ();
                normal = new Vector3D(distX, 0, distZ2);
                break;

            case OrthogonalPolygon.Z_AXIS:
                // Is parallel to Z, normal in XY-plane
                double distX2 = sphere.getX() - line.getA().getX();
                double distY2 = sphere.getY() - line.getA().getY();
                normal = new Vector3D(distX2, distY2, 0);
                break;
        }

        return MathUtils.getVector3DNormalized(normal);
    }

    /**
     * Resolve axis that is parallel to line
     */
    private static int getLineAxis(Line3D line) {
        Point3D a = line.getA();
        Point3D b = line.getB();

        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            return OrthogonalPolygon.Z_AXIS; // Is parallel to Z
        } else if (a.getX() == b.getX() && a.getZ() == b.getZ()) {
            return OrthogonalPolygon.Y_AXIS; // Is parallel to Y
        } else if (a.getY() == b.getY() && a.getZ() == b.getZ()) {
            return OrthogonalPolygon.X_AXIS; // Is parallel to X
        } else {
            throw new IllegalArgumentException("Line is not aligned with coordinate axes");
        }
    }

    /**
     * Calculates reflection with the next formula R = V - 2*(V·N)*N
     */
    private static Vector3D calculateReflection(Vector3D vector, Vector3D normal) {
        double dotProduct = MathUtils.dot(vector, normal);

        Vector3D reflection = new Vector3D(
                vector.getX() - 2 * dotProduct * normal.getX(),
                vector.getY() - 2 * dotProduct * normal.getY(),
                vector.getZ() - 2 * dotProduct * normal.getZ()
        );

        return MathUtils.getVector3DNormalized(reflection);
    }
}
