package parallelhyflex.hyperheuristics.records;


/**
 * 
 * @author kommusoft
 */
public class HeuristicRecordBase implements HeuristicRecord {
    
    private final int heuristicIndex;
    
    /**
     *
     * @param heuristicIndex
     */
    public HeuristicRecordBase (int heuristicIndex) {
        this.heuristicIndex = heuristicIndex;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeuristicIndex() {
        return this.heuristicIndex;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode () {
        return ((Integer) this.heuristicIndex).hashCode();
    }

    /**
     *
     * @param obj
     * @return
     */
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
