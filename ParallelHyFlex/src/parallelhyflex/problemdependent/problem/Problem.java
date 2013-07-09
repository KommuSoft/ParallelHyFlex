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

    /**
     *
     * @param index
     * @return
     */
    public Heuristic<TSolution> getHeuristic(int index);

    /**
     *
     * @param index
     * @return
     */
    public ObjectiveFunction<TSolution> getObjectiveFunction(int index);
    
    /**
     *
     * @return
     */
    public ObjectiveFunction<TSolution> getObjectiveFunction();

    /**
     *
     * @param index
     * @return
     */
    public DistanceFunction<TSolution> getDistanceFunction(int index);

    /**
     *
     * @return
     */
    public SolutionGenerator<TSolution> getSolutionGenerator();
}
