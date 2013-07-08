package parallelhyflex.problems.frequencyassignment;

import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public final class FrequencyAssignmentUtils {

    public static final double K = 10e9;
    public static final double Csh = 0.15d;
    public static final double Cadj = 0.05d;//constraint: larger than 0

    public static double Cco(double mu, double sigma) {
        return 100.0d * StatisticsUtils.normalCdf(mu, sigma, Csh);
    }

    public static double Cadj(double mu, double sigma) {
        return 100.0d * StatisticsUtils.normalCdf(mu, sigma, Csh - Cadj);
    }

    public static double evaluate(FrequencyAssignmentProblem problem, int[] fa) {
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

    public static double evaluateDelta(FrequencyAssignmentProblem problem, int[] fa, int index, int fi1) {
        double delta = 0.0d;
        int n = problem.getnTransceivers();
        int[] sectors = problem.getPlacement();
        DoubleUpperMatrix mu = problem.getMeans();
        DoubleUpperMatrix sigma = problem.getStdevs();
        int si = sectors[index], sj;
        int fi0 = fa[index], fj;
        double muj, sigmaj;
        for (int j = 0x00; j < index; j++) {
            muj = mu.getA(j, index);
            sigmaj = sigma.getA(j, index);
            sj = sectors[j];
            fj = fa[j];
            delta += CsigDelta(sj, si, fj, fi0, fi1, muj, sigmaj);
        }
        for (int j = index + 0x01; j < n; j++) {
            muj = mu.getA(index, j);
            sigmaj = sigma.getA(index, j);
            sj = sectors[j];
            fj = fa[j];
            delta += CsigDelta(sj, si, fj, fi0, fi1, muj, sigmaj);
        }
        return delta;
    }

    public static double Csig(int st, int su, int pt, int pu, double mu, double sigma) {
        boolean equal = st == su;
        int delta = Math.abs(pt - pu);
        if (equal) {
            if (delta < 2) {
                return K;
            }
        } else if (mu > 0) {
            if (delta == 0) {
                return Cco(mu, sigma);
            } else if (delta == 1) {
                return Cadj(mu, sigma);
            }
        }
        return 0.0d;
    }

    public static double CsigDelta(int st, int su, int pt, int pu0, int pu1, double mu, double sigma) {
        boolean equal = st == su;
        int delta0 = Math.abs(pt - pu0);
        int delta1 = Math.abs(pt - pu1);
        if (equal) {
            boolean delta02 = delta0 < 2, delta12 = delta1 < 2;
            if (delta02 ^ delta12) {
                if (delta02) {
                    return -K;
                } else {
                    return K;
                }
            }
        } else if (mu > 0) {
            return CotherSectorMuLarge(mu, sigma, delta1) - CotherSectorMuLarge(mu, sigma, delta0);
        }
        return 0.0d;
    }

    public static double CotherSectorMuLarge(double mu, double sigma, int delta) {
        if (delta == 0) {
            return Cco(mu, sigma);
        } else if (delta == 1) {
            return Cadj(mu, sigma);
        } else {
            return 0.0d;
        }
    }

    private FrequencyAssignmentUtils() {
    }
}
