package parallelhyflex.problems.fdcsp.problem.constraint;

import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.problems.fdcsp.problem.FDCOPConstraint;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
public abstract class ConstraintOperatorBase extends OperatorBase implements FDCOPConstraint {

    @Override
    public void process() {
        for(Variable v : this) {
            v.addConstraint(this);
        }
    }
    
}
