package parallelhyflex.problems.threesat;

import parallelhyflex.DistanceFunction;
import parallelhyflex.Heuristic;
import parallelhyflex.ObjectiveFunction;
import parallelhyflex.ProblemBase;
import parallelhyflex.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblem extends ProblemBase<ThreeSatSolution> {

    private final long[] constraints;
    private final int[][] posinfluences;
    private final int[][] neginfluences;
    private final int n, k;
    private final ThreeSatSolutionGenerator generator;
    
    public ThreeSatProblem (long[] constraints) {
        this.constraints = constraints;
        this.n = ClauseUtils.getLargestIndex(constraints);
        this.posinfluences = new int[this.n][];
        this.neginfluences = new int[this.n][];
        int[] np = new int[this.n], nn = new int[this.n];
        for(long constraint : constraints) {
            
        }
        this.k = this.constraints.length;
        this.generator = new ThreeSatSolutionGenerator((this.n+63)>>6);
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
