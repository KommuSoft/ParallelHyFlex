package parallelhyflex.problemdependent.problem;

import parallelhyflex.communication.serialisation.Writeable;
import parallelhyflex.problemdependent.distance.DistanceFunction;
import parallelhyflex.problemdependent.heuristic.Heuristic;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public interface Problem<TSolution extends Solution<TSolution>> extends Writeable, ProblemInterface<TSolution> {

    public Heuristic<TSolution> getHeuristic(int index);

    public ObjectiveFunction<TSolution> getObjectiveFunction(int index);
    
    public ObjectiveFunction<TSolution> getObjectiveFunction();

    public DistanceFunction<TSolution> getDistanceFunction(int index);

    public SolutionGenerator<TSolution> getSolutionGenerator();
}
