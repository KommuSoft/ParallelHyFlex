package parallelhyflex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import mpi.MPI;
import parallelhyflex.communication.Communication;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.problemdependent.Problem;
import parallelhyflex.problemdependent.ProblemReader;

/**
 *
 * @author kommusoft
 */
public class HyperHeuristic<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> {
    
    private final ProxyMemory<TSolution> proxyMemory;
    private final TProblem problem;
    
    /**
     * @note: This constructor can only be initialized if the machine is the root (has rank = 0), otherwise,
     * one needs to construct this class with the constructor with the ProblemReader
     * @param problem 
     * @throws ProtocolException If this constructor is initialized by a machine with a rank different from zero!
     */
    public HyperHeuristic (TProblem problem) throws ProtocolException, IOException {
        if(Communication.getCommunication().getRank() == 0) {
            this.problem = problem;
            this.proxyMemory = new ProxyMemory<>(10,MemoryExchangePolicy.StateAlwaysDistributed);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            problem.write(dos);
            dos.close();
            byte[][] data = new byte[][] {baos.toByteArray()};
            baos.close();
            Communication.BC(data,0,1,MPI.OBJECT,0);
            int nw = this.getWritableMemory();
            for(int i = 0; i < nw; i++) {
                this.initializeSolution(i);
            }
        }
        else {
            Communication.Log("FU");
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine must be zero!");
        }
    }
    public HyperHeuristic (ProblemReader<TSolution,TProblem> problemReader) throws ProtocolException, IOException {
        if(Communication.getCommunication().getRank() != 0) {
            this.proxyMemory = new ProxyMemory<>(10,MemoryExchangePolicy.StateAlwaysDistributed);
            byte[][] data = new byte[1][];
            Communication.BC(data,0,1,MPI.OBJECT,0);
            ByteArrayInputStream bais = new ByteArrayInputStream(data[0]);
            DataInputStream dis = new DataInputStream(bais);
            this.problem = problemReader.readAndGenerate(dis);
            dis.close();
            bais.close();
            int nw = this.getWritableMemory();
            for(int i = 0; i < nw; i++) {
                this.initializeSolution(i);
            }
        }
        else {
            Communication.Log("FU");
            throw new ProtocolException("Cannot construct the HyperHeuristic with this constructor: Rank of the machine cannot be equal to zero!");
        }
    }
    public void applyHeuristic (int heuristic, int from, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from, to);
    }
    public void applyHeuristic (int heuristic, int from1, int from2, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from1, from2, to);
    }
    public void initializeSolution (int index) {
        this.proxyMemory.setSolution(index,this.problem.getSolutionGenerator().generateSolution());
    }
    public double getObjectiveFunction (int objective, int index) {
        return this.problem.getObjectiveFunction(objective).evaluateSolution(this.proxyMemory.getSolution(index));
    }
    public int getReadableMemory () {
        return this.proxyMemory.getMemorySize();
    }
    public int getWritableMemory () {
        return this.proxyMemory.getLocalMemorySize();
    }
    public double getDistanceFunction (int distance, int index1, int index2) {
        return this.problem.getDistanceFunction(distance).evaluateDistance(this.proxyMemory.getSolution(index1),this.proxyMemory.getSolution(index2));
    }
    public boolean areEqual (int solution1, int solution2) {
        return this.proxyMemory.peekSolution(solution1).equalSolution(this.proxyMemory.peekSolution(solution2));
    }
    
}
