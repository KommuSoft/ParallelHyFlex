package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.algebra.CircularList;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MAX;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MIN;
import parallelhyflex.utils.Utils;


/**
 * 
 * @author kommusoft
 */
public class AdapHHHybridRelaxationHeuristicRecord extends AdapHHHeuristicRecord {
    
    private final CircularList<AdapHHHeuristicRecord> nextHeuristic;

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex) {
        super(adaphh, heuristicIndex);
        this.nextHeuristic = new CircularList<AdapHHHeuristicRecord>(AdapHH.LIST_SIZE);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        super(adaphh, heuristicIndex, tabuDurationOffset);
        this.nextHeuristic = new CircularList<AdapHHHeuristicRecord>(AdapHH.LIST_SIZE);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(adaphh, heuristicIndex, tabuDurationOffset, tabuDurationLimit);
        this.nextHeuristic = new CircularList<AdapHHHeuristicRecord>(AdapHH.LIST_SIZE);
    }
    
    public void execute () {
        double gamma = Utils.border(GAMMA_MIN, (this.getAdaphh().getCBestS() + 1.0d) / (this.getAdaphh().getCBestR() + 1.0d), GAMMA_MAX);
        if (Utils.StaticRandom.nextDouble() < Math.pow((double) this.getAdaphh().getCPhase() / this.getAdaphh().getPl(), gamma)) {
            this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), AdapHH.S, AdapHH.Sa);
            /*if() {
                
             }
             else {
                
             }*/
            //TODO: do relayHybridisation
        }
    }
    
}
