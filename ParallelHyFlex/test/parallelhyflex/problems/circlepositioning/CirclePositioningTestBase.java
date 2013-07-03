/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning;

import parallelhyflex.problems.ProblemTestBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningTestBase extends ProblemTestBase<CirclePositioningSolutionGenerator,CirclePositioningProblem,CirclePositioningProblemGenerator,CirclePositioningSolution> {

    

    @Override
    protected void renewProblem() {
        System.out.println("renewProblem");
        setTsp(getTspg().generateProblem());
    }

    @Override
    protected void renewProblemGenerator() {
        System.out.println("renewProblemGenerator");
        setTspg(new CirclePositioningProblemGenerator());
    }

    @Override
    protected void renewSolution() {
        System.out.println("renewSolution");
        setTss(getTsg().generateSolution());
    }

    @Override
    protected void renewSolutionGenerator() {
        System.out.println("renewGenerator");
        setTsg(getTsp().getSolutionGenerator());
    }
}
