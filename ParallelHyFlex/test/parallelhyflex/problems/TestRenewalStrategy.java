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
public interface TestRenewalStrategy<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> {

    public void renewProblem(ProblemTestBase<TSG, TP, TPG, TS> testbase);

    public void renewProblemGenerator(ProblemTestBase<TSG, TP, TPG, TS> testbase);

    public void renewSolution(ProblemTestBase<TSG, TP, TPG, TS> testbase);

    public void renewSolutionGenerator(ProblemTestBase<TSG, TP, TPG, TS> testbase);
}
