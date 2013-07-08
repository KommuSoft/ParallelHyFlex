package parallelhyflex.utils;

/**
 *
 * @author kommusoft
 */
public final class PolynomialUtils {
    
    public static double[] solveSecondOrder (double a, double b, double c) {
        double D = b*b-4*a*c;
        if(D < 0.0d) {
            return new double[0x00];
        }
        else if(D > 0.0d) {
            double sqrtD = Math.sqrt(D);
            double ainv2 = -0.5d/a;
            return new double[] {ainv2*(b+sqrtD),ainv2*(b-sqrtD)};
        }
        else {
            return new double[] {-0.5d*b/a};
        }
    }
    
    public static double[] solveSecondOrderOrdered (double a, double b, double c) {
        double D = b*b-4*a*c;
        if(D < 0.0d) {
            return new double[0x00];
        }
        else if(D > 0.0d) {
            double sqrtD = Math.sqrt(D);
            double ainv2 = -0.5d/a;
            if(ainv2 <= 0.0d) {
                return new double[] {ainv2*(b+sqrtD),ainv2*(b-sqrtD)};
            }
            else {
                return new double[] {ainv2*(b-sqrtD),ainv2*(b-sqrtD)};
            }
        }
        else {
            return new double[] {-0.5d*b/a};
        }
    }
    
    private PolynomialUtils () {}
    
}
