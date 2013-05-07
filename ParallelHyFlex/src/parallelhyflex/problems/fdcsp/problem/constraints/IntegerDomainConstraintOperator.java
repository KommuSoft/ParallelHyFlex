package parallelhyflex.problems.fdcsp.problem.constraints;

import java.util.Iterator;
import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.problems.fdcsp.problem.FDCOPConstraint;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
@OperatorAnnotation()
public class IntegerDomainConstraintOperator extends OperatorBase implements FDCOPConstraint {

    private final IntegerDomainConstraint constraint;

    public IntegerDomainConstraintOperator(IntegerDomainConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean canSetLeft(Token token) {
        return (token instanceof Variable);
    }

    @Override
    public boolean canSetRight(Token token) {
        return (token instanceof Variable);
    }

    @Override
    public void process() {
        Variable vl = getLeftVariable();
        Variable vr = getRightVariable();
        System.out.println(String.format("reducing domains of %s=%s and %s=%s", vl, vl.getDomain(), vr, vr.getDomain()));
        this.constraint.reduceDomains(vl.getDomain(), vr.getDomain());
        System.out.println(String.format("now they are: %s=%s and %s=%s", vl, vl.getDomain(), vr, vr.getDomain()));
    }

    @Override
    public Iterator<Variable> iterator() {
        return new ArrayIterator(getLeftVariable(), getRightVariable());
    }

    @Override
    public String toString() {
        return String.format("<%s;%s;%s>",this.constraint,this.getLeft(),this.getRight());
    }

    @Override
    public boolean relaxDomains() {
        return this.constraint.reduceDomains(getLeftDomain(),getRightDomain());
    }

    public Variable getLeftVariable() {
        return (Variable) this.getLeft();
    }

    public Variable getRightVariable() {
        return (Variable) this.getRight();
    }

    public FiniteIntegerDomain getLeftDomain() {
        return this.getLeftVariable().getDomain();
    }

    public FiniteIntegerDomain getRightDomain() {
        return this.getRightVariable().getDomain();
    }
    
}
