package parallelhyflex.hyperheuristics.paradaphh.records;

import java.util.ArrayList;
import java.util.logging.Logger;
import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.population.TabuableTabuPopulationBase;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public class AdaptiveDynamicHeuristicSetStrategy extends TabuableTabuPopulationBase<ParAdapHHHeuristicRecord> implements Phasable {

    private static final Logger LOG = Logger.getLogger(AdaptiveDynamicHeuristicSetStrategy.class.getName());
    private final ParAdapHHHeuristicRecordEvaluator evaluator;

    /**
     *
     * @param evaluator
     */
    public AdaptiveDynamicHeuristicSetStrategy(ParAdapHHHeuristicRecordEvaluator evaluator) {
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
        //Communication.log("A new phase with size %s, will tabu %s heuristics", this.size(), tabuing);
        ArrayList<ParAdapHHHeuristicRecord> tabucollection = new ArrayList<>();
        if (tabuing > 0) {
            tabucollection.addAll(this.getWorsts(evaluator, tabuing));
        }
        double fastest = 0.0d;
        double[] exc = new double[this.size()];
        ParAdapHHHeuristicRecord[] recs = new ParAdapHHHeuristicRecord[this.size()];
        double exci;
        double avg = 0.0d;
        double fas = Double.POSITIVE_INFINITY;
        int nb = 0, i = 0;
        for (ParAdapHHHeuristicRecord record : this) {
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
                if (exc[i] > avg) {
                    tabucollection.add(recs[i]);
                }
            }
        }
        this.tabu(tabucollection);
    }
}