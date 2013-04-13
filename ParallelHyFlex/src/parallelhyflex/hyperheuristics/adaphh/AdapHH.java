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
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class AdapHH<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends HyperHeuristic<TSolution,TProblem,TEC> {
    
    public static final int PH_FACTOR = 500;
    public static final int PH_REQUESTED = 100;
    public static final int LIST_SIZE = 10;
    public static final double GAMMA_MIN = 0.02d;
    public static final double GAMMA_MAX = 50.0d;
    
    private final AdaptiveDynamicHeuristicSetStrategy adhs;
    private final AdapHHHeuristicRecord[] records;
    private final ProbabilityVectorBase heuristicSelector;
    
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
        super(problem,durationTicks,experience,negotiator,negotiationTicks,solutionReader);
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
        super(problemReader,durationTicks,experience,negotiator,negotiationTicks,solutionReader);
        this.heuristicSelector = new ProbabilityVectorBase(this.getNumberOfHeuristics());
        this.adhs = new AdaptiveDynamicHeuristicSetStrategy(new AdapHHHeuristicRecordEvaluator(this));
        this.records = new AdapHHHeuristicRecord[this.getNumberOfHeuristics()];
        this.init();
    }
    
    @Override
    protected void execute() {
        while(this.hasTimeLeft()) {
            this.periodGlobalImprovement = false;
            this.pl = PH_FACTOR*((int) Math.sqrt(2*this.adhs.size()));
            this.cPhase = 0;
            for(int i = this.pl; i > 0; i--) {
                iteration();
                this.cPhase++;
            }
            endPhase();
        }
    }
    
    @Override
    public void applyHeuristic (int heuristic, int from, int to) {
        long oldticks = new Date().getTime();
        double oldeval = this.getObjectiveFunction(0,from);
        super.applyHeuristic(heuristic, from, to);
        long dt = new Date().getTime()-oldticks;
        double neweval = this.getObjectiveFunction(0, to);
        records[heuristic].processed(dt,neweval-oldeval);
        this.checkImprovement(heuristic,neweval);
    }
    
    @Override
    public void applyHeuristic (int heuristic, int from1, int from2, int to) {
        long oldticks = new Date().getTime();
        double oldeval1 = this.getObjectiveFunction(0,from1);
        double oldeval2 = this.getObjectiveFunction(0,from2);
        super.applyHeuristic(heuristic, from1, from2, to);
        double neweval = this.getObjectiveFunction(0, to);
        long dt = new Date().getTime()-oldticks;
        records[heuristic].processed(dt,neweval-0.5d*(oldeval1+oldeval2));
        this.checkImprovement(heuristic,neweval);
    }
    
    private void iteration () {
        
    }
    
    private void relayHybridisation () {
        double gamma = Utils.border(GAMMA_MIN,(this.cBestS+1.0d)/(this.cBestR+1.0d),GAMMA_MAX);
        if(Utils.StaticRandom.nextDouble() < Math.pow((double) this.cPhase/this.pl, gamma)) {
            //TODO: do relayHybridisation
        }
    }
    
    private void aILLAMoveAcceptance () {
        
    }
    
    private void endPhase () {
        this.adhs.newPhase();
    }

    /**
     * @return the periodGlobalImprovement
     */
    public boolean getPeriodGlobalImprovement() {
        return periodGlobalImprovement;
    }

    private void init() {
        for(int i = 0; i < this.records.length; i++) {
            this.records[i] = new AdapHHHeuristicRecord(i);
            this.adhs.add(this.records[i]);
        }
        double globmin = Double.POSITIVE_INFINITY;
        for(int i = 0; i < this.getReadableMemory(); i++) {
            globmin = Math.min(globmin,this.getObjectiveFunction(0, i));
        }
        this.globalOptimum = globmin;
    }

    private void checkImprovement(int heuristic, double neweval) {
        if(neweval < this.globalOptimum) {
            this.globalOptimum = neweval;
            this.records[heuristic].newBest();
        }
    }
    
}
