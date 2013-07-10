package parallelhyflex.hyperheuristics.paradaphh.records;

import java.util.Date;
import java.util.logging.Logger;
import parallelhyflex.algebra.Phasable;
import parallelhyflex.algebra.Tabuable;
import parallelhyflex.communication.Communication;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_BEST_IM1;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_BEST_IM2;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_BEST_IOE;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_BEST_WM;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_EQUA_IM;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_EQUA_IOE1;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_EQUA_IOE2;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_IMPR_IM1;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_IMPR_IM2;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_IMPR_IOE;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_IMPR_WM;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.P_WORS_IM;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.THETA1;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.THETA2;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.THETA3;
import static parallelhyflex.hyperheuristics.paradaphh.ParAdapHH.THETA4;
import parallelhyflex.hyperheuristics.records.EvaluatedHeuristicRecordBase;
import parallelhyflex.hyperheuristics.records.HeuristicPerformanceType;
import static parallelhyflex.hyperheuristics.records.HeuristicPerformanceType.ImprovingMore;
import static parallelhyflex.hyperheuristics.records.HeuristicPerformanceType.ImprovingOrEqual;
import static parallelhyflex.hyperheuristics.records.HeuristicPerformanceType.WorseningMore;
import parallelhyflex.logging.LoggingParameters;
import parallelhyflex.problemdependent.heuristic.HeuristicType;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHHeuristicRecord extends EvaluatedHeuristicRecordBase implements Tabuable, Phasable {

    private static final Logger LOG = Logger.getLogger(ParAdapHHHeuristicRecord.class.getName());
    private final boolean crossover;
    private final ParAdapHH adaphh;
    private int cpbest = 0;
    private double fpimp = 0.0d, fpwrs = 0.0d;
    private long tpspent = 0x00;
    private final int tabuDurationOffset, tabuDurationLimit;
    private int tabuDuration;
    private boolean tabued = true;
    private int tabuEscapeCounter = 0;
    private double dosiom = 0.6d;
    private HeuristicPerformanceType performanceType = HeuristicPerformanceType.OnlyEqual;
    private final ParAdapHHHeuristicExchangeRecord exchangeRecord = new ParAdapHHHeuristicExchangeRecord();
    private Iterable<ParAdapHHHeuristicExchangeRecord> allProxy;

    /**
     *
     * @param adaphh
     * @param heuristicIndex The index of the heuristic
     */
    public ParAdapHHHeuristicRecord(ParAdapHH adaphh, int heuristicIndex) {
        this(adaphh, heuristicIndex, 5);
    }

    /**
     *
     * @param adaphh
     * @param heuristicIndex The index of the heuristic
     * @param tabuDurationOffset According to the paper of Mustafa Misir, the
     * tabu-duration must be set to sqrt(2*n) with n the number of heuristics.
     */
    public ParAdapHHHeuristicRecord(ParAdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        this(adaphh, heuristicIndex, tabuDurationOffset, 2 * tabuDurationOffset);
    }

    /**
     *
     * @param adaphh
     * @param heuristicIndex The index of the heuristic
     * @param tabuDurationOffset According to the paper of Mustafa Misir, the
     * tabu-duration must be set to sqrt(2*n) with n the number of heuristics.
     * @param tabuDurationLimit
     */
    public ParAdapHHHeuristicRecord(ParAdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(heuristicIndex);
        this.crossover = adaphh.getHeuristicType(heuristicIndex).equals(HeuristicType.Crossover);
        this.adaphh = adaphh;
        this.tabuDurationOffset = tabuDurationOffset;
        this.tabuDurationLimit = tabuDurationLimit;
        this.tabuDuration = tabuDurationOffset;
    }

    /**
     *
     */
    @Override
    public void newPhase() {
        if (!this.isTabued()) {
            this.resetPhaseMemory();
            this.tabuEscapeCounter++;
        }
    }

    /**
     *
     * @param dt
     */
    public void processed(long dt) {
        this.getExchangeRecord().processed(dt);
        this.tpspent += dt;
    }

    /**
     *
     */
    public void newBest() {
        this.getExchangeRecord().newBest();
        this.cpbest++;
    }

    /**
     *
     * @param df
     */
    public void addImprovement(double df) {
        this.getExchangeRecord().addImprovement(df);
        this.fpimp += df;
    }

    /**
     *
     * @param df
     */
    public void addWorsening(double df) {
        this.getExchangeRecord().addWorsening(df);
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
        return this.getExchangeRecord().getFimp();
    }

    /**
     * @return the fwrs
     */
    public double getFwrs() {
        return this.getExchangeRecord().getFwrs();
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
        return this.getExchangeRecord().getTspent();
    }

    /**
     * @return the tpspent
     */
    public double getTpspent() {
        return tpspent;
    }

    /**
     *
     */
    public void incrementTabuDuration() {
        this.tabuDuration = Math.min(this.getTabuDuration() + 1, this.tabuDurationLimit);
    }

    /**
     *
     */
    public void resetTabuDuration() {
        this.tabuDuration = this.tabuDurationOffset;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     */
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
        if (this.fpimp == 0.0d && this.fpwrs == 0.0d) {
            this.setPerformanceType(HeuristicPerformanceType.OnlyEqual);
        } else if (this.fpwrs == 0.0d) {
            this.setPerformanceType(HeuristicPerformanceType.ImprovingOrEqual);
        } else if (this.fpimp == 0.0d) {
            this.setPerformanceType(HeuristicPerformanceType.WorseningOrEqual);
        } else if (this.fpimp > this.fpwrs) {
            this.setPerformanceType(HeuristicPerformanceType.ImprovingMore);
        } else {
            this.setPerformanceType(HeuristicPerformanceType.WorseningMore);
        }
        this.cpbest = 0;
        this.fpimp = 0.0d;
        this.fpwrs = 0.0d;
        this.tpspent = 0;
    }

    /**
     *
     */
    @Override
    public void willUntabu() {
        this.tabued = false;
    }

    /**
     * @return the cmoves
     */
    public int getCmoves() {
        return this.getExchangeRecord().getCmoves();
    }

    /**
     * @return the cbest
     */
    public int getCbest() {
        return this.getExchangeRecord().getCbest();
    }

    /**
     *
     * @param dt
     * @param delta
     */
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

    /**
     *
     * @return
     */
    public double getCiMove() {
        return (double) this.getCmoves() / this.getTspent();
    }

    /**
     *
     */
    public void execute() {
        this.execute(ParAdapHH.S, ParAdapHH.Sa);
    }

    /**
     *
     * @param from
     */
    public void execute(int from) {
        this.execute(from, from + 1);
    }

    /**
     *
     * @param from
     * @param to
     */
    public void execute(int from, int to) {
        Communication.logFileTime(LoggingParameters.LOG_ADAPHH_EXRC, LoggingParameters.LOG_ADAPHH_EXRC_TEXT, this.getHeuristicIndex(), from, to);
        adaphh.setDepthOfSearch(this.getDOSIOM());
        adaphh.setIntensityOfMutation(this.getDOSIOM());
        double oldeval = this.getAdaphh().getObjectiveFunction(0, from);
        int from2 = 0;
        if (this.isCrossover()) {
            from2 = this.getAdaphh().getRandomHistorySolutionIndex();
            oldeval = 0.5 * (oldeval + this.getAdaphh().getObjectiveFunction(0, from2));
        }
        long oldticks = new Date().getTime();
        if (this.isCrossover()) {
            this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), from, from2, to);
        } else {
            this.getAdaphh().applyHeuristic(this.getHeuristicIndex(), from, to);
        }
        long dt = new Date().getTime() - oldticks;
        double neweval = this.getAdaphh().getObjectiveFunction(0, to);
        this.processed(dt, neweval - oldeval);
        if (this.getAdaphh().checkImprovement(neweval)) {
            this.newBest();
        }
        this.parameterAdaption(to, ParAdapHH.Sb, from);
    }

    /**
     * @return the adaphh
     */
    public ParAdapHH getAdaphh() {
        return adaphh;
    }

    /**
     * @return the crossover
     */
    public boolean isCrossover() {
        return crossover;
    }

    /**
     * @return the valuei
     */
    public double getDOSIOM() {
        return dosiom;
    }

    /**
     * @param valuei the valuei to set
     */
    public void setDOSIOM(double valuei) {
        this.dosiom = Utils.border(0.2d, valuei, 1.0d);
    }

    private void parameterAdaption(int sa, int sb, int s) {
        double u = 1.0d;
        double val = this.getDOSIOM();
        double p = Utils.StaticRandom.nextDouble();
        double fsa = this.getAdaphh().getObjectiveFunction(sa);
        double fsb = this.getAdaphh().getObjectiveFunction(sb);
        double fs = this.getAdaphh().getObjectiveFunction(s);
        if (fsa < fsb) {
            switch (getPerformanceType()) {
                case ImprovingOrEqual:
                    if (p < P_BEST_IOE) {
                        u = 0.0d;
                    }
                    break;
                case ImprovingMore:
                    if (p < P_BEST_IM1) {
                        u = -1.0d;
                    } else if (p < P_BEST_IM2) {
                        u = 0.0d;
                    }
                    break;
                case WorseningMore:
                    if (p < P_BEST_WM) {
                        u = 0.0d;
                    }
                    break;
            }
            val += THETA1 * u;
        } else if (fsa < fs) {
            switch (getPerformanceType()) {
                case ImprovingOrEqual:
                    if (p < P_IMPR_IOE) {
                        u = 0.0d;
                    }
                    break;
                case ImprovingMore:
                    if (p < P_IMPR_IM1) {
                        u = -1.0d;
                    } else if (p < P_IMPR_IM2) {
                        u = 0.0d;
                    }
                    break;
                case WorseningMore:
                    if (p < P_IMPR_WM) {
                        u = -1.0d;
                    }
                    break;
            }
            val += THETA2 * u;
        } else if (fsa > fs) {
            if (getPerformanceType().equals(HeuristicPerformanceType.ImprovingMore) && p < P_WORS_IM) {
                u = 0.0d;
            }
            val -= THETA3 * u;
        } else {
            switch (getPerformanceType()) {
                case ImprovingOrEqual:
                    if (p < P_EQUA_IOE1) {
                        u = -1.0d;
                    } else if (p < P_EQUA_IOE2) {
                        u = 0.0d;
                    }
                    break;
                case ImprovingMore:
                    if (p < P_EQUA_IM) {
                        u = 0.0d;
                    }
                    break;
                default:
                    u = -1.0d;
            }
            val -= THETA4 * u;
        }
        this.setDOSIOM(val);
        Communication.logFileTime(LoggingParameters.LOG_ADAPHH_PARC, LoggingParameters.LOG_ADAPHH_PARC_TEXT, this.getHeuristicIndex(), val);
    }

    /**
     * @return the performanceType
     */
    public HeuristicPerformanceType getPerformanceType() {
        return performanceType;
    }

    /**
     * @param performanceType the performanceType to set
     */
    public void setPerformanceType(HeuristicPerformanceType performanceType) {
        this.performanceType = performanceType;
    }

    /**
     * @return the exchangeRecord
     */
    public ParAdapHHHeuristicExchangeRecord getExchangeRecord() {
        return exchangeRecord;
    }

    /**
     * @return the foreignProxy
     */
    public Iterable<ParAdapHHHeuristicExchangeRecord> getAllProxy() {
        return allProxy;
    }

    /**
     * @param foreignProxy the foreignProxy to set
     */
    public void setAllProxy(Iterable<ParAdapHHHeuristicExchangeRecord> foreignProxy) {
        this.allProxy = foreignProxy;
    }

    @Override
    public String toString() {
        int cpbest = getCpbest();
        double fimp = getFimp();
        double fwrs = getFwrs();
        double fpimp = getFpimp();
        double fpwrs = getFpwrs();
        double tspent = getTspent();
        double tpspent = getTpspent();
        double tgspent = 0.0d;
        double fgimp = 0.0d;
        double fgwrs = 0.0d;

        for (ParAdapHHHeuristicExchangeRecord her : getAllProxy()) {
            if (her != null) {
                tgspent += her.getTspent();
                fgimp += her.getFimp();
                fgwrs += her.getFwrs();
            }
        }
        return String.format("{id=%s,cpbest=%s,fimp=%s,fwrs=%s,fpimp=%s,fpwrs=%s,tspent=%s,tpspent=%s,tgspent=%s,fgimp=%s,fgwrs=%s}",this.getHeuristicIndex(),cpbest,fimp,fwrs,fpimp,fpwrs,tspent,tpspent,tgspent,fgimp,fgwrs);
    }
}
