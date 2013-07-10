package parallelhyflex.hyperheuristics.paradaphh;

import java.util.logging.Logger;
import parallelhyflex.algebra.improvement.FixedImprovementList;
import parallelhyflex.communication.Communication;
import parallelhyflex.logging.LoggingParameters;

/**
 *
 * @author kommusoft
 */
public class AdaptiveIterationLimitedListbasedThresholdMoveAcceptor extends ParAdapHHPointerBase {

    private static final Logger LOG = Logger.getLogger(AdaptiveIterationLimitedListbasedThresholdMoveAcceptor.class.getName());
    private int k, adapt_iterations, w_iterations = 0, best_list_index = 0;
    private FixedImprovementList<Double> bestList;

    /**
     *
     * @param adaphh
     */
    public AdaptiveIterationLimitedListbasedThresholdMoveAcceptor(ParAdapHH adaphh) {
        super(adaphh);
    }

    /**
     *
     * @param adaphh
     */
    public AdaptiveIterationLimitedListbasedThresholdMoveAcceptor(ParAdapHH adaphh, FixedImprovementList<Double> bestList) {
        this(adaphh);
        this.setBestList(bestList);
    }

    /**
     *
     * @param sa
     * @param s
     * @param sb
     */
    public boolean acceptMove(int sa, int s, int sb) {
        //Communication.log("accepting...");
        double fs = this.getParAdapHH().getObjectiveFunction(s);
        double fsa = this.getParAdapHH().getObjectiveFunction(sa);
        double fsb = this.getParAdapHH().getObjectiveFunction(sb);
        if (adapt_iterations >= ParAdapHH.AILLA_K && best_list_index < ParAdapHH.AILLA_LENGTH - 1) {
            best_list_index++;
        }
        if (fsa < fs) {
            this.getParAdapHH().copySolution(sa, s);
            w_iterations = 0;
            if (fsa < fsb) {
                this.best_list_index = 0;
                this.getParAdapHH().copySolution(sa, sb);
                this.adapt_iterations = 0;
                this.getBestList().addImprovement(fsa);
                /*Communication.log("added %s/best was %s",fsa,fsb);
                 Communication.log("BESTLIST[%s]=%s WITH BESTLIST=%s", best_list_index, this.getBestList().getHistoryElement(best_list_index), this.bestList);*/
                Communication.logFileTime(LoggingParameters.LOG_ADAPHH_BLST, LoggingParameters.LOG_ADAPHH_BLST_TEXT, this.getBestList());
            }
            return true;
        } else if (fs == fsa) {
            this.getParAdapHH().copySolution(sa, s);
        } else {
            this.w_iterations++;
            this.adapt_iterations++;
            if (w_iterations >= k) {
                double val = this.getBestList().getHistoryElement(best_list_index);
                if (fsa < val) {
                    this.getParAdapHH().copySolution(sa, s);
                    this.w_iterations = 0;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the bestList
     */
    public FixedImprovementList<Double> getBestList() {
        return bestList;
    }

    /**
     * @param bestList the bestList to set
     */
    public void setBestList(FixedImprovementList<Double> bestList) {
        this.bestList = bestList;
        double best = this.getParAdapHH().getBestObjectiveSolution();
        this.bestList.reset(best);
    }
}
