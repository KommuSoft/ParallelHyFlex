/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public interface SolutionGenerator<TSolution extends Solution> {
    
    public TSolution generateSolution ();
    public void setSeed (long seed);
    
}
