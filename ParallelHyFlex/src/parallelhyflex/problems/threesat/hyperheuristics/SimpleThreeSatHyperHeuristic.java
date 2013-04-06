package parallelhyflex.problems.threesat.hyperheuristics;

import java.io.IOException;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraintGenerator1;
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
        super(problem, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)),negoGenerator(), 1_000, new ThreeSatSolutionGenerator(null));
    }

    public SimpleThreeSatHyperHeuristic(ProblemReader<ThreeSatSolution, ThreeSatProblem> problemReader, long intervalTicks) throws ProtocolException, IOException {
        super(problemReader, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)),negoGenerator(), 1_000, new ThreeSatSolutionGenerator(null));
    }
    
    private static Generator<ThreeSatProblem,TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1,ThreeSatWritableEnforceableConstraintGenerator1>> negoGenerator () {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1,ThreeSatWritableEnforceableConstraintGenerator1>(new ThreeSatWritableEnforceableConstraintGenerator1(null)));
    }

    @Override
    protected void execute() {
        int nh = this.getNumberOfHeuristics();
        while (this.hasTimeLeft()) {
            //Communication.Log("New iteration!");
            int heus = Utils.StaticRandom.nextInt(nh);
            this.applyHeuristic(heus, 0, 0);
        }
    }
}
