package parallelhyflex.problems.threesat;

import parallelhyflex.DistanceFunction;
import parallelhyflex.Heuristic;
import parallelhyflex.ObjectiveFunction;
import parallelhyflex.Problem;
import parallelhyflex.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblem implements Problem<ThreeSatSolution> {

    @Override
    public Heuristic<ThreeSatSolution> getHeuristic(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNumberOfHeuristics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ObjectiveFunction<ThreeSatSolution> getObjectiveFunction(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNumberOfObjectiveFunctions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DistanceFunction<ThreeSatSolution> getDistanceFunction(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SolutionGenerator<ThreeSatSolution> getSolutionGenerator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
