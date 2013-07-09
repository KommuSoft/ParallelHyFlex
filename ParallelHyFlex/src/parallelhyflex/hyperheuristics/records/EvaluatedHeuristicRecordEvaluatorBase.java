package parallelhyflex.hyperheuristics.records;


/**
 *
 * @author kommusoft
 * @param <TEvaluatedHeuristicRecord>
 */
public abstract class EvaluatedHeuristicRecordEvaluatorBase<TEvaluatedHeuristicRecord extends EvaluatedHeuristicRecord> extends HeuristicRecordEvaluatorBase<TEvaluatedHeuristicRecord> implements EvaluatedHeuristicRecordEvaluator<TEvaluatedHeuristicRecord> {

    /**
     *
     * @param parameter
     */
    @Override
    public void execute(TEvaluatedHeuristicRecord parameter) {
        parameter.setEvaluation(this.generate(parameter));
    }
    
}
