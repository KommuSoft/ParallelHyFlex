package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.hyperheuristics.adaphh.AdapHHPointer;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordEvaluatorBase;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W1;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W2;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W3;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W4;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W5;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W6;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W7;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicRecordEvaluator extends EvaluatedHeuristicRecordEvaluatorBase<AdapHHHeuristicRecord> implements AdapHHPointer {

    private final AdapHH adapHH;

    public AdapHHHeuristicRecordEvaluator(AdapHH adapHH) {
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
        if (this.getAdapHH().getPeriodGlobalImprovement()) {
            var = cpbest + 1;
            var *= ADHS_W1 * var * this.getAdapHH().getRemaingTime();
        }
        var += ADHS_W2 * fpimp - ADHS_W3 * fpwrs;
        var /= tpspent;
        var += (ADHS_W4 * fimp - ADHS_W5 * fwrs) / tspent;
        return var;
    }

    @Override
    public AdapHH getAdapHH() {
        return this.adapHH;
    }
}
