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
    
}
