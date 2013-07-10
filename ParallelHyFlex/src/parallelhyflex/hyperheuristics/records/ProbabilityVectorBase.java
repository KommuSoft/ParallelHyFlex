package parallelhyflex.hyperheuristics.records;

import java.util.Arrays;
import java.util.logging.Logger;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.generators.ConstantGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ProbabilityVectorBase implements ProbabilityVector {

    private double[] cdf;

    /**
     *
     * @param initialLength
     */
    public ProbabilityVectorBase(int initialLength) {
        this(initialLength, new ConstantGenerator<Integer, Double>(1.0d));
    }

    /**
     *
     * @param initialLength
     * @param generator
     */
    public ProbabilityVectorBase(int initialLength, Generator<Integer, Double> generator) {
        this.cdf = new double[initialLength];
        this.resetProbabilities(generator);
    }

    /**
     *
     * @return
     */
    @Override
    public double[] getProbabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void resetProbabilities() {
        this.resetProbabilities(new ConstantGenerator<Integer, Double>(1.0d));
    }

    /**
     *
     * @return
     */
    @Override
    public int generateIndex() {
        double rand = Utils.StaticRandom.nextDouble() * cdf[cdf.length - 1];
        int index = Arrays.binarySearch(cdf, rand);
        if (index < 0) {
            index = ~index;
        }
        return index;
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public double getProbability(int index) {
        return this.getUnnormalizedProbability(index) / this.getUnnormalizedTotal();
    }

    /**
     *
     * @return
     */
    @Override
    public double getUnnormalizedTotal() {
        return this.cdf[this.cdf.length - 1];
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void setProbability(int index, double probability) {
        double P = this.getUnnormalizedTotal();
        double pj = this.getUnnormalizedProbability(index);
        this.setUnnormalizedProbability(index, probability * (P - pj) / (1 - probability));
    }

    /**
     *
     * @param newLength
     */
    @Override
    public void setLength(int newLength) {
        if (this.cdf.length != newLength) {
            double[] newcdf = new double[newLength];
            int min = Math.min(newLength, this.cdf.length);
            System.arraycopy(cdf, 0, newcdf, 0, min);
            double total = this.getUnnormalizedTotal();
            for (int i = this.cdf.length; i < newLength; i++) {
                newcdf[i] = total;
            }
            this.cdf = newcdf;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getLength() {
        return this.cdf.length;
    }

    /**
     *
     * @param generator
     */
    @Override
    public void resetProbabilities(Generator<Integer, Double> generator) {
        double cdfi = 0.0d;
        for (int i = 0; i < this.cdf.length; i++) {
            cdfi += generator.generate(i);
            cdf[i] = cdfi;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public double getUnnormalizedProbability(int index) {
        if (index > 0) {
            return this.cdf[index] - this.cdf[index - 1];
        } else {
            return this.cdf[0];
        }
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void setUnnormalizedProbability(int index, double probability) {
        double dp;
        if (index > 0) {
            dp = this.cdf[index] - this.cdf[index - 1];
        } else {
            dp = this.cdf[0];
        }
        dp = probability - dp;
        for (int i = index; i < this.cdf.length; i++) {
            this.cdf[i] += dp;
        }
    }
    private static final Logger LOG = Logger.getLogger(ProbabilityVectorBase.class.getName());
}
