package parallelhyflex.hyperheuristics.adaphh.records;

import java.util.Collection;
import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.population.TabuableTabuPopulationBase;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public class AdaptiveDynamicHeuristicSetStrategy extends TabuableTabuPopulationBase<AdapHHHeuristicRecord> implements Phasable {

    private final AdapHHHeuristicRecordEvaluator evaluator;
    
    /**
     *
     * @param evaluator
     */
    public AdaptiveDynamicHeuristicSetStrategy (AdapHHHeuristicRecordEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    /**
     *
     */
    @Override
    public void newPhase() {
        this.tabuTick();
        //this.processAll(this.evaluator);
        int k = this.size();
        int n = k + this.getNumberOfTabuedIndividuals();
        int tabuing = (k * (k - 1)) / (2 * n);
        Collection<AdapHHHeuristicRecord> tabucollection = this.getWorsts(evaluator, tabuing);
        double fastest = 0.0d;
        double[] exc = new double[this.size()];
        AdapHHHeuristicRecord[] recs = new AdapHHHeuristicRecord[this.size()];
        double exci;
        double avg = 0.0d;
        double fas = Double.POSITIVE_INFINITY;
        int nb = 0, i = 0;
        for (AdapHHHeuristicRecord record : this) {
            nb += record.getCpbest();
            exci = (double) record.getTspent() / record.getCmoves();
            if (exci < fas) {
                fas = exci;
            }
            avg += exci;
            exc[i] = exci;
            recs[i++] = record;
            //tabucollection.add(record);
            record.newPhase();
        }
        fas = 1.0d / fas;
        avg /= this.size();
        double sigma = StatisticsUtils.variation(exc, avg) * fas * fas;
        avg *= 2.0d * fas;
        if (sigma > 2.0d && nb > 1.0d) {
            for (i = 0; i < exc.length; i++) {
                if(exc[i] > avg) {
                    tabucollection.add(recs[i]);
                }
            }
        }
        this.tabu(tabucollection);
    }
}