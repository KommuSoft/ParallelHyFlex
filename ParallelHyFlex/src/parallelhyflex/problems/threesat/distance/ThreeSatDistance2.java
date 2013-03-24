package parallelhyflex.problems.threesat.distance;

import parallelhyflex.problemdependent.distance.DistanceFunctionBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
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

    @Override
    public boolean evaluateDistanceSmallerThanOrEqual(ThreeSatSolution solution1, ThreeSatSolution solution2, double maxDistance) {
        CompactBitArray cba1 = solution1.getCompactBitArray();
        CompactBitArray cba2 = solution2.getCompactBitArray();
        int dis = 0;
        int n = this.getProblem().getConstraints().length;
        for(long constraint : this.getProblem().getConstraints()) {
            if(cba1.satisfiesClause(constraint)^cba2.satisfiesClause(constraint)) {
                dis++;
                if(dis > maxDistance) {
                    return false;
                }
                else if(dis+n-1 <= maxDistance) {
                    return true;
                }
            }
        }
        return true;
    }
    
}