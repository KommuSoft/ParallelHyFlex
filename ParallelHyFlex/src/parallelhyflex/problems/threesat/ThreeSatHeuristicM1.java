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
            swapRandomBit(n, cba, constraints, from);
        }
        while(Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }
    
}
