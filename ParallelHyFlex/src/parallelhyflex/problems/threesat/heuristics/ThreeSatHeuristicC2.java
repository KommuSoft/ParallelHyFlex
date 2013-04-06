package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 * A crossover heuristic where the variable is selected based on the number of influenced clauses that are true
 * @author kommusoft
 */
public class ThreeSatHeuristicC2 extends CrossoverHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicC2(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
    }//Do nothing, this is a crossover heuristic

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from1, ThreeSatSolution from2) {
        int[][] influence = this.getProblem().getInfluences();
        long[] clauses = this.getProblem().getClauses();
        
    }
}
