package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.algebra.collections.CircularList;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MAX;
import static parallelhyflex.hyperheuristics.adaphh.AdapHH.GAMMA_MIN;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class AdapHHHybridRelaxationHeuristicRecord extends AdapHHHeuristicRecord {

    private final CircularList<AdapHHHeuristicRecord> nextHeuristic;

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
        if (this.nextHeuristic.size() > 0 && Utils.StaticRandom.nextDouble() < AdapHH.LIST_PROBABILITY) {
            adhr = ProbabilityUtils.randomElement(nextHeuristic);
        } else {
            adhr = this.getAdaphh().getAdhs().getRandomIndividual();
        }
        adhr.execute(AdapHH.Sa, AdapHH.Saa);
    }
}
