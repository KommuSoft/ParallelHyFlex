package parallelhyflex.problems.threesat.heuristic;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.ThreeSatTestBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicTestBase extends ThreeSatTestBase {

    protected HeuristicBase<ThreeSatSolution, ThreeSatProblem> hb;

    public abstract HeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic();

    public void testApplyHeuristicLocallyConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            this.applyHeuristic();
            Assert.assertEquals(ClauseUtils.getNumberOfFailedClauses(getTss().getCompactBitArray(), getTsp().getClauses()), getTss().getConflictingClauses());
        }
    }

    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            int old = getTss().getConflictingClauses();
            this.applyHeuristic();
            Assert.assertTrue(old >= getTss().getConflictingClauses());
        }
    }

    public void applyHeuristic() {
        hb.applyHeuristicLocally(getTss());
    }
}
