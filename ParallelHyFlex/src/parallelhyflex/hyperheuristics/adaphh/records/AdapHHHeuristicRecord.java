package parallelhyflex.hyperheuristics.adaphh.records;

import java.util.Date;
import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.Tabuable;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordBase;
import parallelhyflex.problemdependent.heuristics.HeuristicType;

/**
 *
 * @author kommusoft
 */
public class AdapHHHeuristicRecord extends EvaluatedHeuristicRecordBase implements Tabuable, Phasable {

    private final boolean crossover;
    private final AdapHH adaphh;
    private int cpbest = 0, cbest = 0, cmoves = 0;
    private double fimp = 0.0d, fwrs = 0.0d;
    private double fpimp = 0.0d, fpwrs = 0.0d;
    private long tspent = 0x00, tpspent = 0x00;
    private final int tabuDurationOffset, tabuDurationLimit;
    private int tabuDuration;
    private boolean tabued = true;
    private int tabuEscapeCounter = 0;

    /**
     *
     * @param heuristicIndex The index of the heuristic
     */
    public AdapHHHeuristicRecord(AdapHH adaphh, int heuristicIndex) {
        this(adaphh, heuristicIndex, 5);
    }

    /**
     *
     * @param heuristicIndex The index of the heuristic
     * @param tabuDurationOffset According to the paper of Mustafa Misir, the
     * tabu-duration must be set to sqrt(2*n) with n the number of heuristics.
     */
    public AdapHHHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        this(adaphh, heuristicIndex, tabuDurationOffset, 2 * tabuDurationOffset);
    }

    /**
     *
     * @param heuristicIndex The index of the heuristic
     * @param tabuDurationOffset According to the paper of Mustafa Misir, the
     * tabu-duration must be set to sqrt(2*n) with n the number of heuristics.
     */
    public AdapHHHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(heuristicIndex);
        this.crossover = adaphh.getHeuristicType(heuristicIndex).equals(HeuristicType.Crossover);
        this.adaphh = adaphh;
        this.tabuDurationOffset = tabuDurationOffset;
        this.tabuDurationLimit = tabuDurationLimit;
        this.tabuDuration = tabuDurationOffset;
    }

    @Override
    public void newPhase() {
        if (!this.isTabued()) {
            this.resetPhaseMemory();
            this.tabuEscapeCounter++;
        }
    }

    public void processed(long dt) {
        this.cmoves++;
        this.tspent += dt;
        this.tpspent += dt;
    }

    public void newBest() {
        this.cbest++;
        this.cpbest++;
    }

    public void addImprovement(double df) {
        this.fpimp += df;
        this.fimp += df;
    }

    public void addWorsening(double df) {
        this.fwrs += df;
        this.fpwrs += df;
    }

    /**
     * @return the cpbest
     */
    public int getCpbest() {
        return cpbest;
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
     * @return the fpimp
     */
    public double getFpimp() {
        return fpimp;
    }

    /**
     * @return the fpwrs
     */
    public double getFpwrs() {
        return fpwrs;
    }

    /**
     * @return the tspent
     */
    public double getTspent() {
        return tspent;
    }

    /**
     * @return the tpspent
     */
    public double getTpspent() {
        return tpspent;
    }

    public void incrementTabuDuration() {
        this.tabuDuration = Math.min(this.getTabuDuration() + 1, this.tabuDurationLimit);
    }

    public void resetTabuDuration() {
        this.tabuDuration = this.tabuDurationOffset;
    }

    public boolean shouldExclude() {
        return this.tabuDuration >= this.tabuDurationOffset;
    }

    /**
     * @return the tabuDuration
     */
    @Override
    public int getTabuDuration() {
        return tabuDuration;
    }

    @Override
    public void willTabu() {
        this.tabued = true;
        if (this.getTabuEscapeCounter() <= 1) {
            this.incrementTabuDuration();
        } else {
            this.resetTabuDuration();
        }
        this.resetPhaseMemory();
    }

    private void resetPhaseMemory() {
        this.cpbest = 0;
        this.fpimp = 0.0d;
        this.fpwrs = 0.0d;
        this.tpspent = 0;
    }

    @Override
    public void willUntabu() {
        this.tabued = false;
    }

    /**
     * @return the cmoves
     */
    public int getCmoves() {
        return cmoves;
    }

    /**
     * @return the cbest
     */
    public int getCbest() {
        return cbest;
    }

    public void processed(long dt, double delta) {
        this.processed(dt);
        if (delta > 0.0d) {
            this.addWorsening(delta);
        } else {
            this.addImprovement(-delta);
        }
    }

    /**
     * @return the tabued
     */
    public boolean isTabued() {
        return tabued;
    }

    /**
     * @return the tabuEscapeCounter
     */
    public int getTabuEscapeCounter() {
        return tabuEscapeCounter;
    }

    public double getCiMove() {
        return (double) this.cmoves / (double) this.tspent;
    }

    public void execute(int from, int to) {
        double oldeval = this.getAdaphh().getObjectiveFunction(0, from);
        int from2 = 0;
        if(this.isCrossover()) {
            from2 = this.getAdaphh().getRandomHistorySolutionIndex();
            oldeval = 0.5*(oldeval+this.getAdaphh().getObjectiveFunction(0,from2));
        }
        long oldticks = new Date().getTime();
        if(this.isCrossover()) {
            this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), from, from2, to);
        }
        else {
            this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), from, to);
        }
        long dt = new Date().getTime() - oldticks;
        double neweval = this.getAdaphh().getObjectiveFunction(0, to);
        this.processed(dt, neweval - oldeval);
        if (this.getAdaphh().checkImprovement(neweval)) {
            this.newBest();
        }
    }

    /**
     * @return the adaphh
     */
    public AdapHH getAdaphh() {
        return adaphh;
    }

    /**
     * @return the crossover
     */
    public boolean isCrossover() {
        return crossover;
    }
}
