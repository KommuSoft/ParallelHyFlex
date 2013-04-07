/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.records;


public class ParameterizedHeuristicRecordBase extends HeuristicRecordBase implements ParameterizedHeuristicRecord {
    
    private final double intensityOfMutation, depthOfSearch;
    
    public ParameterizedHeuristicRecordBase (int heuristicIndex, double intensityOfMutation, double depthOfSearch) {
        super(heuristicIndex);
        this.intensityOfMutation = intensityOfMutation;
        this.depthOfSearch = depthOfSearch;
    }

    @Override
    public double getIntensityOfMutation() {
        return this.intensityOfMutation;
    }

    @Override
    public double getDepthOfSearch() {
        return this.depthOfSearch;
    }
    
}
