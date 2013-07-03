package parallelhyflex.problems.threesat;

import parallelhyflex.TestParameters;
import parallelhyflex.problems.ProblemTestBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatTestBase extends ProblemTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> {

    @Override
    protected void renewProblem() {
        setTsp(getTspg().generateProblem());
    }

    @Override
    protected void renewProblemGenerator() {
        setTspg(new ThreeSatProblemGenerator(TestParameters.NUMBER_OF_VARIABLES, TestParameters.NUMBER_OF_CLAUSES));
    }

    @Override
    protected void renewSolution() {
        setTss(getTsg().generateSolution());
    }

    @Override
    protected void renewSolutionGenerator() {
        setTsg(getTsp().getSolutionGenerator());
    }
}
