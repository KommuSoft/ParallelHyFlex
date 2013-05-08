package parallelhyflex.problems.fdcsp.problem;

import java.util.ArrayList;
import java.util.Iterator;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;
import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolution;

/**
 *
 * @author kommusoft
 */
public class Variable implements Token, Expression, Iterable<FDCOPConstraint> {

    private static int idDispatcher = 0;
    private String name;
    private final int id;
    private int index;
    private MutableFiniteIntegerDomain domain;
    private final ArrayList<FDCOPConstraint> constraints = new ArrayList<>();

    public Variable() {
        this.id = idDispatcher++;
        this.name = null;
        this.domain = MutableFiniteIntegerDomain.all();
    }

    public Variable(String name) {
        this();
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        return hash;
    }
    
    public void addConstraint (FDCOPConstraint constraint) {
        this.constraints.add(constraint);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Variable other = (Variable) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.name == null) {
            return "_G" + id;
        } else {
            return this.name;
        }
    }

    /**
     * @return the name
     */
    protected String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * @return the domain
     */
    public MutableFiniteIntegerDomain getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(MutableFiniteIntegerDomain domain) {
        this.domain = domain;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public double getExpressionValue(FDCOPSolution solution) {
        return solution.getVariableValue(this);
    }

    @Override
    public Iterator<FDCOPConstraint> iterator() {
        return this.constraints.iterator();
    }
}
