package parallelhyflex.utils;

/**
 *
 * @author kommusoft
 */
public final class GeometryUtils {

    private GeometryUtils() {
    }

    public static double[] lineCircleCrossing(double a, double b, double c, double x0, double y0, double r) {
        //TODO
        return null;
    }

    public static double normalizedLineCircleDistance(double a, double b, double c, double x0, double y0, double r) {
        return Math.max(0.0d, Math.abs(a * x0 + b * y0 - c) - r);
    }

    public static double normalizedLinePointDistance(double a, double b, double c, double x0, double y0) {
        return Math.abs(a * x0 + b * y0 - c);
    }

    public static double linePointDistance(double a, double b, double c, double x0, double y0) {
        double rinv = 1.0d / Math.sqrt(a * a + b * b);
        return Math.abs(rinv * (a * x0 + b * y0 - c));
    }
}
