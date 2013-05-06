package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.problems.fdcsp.problem.expressions.VariableExpression;

/**
 *
 * @author kommusoft
 */
public class Variable implements Token, VariableExpression {
    private static int idDispatcher = 0;

    private String name;
    private int id;
    private FiniteIntegerDomain domain;

    public Variable() {
        this.id = idDispatcher++;
        this.name = null;
        this.domain = FiniteIntegerDomain.all();
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
        if(this.name == null) {
            return "_G" + id;
        }
        else {
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
    public FiniteIntegerDomain getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(FiniteIntegerDomain domain) {
        this.domain = domain;
    }

}
