package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.DistanceFunction;
import parallelhyflex.problemdependent.Heuristic;
import parallelhyflex.problemdependent.ObjectiveFunction;
import parallelhyflex.problemdependent.ProblemBase;
import parallelhyflex.problemdependent.SolutionGenerator;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblem extends ProblemBase<ThreeSatSolution> {

    private final long[] constraints;
    private final int[][] influences;
    private final int n, k;
    private final ThreeSatSolutionGenerator generator;
    private CompactBitArray fixedArray;
    private CompactBitArray valueArray;
    
    public ThreeSatProblem (long[] constraints) {
        this.constraints = constraints;
        this.k = this.constraints.length;
        int n = ClauseUtils.getLargestIndex(constraints)+1;
        this.n = n;
        this.influences = new int[n][];
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
        int nplus;
        int[] arr;
        for(int i = 0; i < n; i++) {
            nplus = npn[i];
            arr = new int[nplus+nnn[i]];
            arr[0] = nplus;
            nnn[i] += nplus;
            this.influences[i] = arr;
        }
        int j = 0;
        for(long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for(int i = 1; i < np[0]; i++) {
                index = np[i];
                this.influences[index][npn[index]--] = j;
            }
            for(int i = 1; i < nn[0]; i++) {
                index = nn[i];
                this.influences[index][nnn[index]--] = j;
            }
            j++;
        }
        this.generator = new ThreeSatSolutionGenerator(this);
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

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @return the constraints
     */
    public long[] getConstraints() {
        return constraints;
    }

    /**
     * @return the influences
     */
    public int[][] getInfluences() {
        return influences;
    }
    
}
