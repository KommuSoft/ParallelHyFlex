/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.InstanceHypothesisGeneratorBase;

/**
 *
 * @author kommusoft
 */
public class ThreeSatInstanceHypothesisGenerator1 extends InstanceHypothesisGeneratorBase<ThreeSatSolution,ThreeSatWritableEnforceableConstraint1,ThreeSatProblem> {
    
    public ThreeSatInstanceHypothesisGenerator1 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public ThreeSatWritableEnforceableConstraint1 generate(ThreeSatSolution variable) {
        double[] cdf = this.getProblem().getIndexCDF();
        long clause;
        do {
        clause = ClauseUtils.generateCompletelyTrueClause(variable.getCompactBitArray(), cdf);
        } while(!ClauseUtils.isValidClause(clause));
        return new ThreeSatWritableEnforceableConstraint1(this.getProblem(),clause);
    }
    
}
