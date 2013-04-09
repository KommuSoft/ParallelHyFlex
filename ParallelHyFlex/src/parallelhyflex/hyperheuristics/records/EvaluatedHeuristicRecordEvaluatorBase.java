package parallelhyflex.hyperheuristics.records;


public abstract class EvaluatedHeuristicRecordEvaluatorBase<TEvaluatedHeuristicRecord extends EvaluatedHeuristicRecord> extends HeuristicRecordEvaluatorBase<TEvaluatedHeuristicRecord> implements EvaluatedHeuristicRecordEvaluator<TEvaluatedHeuristicRecord> {

    @Override
    public void execute(TEvaluatedHeuristicRecord parameter) {
        parameter.setEvaluation(this.generate(parameter));
    }
    
}
