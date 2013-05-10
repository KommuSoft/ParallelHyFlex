package parallelhyflex.problemdependent.experience;

/**
 *
 * @author kommusoft
 */
public interface EvaluatedHypothesis {
    
    int getNumberOfEvaluations ();
    void evaluateTrue (double fitness);
    void evaluateFalse (double fitness);
    double getEvaluation ();
    
}
