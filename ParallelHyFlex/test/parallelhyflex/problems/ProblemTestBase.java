/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemTestBase<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> {

    private TSG tsg;
    private TP tsp;
    private TPG tspg;
    private TS tss;
    private TestRenewalStrategy renewalStrategy;

    public ProblemTestBase() {
        this.renewalStrategy = this.getRenewalStrategy();
    }

    public abstract TestRenewalStrategy<TSG, TP, TPG, TS> getRenewalStrategy();

    protected final void renewProblem() {
        this.renewalStrategy.renewProblem(this);
    }

    protected final void renewProblemGenerator() {
        this.renewalStrategy.renewProblemGenerator(this);
    }

    protected final void renewSolution() {
        this.renewalStrategy.renewSolution(this);
    }

    protected final void renewSolutionGenerator() {
        this.renewalStrategy.renewSolutionGenerator(this);
    }

    /**
     * @return the tsg
     */
    public TSG getTsg() {
        return tsg;
    }

    /**
     * @param tsg the tsg to set
     */
    public void setTsg(TSG tsg) {
        this.tsg = tsg;
    }

    /**
     * @return the tsp
     */
    public TP getTsp() {
        return tsp;
    }

    /**
     * @param tsp the tsp to set
     */
    public void setTsp(TP tsp) {
        this.tsp = tsp;
    }

    /**
     * @return the tspg
     */
    public TPG getTspg() {
        return tspg;
    }

    /**
     * @param tspg the tspg to set
     */
    public void setTspg(TPG tspg) {
        this.tspg = tspg;
    }

    /**
     * @return the tss
     */
    public TS getTss() {
        return tss;
    }

    /**
     * @param tss the tss to set
     */
    public void setTss(TS tss) {
        this.tss = tss;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(TestRenewalStrategy strategy) {
        this.renewalStrategy = strategy;
    }
}
