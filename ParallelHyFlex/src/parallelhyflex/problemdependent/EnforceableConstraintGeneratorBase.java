/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class EnforceableConstraintGeneratorBase<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>, TEC extends EnforceableConstraint<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements EnforceableConstraintGenerator<TSolution,TEC> {
    
    public EnforceableConstraintGeneratorBase (TProblem problem) {
        super(problem);
    }
    
}
