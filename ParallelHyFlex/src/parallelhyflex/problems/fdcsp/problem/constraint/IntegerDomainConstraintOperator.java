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

    /**
     *
     * @param constraint
     */
    public IntegerDomainConstraintOperator(IntegerDomainConstraint constraint) {
        this.constraint = constraint;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetLeft(Token token) {
        return (token instanceof Variable);
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetRight(Token token) {
        return (token instanceof Variable);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Variable> iterator() {
        return new ArrayIterator(getLeftVariable(), getRightVariable());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("<%s;%s;%s>", this.constraint, this.getLeft(), this.getRight());
    }

    /**
     *
     * @return
     */
    @Override
    public boolean relaxDomains() {
        return this.constraint.reduceDomains(this.getLeftDomain(), this.getRightDomain());
    }

    /**
     *
     * @return
     */
    public Variable getLeftVariable() {
        return (Variable) this.getLeft();
    }

    /**
     *
     * @return
     */
    public Variable getRightVariable() {
        return (Variable) this.getRight();
    }

    /**
     *
     * @return
     */
    public MutableFiniteIntegerDomain getLeftDomain() {
        return this.getLeftVariable().getDomain();
    }

    /**
     *
     * @return
     */
    public MutableFiniteIntegerDomain getRightDomain() {
        return this.getRightVariable().getDomain();
    }

    /**
     *
     * @param domains
     * @return
     */
    @Override
    public boolean relaxDomains(MutableFiniteIntegerDomain[] domains) {
        return this.constraint.reduceDomains(domains[this.getLeftVariable().getIndex()],domains[this.getRightVariable().getIndex()]);
    }
    
}
