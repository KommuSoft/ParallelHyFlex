package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.records.HeuristicRecordEvaluatorBase;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicRecordEvaluator extends HeuristicRecordEvaluatorBase<AdapHHHeuristicRecord> {

    public static final double w1 = 16.0d, w2 = 8.0d, w3 = 4.0d, w4 = 2.0d, w5 = 1.0d;//TODO: exact parameters
    
    private boolean globalImprovement = false;
    private double tRemain = Double.POSITIVE_INFINITY;

    @Override
    public Double generate(AdapHHHeuristicRecord rec) {
        int cpbest = rec.getCpbest();
        double fimp = rec.getFimp();
        double fwrs = rec.getFwrs();
        double fpimp = rec.getFpimp();
        double fpwrs = rec.getFpwrs();
        double tspent = rec.getTspent();
        double tpspent = rec.getTpspent();
        double tremain = 10.0d;
        double var = 0.0d;
        if(this.globalImprovement) {
            var = 1.0d;
        }
        var *= cpbest+1;
        var *= w1*var*tremain;
        var += w2*fpimp-w3*fpwrs;
        var /= tpspent;
        var += (w4*fimp-w5*fwrs)/tspent;
        return var;
    }

    /**
     * @return the globalImprovement
     */
    public boolean isGlobalImprovement() {
        return globalImprovement;
    }

    /**
     * @param globalImprovement the globalImprovement to set
     */
    public void setGlobalImprovement(boolean globalImprovement) {
        this.globalImprovement = globalImprovement;
    }

    /**
     * @return the tRemain
     */
    public double gettRemain() {
        return tRemain;
    }

    /**
     * @param tRemain the tRemain to set
     */
    public void settRemain(double tRemain) {
        this.tRemain = tRemain;
    }
}
