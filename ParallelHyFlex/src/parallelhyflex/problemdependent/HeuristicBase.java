/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problemdependent;

import parallelhyflex.HeuristicType;

/**
 *
 * @author kommusoft
 */
public abstract class HeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends Heuristic<TSolution> {
    
    private final TProblem problem;
    
    public HeuristicBase (TProblem problem, HeuristicType type) {
        super(type);
        this.problem = problem;
    }
    
    /**
     * @return the problem
     */
    public TProblem getProblem() {
        return problem;
    }
    
    public double getIntensityOfMutation () {
        return this.getProblem().getIntensityOfMutation();
    }
    public double getDepthOfSearch () {
        return this.getProblem().getDepthOfSearch();
    }
    
}
