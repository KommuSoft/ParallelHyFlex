package parallelhyflex.problems.frequencyassignment;

import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public final class FrequencyAssignmentUtils {

    /**
     *
     * @param mu
     * @param sigma
     * @return
     */
    public static double Cco(double mu, double sigma, double Csh) {
        return 100.0d * StatisticsUtils.normalCdf(mu, sigma, Csh);
    }

    /**
     *
     * @param mu
     * @param sigma
     * @return
     */
    public static double Cadj(double mu, double sigma, double Csh, double Cadj) {
        return 100.0d * StatisticsUtils.normalCdf(mu, sigma, Csh - Cadj);
    }

    /**
     *
     * @param problem
     * @param fa
     * @return
     */
    public static double calculateInterference(FrequencyAssignmentProblem problem, int[] fa) {
        double eval = 0.0d;
        int n = problem.getnTransceivers();
        int[] sectors = problem.getPlacement();
        DoubleUpperMatrix mu = problem.getMeans();
        DoubleUpperMatrix sigma = problem.getStdevs();
        for (int i = 0x00; i < n; i++) {
            for (int j = i + 0x01; j < n; j++) {
                eval += Csig(sectors[i], sectors[j], fa[i], fa[j], mu.get(i, j), sigma.get(i, j));
            }
        }
        return eval;
    }

    /**
     *
     * @param problem
     * @param fa
     * @param index
     * @param fi1
     * @return
     */
    public static double calculateInterferenceDelta(FrequencyAssignmentProblem problem, int[] fa, int index, int fi1) {
        int fi0 = fa[index];
        if (fi0 != fi1) {
            double delta = 0.0d;
            int n = problem.getnTransceivers();
            int[] sectors = problem.getPlacement();
            DoubleUpperMatrix mu = problem.getMeans();
            DoubleUpperMatrix sigma = problem.getStdevs();
            int si = sectors[index];
            for (int j = 0x00; j < index; j++) {
                double muj = mu.get(j, index);
                double sigmaj = sigma.get(j, index);
                int sj = sectors[j];
                int fj = fa[j];
                delta += Csig(si, sj, fi1, fj, muj, sigmaj) - Csig(si, sj, fi0, fj, muj, sigmaj);
            }
            for (int j = index + 0x01; j < n; j++) {
                double muj = mu.get(index, j);
                double sigmaj = sigma.get(index, j);
                int sj = sectors[j];
                int fj = fa[j];
                delta += Csig(sj, si, fj, fi1, muj, sigmaj) - Csig(sj, si, fj, fi0, muj, sigmaj);
            }
            return delta;
        } else {
            return 0.0d;
        }
    }

    /**
     *
     * @param st
     * @param su
     * @param pt
     * @param pu
     * @return
     */
    public static int Ccon(int st, int su, int pt, int pu) {
        if (st == su && Math.abs(pt - pu) < 2) {
            return 0x01;
        } else {
            return 0x00;
        }
    }

    /**
     *
     * @param st
     * @param su
     * @param pt
     * @param pu
     * @param mu
     * @param sigma
     * @return
     */
    public static double Csig(int st, int su, int pt, int pu, double mu, double sigma, double Csh, double Cadj) {
        if (st == su && mu > 0) {
            int delta = Math.abs(pt - pu);
            if (delta == 0) {
                return Cco(mu, sigma, Csh);
            } else if (delta == 1) {
                return Cadj(mu, sigma, Csh, Cadj);
            }
        }
        return 0.0d;
    }

    /**
     *
     * @param st
     * @param su
     * @param pt
     * @param pu0
     * @param pu1
     * @return
     */
    public static int CconDelta(int st, int su, int pt, int pu0, int pu1) {
        boolean delta02 = Math.abs(pt - pu0) < 2;
        boolean delta12 = Math.abs(pt - pu1) < 2;
        if (delta02 ^ delta12) {
            if (delta02) {
                return -0x01;
            } else {
                return 0x01;
            }
        }
        return 0x00;
    }

    /**
     *
     * @param mu
     * @param sigma
     * @param delta
     * @return
     */
    public static double CotherSectorMuLarge(double mu, double sigma, int delta, double Csh, Cadj) {
        if (delta == 0) {
            return Cco(mu, sigma);
        } else if (delta == 1) {
            return Cadj(mu, sigma);
        } else {
            return 0.0d;
        }
    }

    /**
     *
     * @param problem
     * @param fa
     * @return
     */
    public static int calculateNConflicts(FrequencyAssignmentProblem problem, int[] fa) {
        int eval = 0x00;
        int n = problem.getnTransceivers();
        int[] sectors = problem.getPlacement();
        for (int i = 0x00; i < n; i++) {
            for (int j = i + 0x01; j < n; j++) {
                eval += Ccon(sectors[i], sectors[j], fa[i], fa[j]);
            }
        }
        return eval;
    }

    /**
     *
     * @param problem
     * @param fa
     * @param index
     * @param fi1
     * @return
     */
    public static int calculateNConflictsDelta(FrequencyAssignmentProblem problem, int[] fa, int index, int fi1) {
        int delta = 0x00;
        int n = problem.getnTransceivers();
        int[] sectors = problem.getPlacement();
        int si = sectors[index];
        int fi0 = fa[index];
        for (int j = 0x00; j < index; j++) {
            int sj = sectors[j];
            int fj = fa[j];
            delta += Ccon(sj, si, fj, fi1) - Ccon(sj, si, fj, fi0);
        }
        for (int j = index + 0x01; j < n; j++) {
            int sj = sectors[j];
            int fj = fa[j];
            delta += Ccon(sj, si, fj, fi1) - Ccon(sj, si, fj, fi0);
        }
        return delta;
    }

    private FrequencyAssignmentUtils() {
    }
}
