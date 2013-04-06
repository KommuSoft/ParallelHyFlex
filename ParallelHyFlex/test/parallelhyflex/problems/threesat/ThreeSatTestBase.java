/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import parallelhyflex.TestParameters;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatTestBase {

    protected ThreeSatSolutionGenerator tsg;
    protected ThreeSatProblem tsp;
    protected ThreeSatProblemGenerator tspg;
    protected ThreeSatSolution tss;

    protected void renewProblem() {
        tsp = tspg.generateProblem();
    }

    protected void renewProblemGenerator() {
        tspg = new ThreeSatProblemGenerator(TestParameters.NUMBER_OF_VARIABLES, TestParameters.NUMBER_OF_CLAUSES);
    }

    protected void renewSolution() {
        tss = tsg.generateSolution();
    }

    protected void renewSolutionGenerator() {
        tsg = tsp.getSolutionGenerator();
    }
}
