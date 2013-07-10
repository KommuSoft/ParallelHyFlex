package parallelhyflex.hyperheuristics.paradaphh.records;

import java.util.logging.Logger;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordEvaluatorBase;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHHeuristicRecordProbabilityVector extends EvaluatedHeuristicRecordEvaluatorBase<ParAdapHHHeuristicRecord> {
    
    private double tf = 1.0d;
    private double tspent = 0.5d;

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public Double generate(ParAdapHHHeuristicRecord variable) {
        if(tspent > 0.0d) {
            return Math.pow(((double) variable.getCbest()+1)/tspent,1+3.0d*this.getTf()*this.getTf()*this.getTf());
        }
        else {
            return 1.0d;
        }
    }

    /**
     * @return the tf
     */
    public double getTf() {
        return tf;
    }

    /**
     * @param tf the tf to set
     */
    public void setTf(double tf) {
        this.tf = tf;
    }

    /**
     * @return the tspent
     */
    public double getTspent() {
        return tspent;
    }

    /**
     * @param tspent the tspent to set
     */
    public void setTspent(double tspent) {
        this.tspent = tspent;
    }
    private static final Logger LOG = Logger.getLogger(ParAdapHHHeuristicRecordProbabilityVector.class.getName());
    
}
