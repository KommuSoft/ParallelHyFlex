package parallelhyflex.problems.threesat;

import java.util.Arrays;
import java.util.HashSet;
import parallelhyflex.problemdependent.DistanceFunction;
import parallelhyflex.problemdependent.Heuristic;
import parallelhyflex.problemdependent.ObjectiveFunction;
import parallelhyflex.problemdependent.ProblemBase;
import parallelhyflex.problemdependent.SolutionGenerator;
import parallelhyflex.utils.Utils;

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
    private final double vcVariableMean, vcVariableVariation, vcVariableMin, vcVariableMax, vcVariableEntropy, vcClauseMean, vcClauseVariation, vcClauseMin, vcClauseMax, vcClauseEntropy;
    private final ThreeSatSolutionGenerator generator;
    private final Object[] distanceFunctions;
    private final Object[] heuristics;
    
    public ThreeSatProblem (long[] constraints) {
        this.constraints = constraints;
        this.c = this.constraints.length;
        int n = ClauseUtils.getLargestIndex(constraints)+1;
        this.v = n;
        int[] npn = new int[n], nnn = new int[n], np = new int[4], nn = new int[4], arr;
        int[] degclause = new int[this.c];
        int index, nplus, i, j = 0, k = 0, l;
        this.influences = new int[n][];
        this.blockInfluences = new int[(n+63)>>6][];
        HashSet<Integer> blockCache = new HashSet<>();
        for(long constraint : constraints) {
            degclause[k] = ClauseUtils.degree(constraint);
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
        this.vcClauseMean = Utils.Mean(degclause);
        this.vcClauseVariation = Utils.Variation(degclause,this.vcClauseMean);
        this.vcClauseMin = Utils.Min(degclause);
        this.vcClauseMax = Utils.Max(degclause);
        this.vcClauseEntropy = Utils.entropy(degclause);
        this.vcVariableMean = Utils.Mean(nnn);
        this.vcVariableVariation = Utils.Variation(nnn,this.vcVariableMean);
        this.vcVariableMin = Utils.Min(nnn);
        this.vcVariableMax = Utils.Max(nnn);
        this.vcVariableEntropy = Utils.entropy(nnn);
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
        return (Heuristic<ThreeSatSolution>) this.getHeuristics()[index];
    }

    @Override
    public int getNumberOfHeuristics() {
        return this.getHeuristics().length;
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
        return (DistanceFunction<ThreeSatSolution>) this.getDistanceFunctions()[index];
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        return this.getDistanceFunctions().length;
    }

    @Override
    public SolutionGenerator<ThreeSatSolution> getSolutionGenerator() {
        return this.getGenerator();
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

    /**
     * @return the ratio1
     */
    public double getRatio1() {
        return ratio1;
    }

    /**
     * @return the ratio2
     */
    public double getRatio2() {
        return ratio2;
    }

    /**
     * @return the ratio3
     */
    public double getRatio3() {
        return ratio3;
    }

    /**
     * @return the ratioReciprocal1
     */
    public double getRatioReciprocal1() {
        return ratioReciprocal1;
    }

    /**
     * @return the ratioReciprocal2
     */
    public double getRatioReciprocal2() {
        return ratioReciprocal2;
    }

    /**
     * @return the ratioReciprocal3
     */
    public double getRatioReciprocal3() {
        return ratioReciprocal3;
    }

    /**
     * @return the linearizedRatio1
     */
    public double getLinearizedRatio1() {
        return linearizedRatio1;
    }

    /**
     * @return the linearizedRatio2
     */
    public double getLinearizedRatio2() {
        return linearizedRatio2;
    }

    /**
     * @return the linearizedRatio3
     */
    public double getLinearizedRatio3() {
        return linearizedRatio3;
    }

    /**
     * @return the vcVariableMean
     */
    public double getVcVariableMean() {
        return vcVariableMean;
    }

    /**
     * @return the vcVariableVariation
     */
    public double getVcVariableVariation() {
        return vcVariableVariation;
    }

    /**
     * @return the vcVariableMin
     */
    public double getVcVariableMin() {
        return vcVariableMin;
    }

    /**
     * @return the vcVariableMax
     */
    public double getVcVariableMax() {
        return vcVariableMax;
    }

    /**
     * @return the vcVariableEntropy
     */
    public double getVcVariableEntropy() {
        return vcVariableEntropy;
    }

    /**
     * @return the vcClauseMean
     */
    public double getVcClauseMean() {
        return vcClauseMean;
    }

    /**
     * @return the vcClauseVariation
     */
    public double getVcClauseVariation() {
        return vcClauseVariation;
    }

    /**
     * @return the vcClauseMin
     */
    public double getVcClauseMin() {
        return vcClauseMin;
    }

    /**
     * @return the vcClauseMax
     */
    public double getVcClauseMax() {
        return vcClauseMax;
    }

    /**
     * @return the vcClauseEntropy
     */
    public double getVcClauseEntropy() {
        return vcClauseEntropy;
    }

    /**
     * @return the generator
     */
    public ThreeSatSolutionGenerator getGenerator() {
        return generator;
    }

    /**
     * @return the distanceFunctions
     */
    public Object[] getDistanceFunctions() {
        return distanceFunctions;
    }

    /**
     * @return the heuristics
     */
    public Object[] getHeuristics() {
        return heuristics;
    }
    
}
