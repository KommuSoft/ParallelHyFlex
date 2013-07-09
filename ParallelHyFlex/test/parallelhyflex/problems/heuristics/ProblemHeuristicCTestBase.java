package parallelhyflex.problems.heuristics;

import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemHeuristicCTestBase<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> extends ProblemHeuristicTestBase<TSG, TP, TPG, TS> {

    /**
     *
     * @return
     */
    @Override
    public abstract CrossoverHeuristicBase<TS, TP> renewHeuristic();

    /**
     *
     */
    @Override
    public void applyHeuristic() {
        TS tss2 = this.getTsg().generateSolution();
        this.getHeuristicBase().applyHeuristicLocally(this.getTss(), tss2);
    }
}
