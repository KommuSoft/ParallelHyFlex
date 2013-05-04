package parallelhyflex.problems.fdcsp.problem.constraints;

/**
 *
 * @author kommusoft
 */
public class Variable {
    
    private int id;
    private static int idDispatcher = 0;
    
    public Variable () {
        this.id = idDispatcher++;
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
        return "_G"+id;
    }
    
}
