/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problemdependent.solution;

/**
 *
 * @author kommusoft
 */
public interface SolutionGenerator<TSolution extends Solution<TSolution>> extends SolutionReader<TSolution> {

    public TSolution generateSolution();

    public void setSeed(long seed);
}
