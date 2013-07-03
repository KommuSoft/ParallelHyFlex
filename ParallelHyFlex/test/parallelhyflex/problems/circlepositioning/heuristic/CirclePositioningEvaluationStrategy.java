package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;
import parallelhyflex.problems.heuristics.ProblemHeuristicTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningEvaluationStrategy implements TestHeuristicEvaluationStrategy<CirclePositioningSolutionGenerator,CirclePositioningProblem,CirclePositioningProblemGenerator,CirclePositioningSolution> {

    @Override
    public double[] calculateApproximatedEvaluations(ProblemHeuristicTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> heuristicTestbase) {
        return new double[] {heuristicTestbase.getTss().getOuterArea(),heuristicTestbase.getTss().getOverlapArea()};
    }

    @Override
    public double[] calculateRealEvaluations(ProblemHeuristicTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> heuristicTestbase) {
        return new double[] {heuristicTestbase.getTss().calculateOuter(heuristicTestbase.getTsp()),heuristicTestbase.getTss().calculateOverlap(heuristicTestbase.getTsp())};
    }
    
}
