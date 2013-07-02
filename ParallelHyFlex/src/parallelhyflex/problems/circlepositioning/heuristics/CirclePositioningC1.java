package parallelhyflex.problems.circlepositioning.heuristics;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningC1 extends CrossoverHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {
    
    public CirclePositioningC1 (CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
    }//Do nothing, this is a crossover heuristic
    
    @Override
    public void applyHeuristicLocally (CirclePositioningSolution from1, CirclePositioningSolution from2) {
        
        //TODO: recalculate evaluation
    }
    private static final Logger LOG = Logger.getLogger(CirclePositioningC1.class.getName());
    
}
