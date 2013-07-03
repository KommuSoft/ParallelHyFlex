package parallelhyflex.problems.heuristics;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public interface TestHeuristicEvaluationStrategy<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> {
    
    public double[] calculateApproximatedEvaluations (ProblemHeuristicTestBase<TSG,TP,TPG,TS> heuristicTestbase);
    public double[] calculateRealEvaluations (ProblemHeuristicTestBase<TSG,TP,TPG,TS> heuristicTestbase);
    
}
