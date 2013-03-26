package parallelhyflex.problemdependent.problem;

import parallelhyflex.ProblemInterface;
import parallelhyflex.problemdependent.distance.DistanceFunction;
import parallelhyflex.problemdependent.heuristics.Heuristic;
import parallelhyflex.communication.Writable;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public interface Problem<TSolution extends Solution<TSolution>> extends Writable, ProblemInterface<TSolution> {

    public Heuristic<TSolution> getHeuristic(int index);

    public ObjectiveFunction<TSolution> getObjectiveFunction(int index);

    public DistanceFunction<TSolution> getDistanceFunction(int index);

    public SolutionGenerator<TSolution> getSolutionGenerator();
}
