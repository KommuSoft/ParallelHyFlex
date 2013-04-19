package parallelhyflex.hyperheuristics.records;

import java.util.Arrays;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.generators.ConstantGenerator;
import parallelhyflex.utils.Utils;

public class ProbabilityVectorBase implements ProbabilityVector {

    private double[] cdf;

    public ProbabilityVectorBase(int initialLength) {
        this(initialLength, new ConstantGenerator<Integer, Double>(1.0d));
    }

    public ProbabilityVectorBase(int initialLength, Generator<Integer, Double> generator) {
        this.cdf = new double[initialLength];
        this.resetProbabilities(generator);
    }

    @Override
    public double[] getProbabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetProbabilities() {
        this.resetProbabilities(new ConstantGenerator<Integer, Double>(1.0d));
    }

    @Override
    public int generateIndex() {
        double rand = Utils.StaticRandom.nextDouble() * cdf[cdf.length - 1];
        int index = Arrays.binarySearch(cdf, rand);
        if (index < 0) {
            index = ~index;
        }
        return index;
    }

    @Override
    public double getProbability(int index) {
        return this.getUnnormalizedProbability(index) / this.getUnnormalizedTotal();
    }

    @Override
    public double getUnnormalizedTotal() {
        return this.cdf[this.cdf.length - 1];
    }

    @Override
    public void setProbability(int index, double probability) {
        double P = this.getUnnormalizedTotal();
        double pj = this.getUnnormalizedProbability(index);
        this.setUnnormalizedProbability(index, probability * (P - pj) / (1 - probability));
    }

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

    @Override
    public int getLength() {
        return this.cdf.length;
    }

    @Override
    public void resetProbabilities(Generator<Integer, Double> generator) {
        double cdfi = 0.0d;
        for (int i = 0; i < this.cdf.length; i++) {
            cdfi += generator.generate(i);
            cdf[i] = cdfi;
        }
    }

    @Override
    public double getUnnormalizedProbability(int index) {
        if (index > 0) {
            return this.cdf[index] - this.cdf[index - 1];
        } else {
            return this.cdf[0];
        }
    }

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
}
