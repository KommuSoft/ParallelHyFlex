package parallelhyflex.hyperheuristics.records;

/**
 *
 * @author kommusoft
 */
public interface EvaluatedHeuristicRecord extends HeuristicRecord {
    
    public double getEvaluation ();
    public void setEvaluation (double evaluation);
    
}
