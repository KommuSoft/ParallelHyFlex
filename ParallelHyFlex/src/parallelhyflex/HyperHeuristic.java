package parallelhyflex;

import parallelhyflex.memory.ProxyMemory;
import parallelhyflex.memory.MemoryExchangePolicy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import mpi.MPI;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.SolutionReader;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WritableExperience;
import parallelhyflex.problemdependent.heuristics.HeuristicType;

/**
 *
 * @author kommusoft
 */
public abstract class HyperHeuristic<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> implements ProblemInterface<TSolution> {

    private final ProxyMemory<TSolution> proxyMemory;
    private final WritableExperience<TSolution, TEC> experience;
    private final Date startTime, stopTime;
    private final long intervalTicks;
    private final TProblem problem;

    /**
     * @note: This constructor can o, generator, tssgnly be initialized if the
     * machine is the root (has rank = 0), otherwise, one needs to construct
     * this class with the constructor with the ProblemReader
     * @param problem
     * @throws ProtocolException If this constructor is initialized by a machine
     * with a rank different from zero!
     */
    public HyperHeuristic(TProblem problem, long intervalTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        if (Communication.getCommunication().getRank() == 0) {
            this.startTime = new Date();
            this.stopTime = new Date();
            this.intervalTicks = intervalTicks;
            this.problem = problem;
            this.experience = experience.generate(problem);
            this.proxyMemory = new ProxyMemory<>(10, MemoryExchangePolicy.StateAlwaysDistributed, solutionReader);
            this.proxyMemory.setWritableExperience(this.experience);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            problem.write(dos);
            dos.close();
            byte[][] data = new byte[][]{baos.toByteArray()};
            baos.close();
            Communication.BC(data, 0, 1, MPI.OBJECT, 0);
            int nw = this.getWritableMemory();
            for (int i = 0; i < nw; i++) {
                this.initializeSolution(i);
            }
            //Communication.Log(this.problem.toString());
            this.startExecute();
        } else {
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine must be zero!");
        }
    }

    public HyperHeuristic(ProblemReader<TSolution, TProblem> problemReader, long intervalTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        if (Communication.getCommunication().getRank() != 0) {
            this.startTime = new Date();
            this.stopTime = new Date();
            this.intervalTicks = intervalTicks;
            this.proxyMemory = new ProxyMemory<>(10, MemoryExchangePolicy.StateAlwaysBroadcasted, solutionReader);
            byte[][] data = new byte[1][];
            Communication.BC(data, 0, 1, MPI.OBJECT, 0);
            ByteArrayInputStream bais = new ByteArrayInputStream(data[0]);
            DataInputStream dis = new DataInputStream(bais);
            this.problem = problemReader.readAndGenerate(dis);
            dis.close();
            bais.close();
            this.experience = experience.generate(problem);
            this.proxyMemory.setWritableExperience(this.experience);
            int nw = this.getWritableMemory();
            for (int i = 0; i < nw; i++) {
                this.initializeSolution(i);
            }
            //Communication.Log(this.problem.toString());
            this.startExecute();
        } else {
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine cannot be equal to zero!");
        }
    }

    public void applyHeuristic(int heuristic, int from, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from, to);
    }

    public void applyHeuristic(int heuristic, int from1, int from2, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from1, from2, to);
    }

    public void initializeSolution(int index) {
        this.proxyMemory.setSolution(index, this.problem.getSolutionGenerator().generateSolution());
    }

    public double getObjectiveFunction(int objective, int index) {
        return this.problem.getObjectiveFunction(objective).evaluateSolution(this.proxyMemory.getSolution(index));
    }

    public int getReadableMemory() {
        return this.proxyMemory.getMemorySize();
    }

    public int getWritableMemory() {
        return this.proxyMemory.getLocalMemorySize();
    }

    public double getDistanceFunction(int distance, int index1, int index2) {
        return this.problem.getDistanceFunction(distance).evaluateDistance(this.proxyMemory.getSolution(index1), this.proxyMemory.getSolution(index2));
    }

    public boolean areEqual(int solution1, int solution2) {
        return this.proxyMemory.peekSolution(solution1).equalSolution(this.proxyMemory.peekSolution(solution2));
    }

    private void startExecute() {
        Date date = new Date();
        long time = date.getTime();
        this.startTime.setTime(time);
        this.stopTime.setTime(time + intervalTicks);
        this.execute();
    }

    public boolean hasTimeLeft() {
        return this.stopTime.after(new Date());
    }

    public abstract void execute();

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
        this.setDepthOfSearch(dos);
    }

    @Override
    public void setIntensityOfMutation(double iom) {
        this.setIntensityOfMutation(iom);
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
}
