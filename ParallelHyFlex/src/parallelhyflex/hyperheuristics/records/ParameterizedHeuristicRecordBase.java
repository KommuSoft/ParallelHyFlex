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

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 73 * hash + ((Double) this.depthOfSearch).hashCode();
        hash = 73 * hash + ((Double) this.intensityOfMutation).hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParameterizedHeuristicRecordBase other = (ParameterizedHeuristicRecordBase) obj;
        return (super.equals(other) && other.depthOfSearch == this.depthOfSearch && other.intensityOfMutation == this.intensityOfMutation);
    }
    
}
