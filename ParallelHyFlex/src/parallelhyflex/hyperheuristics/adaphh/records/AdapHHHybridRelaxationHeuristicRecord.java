package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MAX;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MIN;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.S;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.Sa;
import parallelhyflex.utils.Utils;


/**
 * 
 * @author kommusoft
 */
public class AdapHHHybridRelaxationHeuristicRecord extends AdapHHHeuristicRecord {

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex) {
        super(adaphh, heuristicIndex);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        super(adaphh, heuristicIndex, tabuDurationOffset);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(adaphh, heuristicIndex, tabuDurationOffset, tabuDurationLimit);
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
