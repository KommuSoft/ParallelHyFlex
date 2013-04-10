package parallelhyflex.hyperheuristics.records;

/**
 *
 * @author kommusoft
 */
public interface ProbabilityVector {
    
    public double[] getProbabilities ();
    public void resetProbabilities ();
    public int generateIndex ();
    public double getProbability (int index);
    public void setProbability (int index, double probability);
    
}
