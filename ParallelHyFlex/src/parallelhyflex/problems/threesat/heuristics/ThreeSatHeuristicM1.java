package parallelhyflex.problems.threesat.heuristics;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic that swaps one or more bits (depending on the intensity
 * of mutation). The swapped indices are uniform random.
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM1 extends MutationHeuristicBase<ThreeSatSolution, ThreeSatProblem> {
    //swaps a random variable

    /**
     *
     * @param problem
     */
    public ThreeSatHeuristicM1(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength();
        long[] constraints = this.getProblem().getClauses();
        int[][] influences = this.getProblem().getInfluences();
        CompactBitArray cba = from.getCompactBitArray();
        do {
            ClauseUtils.swapRandomBit(n, influences, cba, constraints, from);
        } while (Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean usesIntensityOfMutation() {
        return true;
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatHeuristicM1.class.getName());
}
