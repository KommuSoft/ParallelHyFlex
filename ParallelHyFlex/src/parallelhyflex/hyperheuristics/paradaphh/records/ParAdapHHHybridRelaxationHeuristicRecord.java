package parallelhyflex.hyperheuristics.paradaphh.records;

import java.util.logging.Logger;
import parallelhyflex.algebra.collections.CircularList;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHHybridRelaxationHeuristicRecord extends ParAdapHHHeuristicRecord {

    private final CircularList<ParAdapHHHeuristicRecord> nextHeuristic;//TODO: fill list with records and maintain them

    /**
     *
     * @param adaphh
     * @param heuristicIndex
     */
    public ParAdapHHHybridRelaxationHeuristicRecord(ParAdapHH adaphh, int heuristicIndex) {
        super(adaphh, heuristicIndex);
        this.nextHeuristic = new CircularList<>(ParAdapHH.LIST_SIZE);
    }

    /**
     *
     * @param adaphh
     * @param heuristicIndex
     * @param tabuDurationOffset
     */
    public ParAdapHHHybridRelaxationHeuristicRecord(ParAdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        super(adaphh, heuristicIndex, tabuDurationOffset);
        this.nextHeuristic = new CircularList<>(ParAdapHH.LIST_SIZE);
    }

    /**
     *
     * @param adaphh
     * @param heuristicIndex
     * @param tabuDurationOffset
     * @param tabuDurationLimit
     */
    public ParAdapHHHybridRelaxationHeuristicRecord(ParAdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(adaphh, heuristicIndex, tabuDurationOffset, tabuDurationLimit);
        this.nextHeuristic = new CircularList<>(ParAdapHH.LIST_SIZE);
    }

    /**
     *
     */
    @Override
    public void execute() {
        this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), ParAdapHH.S, ParAdapHH.Sa);
        ParAdapHHHeuristicRecord adhr;
        if (this.nextHeuristic.size() > 0 && Utils.StaticRandom.nextDouble() < ParAdapHH.LIST_PROBABILITY) {
            //System.out.println("nextHeuristic");
            adhr = ProbabilityUtils.randomElement(nextHeuristic);
        } else {
            //System.out.println("adhs");
            adhr = this.getAdaphh().getAdhs().getRandomIndividual();
        }
        //System.out.println("ADHR===="+adhr);
        if(adhr != null) {
            adhr.execute(ParAdapHH.Sa, ParAdapHH.Saa);
        }
        else {
            this.getAdaphh().copySolution(ParAdapHH.Sa,ParAdapHH.Saa);
        }
    }
    private static final Logger LOG = Logger.getLogger(ParAdapHHHybridRelaxationHeuristicRecord.class.getName());
}
