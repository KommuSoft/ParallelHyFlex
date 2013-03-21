/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.InstanceHypothesisGeneratorBase;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatInstanceHypothesisGenerator extends InstanceHypothesisGeneratorBase<ThreeSatSolution,ThreeSatWritableEnforceableConstraint1,ThreeSatProblem> {
    
    public ThreeSatInstanceHypothesisGenerator (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public ThreeSatWritableEnforceableConstraint1 generate(ThreeSatSolution variable) {
        double[] cdf = this.getProblem().getIndexCDF();
        int index0 = Utils.getRandomIndexFromCDF(cdf);
        int index1 = Utils.getRandomIndexFromCDF(cdf);
        int index2 = Utils.getRandomIndexFromCDF(cdf);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
