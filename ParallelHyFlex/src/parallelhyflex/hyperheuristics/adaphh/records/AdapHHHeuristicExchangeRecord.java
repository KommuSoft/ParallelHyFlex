package parallelhyflex.hyperheuristics.adaphh.records;

import java.io.Serializable;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicExchangeRecord implements Serializable {

    private double fimp = 0.0d, fwrs = 0.0d;
    private long tspent = 0x00;
    private int cbest = 0, cmoves = 0;

    public void processed(long dt) {
        this.cmoves++;
        this.tspent += dt;
    }

    public void newBest() {
        this.cbest++;
    }

    public void addImprovement(double df) {
        this.fimp += df;
    }

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

    @Override
    public String toString() {
        return String.format("AdapHHHeuristicExchangeRecord{fimp=%s, fwrs=%s, tspent=%s, cbest=%s, cmoves=%s%s", fimp, fwrs, tspent, cbest, cmoves, '}');
    }
}
