package parallelhyflex.problemdependent;

import parallelhyflex.communication.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public interface ProblemReader<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> extends ReadableGenerator<TProblem> {
    
}
