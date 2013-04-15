package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic where one or more a failing clause is made completely
 * true
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM3 extends MutationHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicM3(ThreeSatProblem problem) {
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
        for (Integer i : ClauseUtils.getUniqueIndices(clauses[ClauseUtils.getFalseClauseIndex(from, clauses)])) {
            from.swapBit(i, problem);
        }
    }
    
    @Override
    public boolean usesIntensityOfMutation() {
        return true;
    }
}
