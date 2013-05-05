package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenBase;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "[A-Z][A-Za-z]*")
public class Variable extends TokenBase<Variable> {

    private String name;
    private int id;
    private static int idDispatcher = 0;
    private FiniteIntegerDomain domain;

    public Variable() {
        this.id = idDispatcher++;
        this.name = null;
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
    protected FiniteIntegerDomain getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    protected void setDomain(FiniteIntegerDomain domain) {
        this.domain = domain;
    }

    @Override
    public Variable generate(String variable) {
        return new Variable(variable);
    }
}
