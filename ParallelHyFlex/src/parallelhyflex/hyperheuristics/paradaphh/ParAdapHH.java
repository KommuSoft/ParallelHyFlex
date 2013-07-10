package parallelhyflex.hyperheuristics.paradaphh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.improvement.ComposedFixedImprovementList;
import parallelhyflex.algebra.improvement.SingleFixedImprovementList;
import parallelhyflex.algebra.probability.ComposedNormalizedProbabilityVector;
import parallelhyflex.algebra.probability.SingleNormalizedProbabilityVector;
import parallelhyflex.communication.Communication;
import parallelhyflex.hyperheuristics.learning.LearningAutomaton;
import parallelhyflex.hyperheuristics.paradaphh.records.AdaptiveDynamicHeuristicSetStrategy;
import parallelhyflex.hyperheuristics.paradaphh.records.ParAdapHHHeuristicRecord;
import parallelhyflex.hyperheuristics.paradaphh.records.ParAdapHHHeuristicRecordEvaluator;
import parallelhyflex.hyperheuristics.paradaphh.records.ParAdapHHHybridRelaxationHeuristicRecord;
import parallelhyflex.hyperheuristics.records.ProbabilityVectorBase;
import parallelhyflex.memory.MemoryExchangePolicy;
import parallelhyflex.memory.stateexchange.AllStateExchangerProxy;
import parallelhyflex.memory.stateexchange.ExchangeState;
import parallelhyflex.memory.stateexchange.ForeignStateExchangerProxy;
import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WriteableExperience;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.SearchSpaceNegotiator;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionReader;
import parallelhyflex.utils.Utils;
import parallelhyflex.utils.comparator.DoubleComparator;

/**
 *
 * @author kommusoft
 */
public class ParAdapHH<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WriteableEnforceableConstraint<TSolution>> extends HyperHeuristic<TSolution, TProblem, TEC> {

    /**
     *
     */
    public static final double ADHS_W1 = 16.0d;
    /**
     *
     */
    public static final double ADHS_W2 = 8.0d;
    /**
     *
     */
    public static final double ADHS_W3 = 4.0d;
    /**
     *
     */
    public static final double ADHS_W4 = 2.0d;
    /**
     *
     */
    public static final double ADHS_W5 = 1.0d;
    /**
     *
     */
    public static double ADHS_W6 = 0.5d;
    /**
     *
     */
    public static double ADHS_W7 = 0.25d;
    /**
     *
     */
    public static final int PH_FACTOR = 500;
    /**
     *
     */
    public static final int PH_REQUESTED = 100;
    /**
     *
     */
    public static final int LIST_SIZE = 10;
    /**
     *
     */
    public static final int S = 0;//S location
    /**
     *
     */
    public static final int Sa = 1;//S' location
    /**
     *
     */
    public static final int Saa = 2;//S'' location
    /**
     *
     */
    public static final int Sb = 3;//best solution location
    /**
     *
     */
    public static final int HISTORY_LENGTH = 5;
    /**
     *
     */
    public static final int LOCAL_MEMORY_SIZE = Sb + 1 + HISTORY_LENGTH;//S, S', S'', Sb and HISTORY
    /**
     *
     */
    public static final int NON_EXCHANGE_MEMORY_FROM = 0;
    /**
     *
     */
    public static final int NON_EXCHANGE_MEMORY_TO = 2;
    /**
     *
     */
    public static final int AILLA_K = 20;
    /**
     *
     */
    public static final int AILLA_LBASE = 5;
    /**
     *
     */
    public static final int AILLA_LINITIAL = 10;
    /**
     *
     */
    public static final double GAMMA_MIN = 0.02d;
    /**
     *
     */
    public static final double GAMMA_MAX = 50.0d;
    /**
     *
     */
    public static final double LIST_PROBABILITY = 0.25d;
    /**
     *
     */
    public static double LEARNING_AUTOMATON_LOCAL_INFLUENCE = 0.25d;
    /**
     *
     */
    public static final double THETA1 = 0.01d;
    /**
     *
     */
    public static final double THETA2 = 0.001d;
    /**
     *
     */
    public static final double THETA3 = 0.0005d;
    /**
     *
     */
    public static final double THETA4 = 0.0001d;
    /**
     *
     */
    public static final double P_BEST_IOE = 0.5d;
    /**
     *
     */
    public static final double P_BEST_IM1 = 0.25d;
    /**
     *
     */
    public static final double P_BEST_IM2 = 0.5d;
    /**
     *
     */
    public static final double P_BEST_WM = 0.5d;
    /**
     *
     */
    public static final double P_IMPR_IOE = 0.5d;
    /**
     *
     */
    public static final double P_IMPR_IM1 = 0.25d;
    /**
     *
     */
    public static final double P_IMPR_IM2 = 0.5d;
    /**
     *
     */
    public static final double P_IMPR_WM = 0.5d;
    /**
     *
     */
    public static final double P_WORS_IM = 0.5d;
    /**
     *
     */
    public static final double P_EQUA_IOE1 = 0.25d;
    /**
     *
     */
    public static final double P_EQUA_IOE2 = 0.5d;
    /**
     *
     */
    public static final double P_EQUA_IM = 0.5d;
    /**
     *
     */
    public static int AILLA_LENGTH = 10;
    /**
     *
     */
    public static int AILLA_LOCAL_LENGTH = 5;
    /**
     *
     */
    public static final double LEARNING_AUTOMATON_ADAPTION = 0.05d;
    private static final Logger LOG = Logger.getLogger(ParAdapHH.class.getName());

    public static void setHeuristicRecordInfluence(double localInfluence) {
        double factor = ADHS_W7 / ADHS_W6;
        ADHS_W6 = localInfluence * ADHS_W5;
        ADHS_W7 = ADHS_W6 * factor;
    }
    private final AdaptiveDynamicHeuristicSetStrategy adhs;
    private final ParAdapHHHeuristicRecord[] records;
    private final ProbabilityVectorBase heuristicSelector;
    private final ComposedNormalizedProbabilityVector cpv = new ComposedNormalizedProbabilityVector();
    private final LearningAutomaton<ParAdapHHHybridRelaxationHeuristicRecord> learningAutomaton;
    private final AdaptiveIterationLimitedListbasedThresholdMoveAcceptor ailla;
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
    public ParAdapHH(TProblem problem, long durationTicks, Generator<TProblem, ? extends WriteableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        super(problem, durationTicks, experience, negotiator, negotiationTicks, stateExchangeTicks, solutionReader, LOCAL_MEMORY_SIZE, MemoryExchangePolicy.QueuedProbableDistributed);
        this.learningAutomaton = new LearningAutomaton<>(cpv, LEARNING_AUTOMATON_LOCAL_INFLUENCE);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new ParAdapHHHeuristicRecordEvaluator(this));
        this.records = new ParAdapHHHeuristicRecord[this.getNumberOfHeuristics()];
        this.ailla = new AdaptiveIterationLimitedListbasedThresholdMoveAcceptor(this);
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
    public ParAdapHH(ProblemReader<TSolution, TProblem> problemReader, long durationTicks, Generator<TProblem, ? extends WriteableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        super(problemReader, durationTicks, experience, negotiator, negotiationTicks, stateExchangeTicks, solutionReader, LOCAL_MEMORY_SIZE, MemoryExchangePolicy.QueuedProbableDistributed);
        this.learningAutomaton = new LearningAutomaton<>(cpv, LEARNING_AUTOMATON_LOCAL_INFLUENCE);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new ParAdapHHHeuristicRecordEvaluator(this));
        this.records = new ParAdapHHHeuristicRecord[this.getNumberOfHeuristics()];
        this.ailla = new AdaptiveIterationLimitedListbasedThresholdMoveAcceptor(this);
        this.init();
    }

    /**
     *
     */
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
        ParAdapHHHeuristicRecord adhr = this.getAdhs().getRandomIndividual();
        adhr.execute();
        this.ailla.acceptMove(Sa, S, Sb);
        double gamma = Utils.border(GAMMA_MIN, (this.getCBestS() + 1.0d) / (this.getCBestR() + 1.0d), GAMMA_MAX);
        if (Utils.StaticRandom.nextDouble() < Math.pow((double) this.getCPhase() / this.getPl(), gamma)) {
            ParAdapHHHybridRelaxationHeuristicRecord ahrh = this.learningAutomaton.getAction();
            ahrh.execute();
            if (this.ailla.acceptMove(Saa, S, Sb)) {
                this.learningAutomaton.update(ahrh, LEARNING_AUTOMATON_ADAPTION);
            }
        }
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
        ExchangeState es = this.getLocalState();
        initRecords(es);
        initGlobalCounters();
        initLearningAutomaton(es);
        initAilla(es);
    }

    /**
     *
     * @param neweval
     * @return
     */
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
    public LearningAutomaton<ParAdapHHHybridRelaxationHeuristicRecord> getLearningAutomaton() {
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

    /**
     *
     * @return
     */
    public int getRandomHistorySolutionIndex() {
        int a = Utils.StaticRandom.nextInt(Communication.getCommunication().getSize());
        int b = Utils.StaticRandom.nextInt(HISTORY_LENGTH);
        return LOCAL_MEMORY_SIZE * a + LOCAL_MEMORY_SIZE - b - 1;
    }

    private void initRecords(ExchangeState es) {
        int durationoffset = (int) Math.round(Math.sqrt(2.0d * this.records.length));
        for (int i = 0; i < this.records.length; i++) {
            ParAdapHHHeuristicRecord hr = new ParAdapHHHeuristicRecord(this, i, durationoffset);
            hr.setAllProxy(this.turnAllProxy(hr.getExchangeRecord()));
            this.records[i] = hr;
            this.getAdhs().add(this.records[i]);
        }
    }

    private void initGlobalCounters() {
        double globmin = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.getReadableMemory(); i++) {
            globmin = Math.min(globmin, this.getObjectiveFunction(0, i));
        }
        this.globalOptimum = globmin;
    }

    private void initLearningAutomaton(ExchangeState es) {
        AllStateExchangerProxy<SingleNormalizedProbabilityVector> proxy = this.generateAllProxy(es.size());
        this.cpv.setOthers(proxy);
        es.add(this.cpv.getLocalData());
        ArrayList<ParAdapHHHybridRelaxationHeuristicRecord> all = new ArrayList<>();
        for (int i = 0; i < this.records.length; i++) {
            all.add(new ParAdapHHHybridRelaxationHeuristicRecord(this, i));
        }
        this.getLearningAutomaton().reset(all);
    }

    private void initAilla(ExchangeState es) {
        SingleFixedImprovementList<Double> local = new SingleFixedImprovementList<>(AILLA_LENGTH, Double.POSITIVE_INFINITY);
        ForeignStateExchangerProxy<SingleFixedImprovementList<Double>> proxy = this.turnForeignProxy(local);
        ComposedFixedImprovementList<Double> cfil = new ComposedFixedImprovementList<>(DoubleComparator.getInstance(), local, AILLA_LOCAL_LENGTH, proxy);
        this.ailla.setBestList(cfil);
    }
}
