package parallelhyflex.hyperheuristics.records;


/**
 * 
 * @author kommusoft
 */
public class HeuristicRecordBase implements HeuristicRecord {
    
    private final int heuristicIndex;
    
    public HeuristicRecordBase (int heuristicIndex) {
        this.heuristicIndex = heuristicIndex;
    }

    @Override
    public int getHeuristicIndex() {
        return this.heuristicIndex;
    }
    
    @Override
    public int hashCode () {
        return ((Integer) this.heuristicIndex).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HeuristicRecordBase other = (HeuristicRecordBase) obj;
        if (this.heuristicIndex != other.heuristicIndex) {
            return false;
        }
        return true;
    }
    
}
