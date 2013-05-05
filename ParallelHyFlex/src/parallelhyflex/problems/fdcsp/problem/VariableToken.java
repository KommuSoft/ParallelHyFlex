package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.TokenAnnotation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="[A-Z][A-Za-z]*")
public class VariableToken {
    
    private String name;
    private int id;
    private static int idDispatcher = 0;
    private FiniteIntegerDomain domain;
    
    public VariableToken () {
        this.id = idDispatcher++;
        this.name = null;
    }
    public VariableToken (String name) {
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
        final VariableToken other = (VariableToken) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "_G"+id;
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
    
}
