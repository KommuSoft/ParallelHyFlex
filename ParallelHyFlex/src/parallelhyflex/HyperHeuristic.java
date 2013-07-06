package parallelhyflex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpi.MPI;
import mpi.Status;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.abstraction.CommMode;
import parallelhyflex.communication.abstraction.RequestResult;
import parallelhyflex.communication.routing.PacketReceiver;
import parallelhyflex.communication.routing.PacketRouter;
import parallelhyflex.communication.routing.PacketRouterBase;
import parallelhyflex.logging.LoggingParameters;
import parallelhyflex.memory.MemoryExchangePolicy;
import parallelhyflex.memory.ProxyMemory;
import parallelhyflex.memory.stateexchange.ExchangeState;
import parallelhyflex.memory.stateexchange.StateExchanger;
import parallelhyflex.memory.stateexchange.StateExchangerBase;
import parallelhyflex.memory.stateexchange.StateExchangerProxy;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WritableExperience;
import parallelhyflex.problemdependent.heuristic.HeuristicType;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemInterface;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.searchspace.negotation.SearchSpaceNegotiator;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionReader;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public abstract class HyperHeuristic<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> implements ProblemInterface<TSolution>, PacketRouter, StateExchanger {

    private final ProxyMemory<TSolution> proxyMemory;
    private final WritableExperience<TSolution, TEC> experience;
    private final StateExchangerBase stateExchanger;
    private final Date startTime, stopTime;
    private final long durationTicks;
    private long negotiationTicks;
    private long stateExchangeTicks;
    private final TProblem problem;
    private final SearchSpaceNegotiator<TSolution, TEC> negotiator;
    private final PacketRouterBase prr = new PacketRouterBase();
    private final double[] bestObjectives;
    private final int[] bestObjectiveSolutionIndices;
    private final FetchControl fetchContol;

    /**
     * @note: This constructor can only be initialized if the machine is the
     * root (has rank = 0), otherwise, one needs to construct this class with
     * the constructor with the ProblemReader
     * @param problem
     * @throws ProtocolException If this constructor is initialized by a machine
     * with a rank different from zero!
     */
    public HyperHeuristic(TProblem problem, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        this(problem, durationTicks, experience, negotiator, negotiationTicks, stateExchangeTicks, solutionReader, 10, MemoryExchangePolicy.StateIthDistributed);
    }

    /**
     * @note: This constructor can only be initialized if the machine is the
     * root (has rank = 0), otherwise, one needs to construct this class with
     * the constructor with the ProblemReader
     * @param problem
     * @throws ProtocolException If this constructor is initialized by a machine
     * with a rank different from zero!
     */
    public HyperHeuristic(TProblem problem, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader, int localMemorySize, MemoryExchangePolicy localMemoryExchangePolicy) throws ProtocolException, IOException {
        if (Communication.getCommunication().getRank() == 0) {
            this.startTime = new Date();
            this.stopTime = new Date();
            this.durationTicks = durationTicks;
            this.stateExchangeTicks = stateExchangeTicks;
            this.negotiationTicks = negotiationTicks;
            this.fetchContol = new FetchControl();
            this.problem = problem;
            this.experience = experience.generate(problem);
            this.negotiator = negotiator.generate(this.problem);
            this.stateExchanger = new StateExchangerBase();
            this.proxyMemory = new ProxyMemory<>(localMemorySize, localMemoryExchangePolicy, solutionReader);
            this.proxyMemory.setObjectiveGenerator(this.problem.getObjectiveFunction());
            this.registerPacketReceiver(this.proxyMemory);
            this.proxyMemory.setWritableExperience(this.experience);
            byte[][] data;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                try (DataOutputStream dos = new DataOutputStream(baos)) {
                    problem.write(dos);
                }
                data = new byte[][]{baos.toByteArray()};
            }
            Communication.Bcast(CommMode.MpiBlocking, data, 0, 1, MPI.OBJECT, 0);
            int nw = this.getWritableMemory();
            int O = this.problem.getNumberOfObjectiveFunctions();
            this.bestObjectives = new double[O];
            this.bestObjectiveSolutionIndices = new int[O];
            init(O, nw);
        } else {
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine must be zero!");
        }
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
    public HyperHeuristic(ProblemReader<TSolution, TProblem> problemReader, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        this(problemReader, durationTicks, experience, negotiator, negotiationTicks, stateExchangeTicks, solutionReader, 10, MemoryExchangePolicy.StateIthDistributed);
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
    public HyperHeuristic(ProblemReader<TSolution, TProblem> problemReader, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, long stateExchangeTicks, SolutionReader<TSolution> solutionReader, int localMemorySize, MemoryExchangePolicy localMemoryExchangePolicy) throws ProtocolException, IOException {
        if (Communication.getCommunication().getRank() != 0) {
            this.startTime = new Date();
            this.stopTime = new Date();
            this.durationTicks = durationTicks;
            this.stateExchangeTicks = stateExchangeTicks;
            this.negotiationTicks = negotiationTicks;
            this.fetchContol = new FetchControl();
            this.proxyMemory = new ProxyMemory<>(localMemorySize, localMemoryExchangePolicy, solutionReader);
            this.registerPacketReceiver(this.proxyMemory);
            byte[][] data = new byte[1][];
            Communication.Bcast(CommMode.MpiBlocking, data, 0, 1, MPI.OBJECT, 0);
            try (ByteArrayInputStream bais = new ByteArrayInputStream(data[0]); DataInputStream dis = new DataInputStream(bais)) {
                this.problem = problemReader.readAndGenerate(dis);
            }
            this.proxyMemory.setObjectiveGenerator(this.problem.getObjectiveFunction());
            this.experience = experience.generate(this.problem);
            this.stateExchanger = new StateExchangerBase();
            this.negotiator = negotiator.generate(this.problem);
            this.proxyMemory.setWritableExperience(this.experience);
            int nw = this.getWritableMemory();
            int O = this.problem.getNumberOfObjectiveFunctions();
            this.bestObjectives = new double[O];
            this.bestObjectiveSolutionIndices = new int[O];
            init(O, nw);
        } else {
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine cannot be equal to zero!");
        }
    }

    @Override
    public void synchronizeState() throws IOException {
        stateExchanger.synchronizeState();
    }

    @Override
    public ExchangeState getLocalState() {
        return stateExchanger.getLocalState();
    }

    @Override
    public ExchangeState getState(int rank) {
        return stateExchanger.getState(rank);
    }

    @Override
    public ArrayIterator<ExchangeState> stateIterator() {
        return stateExchanger.stateIterator();
    }

    public void applyHeuristic(int heuristic, int from, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from, to);
        postHeuristicApplication(to);
    }

    public double getBestObjectiveSolution(int objective) {
        return this.bestObjectives[objective];
    }

    public int getBestObjectiveSolutionIndex(int objective) {
        return this.bestObjectiveSolutionIndices[objective];
    }

    public double getBestObjectiveSolution() {
        return this.getBestObjectiveSolution(0);
    }

    public int getBestObjectiveSolutionIndex() {
        return this.getBestObjectiveSolutionIndex(0);
    }

    public void applyHeuristic(int heuristic, int from1, int from2, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from1, from2, to);
        postHeuristicApplication(to);
    }

    public final void initializeSolution(int index) {
        this.proxyMemory.setSolution(index, this.problem.getSolutionGenerator().generateSolution());
        updateBestObjectives(index);
    }

    public double getObjectiveFunction(int objective, int solutionIndex) {
        TSolution tsol = this.proxyMemory.getSolution(solutionIndex);
        if (tsol != null) {
            return this.problem.getObjectiveFunction(objective).evaluateSolution(tsol);
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public double getObjectiveFunction(int solutionIndex) {
        return this.getObjectiveFunction(0, solutionIndex);
    }

    public int getReadableMemory() {
        return this.proxyMemory.getMemorySize();
    }

    public final int getWritableMemory() {
        return this.proxyMemory.getLocalMemorySize();
    }

    public double getDistanceFunction(int distance, int index1, int index2) {
        return this.problem.getDistanceFunction(distance).evaluateDistance(this.proxyMemory.getSolution(index1), this.proxyMemory.getSolution(index2));
    }

    public boolean areEqual(int solution1, int solution2) {
        return this.proxyMemory.peekSolution(solution1).equalSolution(this.proxyMemory.peekSolution(solution2));
    }

    public final void startExecute() {
        Date date = new Date();
        long time = date.getTime();
        this.startTime.setTime(time);
        this.stopTime.setTime(time + durationTicks);
        fetchContol.init();
        this.execute();
    }

    public boolean hasTimeLeft() {
        return this.stopTime.after(new Date());
    }

    public long getRemaingTime() {
        return this.stopTime.getTime() - new Date().getTime();
    }

    public long getTotalTime() {
        return this.durationTicks;
    }

    public long getElapsedTime() {
        return new Date().getTime() - this.startTime.getTime();
    }

    protected abstract void execute();

    @Override
    public double getDepthOfSearch() {
        return this.problem.getDepthOfSearch();
    }

    @Override
    public double getIntensityOfMutation() {
        return this.problem.getIntensityOfMutation();
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        return this.problem.getNumberOfDistanceFunctions();
    }

    @Override
    public int getNumberOfHeuristics() {
        return this.problem.getNumberOfHeuristics();
    }

    @Override
    public int getNumberOfObjectiveFunctions() {
        return this.problem.getNumberOfObjectiveFunctions();
    }

    @Override
    public void setDepthOfSearch(double dos) {
        this.problem.setDepthOfSearch(dos);
    }

    @Override
    public void setIntensityOfMutation(double iom) {
        this.problem.setIntensityOfMutation(iom);
    }

    @Override
    public int getNumberOfLocalSearchHeuristics() {
        return this.problem.getNumberOfLocalSearchHeuristics();
    }

    @Override
    public int getNumberOfMutationHeuristics() {
        return this.problem.getNumberOfMutationHeuristics();
    }

    @Override
    public int getNumberOfCrossoverHeuristics() {
        return this.problem.getNumberOfCrossoverHeuristics();
    }

    @Override
    public int getNumberOfRuinRecreateHeuristics() {
        return this.problem.getNumberOfRuinRecreateHeuristics();
    }

    @Override
    public HeuristicType getHeuristicType(int heuristic) {
        return this.problem.getHeuristicType(heuristic);
    }

    @Override
    public int getNumberOfHeuristicsOfType(HeuristicType type) {
        return this.problem.getNumberOfHeuristicsOfType(type);
    }

    /**
     * @return the negotiationTicks
     */
    public long getNegotiationTicks() {
        return negotiationTicks;
    }

    /**
     * @param negotiationTicks the negotiationTicks to set
     */
    public final void setNegotiationTicks(long negotiationTicks) {
        this.negotiationTicks = negotiationTicks;
    }

    @Override
    public final void registerPacketReceiver(PacketReceiver receiver) {
        this.prr.registerPacketReceiver(receiver);
    }

    @Override
    public final void unregisterPacketReceiver(PacketReceiver receiver) {
        this.prr.unregisterPacketReceiver(receiver);
    }

    @Override
    public void routePacket(int sender, int tag, Object data) {
        this.prr.routePacket(sender, tag, data);
    }

    @Override
    public int[] getPacketTags() {
        return this.prr.getPacketTags();
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        this.prr.receivePacket(from, tag, data);
    }

    public CompactBitArray getExchangeBlockingMask() {
        return this.proxyMemory.getExchangeBlockingMask();
    }

    private void updateBestObjectives(int to) {
        for (int o = this.problem.getNumberOfObjectiveFunctions() - 1; o >= 0; o--) {
            double eval = this.getObjectiveFunction(o, to);
            if (eval < this.bestObjectives[o]) {
                this.bestObjectives[o] = eval;
                this.bestObjectiveSolutionIndices[o] = to;
            }
        }
    }

    public void copySolution(int from, int to) {
        this.proxyMemory.copySolution(from, to);
    }

    private void init(int O, int nw) {
        for (int i = 0; i < O; i++) {
            this.bestObjectives[i] = Double.POSITIVE_INFINITY;
            this.bestObjectiveSolutionIndices[i] = 0;
        }
        for (int i = 0; i < nw; i++) {
            this.initializeSolution(i);
        }
        this.proxyMemory.initializeProxyMemory(this.problem.getSolutionGenerator());
        this.prr.registerPacketReceiver(this.negotiator);
    }

    private void postHeuristicApplication(int to) {
        updateBestObjectives(to);
        this.fetchContol.recheck();
    }

    @Override
    public <T extends Serializable> StateExchangerProxy<T> generateProxy(int index) {
        return this.stateExchanger.generateProxy(index);
    }

    /**
     * @return the stateExchangeTicks
     */
    public long getStateExchangeTicks() {
        return stateExchangeTicks;
    }

    /**
     * @param stateExchangeTicks the stateExchangeTicks to set
     */
    public void setStateExchangeTicks(long stateExchangeTicks) {
        this.stateExchangeTicks = stateExchangeTicks;
    }

    private class FetchControl {

        private final Object[] buffer = new Object[1];
        private RequestResult req;
        private long lastNegotiation = 0;
        private long lastStateExchange = 0;

        public void recheck() {
            Status stat = req.Test();
            if (stat != null) {
                prr.routePacket(stat.source, stat.tag, buffer[0x00]);
                reinit();
                if (negotiator.isReady()) {
                    SearchSpace ss = negotiator.getSearchSpace();
                    if (ss != null) {
                        HyperHeuristic.this.proxyMemory.setSearchSpace(ss);
                    }
                    Communication.logFileTime(LoggingParameters.LOG_NEGOTI_STOP, LoggingParameters.LOG_NEGOTI_STOP_TEXT);
                    Communication.logFileTime(LoggingParameters.LOG_SEASPA_SETV, LoggingParameters.LOG_SEASPA_SETV_TEXT, ss);
                }
            }
            long now = new Date().getTime();
            if ((now - lastNegotiation >= HyperHeuristic.this.negotiationTicks) && !negotiator.isActive()) {
                this.lastNegotiation = now;
                Communication.logFileTime(LoggingParameters.LOG_NEGOTI_START, LoggingParameters.LOG_NEGOTI_START_TEXT);
                negotiator.sendEnforceableConstraints(experience.generateEnforceableConstraints());
            }
            if ((now - lastStateExchange >= HyperHeuristic.this.stateExchangeTicks) && !stateExchanger.isActive()) {
                this.lastStateExchange = now;
                Communication.logFileTime(LoggingParameters.LOG_STTEXC, LoggingParameters.LOG_STTEXC_TEXT);
                try {
                    stateExchanger.synchronizeState();
                } catch (IOException ex) {
                    Logger.getLogger(HyperHeuristic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void init() {
            this.lastNegotiation = new Date().getTime();
            reinit();
        }

        private void reinit() {
            req = Communication.Recv(CommMode.MpiNonBlocking, buffer, 0, 1, MPI.OBJECT, MPI.ANY_SOURCE, MPI.ANY_TAG);
        }
    }
}
