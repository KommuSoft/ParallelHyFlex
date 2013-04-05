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
public class ThreeSatHeuristicM2 extends MutationHeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatHeuristicM2 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        ThreeSatProblem problem = this.getProblem();
        int c = problem.getC();
        long[] clauses = problem.getConstraints();
        do {
        enforceClause(c, from, clauses, problem);
        } while (Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }

    private void enforceClause(int c, ThreeSatSolution from, long[] clauses, ThreeSatProblem problem) {
        int c0 = Utils.StaticRandom.nextInt(c);
        for(int i = c0; i < c; i++) {
            if(!from.satisfiesClause(clauses[i])) {
                int k = Utils.StaticRandom.nextInt(3);
                int index = ClauseUtils.getIndexI(clauses[i],k);
                from.swapBit(index,problem);
                return;
            }
        }
        for(int i = 0; i < c0; i++) {
            if(!from.satisfiesClause(clauses[i])) {
                int k = Utils.StaticRandom.nextInt(3);
                int index = ClauseUtils.getIndexI(clauses[i],k);
                from.swapBit(index,problem);
                return;
            }
        }
    }
    
}
