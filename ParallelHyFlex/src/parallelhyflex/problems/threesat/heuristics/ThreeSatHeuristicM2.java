package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM2 extends MutationHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicM2(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        ThreeSatProblem problem = this.getProblem();
        long[] clauses = problem.getClauses();
        do {
            enforceClause(from, clauses, problem);
        } while (Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }

    private void enforceClause(ThreeSatSolution from, long[] clauses, ThreeSatProblem problem) {
        int i = ClauseUtils.getFalseClauseIndex(from, clauses);
        if (i != -1) {
            int k = Utils.StaticRandom.nextInt(3);
            int index = ClauseUtils.getIndexI(clauses[i], k);
            from.swapBit(index, problem);
        }
    }

    @Override
    public boolean usesIntensityOfMutation() {
        return true;
    }
}
