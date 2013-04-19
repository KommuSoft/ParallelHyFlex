package parallelhyflex.problemdependent.solution;

/**
 *
 * @author kommusoft
 */
public interface SolutionGenerator<TSolution extends Solution<TSolution>> extends SolutionReader<TSolution> {

    public TSolution generateSolution();

    public void setSeed(long seed);
}
