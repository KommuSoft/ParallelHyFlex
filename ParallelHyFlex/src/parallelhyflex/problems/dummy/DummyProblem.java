package parallelhyflex.problems.dummy;

import parallelhyflex.problemdependent.DistanceFunction;
import parallelhyflex.problemdependent.Heuristic;
import parallelhyflex.problemdependent.ObjectiveFunction;
import parallelhyflex.problemdependent.ProblemBase;
import parallelhyflex.problemdependent.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class DummyProblem extends ProblemBase<DummySolution> {

    @Override
    public Heuristic<DummySolution> getHeuristic(int index) {
        return null;
    }

    @Override
    public int getNumberOfHeuristics() {
        return 0;
    }

    @Override
    public ObjectiveFunction<DummySolution> getObjectiveFunction(int index) {
        return null;
    }

    @Override
    public int getNumberOfObjectiveFunctions() {
        return 0;
    }

    @Override
    public DistanceFunction<DummySolution> getDistanceFunction(int index) {
        return null;
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        return 0;
    }

    @Override
    public SolutionGenerator<DummySolution> getSolutionGenerator() {
        return null;
    }
    
}
