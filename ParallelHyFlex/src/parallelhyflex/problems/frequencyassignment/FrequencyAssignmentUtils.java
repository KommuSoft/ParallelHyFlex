package parallelhyflex.problems.frequencyassignment;

import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public final class FrequencyAssignmentUtils {

    public static double K = 10e9;
    public static double Csh = 0.15d;
    public static double Cadj = 0.05d;//constraint: larger than 0

    public static double Cco(double mu, double sigma) {
        return 100.0d*StatisticsUtils.normalCdf(mu, sigma, Csh);
    }
    public static double Cadj(double mu, double sigma) {
        return 100.0d*StatisticsUtils.normalCdf(mu, sigma, Csh-Cadj);
    }

    public static double evaluate(FrequencyAssignmentProblem problem, int[] fa) {
        double eval = 0.0d;
        int n = problem.getnTransceivers();
        int[] sectors = problem.getPlacement();
        DoubleUpperMatrix mu = problem.getMeans();
        DoubleUpperMatrix sigma = problem.getStdevs();
        for(int i = 0x00; i < n; i++) {
            for(int j = i+0x01; j < n; j++) {
                eval += Csig(sectors[i],sectors[j],fa[i],fa[j],mu.get(i, j),sigma.get(i, j));
            }
        }
        return eval;
    }

    public static double Csig(int st, int su, int pt, int pu, double mu, double sigma) {
        boolean equal = st == su;
        int delta = Math.abs(pt - pu);
        if (equal) {
            if(delta < 2) {
                return K;
            }
        } else if (mu > 0) {
            if(delta == 0) {
                return Cco(mu,sigma);
            }
            else if(delta == 1) {
                return Cadj(mu,sigma);
            }
        }
        return 0.0d;
    }

    private FrequencyAssignmentUtils() {
    }
}
