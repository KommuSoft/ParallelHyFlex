package parallelhyflex.problems.threesat.hyperheuristics;

import java.io.IOException;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraintGenerator;
import parallelhyflex.problems.threesat.experience.ThreeSatExperience;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class SimpleThreeSatHyperHeuristic extends HyperHeuristic<ThreeSatSolution, ThreeSatProblem, ThreeSatWritableEnforceableConstraint1> {

    public SimpleThreeSatHyperHeuristic(ThreeSatProblem problem, long intervalTicks) throws ProtocolException, IOException {
        super(problem, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)),negoGenerator(),1000, new ThreeSatSolutionGenerator(null));
    }

    public SimpleThreeSatHyperHeuristic(ProblemReader<ThreeSatSolution, ThreeSatProblem> problemReader, long intervalTicks) throws ProtocolException, IOException {
        super(problemReader, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)),negoGenerator(),1000, new ThreeSatSolutionGenerator(null));
    }
    
    private static Generator<ThreeSatProblem,TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution,ThreeSatProblem,WritableEnforceableConstraint<ThreeSatSolution>,ThreeSatWritableEnforceableConstraintGenerator>> negoGenerator () {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator(new ThreeSatWritableEnforceableConstraintGenerator(null)));
    }

    @Override
    public void execute() {
        int nh = this.getNumberOfHeuristics();
        while (this.hasTimeLeft()) {
            int heus = Utils.StaticRandom.nextInt(nh);
            this.applyHeuristic(heus, 0, 0);
        }
    }
}
