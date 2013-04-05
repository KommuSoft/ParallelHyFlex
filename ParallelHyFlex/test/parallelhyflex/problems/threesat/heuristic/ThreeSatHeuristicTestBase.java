/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicL1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicTestBase {

    protected ThreeSatProblemGenerator tspg;
    protected ThreeSatProblem tsp;
    protected ThreeSatSolutionGenerator tsg;
    protected ThreeSatSolution tss;
    protected HeuristicBase<ThreeSatSolution, ThreeSatProblem> hb;

    protected void renewProblemGenerator() {
        tspg = new ThreeSatProblemGenerator(TestParameters.NUMBER_OF_VARIABLES,TestParameters.NUMBER_OF_CLAUSES);
    }

    protected void renewProblem() {
        tsp = tspg.generateProblem();
    }

    protected void renewSolutionGenerator() {
        tsg = tsp.getSolutionGenerator();
    }

    protected void renewSolution() {
        tss = tsg.generateSolution();
    }
    
    public abstract HeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();

    public void testApplyHeuristicLocallyConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            this.applyHeuristic();
            Assert.assertEquals(ClauseUtils.getNumberOfFailedClauses(tss.getCompactBitArray(), tsp.getConstraints()), tss.getConflictingClauses());
        }
    }
    
    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.hb = this.renewHeuristic();
            this.renewSolutionGenerator();
            this.renewSolution();
            int old = tss.getConflictingClauses();
            this.applyHeuristic();
            Assert.assertTrue(old >= tss.getConflictingClauses());
        }
    }

    public void applyHeuristic() {
        hb.applyHeuristicLocally(tss);
    }
}
