package parallelhyflex.hyperheuristics.records;

import java.util.logging.Logger;


/**
 *
 * @author kommusoft
 */
public class EvaluatedHeuristicRecordBase extends HeuristicRecordBase implements EvaluatedHeuristicRecord {
    
    private double evaluation = Double.NaN;
    
    /**
     *
     * @param heuristicIndex
     */
    public EvaluatedHeuristicRecordBase (int heuristicIndex) {
        super(heuristicIndex);
    }

    /**
     *
     * @return
     */
    @Override
    public double getEvaluation() {
        return this.evaluation;
    }

    /**
     *
     * @param evaluation
     */
    @Override
    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(EvaluatedHeuristicRecord o) {
        return ((Double) this.getEvaluation()).compareTo(o.getEvaluation());
    }
    private static final Logger LOG = Logger.getLogger(EvaluatedHeuristicRecordBase.class.getName());
    
}
