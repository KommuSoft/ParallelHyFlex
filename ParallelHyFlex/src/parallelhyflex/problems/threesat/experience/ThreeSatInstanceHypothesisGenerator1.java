package parallelhyflex.problems.threesat.experience;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.experience.InstanceHypothesisGeneratorBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraint1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatInstanceHypothesisGenerator1 extends InstanceHypothesisGeneratorBase<ThreeSatSolution, ThreeSatWriteableEnforceableConstraint1, ThreeSatProblem> {

    private static final Logger LOG = Logger.getLogger(ThreeSatInstanceHypothesisGenerator1.class.getName());

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
    public ThreeSatWriteableEnforceableConstraint1 generate(ThreeSatSolution variable) {
        double[] cdf = this.getProblem().getIndexCDF();
        long clause;
        do {
            clause = ClauseUtils.generateCompletelyTrueClause(variable.getCompactBitArray(), cdf);
        } while (!ClauseUtils.isValidClause(clause));
        return new ThreeSatWriteableEnforceableConstraint1(this.getProblem(), clause);
    }
}
