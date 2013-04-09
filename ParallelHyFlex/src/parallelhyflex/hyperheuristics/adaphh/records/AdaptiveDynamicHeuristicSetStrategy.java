package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.TabuPopulationBase;

/**
 *
 * @author kommusoft
 */
public class AdaptiveDynamicHeuristicSetStrategy extends TabuPopulationBase<AdapHHHeuristicRecord> implements Phasable {
    
    private AdapHHHeuristicRecordEvaluator evaluator = new AdapHHHeuristicRecordEvaluator();

    @Override
    public void newPhase() {
        this.processAll(this.evaluator);
    }
    
}