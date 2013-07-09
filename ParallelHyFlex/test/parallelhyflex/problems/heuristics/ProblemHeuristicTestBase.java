/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.heuristics;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.heuristic.HeuristicBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;
import parallelhyflex.problems.ProblemTestBase;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemHeuristicTestBase<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> extends ProblemTestBase<TSG, TP, TPG, TS> {

    private HeuristicBase<TS, TP> heuristicBase;
    
    private TestHeuristicEvaluationStrategy<TSG,TP,TPG,TS> heuristicEvaluationStrategy;
    
    /**
     *
     */
    public ProblemHeuristicTestBase () {
        this.heuristicEvaluationStrategy = this.generateHeuristicEvaluationStrategy();
    }
    
    /**
     *
     * @return
     */
    public abstract TestHeuristicEvaluationStrategy<TSG,TP,TPG,TS> generateHeuristicEvaluationStrategy ();

    /**
     *
     * @return
     */
    public abstract HeuristicBase<TS, TP> renewHeuristic();

    /**
     *
     */
    public void testApplyHeuristicLocallyEvaluationApprox() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.setHeuristicBase(this.renewHeuristic());
            this.renewSolutionGenerator();
            this.renewSolution();
            this.applyHeuristic();
            double[] approx = this.heuristicEvaluationStrategy.calculateApproximatedEvaluations(this);
            double[] real = this.heuristicEvaluationStrategy.calculateRealEvaluations(this);
            Assert.assertEquals(approx.length, real.length);
            for(int j = 0x00; j < approx.length; j++) {
                Assert.assertTrue(String.format("%s versus %s",approx[j],real[j]),Math.abs(approx[j]-real[j]) <= TestParameters.TOLERANCE);
            }
        }
    }

    /**
     *
     */
    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.setHeuristicBase(this.renewHeuristic());
            this.renewSolutionGenerator();
            this.renewSolution();
            double[] od = this.heuristicEvaluationStrategy.calculateApproximatedEvaluations(this);
            this.applyHeuristic();
            double[] nw = this.heuristicEvaluationStrategy.calculateApproximatedEvaluations(this);
            Assert.assertEquals(od.length,nw.length);
            for(int j = 0x00; j < od.length; j++) {
                Assert.assertTrue(od[j] >= nw[j]);
            }
        }
    }

    /**
     *
     */
    public void applyHeuristic() {
        getHeuristicBase().applyHeuristicLocally(getTss());
    }

    /**
     * @return the heuristicBase
     */
    public HeuristicBase<TS, TP> getHeuristicBase() {
        return heuristicBase;
    }

    /**
     * @param heuristicBase the heuristicBase to set
     */
    public void setHeuristicBase(HeuristicBase<TS, TP> heuristicBase) {
        this.heuristicBase = heuristicBase;
    }
}
