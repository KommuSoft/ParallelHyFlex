package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.hyperheuristics.adaphh.AdapHHPointer;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordEvaluatorBase;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicRecordEvaluator extends EvaluatedHeuristicRecordEvaluatorBase<AdapHHHeuristicRecord> implements AdapHHPointer {

    public static final double w1 = 16.0d, w2 = 8.0d, w3 = 4.0d, w4 = 2.0d, w5 = 1.0d;//TODO: exact parameters
    private final AdapHH adapHH;
    
    public AdapHHHeuristicRecordEvaluator (AdapHH adapHH) {
        this.adapHH = adapHH;
    }

    @Override
    public Double generate(AdapHHHeuristicRecord rec) {
        int cpbest = rec.getCpbest();
        double fimp = rec.getFimp();
        double fwrs = rec.getFwrs();
        double fpimp = rec.getFpimp();
        double fpwrs = rec.getFpwrs();
        double tspent = rec.getTspent();
        double tpspent = rec.getTpspent();
        double var = 0.0d;
        if(this.getAdapHH().getPeriodGlobalImprovement()) {
            var = cpbest+1;
            var *= w1*var*this.getAdapHH().getRemaingTime();
        }
        var += w2*fpimp-w3*fpwrs;
        var /= tpspent;
        var += (w4*fimp-w5*fwrs)/tspent;
        return var;
    }

    @Override
    public AdapHH getAdapHH() {
        return this.adapHH;
    }
}
