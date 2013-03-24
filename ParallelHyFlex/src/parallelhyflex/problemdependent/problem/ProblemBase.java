package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemBase<TSolution extends Solution<TSolution>> implements Problem<TSolution> {
    
    private double intensityOfMutation = 0.1d;
    private double depthOfSearch = 0.1d;
    
    @Override
    public double getIntensityOfMutation () {
        return this.intensityOfMutation;
    }
    @Override
    public double getDepthOfSearch () {
        return this.depthOfSearch;
    }

    @Override
    public void setIntensityOfMutation(double intensityOfMutation) {
        this.intensityOfMutation = Math.max(0.0d,Math.min(0.99999d,intensityOfMutation));
    }

    @Override
    public void setDepthOfSearch(double depthOfSearch) {
        this.depthOfSearch = Math.max(0.0d,Math.min(0.99999d,depthOfSearch));
    }
    
}
