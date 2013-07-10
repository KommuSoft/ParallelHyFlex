package parallelhyflex.problems.threesat.hyperheuristics;

import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.ProtocolException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraint1;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraintGenerator1;
import parallelhyflex.problems.threesat.experience.ThreeSatExperience;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class SimpleThreeSatHyperHeuristic extends HyperHeuristic<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1> {

    private static Generator<ThreeSatProblem, TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1, ThreeSatWriteableEnforceableConstraintGenerator1>> negoGenerator() {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1, ThreeSatWriteableEnforceableConstraintGenerator1>(new ThreeSatWriteableEnforceableConstraintGenerator1(null)));
    }

    /**
     *
     * @param problem
     * @param intervalTicks
     * @throws ProtocolException
     * @throws IOException
     */
    public SimpleThreeSatHyperHeuristic(ThreeSatProblem problem, long intervalTicks) throws ProtocolException, IOException {
        super(problem, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)), negoGenerator(), 1_000, 1_000, new ThreeSatSolutionGenerator(null));
    }

    /**
     *
     * @param problemReader
     * @param intervalTicks
     * @throws ProtocolException
     * @throws IOException
     */
    public SimpleThreeSatHyperHeuristic(ProblemReader<ThreeSatSolution, ThreeSatProblem> problemReader, long intervalTicks) throws ProtocolException, IOException {
        super(problemReader, intervalTicks, new CloningGenerator<>(new ThreeSatExperience(null)), negoGenerator(), 1_000, 1_000, new ThreeSatSolutionGenerator(null));
    }

    /**
     *
     */
    @Override
    protected void execute() {
        int nh = this.getNumberOfHeuristics();
        while (this.hasTimeLeft()) {
            //Communication.Log("New iteration!");
            int heus = Utils.StaticRandom.nextInt(nh);
            this.applyHeuristic(heus, 0, 0);
        }
    }
    private static final Logger LOG = Logger.getLogger(SimpleThreeSatHyperHeuristic.class.getName());
}
