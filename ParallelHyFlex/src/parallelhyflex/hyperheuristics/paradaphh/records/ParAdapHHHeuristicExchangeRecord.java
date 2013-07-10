package parallelhyflex.hyperheuristics.paradaphh.records;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHHeuristicExchangeRecord implements Serializable {

    private double fimp = 0.0d, fwrs = 0.0d;
    private long tspent = 0x00;
    private int cbest = 0, cmoves = 0;

    /**
     *
     * @param dt
     */
    public void processed(long dt) {
        this.cmoves++;
        this.tspent += dt;
    }

    /**
     *
     */
    public void newBest() {
        this.cbest++;
    }

    /**
     *
     * @param df
     */
    public void addImprovement(double df) {
        this.fimp += df;
    }

    /**
     *
     * @param df
     */
    public void addWorsening(double df) {
        this.fwrs += df;
    }

    /**
     * @return the fimp
     */
    public double getFimp() {
        return fimp;
    }

    /**
     * @return the fwrs
     */
    public double getFwrs() {
        return fwrs;
    }

    /**
     * @return the tspent
     */
    public long getTspent() {
        return tspent;
    }

    /**
     * @return the cbest
     */
    public int getCbest() {
        return cbest;
    }

    /**
     * @return the cmoves
     */
    public int getCmoves() {
        return cmoves;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("AdapHHHeuristicExchangeRecord{fimp=%s, fwrs=%s, tspent=%s, cbest=%s, cmoves=%s%s", fimp, fwrs, tspent, cbest, cmoves, '}');
    }
    private static final Logger LOG = Logger.getLogger(ParAdapHHHeuristicExchangeRecord.class.getName());
}
