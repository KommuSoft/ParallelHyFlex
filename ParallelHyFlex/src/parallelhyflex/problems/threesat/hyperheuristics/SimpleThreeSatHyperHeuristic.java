package parallelhyflex.problems.threesat.hyperheuristics;

import java.io.IOException;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.experience.ThreeSatExperience;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class SimpleThreeSatHyperHeuristic extends HyperHeuristic<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1> {
    
    public SimpleThreeSatHyperHeuristic(ThreeSatProblem problem, long intervalTicks) throws ProtocolException, IOException {
        super(problem,intervalTicks,new CloningGenerator<>(new ThreeSatExperience(null)),new ThreeSatSolutionGenerator(null));
    }

    public SimpleThreeSatHyperHeuristic(ProblemReader<ThreeSatSolution,ThreeSatProblem> problemReader, long intervalTicks) throws ProtocolException, IOException {
        super(problemReader,intervalTicks,new CloningGenerator<>(new ThreeSatExperience(null)),new ThreeSatSolutionGenerator(null));
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
