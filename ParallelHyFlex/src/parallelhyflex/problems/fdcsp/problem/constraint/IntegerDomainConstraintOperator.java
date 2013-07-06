package parallelhyflex.problems.fdcsp.problem.constraint;

import java.util.Iterator;
import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
@OperatorAnnotation()
public class IntegerDomainConstraintOperator extends ConstraintOperatorBase {

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
    public Iterator<Variable> iterator() {
        return new ArrayIterator(getLeftVariable(), getRightVariable());
    }

    @Override
    public String toString() {
        return String.format("<%s;%s;%s>", this.constraint, this.getLeft(), this.getRight());
    }

    @Override
    public boolean relaxDomains() {
        return this.constraint.reduceDomains(this.getLeftDomain(), this.getRightDomain());
    }

    public Variable getLeftVariable() {
        return (Variable) this.getLeft();
    }

    public Variable getRightVariable() {
        return (Variable) this.getRight();
    }

    public MutableFiniteIntegerDomain getLeftDomain() {
        return this.getLeftVariable().getDomain();
    }

    public MutableFiniteIntegerDomain getRightDomain() {
        return this.getRightVariable().getDomain();
    }

    @Override
    public boolean relaxDomains(MutableFiniteIntegerDomain[] domains) {
        return this.constraint.reduceDomains(domains[this.getLeftVariable().getIndex()],domains[this.getRightVariable().getIndex()]);
    }
    
}