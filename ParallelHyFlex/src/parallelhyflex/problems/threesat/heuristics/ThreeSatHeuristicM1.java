package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.HeuristicType;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
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
        int n = from.getLength();
        long[] constraints = this.getProblem().getConstraints();
        int[][] influences = this.getProblem().getInfluences();
        CompactBitArray cba = from.getCompactBitArray();
        do {
            ClauseUtils.swapRandomBit(n, influences, cba, constraints, from);
        }
        while(Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }
    
}
