package parallelhyflex.hyperheuristics.paradaphh.records;

import java.util.logging.Logger;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W1;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W2;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W3;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W4;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W5;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W6;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.ADHS_W7;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHHPointer;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordEvaluatorBase;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHHeuristicRecordEvaluator extends EvaluatedHeuristicRecordEvaluatorBase<ParAdapHHHeuristicRecord> implements ParAdapHHPointer {

    private final ParAdapHH adapHH;

    /**
     *
     * @param adapHH
     */
    public ParAdapHHHeuristicRecordEvaluator(ParAdapHH adapHH) {
        this.adapHH = adapHH;
    }

    /**
     *
     * @param rec
     * @return
     */
    @Override
    public Double generate(ParAdapHHHeuristicRecord rec) {
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

        for (ParAdapHHHeuristicExchangeRecord her : rec.getAllProxy()) {
            if (her != null) {
                tgspent += her.getTspent();
                fgimp += her.getFimp();
                fgwrs += her.getFwrs();
            }
        }
        double var = ADHS_W2 * fpimp - ADHS_W3 * fpwrs;
        if (this.getParAdapHH().getPeriodGlobalImprovement()) {
            var += (cpbest + 1) * (ADHS_W1 * var * this.getParAdapHH().getRemaingTime());
        }
        var /= tpspent;
        var += (ADHS_W4 * fimp - ADHS_W5 * fwrs) / tspent;
        var += (ADHS_W6 * fgimp - ADHS_W7 * fgwrs) / tgspent;
        return var;
    }

    /**
     *
     * @return
     */
    @Override
    public ParAdapHH getParAdapHH() {
        return this.adapHH;
    }
    private static final Logger LOG = Logger.getLogger(ParAdapHHHeuristicRecordEvaluator.class.getName());
}
