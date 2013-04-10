package parallelhyflex.hyperheuristics.records;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public interface ProbabilityVector {

    public double[] getProbabilities();

    public void setLength(int newLength);

    public int getLength();

    public void resetProbabilities();

    public void resetProbabilities(Generator<Integer, Double> generator);

    public int generateIndex();

    public double getProbability(int index);

    public void setProbability(int index, double probability);

    public double getUnnormalizedProbability(int index);

    public void setUnnormalizedProbability(int index, double probability);

    double getUnnormalizedTotal();
}
