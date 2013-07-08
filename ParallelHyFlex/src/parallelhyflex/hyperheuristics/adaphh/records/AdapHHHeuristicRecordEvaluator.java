package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W1;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W2;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W3;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W4;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W5;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W6;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.ADHS_W7;
import parallelhyflex.hyperheuristics.adaphh.AdapHHPointer;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordEvaluatorBase;

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
        double tgspent = 0.0d;
        double fgimp = 0.0d;
        double fgwrs = 0.0d;

        for (AdapHHHeuristicExchangeRecord her : rec.getForeignProxy()) {
            if (her != null) {
                tgspent += her.getTspent();
                fgimp += her.getFimp();
                fgwrs += her.getFwrs();
            }
        }
        double var = ADHS_W2 * fpimp - ADHS_W3 * fpwrs;
        if (this.getAdapHH().getPeriodGlobalImprovement()) {
            var += (cpbest + 1) * (ADHS_W1 * var * this.getAdapHH().getRemaingTime());
        }
        var /= tpspent;
        var += (ADHS_W4 * fimp - ADHS_W5 * fwrs) / tspent;
        var += (ADHS_W6 * fgimp - ADHS_W7 * fgwrs) / tgspent;
        return var;
    }

    @Override
    public AdapHH getAdapHH() {
        return this.adapHH;
    }
}
