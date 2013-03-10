package parallelhyflex.problems.threesat;

import parallelhyflex.DistanceFunction;
import parallelhyflex.Heuristic;
import parallelhyflex.ObjectiveFunction;
import parallelhyflex.ProblemBase;
import parallelhyflex.SolutionGenerator;
import parallelhyflex.utils.CompactBitArray;

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
    private CompactBitArray fixedArray;
    private CompactBitArray valueArray;
    
    public ThreeSatProblem (long[] constraints) {
        this.constraints = constraints;
        this.k = this.constraints.length;
        int n = ClauseUtils.getLargestIndex(constraints)+1;
        this.n = n;
        this.posinfluences = new int[n][];
        this.neginfluences = new int[n][];
        int[] npn = new int[n], nnn = new int[n];
        int[] np = new int[4], nn = new int[4];
        int index;
        for(long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for(int i = 1; i < np[0]; i++) {
                index = np[i];
                npn[np[i]]++;
            }
            for(int i = 1; i < nn[0]; i++) {
                index = nn[i];
                nnn[nn[i]]++;
            }
        }
        for(int i = 0; i < n; i++) {
            this.posinfluences[i] = new int[npn[i]];
            this.neginfluences[i] = new int[nnn[i]];
        }
        int j = 0;
        for(long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for(int i = 1; i < np[0]; i++) {
                index = np[i];
                this.posinfluences[index][--npn[index]] = j;
            }
            for(int i = 1; i < nn[0]; i++) {
                index = nn[i];
                this.neginfluences[index][--nnn[index]] = j;
            }
            j++;
        }
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
