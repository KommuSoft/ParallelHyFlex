package parallelhyflex.problemdependent.experience;

import parallelhyflex.problemdependent.constraints.Constraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface EvaluatedHypothesis<TSolution extends Solution<TSolution>, THypothesis extends Constraint<TSolution>> {

    THypothesis getHypothesis();

    void evaluate(TSolution solution, double fitness);

    int getNumberOfEvaluations();

    void evaluateTrue(double fitness);

    void evaluateFalse(double fitness);

    double getEvaluation();
}
