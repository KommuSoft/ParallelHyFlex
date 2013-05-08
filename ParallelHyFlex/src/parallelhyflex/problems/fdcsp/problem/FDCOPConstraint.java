package parallelhyflex.problems.fdcsp.problem;

/**
 *
 * @author kommusoft
 */
public interface FDCOPConstraint extends Iterable<Variable> {
    
    boolean relaxDomains ();

    public boolean relaxDomains(FiniteIntegerDomain[] domains);//TODO: find ways to improve performance
    
}
