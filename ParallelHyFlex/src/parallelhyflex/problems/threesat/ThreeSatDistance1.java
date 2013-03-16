package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.DistanceFunctionBase;
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
    
}
