package parallelhyflex.hyperheuristics.adaphh;

import java.io.IOException;
import java.util.ArrayList;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.hyperheuristics.adaphh.records.AdapHHHeuristicRecord;
import parallelhyflex.hyperheuristics.adaphh.records.AdapHHHeuristicRecordEvaluator;
import parallelhyflex.hyperheuristics.adaphh.records.AdapHHHybridRelaxationHeuristicRecord;
import parallelhyflex.hyperheuristics.adaphh.records.AdaptiveDynamicHeuristicSetStrategy;
import parallelhyflex.hyperheuristics.learning.LearningAutomaton;
import parallelhyflex.hyperheuristics.records.ProbabilityVectorBase;
import parallelhyflex.memory.MemoryExchangePolicy;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WritableExperience;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.SearchSpaceNegotiator;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionReader;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class AdapHH<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends HyperHeuristic<TSolution, TProblem, TEC> {

    public static final int PH_FACTOR = 500;
    public static final int PH_REQUESTED = 100;
    public static final int LIST_SIZE = 10;
    public static final int S = 0;//S location
    public static final int Sa = 1;//S' location
    public static final int Saa = 2;//S'' location
    public static final int Sb = 2;//best solution location
    public static final int HISTORY_LENGTH = 5;
    public static final int LOCAL_MEMORY_SIZE = 4+HISTORY_LENGTH;//S, S', S'', Sb and HISTORY
    public static final int NON_EXCHANGE_MEMORY_FROM = 1;
    public static final int NON_EXCHANGE_MEMORY_TO = 2;
    public static final int AILLA_K = 20;
    public static final int AILLA_LBASE = 5;
    public static final int AILLA_LINITIAL = 10;
    public static final double GAMMA_MIN = 0.02d;
    public static final double GAMMA_MAX = 50.0d;
    public static final double LIST_PROBABILITY = 0.25d;
    public static final double LAMBDA1 = 0.5d;
    public static final double THETA1 = 0.01d;
    public static final double THETA2 = 0.001d;
    public static final double THETA3 = 0.0005d;
    public static final double THETA4 = 0.0001d;
    public static final double P_BEST_IOE = 0.5d;
    public static final double P_BEST_IM1 = 0.25d;
    public static final double P_BEST_IM2 = 0.5d;
    public static final double P_BEST_WM = 0.5d;
    public static final double P_IMPR_IOE = 0.5d;
    public static final double P_IMPR_IM1 = 0.25d;
    public static final double P_IMPR_IM2 = 0.5d;
    public static final double P_IMPR_WM = 0.5d;
    public static final double P_WORS_IM = 0.5d;
    public static final double P_EQUA_IOE1 = 0.25d;
    public static final double P_EQUA_IOE2 = 0.5d;
    public static final double P_EQUA_IM = 0.5d;
    private final AdaptiveDynamicHeuristicSetStrategy adhs;
    private final AdapHHHeuristicRecord[] records;
    private final ProbabilityVectorBase heuristicSelector;
    private final LearningAutomaton<AdapHHHybridRelaxationHeuristicRecord> learningAutomaton;
    private boolean periodGlobalImprovement = false;
    private int cPhase, cBestS, cBestR, pl;
    private double globalOptimum = Double.NaN;

    /**
     * @note: This constructor can only be initialized if the machine is the
     * root (has rank = 0), otherwise, one needs to construct this class with
     * the constructor with the ProblemReader
     * @param problem
     * @throws ProtocolException If this constructor is initialized by a machine
     * with a rank different from zero!
     */
    public AdapHH(TProblem problem, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        super(problem, durationTicks, experience, negotiator, negotiationTicks, solutionReader, LOCAL_MEMORY_SIZE, MemoryExchangePolicy.QueuedProbableDistributed);
        this.learningAutomaton = new LearningAutomaton<>(LAMBDA1);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new AdapHHHeuristicRecordEvaluator(this));
        this.records = new AdapHHHeuristicRecord[this.getNumberOfHeuristics()];
        this.init();
    }

    /**
     * @note: This constructor can only be initialized if the machine is not the
     * root (has rank != 0), otherwise, one needs to construct this class with
     * the constructor with the ProblemReader
     * @param problemReader
     * @param durationTicks
     * @param experience
     * @param solutionReader
     * @throws ProtocolException If this constructor is called when the machine
     * is not the root
     * @throws IOException
     */
    public AdapHH(ProblemReader<TSolution, TProblem> problemReader, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        super(problemReader, durationTicks, experience, negotiator, negotiationTicks, solutionReader, LOCAL_MEMORY_SIZE, MemoryExchangePolicy.QueuedProbableDistributed);
        this.learningAutomaton = new LearningAutomaton<>(LAMBDA1);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new AdapHHHeuristicRecordEvaluator(this));
        this.records = new AdapHHHeuristicRecord[this.getNumberOfHeuristics()];
        this.init();
    }

    @Override
    protected void execute() {
        while (this.hasTimeLeft()) {
            this.periodGlobalImprovement = false;
            this.pl = PH_FACTOR * ((int) Math.sqrt(2 * this.getAdhs().size()));
            this.cPhase = 0;
            for (int i = this.getPl(); i > 0; i--) {
                iteration();
                this.cPhase++;
            }
            endPhase();
        }
    }

    private void iteration() {
    }

    private void aILLAMoveAcceptance() {
    }

    private void endPhase() {
        this.getAdhs().newPhase();
    }

    /**
     * @return the periodGlobalImprovement
     */
    public boolean getPeriodGlobalImprovement() {
        return isPeriodGlobalImprovement();
    }

    private void init() {
        this.getExchangeBlockingMask().setRange(NON_EXCHANGE_MEMORY_FROM, NON_EXCHANGE_MEMORY_TO);//block the exchange of temporary solutions
        int durationoffset = (int) Math.round(Math.sqrt(2.0d * this.records.length));
        for (int i = 0; i < this.records.length; i++) {
            this.records[i] = new AdapHHHeuristicRecord(this, i, durationoffset);
            this.getAdhs().add(this.records[i]);
        }
        double globmin = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.getReadableMemory(); i++) {
            globmin = Math.min(globmin, this.getObjectiveFunction(0, i));
        }
        this.globalOptimum = globmin;
        ArrayList<AdapHHHybridRelaxationHeuristicRecord> all = new ArrayList<>();
        for (int i = 0; i < this.records.length; i++) {
            all.add(new AdapHHHybridRelaxationHeuristicRecord(this, i));
        }
        this.getLearningAutomaton().reset(all);
    }

    public boolean checkImprovement(double neweval) {
        if (neweval < this.getGlobalOptimum()) {
            this.globalOptimum = neweval;
            return true;
        }
        return false;
    }

    /**
     * @return the learningAutomaton
     */
    public LearningAutomaton<AdapHHHybridRelaxationHeuristicRecord> getLearningAutomaton() {
        return learningAutomaton;
    }

    /**
     * @return the periodGlobalImprovement
     */
    public boolean isPeriodGlobalImprovement() {
        return periodGlobalImprovement;
    }

    /**
     * @return the cPhase
     */
    public int getCPhase() {
        return cPhase;
    }

    /**
     * @return the cBestS
     */
    public int getCBestS() {
        return cBestS;
    }

    /**
     * @return the cBestR
     */
    public int getCBestR() {
        return cBestR;
    }

    /**
     * @return the pl
     */
    public int getPl() {
        return pl;
    }

    /**
     * @return the globalOptimum
     */
    public double getGlobalOptimum() {
        return globalOptimum;
    }

    /**
     * @return the adhs
     */
    public AdaptiveDynamicHeuristicSetStrategy getAdhs() {
        return adhs;
    }

    public int getRandomHistorySolutionIndex() {
        int a = Utils.StaticRandom.nextInt(Communication.getCommunication().getSize());
        int b = Utils.StaticRandom.nextInt(HISTORY_LENGTH);
        return (HISTORY_LENGTH + 3) * a + b;
    }
}
