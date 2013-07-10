package parallelhyflex.hyperheuristics.records;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public interface ProbabilityVector {

    /**
     *
     * @return
     */
    public double[] getProbabilities();

    /**
     *
     * @param newLength
     */
    public void setLength(int newLength);

    /**
     *
     * @return
     */
    public int getLength();

    /**
     *
     */
    public void resetProbabilities();

    /**
     *
     * @param generator
     */
    public void resetProbabilities(Generator<Integer, Double> generator);

    /**
     *
     * @return
     */
    public int generateIndex();

    /**
     *
     * @param index
     * @return
     */
    public double getProbability(int index);

    /**
     *
     * @param index
     * @param probability
     */
    public void setProbability(int index, double probability);

    /**
     *
     * @param index
     * @return
     */
    public double getUnnormalizedProbability(int index);

    /**
     *
     * @param index
     * @param probability
     */
    public void setUnnormalizedProbability(int index, double probability);

    /**
     *
     * @return
     */
    double getUnnormalizedTotal();
}
