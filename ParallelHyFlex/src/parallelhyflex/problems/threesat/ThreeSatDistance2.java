package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.DistanceFunctionBase;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatDistance2 extends DistanceFunctionBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatDistance2 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public double evaluateDistance(ThreeSatSolution solution1, ThreeSatSolution solution2) {
        CompactBitArray cba1 = solution1.getCompactBitArray();
        CompactBitArray cba2 = solution2.getCompactBitArray();
        int dis = 0;
        for(long constraint : this.getProblem().getConstraints()) {
            if(cba1.satisfiesClause(constraint)^cba2.satisfiesClause(constraint)) {
                dis++;
            }
        }
        return dis;
    }
    
}