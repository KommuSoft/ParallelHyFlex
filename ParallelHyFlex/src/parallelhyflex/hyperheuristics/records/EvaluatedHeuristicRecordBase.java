/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.records;


public class EvaluatedHeuristicRecordBase extends HeuristicRecordBase implements EvaluatedHeuristicRecord {
    
    private double evaluation = Double.NaN;
    
    public EvaluatedHeuristicRecordBase (int heuristicIndex) {
        super(heuristicIndex);
    }

    @Override
    public double getEvaluation() {
        return this.evaluation;
    }

    @Override
    public double setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
    
}
