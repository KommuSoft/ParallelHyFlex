package parallelhyflex.problems.dummy;

import parallelhyflex.DistanceFunction;
import parallelhyflex.Heuristic;
import parallelhyflex.ObjectiveFunction;
import parallelhyflex.ProblemBase;
import parallelhyflex.SolutionGenerator;

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
