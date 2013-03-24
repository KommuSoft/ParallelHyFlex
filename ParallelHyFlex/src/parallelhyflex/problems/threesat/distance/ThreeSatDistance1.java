package parallelhyflex.problems.threesat.distance;

import parallelhyflex.problemdependent.distance.DistanceFunctionBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatDistance1 extends DistanceFunctionBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatDistance1 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public double evaluateDistance(ThreeSatSolution solution1, ThreeSatSolution solution2) {
        int dis = 0;
        long[] cba1 = solution1.getCompactBitArray().values;
        long[] cba2 = solution2.getCompactBitArray().values;
        for(int i = 0; i < cba1.length; i++) {
            dis += Utils.countOnes(cba1[i]^cba2[i]);
        }
        return dis;
    }

    @Override
    public boolean evaluateDistanceSmallerThanOrEqual(ThreeSatSolution solution1, ThreeSatSolution solution2, double maxDistance) {
        int dis = 0;
        long[] cba1 = solution1.getCompactBitArray().values;
        long[] cba2 = solution2.getCompactBitArray().values;
        for(int i = 0; i < cba1.length; i++) {
            dis += Utils.countOnes(cba1[i]^cba2[i]);
            if(dis > maxDistance) {
                return true;
            }
            else if(dis+((cba1.length-i)<<6)-64 <= maxDistance) {
                return true;
            }
        }
        return false;
    }
    
}
