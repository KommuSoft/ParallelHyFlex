package parallelhyflex.hyperheuristics.records;

/**
 *
 * @author kommusoft
 */
public interface EvaluatedHeuristicRecord extends HeuristicRecord, Comparable<EvaluatedHeuristicRecord> {
    
    public double getEvaluation ();
    public void setEvaluation (double evaluation);
    
}
