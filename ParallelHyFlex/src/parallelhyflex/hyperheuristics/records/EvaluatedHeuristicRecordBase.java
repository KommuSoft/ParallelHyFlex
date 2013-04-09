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
    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int compareTo(EvaluatedHeuristicRecord o) {
        return ((Double) this.getEvaluation()).compareTo(o.getEvaluation());
    }
    
}
