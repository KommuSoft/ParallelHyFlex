/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning;

import java.util.logging.Logger;
import parallelhyflex.problems.ProblemTestBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningRenewalStrategy implements TestRenewalStrategy<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> {

    @Override
    public void renewProblem(ProblemTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> testbase) {
        testbase.setTsp(testbase.getTspg().generateProblem());
    }

    @Override
    public void renewProblemGenerator(ProblemTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> testbase) {
        testbase.setTspg(new CirclePositioningProblemGenerator());
    }

    @Override
    public void renewSolution(ProblemTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> testbase) {
        testbase.setTss(testbase.getTsg().generateSolution());
    }

    @Override
    public void renewSolutionGenerator(ProblemTestBase<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> testbase) {
        testbase.setTsg(testbase.getTsp().getSolutionGenerator());
    }
}
