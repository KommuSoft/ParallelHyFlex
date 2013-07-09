package parallelhyflex.utils;

/**
 *
 * @author kommusoft
 */
public final class GeometryUtils {

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param x0
     * @param y0
     * @param r
     * @return
     */
    public static double[] lineCircleCrossing(double a, double b, double c, double x0, double y0, double r) {
        //TODO
        return null;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param x0
     * @param y0
     * @param r
     * @return
     */
    public static double normalizedLineCircleDistance(double a, double b, double c, double x0, double y0, double r) {
        return Math.max(0.0d, Math.abs(a * x0 + b * y0 - c) - r);
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param x0
     * @param y0
     * @return
     */
    public static double normalizedLinePointDistance(double a, double b, double c, double x0, double y0) {
        return Math.abs(a * x0 + b * y0 - c);
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param x0
     * @param y0
     * @return
     */
    public static double linePointDistance(double a, double b, double c, double x0, double y0) {
        double rinv = 1.0d / Math.sqrt(a * a + b * b);
        return Math.abs(rinv * (a * x0 + b * y0 - c));
    }

    private GeometryUtils() {
    }
}
