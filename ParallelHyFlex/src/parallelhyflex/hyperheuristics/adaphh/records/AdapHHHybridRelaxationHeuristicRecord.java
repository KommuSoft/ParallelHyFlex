package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.algebra.collections.CircularList;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class AdapHHHybridRelaxationHeuristicRecord extends AdapHHHeuristicRecord {

    private final CircularList<AdapHHHeuristicRecord> nextHeuristic;//TODO: fill list with records and maintain them

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex) {
        super(adaphh, heuristicIndex);
        this.nextHeuristic = new CircularList<>(AdapHH.LIST_SIZE);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        super(adaphh, heuristicIndex, tabuDurationOffset);
        this.nextHeuristic = new CircularList<>(AdapHH.LIST_SIZE);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(adaphh, heuristicIndex, tabuDurationOffset, tabuDurationLimit);
        this.nextHeuristic = new CircularList<>(AdapHH.LIST_SIZE);
    }

    @Override
    public void execute() {
        this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), AdapHH.S, AdapHH.Sa);
        AdapHHHeuristicRecord adhr;
        if (this.nextHeuristic.size() > 0 && Utils.nextDouble() < AdapHH.LIST_PROBABILITY) {
            //System.out.println("nextHeuristic");
            adhr = ProbabilityUtils.randomElement(nextHeuristic);
        } else {
            //System.out.println("adhs");
            adhr = this.getAdaphh().getAdhs().getRandomIndividual();
        }
        //System.out.println("ADHR===="+adhr);
        if(adhr != null) {
            adhr.execute(AdapHH.Sa, AdapHH.Saa);
        }
        else {
            this.getAdaphh().copySolution(AdapHH.Sa,AdapHH.Saa);
        }
    }
}
