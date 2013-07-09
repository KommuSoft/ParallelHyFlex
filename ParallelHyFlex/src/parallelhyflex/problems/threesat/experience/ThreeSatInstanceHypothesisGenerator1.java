package parallelhyflex.problems.threesat.experience;

import parallelhyflex.problemdependent.experience.InstanceHypothesisGeneratorBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.constraint.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatInstanceHypothesisGenerator1 extends InstanceHypothesisGeneratorBase<ThreeSatSolution, ThreeSatWritableEnforceableConstraint1, ThreeSatProblem> {

    /**
     *
     * @param problem
     */
    public ThreeSatInstanceHypothesisGenerator1(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public ThreeSatWritableEnforceableConstraint1 generate(ThreeSatSolution variable) {
        double[] cdf = this.getProblem().getIndexCDF();
        long clause;
        do {
            clause = ClauseUtils.generateCompletelyTrueClause(variable.getCompactBitArray(), cdf);
        } while (!ClauseUtils.isValidClause(clause));
        return new ThreeSatWritableEnforceableConstraint1(this.getProblem(), clause);
    }
}
