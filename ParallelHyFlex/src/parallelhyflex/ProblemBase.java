package parallelhyflex;

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
        this.intensityOfMutation = intensityOfMutation;
    }

    @Override
    public void setDepthOfSearch(double depthOfSearch) {
        this.depthOfSearch = depthOfSearch;
    }
    
}
