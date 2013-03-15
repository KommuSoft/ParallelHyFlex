package parallelhyflex.problems.threesat;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL1 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatHeuristicL1 (ThreeSatProblem problem) {
        super(problem,HeuristicType.LocalSearch);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), i, delta, j, np, nn;
        int[] tocheck;
        long[] constraints = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved;
        do {
            improved = false;
            for(i = 0; i < n; i++) {
                tocheck = this.getProblem().getInfluences()[i];
                delta = 0;
                np = tocheck[0];
                nn = tocheck.length;
                for(j = 1; j <= np; j++) {
                    if(cba.willSwap(constraints[tocheck[j]],i)) {
                        delta++;
                    }
                }
                for(; j < nn; j++) {
                    if(cba.willSwap(constraints[tocheck[j]],i)) {
                        delta--;
                    }
                }
                delta *= 1-(cba.getBit(i)<<1);
                improved = delta > 0;
                if(improved) {
                    cba.swap(i);
                    from.addConfictingClauses(delta);
                    break;
                }
            }
        }
        while(improved);
    }
    
}
