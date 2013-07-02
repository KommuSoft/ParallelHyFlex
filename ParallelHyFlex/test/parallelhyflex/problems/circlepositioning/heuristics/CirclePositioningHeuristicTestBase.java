/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning.heuristics;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.circlepositioning.CirclePositioningTestBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.threesat.ClauseUtils;

/**
 *
 * @author kommusoft
 */
public abstract class CirclePositioningHeuristicTestBase extends CirclePositioningTestBase {

    protected HeuristicBase<CirclePositioningSolution, CirclePositioningProblem> hb;

    public abstract HeuristicBase<CirclePositioningSolution, CirclePositioningProblem> renewHeuristic();

    public void testApplyHeuristicLocallyConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            this.applyHeuristic();
            Assert.assertEquals(tss.calculateOuter(tsp), tss.getOuterArea());
            Assert.assertEquals(tss.calculateOverlap(tsp), tss.getOverlapArea());
        }
    }

    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            double old = tss.getDefaultEvaluation();
            this.applyHeuristic();
            Assert.assertTrue(old >= tss.getDefaultEvaluation());
        }
    }

    public void applyHeuristic() {
        hb.applyHeuristicLocally(tss);
    }
}
