package org.kdepo.games.ploshchadka.utils;

import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.base.geometry.Vector3D;
import org.kdepo.games.ploshchadka.model.custom.Impulse;

public class BallisticsUtils {

    private static final int ANGLE_PARAM_IDX = 0;
    private static final int SPEED_PARAM_IDX = 1;

    /**
     * Класс для расчета импульса мяча для попадания из startPoint в targetPoint
     *
     * @param startPoint  - ball start position
     * @param targetPoint - target position to hit
     * @param g           - acceleration of gravity
     * @return instance of Impulse class with necessary parameters
     */
    public static Impulse calculateImpulse(Point3D startPoint, Point3D targetPoint, double g) {
        // Вычисляем горизонтальное расстояние и разность высот
        double dx = targetPoint.getX() - startPoint.getX();
        double dy = targetPoint.getY() - startPoint.getY();
        double dz = targetPoint.getZ() - startPoint.getZ();

        // Horizontal distance by projection on XY plane
        double horizontalDistance = Math.sqrt(dx * dx + dy * dy);

        // Разность высот
        double heightDifference = dz;

        // Вычисляем требуемые угол и начальную скорость
        double[] solution = calculateBallisticParameters(horizontalDistance, heightDifference, g);

        // Создаем единичный вектор направления в горизонтальной плоскости
        Vector3D horizontalDirection = calculateHorizontalDirection(dx, dy, horizontalDistance);

        // Создаем полный вектор направления с учетом вертикальной компоненты
        Vector3D fullDirection = calculateFullDirection(horizontalDirection, solution[BallisticsUtils.ANGLE_PARAM_IDX]);

        return new Impulse(fullDirection, solution[BallisticsUtils.SPEED_PARAM_IDX]);
    }

    /**
     * Расчет баллистических параметров (угол и начальная скорость)
     */
    private static double[] calculateBallisticParameters(double horizontalDistance,
                                                         double heightDifference,
                                                         double g) {
        // Используем оптимальный угол 45 градусов для минимальной начальной скорости
        double optimalAngle = Math.PI / 4; // 45 градусов

        // Уравнения движения под углом к горизонту:
        // x = v₀·cos(α)·t
        // z = v₀·sin(α)·t - (g·t²)/2

        // Из первого уравнения: t = x/(v₀·cos(α))
        // Подставляем во второе:
        // z = v₀·sin(α)·(x/(v₀·cos(α))) - g/2·(x/(v₀·cos(α)))²
        // z = x·tan(α) - (g·x²)/(2·v₀²·cos²(α))

        // Выражаем v₀:
        // (g·x²)/(2·v₀²·cos²(α)) = x·tan(α) - z
        // v₀² = (g·x²) / [2·cos²(α)·(x·tan(α) - z)]

        double cosAlpha = Math.cos(optimalAngle);
        double sinAlpha = Math.sin(optimalAngle);
        double tanAlpha = sinAlpha / cosAlpha;

        double denominator = 2 * cosAlpha * cosAlpha * (horizontalDistance * tanAlpha - heightDifference);

        if (denominator <= 0) {
            // Если цель недостижима под углом 45°, используем максимальный достижимый угол
            return calculateAlternativeSolution(horizontalDistance, heightDifference, g);
        }

        double v0Squared = (g * horizontalDistance * horizontalDistance) / denominator;

        if (v0Squared <= 0) {
            return calculateAlternativeSolution(horizontalDistance, heightDifference, g);
        }

        double initialSpeed = Math.sqrt(v0Squared);

        return new double[]{optimalAngle, initialSpeed};
    }

    /**
     * Альтернативное решение, когда цель недостижима под углом 45°
     */
    private static double[] calculateAlternativeSolution(double horizontalDistance,
                                                         double heightDifference,
                                                         double g) {
        // Используем угол, при котором мяч достигает максимальной высоты в цели
        // Это происходит когда tan(α) = (v₀² ± √(v₀⁴ - g(gx² + 2zv₀²))) / (gx)
        // Упрощаем: используем угол, при котором производная по времени в цели равна 0

        double minAngle = Math.toRadians(30); // минимальный угол 30 градусов
        double maxAngle = Math.toRadians(80); // максимальный угол 80 градусов

        double bestAngle = Math.toRadians(45);
        double bestSpeed = Double.MAX_VALUE;

        // Перебираем углы для поиска рабочего решения
        for (double angle = minAngle; angle <= maxAngle; angle += Math.toRadians(1)) {
            double cosAlpha = Math.cos(angle);
            double sinAlpha = Math.sin(angle);
            double tanAlpha = sinAlpha / cosAlpha;

            double denominator = 2 * cosAlpha * cosAlpha * (horizontalDistance * tanAlpha - heightDifference);

            if (denominator > 0) {
                double v0Squared = (g * horizontalDistance * horizontalDistance) / denominator;

                if (v0Squared > 0) {
                    double speed = Math.sqrt(v0Squared);
                    if (speed < bestSpeed) {
                        bestSpeed = speed;
                        bestAngle = angle;
                    }
                }
            }
        }

        if (bestSpeed == Double.MAX_VALUE) {
            // Если решение не найдено, используем угол 45° и большую скорость
            bestAngle = Math.toRadians(45);
            bestSpeed = Math.sqrt(2 * g * (horizontalDistance + Math.abs(heightDifference)));
        }

        return new double[]{bestAngle, bestSpeed};
    }

    /**
     * Расчет горизонтального направления (единичный вектор в плоскости XY)
     */
    private static Vector3D calculateHorizontalDirection(double dx, double dy, double horizontalDistance) {
        if (horizontalDistance == 0) {
            // Если горизонтальное расстояние нулевое, используем произвольное направление
            return new Vector3D(1, 0, 0);
        }

        double xComponent = dx / horizontalDistance;
        double yComponent = dy / horizontalDistance;

        return new Vector3D(xComponent, yComponent, 0);
    }

    /**
     * Расчет полного вектора направления с учетом вертикальной компоненты
     */
    private static Vector3D calculateFullDirection(Vector3D horizontalDirection, double angle) {
        double horizontalMagnitude = Math.sqrt(
                horizontalDirection.getX() * horizontalDirection.getX() +
                        horizontalDirection.getY() * horizontalDirection.getY()
        );

        if (horizontalMagnitude == 0) {
            // Вертикальный бросок
            return new Vector3D(0, 0, Math.sin(angle));
        }

        // Нормализуем горизонтальные компоненты
        double normX = horizontalDirection.getX() / horizontalMagnitude;
        double normY = horizontalDirection.getY() / horizontalMagnitude;

        // Добавляем вертикальную компоненту
        double resultX = normX * Math.cos(angle);
        double resultY = normY * Math.cos(angle);
        double resultZ = Math.sin(angle);

        // Нормализуем итоговый вектор
        double magnitude = Math.sqrt(resultX * resultX + resultY * resultY + resultZ * resultZ);

        return new Vector3D(
                resultX / magnitude,
                resultY / magnitude,
                resultZ / magnitude
        );
    }
}
