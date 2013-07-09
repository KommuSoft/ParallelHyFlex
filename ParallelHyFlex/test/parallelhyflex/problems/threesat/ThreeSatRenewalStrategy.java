package parallelhyflex.problems.threesat;

import parallelhyflex.TestParameters;
import parallelhyflex.problems.ProblemTestBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatRenewalStrategy implements TestRenewalStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> {

    /**
     *
     * @param testbase
     */
    @Override
    public void renewProblem(ProblemTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> testbase) {
        testbase.setTsp(testbase.getTspg().generateProblem());
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewProblemGenerator(ProblemTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> testbase) {
        testbase.setTspg(new ThreeSatProblemGenerator(TestParameters.NUMBER_OF_VARIABLES, TestParameters.NUMBER_OF_CLAUSES));
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewSolution(ProblemTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> testbase) {
        testbase.setTss(testbase.getTsg().generateSolution());
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewSolutionGenerator(ProblemTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> testbase) {
        testbase.setTsg(testbase.getTsp().getSolutionGenerator());
    }
}
