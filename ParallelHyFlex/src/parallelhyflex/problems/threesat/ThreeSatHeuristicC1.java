package parallelhyflex.problems.threesat;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicC1 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatHeuristicC1 (ThreeSatProblem problem) {
        super(problem,HeuristicType.Crossover);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {}//Do nothing, this is a crossover heuristic
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from, ThreeSatSolution from2) {
        //TODO: implement
    }
    
}
