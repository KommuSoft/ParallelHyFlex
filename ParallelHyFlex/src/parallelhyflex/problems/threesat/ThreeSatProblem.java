package parallelhyflex.problems.threesat;

import java.util.Arrays;
import java.util.HashSet;
import parallelhyflex.problemdependent.DistanceFunction;
import parallelhyflex.problemdependent.Heuristic;
import parallelhyflex.problemdependent.ObjectiveFunction;
import parallelhyflex.problemdependent.ProblemBase;
import parallelhyflex.problemdependent.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblem extends ProblemBase<ThreeSatSolution> {

    private final long[] constraints;
    private final int[][] influences;
    private final int[][] blockInfluences;
    private final int v, c;
    private final double ratio1, ratio2, ratio3, ratioReciprocal1, ratioReciprocal2, ratioReciprocal3, linearizedRatio1, linearizedRatio2, linearizedRatio3;
    private final ThreeSatSolutionGenerator generator;
    private final Object[] distanceFunctions;
    private final Object[] heuristics;
    
    public ThreeSatProblem (long[] constraints) {
        this.constraints = constraints;
        this.c = this.constraints.length;
        int n = ClauseUtils.getLargestIndex(constraints)+1;
        this.v = n;
        int[] npn = new int[n], nnn = new int[n], np = new int[4], nn = new int[4], arr;
        int index, nplus, i, j = 0, k, l;
        this.influences = new int[n][];
        this.blockInfluences = new int[(n+63)>>6][];
        HashSet<Integer> blockCache = new HashSet<>();
        for(long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for(i = 1; i < np[0]; i++) {
                index = np[i];
                npn[np[i]]++;
            }
            for(i = 1; i < nn[0]; i++) {
                index = nn[i];
                nnn[nn[i]]++;
            }
        }
        for(i = 0; i < n; i++) {
            nplus = npn[i];
            arr = new int[nplus+nnn[i]];
            arr[0] = nplus;
            nnn[i] += nplus;
            this.influences[i] = arr;
        }
        for(long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for(i = 1; i < np[0]; i++) {
                index = np[i];
                this.influences[index][npn[index]--] = j;
            }
            for(i = 1; i < nn[0]; i++) {
                index = nn[i];
                this.influences[index][nnn[index]--] = j;
            }
            j++;
        }
        for(i = 0, j = 0; i < n; j++) {
            k = Math.min(n,i+64);
            for(; i < k; i++) {
                for(l = 1; l < influences.length; l++) {
                    blockCache.size();
                }
            }
            arr = new int[blockCache.size()];
            k = 0;
            for(int val : blockCache) {
                arr[k++] = val;
            }
            blockCache.clear();
            Arrays.sort(arr);
            this.blockInfluences[j] = arr;
        }
        this.generator = new ThreeSatSolutionGenerator(this);
        this.distanceFunctions = new Object[] {new ThreeSatDistance1(this),new ThreeSatDistance2(this)};
        this.heuristics = new Object[] {new ThreeSatHeuristicC1(this),new ThreeSatHeuristicL1(this),new ThreeSatHeuristicM1(this),new ThreeSatHeuristicM3(this),new ThreeSatHeuristicR1(this)};
        this.ratio1 = (double) this.c/this.v;
        this.ratio2 = this.ratio1*this.ratio1;
        this.ratio3 = this.ratio1*this.ratio2;
        this.ratioReciprocal1 = (double) this.v/this.c;
        this.ratioReciprocal2 = this.ratioReciprocal1*this.ratioReciprocal1;
        this.ratioReciprocal3 = this.ratioReciprocal1*this.ratioReciprocal2;
        this.linearizedRatio1 = Math.abs(4.26-this.ratio1);
        this.linearizedRatio2 = this.linearizedRatio1*this.linearizedRatio1;
        this.linearizedRatio3 = this.linearizedRatio1*this.linearizedRatio2;
    }
    
    @Override
    public Heuristic<ThreeSatSolution> getHeuristic(int index) {
        return (Heuristic<ThreeSatSolution>) this.heuristics[index];
    }

    @Override
    public int getNumberOfHeuristics() {
        return this.heuristics.length;
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
        return (DistanceFunction<ThreeSatSolution>) this.distanceFunctions[index];
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        return this.distanceFunctions.length;
    }

    @Override
    public SolutionGenerator<ThreeSatSolution> getSolutionGenerator() {
        return this.generator;
    }

    /**
     * @return the number of variables
     */
    public int getV() {
        return v;
    }

    /**
     * @return the number of clauses
     */
    public int getC() {
        return c;
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

    /**
     * @return the blockInfluences
     */
    public int[][] getBlockInfluences() {
        return blockInfluences;
    }
    
}
