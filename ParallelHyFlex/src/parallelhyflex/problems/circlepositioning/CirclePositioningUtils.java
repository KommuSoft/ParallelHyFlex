package parallelhyflex.problems.circlepositioning;

/**
 *
 * @author kommusoft
 */
public final class CirclePositioningUtils {

    /**
     *
     * @param r2
     * @return
     */
    public static double calculateCircleAreaRadiusSquare(double r2) {
        return Math.PI * r2;
    }

    /**
     *
     * @param rad
     * @param pos
     * @return
     */
    public static double calculateOverlap(double[] rad, double[] pos) {
        int n = rad.length;
        double overlap = 0.0d;
        for (int i = 0, i2 = 0; i < n;) {
            double x1 = pos[i2++];
            double y1 = pos[i2++];
            double r1 = rad[i++];
            for (int j = i, j2 = i2; j < n;) {
                double x2 = pos[j2++];
                double y2 = pos[j2++];
                double r2 = rad[j++];
                overlap += CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r1, x2, y2, r2);
            }
        }
        return overlap;
    }

    /**
     *
     * @param largeRadius
     * @param rad
     * @param pos
     * @return
     */
    public static double calculateOuter(double largeRadius, double[] rad, double[] pos) {
        double outer = 0.0d;
        int n2 = pos.length;
        for (int i = 0x00, i2 = 0x00; i2 < n2;) {
            double x = pos[i2++];
            double y = pos[i2++];
            double r = rad[i++];
            outer += CirclePositioningUtils.calculateCircleOuterArea(largeRadius, x, y, r);
        }
        return outer;
    }

    /**
     *
     * @param R
     * @param x
     * @param y
     * @param r
     * @return
     */
    public static double calculateCircleOuterArea(double R, double x, double y, double r) {
        double d2 = x * x + y * y;
        double d = Math.sqrt(d2);
        if (d + r <= R) {
            return 0.0d;
        } else {
            double D = -(d + R + r) * (d - R + r) * (d + R - r) * (d - R - r);
            if (D <= 0.0d) {
                return 0.0d;
            } else {
                double R2 = R * R;
                double r2 = r * r;
                return calculateCircleAreaRadiusSquare(r2) + 0.5d * Math.sqrt(D) - R * Math.acos((d2 + R2 - r2) / (2.0d * d * R)) - r * Math.acos((d2 + r2 - R2) / (2.0d * d * r));
            }
        }
    }

    /**
     *
     * @param x1
     * @param y1
     * @param r1
     * @param x2
     * @param y2
     * @param r2
     * @return
     */
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

    /**
     *
     * @param pos
     * @param rad
     * @param index
     * @return
     */
    public static double calculateOptimalRadius(double[] pos, double[] rad, int index) {
        int n = rad.length;
        double[] dist = new double[n];
        int i2 = index<<0x01;
        double x0 = pos[i2];
        double y0 = pos[i2+0x01];
        double r0 = rad[index];
        double norm = 1.0d/Math.sqrt(x0*x0+y0*y0);
        double x0n = x0*norm;
        double y0n = y0*norm;
        //TODO
        return 0.0d;
        
    }
    
    /**
     *
     * @param x1
     * @param y1
     * @param r1
     * @param x2
     * @param y2
     * @param r2
     * @param dr
     * @return
     */
    public static double calculateDifferenceCircleOverlapArea (double x1, double y1, double r1, double x2, double y2, double r2, double dr) {
        if (dr > 0.0d) {
            return -calculateDifferenceCircleOverlapArea(x1, y1, r1, x2, y2, r2 + dr, -dr);
        } else if (dr < 0.0d) {
            double dx = (x2 - x1);
            double dy = (y2 - y1);
            double d2 = dx * dx + dy * dy;
            double d = Math.sqrt(d2);
            double r12 = r1 * r1;
            double r22 = r2 * r2;
            if (d + r2 <= r1) {
                return 0.0d;
            } else {
                return calculateCircleOverlapArea(x1, y1, r1, x2, y2, r2 + dr) - calculateCircleOverlapArea(x1, y1, r1, x2, y2, r2);
            }
        } else {
            return 0.0d;
        }
    }

    /**
     *
     * @param pos
     * @param index0
     * @param index1
     * @param radia
     * @param x2
     * @param y2
     * @param r2
     * @param dr
     * @return
     */
    public static double calculateDifferenceOverlapRadius(double[] pos, int index0, int index1, double[] radia, double x2, double y2, double r2, double dr) {
        int n = radia.length;
        double doverlap = 0.0d, x1, y1, r1;
        for (int i = 0x00, i2 = 0x00; i < index0;) {
            x1 = pos[i2++];
            y1 = pos[i2++];
            r1 = radia[i++];
            doverlap += CirclePositioningUtils.calculateDifferenceCircleOverlapArea(x1, y1, r1, x2, y2, r2, dr);
        }
        for (int i = index0 + 0x01, i2 = i << 0x01; i < index1;) {
            x1 = pos[i2++];
            y1 = pos[i2++];
            r1 = radia[i++];
            doverlap += CirclePositioningUtils.calculateDifferenceCircleOverlapArea(x1, y1, r1, x2, y2, r2, dr);
        }
        for (int i = index1 + 0x01, i2 = i << 0x01; i < n;) {
            x1 = pos[i2++];
            y1 = pos[i2++];
            r1 = radia[i++];
            doverlap += CirclePositioningUtils.calculateDifferenceCircleOverlapArea(x1, y1, r1, x2, y2, r2, dr);
        }
        return doverlap;
    }

    /**
     *
     * @param pos
     * @param index
     * @param radia
     * @param x3
     * @param y3
     * @param r13
     * @param x1
     * @param y1
     * @return
     */
    public static double calculateDifferenceOverlap(double[] pos, int index, double[] radia, double x3, double y3, double r13, double x1, double y1) {
        int n = radia.length;
        double doverlap = 0.0d, x2, y2, r2;
        for (int i = 0x00, i2 = 0x00; i < index;) {
            x2 = pos[i2++];
            y2 = pos[i2++];
            r2 = radia[i++];
            doverlap += CirclePositioningUtils.calculateCircleOverlapArea(x3, y3, r13, x2, y2, r2) - CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r13, x2, y2, r2);
        }
        for (int i = index + 0x01, i2 = i << 0x01; i < n;) {
            x2 = pos[i2++];
            y2 = pos[i2++];
            r2 = radia[i++];
            doverlap += CirclePositioningUtils.calculateCircleOverlapArea(x3, y3, r13, x2, y2, r2) - CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r13, x2, y2, r2);
        }
        return doverlap;
    }

    /**
     *
     * @param R
     * @param x3
     * @param y3
     * @param r13
     * @param x1
     * @param y1
     * @return
     */
    public static double calculateDifferenceOuter(double R, double x3, double y3, double r13, double x1, double y1) {
        return CirclePositioningUtils.calculateCircleOuterArea(R, x3, y3, r13) - CirclePositioningUtils.calculateCircleOuterArea(R, x1, y1, r13);
    }

    /**
     *
     * @param R
     * @param x
     * @param y
     * @param r0
     * @param r1
     * @return
     */
    public static double calculateDifferenceOuterRadius(double R, double x, double y, double r0, double r1) {
        return CirclePositioningUtils.calculateCircleOuterArea(R, x, y, r1) - CirclePositioningUtils.calculateCircleOuterArea(R, x, y, r0);
    }

    private CirclePositioningUtils() {
    }
}