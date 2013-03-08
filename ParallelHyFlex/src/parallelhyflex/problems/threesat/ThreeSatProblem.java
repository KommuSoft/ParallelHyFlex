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

    private final long[] constraints;
    private final int n, k;
    private final ThreeSatSolutionGenerator generator;
    
    public ThreeSatProblem (int nvars, long[] constraints) {
        this.constraints = constraints;
        this.n = nvars;
        this.k = this.constraints.length;
        this.generator = new ThreeSatSolutionGenerator((nvars+63)>>6);
    }
    
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
        return this.generator;
    }
    
}
