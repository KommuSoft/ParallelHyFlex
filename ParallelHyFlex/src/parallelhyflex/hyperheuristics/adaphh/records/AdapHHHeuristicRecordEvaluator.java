package parallelhyflex.hyperheuristics.adaphh.records;

import java.util.Set;
import parallelhyflex.algebra.Tuple2;
import parallelhyflex.hyperheuristics.records.HeuristicRecordEvaluator;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicRecordEvaluator implements HeuristicRecordEvaluator<AdapHHHeuristicRecord> {

    public static final double w1 = 16.0d, w2 = 8.0d, w3 = 4.0d, w4 = 2.0d, w5 = 1.0d;//TODO: exact parameters

    @Override
    public Double generate(Tuple2<AdapHHHeuristicRecord, Set<AdapHHHeuristicRecord>> variable) {
        AdapHHHeuristicRecord rec = variable.getItem1();
        int cpbest = rec.getCpbest();
        double fimp = rec.getFimp();
        double fwrs = rec.getFwrs();
        double fpimp = rec.getFpimp();
        double fpwrs = rec.getFpwrs();
        double tspent = rec.getTspent();
        double tpspent = rec.getTpspent();
        double b = 0.0d;
        double tremain = 10.0d;
        for(AdapHHHeuristicRecord hr : variable.getItem2()) {
            if(hr.getCpbest() > 0) {
                b = 1.0d;
                break;
            }
        }
        double var = cpbest+1;
        var *= w1*var*b*tremain;
        return var;
    }
}
