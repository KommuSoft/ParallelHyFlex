package parallelhyflex.problems.threesat.distance;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.distance.DistanceFunctionBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatDistance1 extends DistanceFunctionBase<ThreeSatSolution, ThreeSatProblem> {

    /**
     *
     * @param problem
     */
    public ThreeSatDistance1(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param solution1
     * @param solution2
     * @return
     */
    @Override
    public double evaluateDistance(ThreeSatSolution solution1, ThreeSatSolution solution2) {
        int dis = 0;
        long[] cba1 = solution1.getCompactBitArray().values;
        long[] cba2 = solution2.getCompactBitArray().values;
        for (int i = 0; i < cba1.length; i++) {
            dis += Utils.countOnes(cba1[i] ^ cba2[i]);
        }
        return dis;
    }

    /**
     *
     * @param solution1
     * @param solution2
     * @param maxDistance
     * @return
     */
    @Override
    public boolean evaluateDistanceSmallerThanOrEqual(ThreeSatSolution solution1, ThreeSatSolution solution2, double maxDistance) {
        int dis = (int) Math.round(-maxDistance);
        long[] cba1 = solution1.getCompactBitArray().values;
        long[] cba2 = solution2.getCompactBitArray().values;
        int upperbound = solution1.getLength()+dis;
        int delta;
        for (int i = 0; i < cba1.length; i++) {
            delta = Utils.countOnes(cba1[i] ^ cba2[i]);
            dis += delta;
            if (dis > 0) {
                return true;
            }
            upperbound += delta-64;
            if (upperbound <= 0) {
                return false;
            }
        }
        return false;
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatDistance1.class.getName());
}
