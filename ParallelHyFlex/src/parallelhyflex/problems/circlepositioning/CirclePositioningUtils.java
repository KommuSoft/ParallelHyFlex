package parallelhyflex.problems.circlepositioning;

/**
 *
 * @author kommusoft
 */
public final class CirclePositioningUtils {

    private CirclePositioningUtils() {
    }
    
    public static double calculateCircleAreaRadiusSquare (double r2) {
        return Math.PI*r2;
    }

    public static double calculateCircleOverlapArea(double x1, double y1, double r1, double x2, double y2, double r2) {
        double dx = (x2 - x1);
        double dy = (y2 - y1);
        double d2 = dx * dx + dy * dy;
        double d = Math.sqrt(d2);
        double r12 = r1 * r1;
        double r22 = r2 * r2;
        if (d + r2 <= r1) {
            return calculateCircleAreaRadiusSquare(r22);
        } else if (d + r1 <= r2) {
            return calculateCircleAreaRadiusSquare(r12);
        } else {
            double D = -(d + r1 + r2) * (d - r1 + r2) * (d + r1 - r2) * (d - r1 - r2);
            if (D <= 0.0d) {
                return 0.0d;
            } else {
                return r1 * Math.acos((d2 + r12 - r22) / (2.0d * d * r1)) + r2 * Math.acos((d2 + r22 - r12) / (2.0d * d * r2)) - 0.5d * Math.sqrt(D);
            }
        }
    }
}