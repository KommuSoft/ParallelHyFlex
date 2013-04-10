package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.TabuableTabuPopulationBase;

/**
 *
 * @author kommusoft
 */
public class AdaptiveDynamicHeuristicSetStrategy extends TabuableTabuPopulationBase<AdapHHHeuristicRecord> implements Phasable {
    
    private AdapHHHeuristicRecordEvaluator evaluator = new AdapHHHeuristicRecordEvaluator();

    @Override
    public void newPhase() {
        this.tabuTick();
        this.processAll(this.evaluator);
        int k = this.size();
        int n = k+this.getNumberOfTabuedIndividuals();
        int tabuing = (k*(k-1))/(2*n);
        for(AdapHHHeuristicRecord record : this) {
            record.newPhase();
        }
        this.tabu(this.getWorsts(evaluator,tabuing));
    }
    
}