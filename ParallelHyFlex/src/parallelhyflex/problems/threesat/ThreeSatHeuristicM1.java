package parallelhyflex.problems.threesat;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic that swaps one or more bits (depending on the intensity of mutation). The swapped indices are uniform random.
 * @author kommusoft
 */
public class ThreeSatHeuristicM1 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    //swaps a random variable
    
    public ThreeSatHeuristicM1 (ThreeSatProblem problem) {
        super(problem,HeuristicType.Mutation);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), i, delta, j, np, nn;
        int[] tocheck;
        long[] constraints = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        do {
            i = Utils.StaticRandom.nextInt(n);
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
            delta *= (cba.swapGetBit(i)<<1)-1;
            from.addConfictingClauses(delta);
        }
        while(Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }
    
}
