package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface ProblemPointer<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> {
    
    public TProblem getProblem ();
    
}
