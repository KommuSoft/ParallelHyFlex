package parallelhyflex.problems.threesat.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.distance.DistanceFunction;
import parallelhyflex.problemdependent.heuristic.Heuristic;
import parallelhyflex.problemdependent.problem.ObjectiveFunction;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.distance.ThreeSatDistance1;
import parallelhyflex.problems.threesat.distance.ThreeSatDistance2;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicC1;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicL1;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicM1;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicM3;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicR1;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.StatisticsUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblem extends ProblemBase<ThreeSatSolution, ThreeSatSolutionGenerator> {

    private long[] clauses;
    private int[][] influences;
    private int[][] blockInfluences;
    private double[] indexCDF;
    private int v, c;
    private double ratio1, ratio2, ratio3, ratioReciprocal1, ratioReciprocal2, ratioReciprocal3, linearizedRatio1, linearizedRatio2, linearizedRatio3;
    private double vcVariableMean, vcVariableVariation, vcVariableMin, vcVariableMax, vcVariableEntropy, vcClauseMean, vcClauseVariation, vcClauseMin, vcClauseMax, vcClauseEntropy;

    private ThreeSatProblem() {
        super();
        this.setHeuristics(new Heuristic[]{new ThreeSatHeuristicC1(this), new ThreeSatHeuristicL1(this), new ThreeSatHeuristicM1(this), new ThreeSatHeuristicM3(this), new ThreeSatHeuristicR1(this)});
        this.setObjectives(new ObjectiveFunction[]{new ThreeSatObjectiveFunction1()});
        this.setDistances(new DistanceFunction[]{new ThreeSatDistance1(this), new ThreeSatDistance2(this)});
        this.setSolutionGenerator(new ThreeSatSolutionGenerator(this));
    }

    public ThreeSatProblem(long[] constraints) {
        this();
        this.clauses = constraints;
        this.c = this.clauses.length;
        int n = ClauseUtils.getLargestIndex(constraints) + 1;
        this.v = n;
        int[] npn = new int[n], nnn = new int[n], np = new int[4], nn = new int[4], arr;
        int[] degclause = new int[this.c];
        int index, nplus, i, j = 0, k = 0, l;
        this.influences = new int[n][];
        this.blockInfluences = new int[(n + 63) >> 6][];
        HashSet<Integer> blockCache = new HashSet<>();
        for (long constraint : constraints) {
            degclause[k] = ClauseUtils.degree(constraint);
            ClauseUtils.setInfluences(constraint, np, nn);
            for (i = 1; i < np[0]; i++) {
                npn[np[i]]++;
            }
            for (i = 1; i < nn[0]; i++) {
                nnn[nn[i]]++;
            }
        }
        this.indexCDF = new double[n];
        for (i = 0; i < n; i++) {
            nplus = npn[i];
            int total = nplus + nnn[i];
            this.indexCDF[i] = total * StatisticsUtils.pqEntropy((double) nplus / total);
            arr = new int[total + 1];
            arr[0] = nplus;
            nnn[i] = total;
            this.influences[i] = arr;
        }
        ProbabilityUtils.unnormalizedWeightsToCDF(this.indexCDF);
        this.vcClauseMean = StatisticsUtils.mean(degclause);
        this.vcClauseVariation = StatisticsUtils.variation(degclause, this.vcClauseMean);
        this.vcClauseMin = StatisticsUtils.min(degclause);
        this.vcClauseMax = StatisticsUtils.max(degclause);
        this.vcClauseEntropy = StatisticsUtils.entropy(degclause);
        this.vcVariableMean = StatisticsUtils.mean(nnn);
        this.vcVariableVariation = StatisticsUtils.variation(nnn, this.vcVariableMean);
        this.vcVariableMin = StatisticsUtils.min(nnn);
        this.vcVariableMax = StatisticsUtils.max(nnn);
        this.vcVariableEntropy = StatisticsUtils.entropy(nnn);
        for (long constraint : constraints) {
            ClauseUtils.setInfluences(constraint, np, nn);
            for (i = 1; i < np[0]; i++) {
                index = np[i];
                this.influences[index][npn[index]--] = j;
            }
            for (i = 1; i < nn[0]; i++) {
                index = nn[i];
                this.influences[index][nnn[index]--] = j;
            }
            j++;
        }
        for (i = 0, j = 0; i < n; j++) {
            k = Math.min(n, i + 64);
            for (; i < k; i++) {
                for (l = 1; l < influences[i].length; l++) {
                    blockCache.add(influences[i][l]);
                }
            }
            arr = new int[blockCache.size()];
            k = 0;
            for (int val : blockCache) {
                arr[k++] = val;
            }
            blockCache.clear();
            Arrays.sort(arr);
            this.blockInfluences[j] = arr;
        }
        this.ratio1 = (double) this.c / this.v;
        this.ratio2 = this.ratio1 * this.ratio1;
        this.ratio3 = this.ratio1 * this.ratio2;
        this.ratioReciprocal1 = (double) this.v / this.c;
        this.ratioReciprocal2 = this.ratioReciprocal1 * this.ratioReciprocal1;
        this.ratioReciprocal3 = this.ratioReciprocal1 * this.ratioReciprocal2;
        this.linearizedRatio1 = Math.abs(4.26 - this.ratio1);
        this.linearizedRatio2 = this.linearizedRatio1 * this.linearizedRatio1;
        this.linearizedRatio3 = this.linearizedRatio1 * this.linearizedRatio2;
    }

    ThreeSatProblem(long[] constraints, int[][] influences, int[][] blockInfluences, double[] indexCDF, int[] vc, double[] stats) {
        this();
        this.clauses = constraints;
        this.influences = influences;
        this.blockInfluences = blockInfluences;
        this.indexCDF = indexCDF;
        this.v = vc[0];
        this.c = vc[1];
        this.ratio1 = stats[0];
        this.ratio2 = stats[1];
        this.ratio3 = stats[2];
        this.ratioReciprocal1 = stats[3];
        this.ratioReciprocal2 = stats[4];
        this.ratioReciprocal3 = stats[5];
        this.linearizedRatio1 = stats[6];
        this.linearizedRatio2 = stats[7];
        this.linearizedRatio3 = stats[8];
        this.vcVariableMean = stats[9];
        this.vcVariableVariation = stats[10];
        this.vcVariableMin = stats[11];
        this.vcVariableMax = stats[12];
        this.vcVariableEntropy = stats[13];
        this.vcClauseMean = stats[14];
        this.vcClauseVariation = stats[15];
        this.vcClauseMin = stats[16];
        this.vcClauseMax = stats[17];
        this.vcClauseEntropy = stats[18];
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
    public long[] getClauses() {
        return clauses;
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

    @Override
    public void write(DataOutputStream dos) throws IOException {
        SerialisationUtils.writeLongArray(dos, clauses);
        SerialisationUtils.writeIntArray2d(dos, influences);
        SerialisationUtils.writeIntArray2d(dos, blockInfluences);
        SerialisationUtils.writeDoubleArray(dos, getIndexCDF());
        SerialisationUtils.writeIntArray(dos, v, c);
        SerialisationUtils.writeDoubleArray(dos, ratio1, ratio2, ratio3,
                ratioReciprocal1, ratioReciprocal2, ratioReciprocal3,
                linearizedRatio1, linearizedRatio2, linearizedRatio3,
                vcVariableMean, vcVariableVariation, vcVariableMin, vcVariableMax, vcVariableEntropy,
                vcClauseMean, vcClauseVariation, vcClauseMin, vcClauseMax, vcClauseEntropy);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeSatProblem) {
            ThreeSatProblem tsp = (ThreeSatProblem) obj;
            return Utils.arrayEquality(this.getClauses(), tsp.getClauses());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.hashCode(this.clauses);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("3SAT %s", ClauseUtils.clausesToString(this.clauses));
    }

    /**
     * @return the indexCDF
     */
    public double[] getIndexCDF() {
        return indexCDF;
    }
}
