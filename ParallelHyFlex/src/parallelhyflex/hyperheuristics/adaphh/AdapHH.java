/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.adaphh;

import java.io.IOException;
import java.util.Date;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.Generator;
import parallelhyflex.hyperheuristics.adaphh.records.AdapHHHeuristicRecord;
import parallelhyflex.hyperheuristics.adaphh.records.AdapHHHeuristicRecordEvaluator;
import parallelhyflex.hyperheuristics.adaphh.records.AdaptiveDynamicHeuristicSetStrategy;
import parallelhyflex.hyperheuristics.records.ProbabilityVectorBase;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WritableExperience;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.SearchSpaceNegotiator;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionReader;

/**
 *
 * @author kommusoft
 */
public class AdapHH<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends HyperHeuristic<TSolution,TProblem,TEC> {
    
    public static final int PH_FACTOR = 500;
    public static final int PH_REQUESTED = 100;
    
    private final AdaptiveDynamicHeuristicSetStrategy adhs;
    private final ProbabilityVectorBase heuristicSelector;
    private boolean periodGlobalImprovement = false;
    

    /**
     * @note: This constructor can only be initialized if the machine is the
     * root (has rank = 0), otherwise, one needs to construct this class with
     * the constructor with the ProblemReader
     * @param problem
     * @throws ProtocolException If this constructor is initialized by a machine
     * with a rank different from zero!
     */
    public AdapHH(TProblem problem, long durationTicks, Generator<TProblem, ? extends WritableExperience<TSolution, TEC>> experience, Generator<TProblem, ? extends SearchSpaceNegotiator<TSolution, TEC>> negotiator, long negotiationTicks, SolutionReader<TSolution> solutionReader) throws ProtocolException, IOException {
        super(problem,durationTicks,experience,negotiator,negotiationTicks,solutionReader);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new AdapHHHeuristicRecordEvaluator(this));
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
        super(problemReader,durationTicks,experience,negotiator,negotiationTicks,solutionReader);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new AdapHHHeuristicRecordEvaluator(this));
    }
    
    @Override
    protected void execute() {
        while(this.hasTimeLeft()) {
            this.periodGlobalImprovement = false;
            for(int i = PH_FACTOR*((int) Math.sqrt(2*this.adhs.size())); i > 0; i--) {
                iteration();
            }
            endPhase();
        }
    }
    
    private void executeHeuristic (AdapHHHeuristicRecord record) {
        long oldticks = new Date().getTime();
        //TODO: execute heuristic
        long dt = new Date().getTime()-oldticks;
        record.processed(dt);
        //TODO: set global improvement
    }
    
    private void iteration () {
        
    }
    
    private void endPhase () {
        
    }

    /**
     * @return the periodGlobalImprovement
     */
    public boolean getPeriodGlobalImprovement() {
        return periodGlobalImprovement;
    }
    
}
