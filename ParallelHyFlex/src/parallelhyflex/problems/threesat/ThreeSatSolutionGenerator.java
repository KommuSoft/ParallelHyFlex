/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.SolutionGeneratorBase;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolutionGenerator extends SolutionGeneratorBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatSolutionGenerator (ThreeSatProblem problem) {
        super(problem);
    }
    
    @Override
    public ThreeSatSolution generateSolution() {
        ThreeSatProblem problem = this.getProblem();
        CompactBitArray cba = CompactBitArray.randomInstance(problem.getV());
        int nfail = 0;
        long[] clauses = problem.getConstraints();
        for(int i = 0; i < clauses.length; i++) {
            if(!cba.satisfiesClause(clauses[i])) {
                nfail++;
            }
        }
        return new ThreeSatSolution(nfail,cba);
    }
    
}
