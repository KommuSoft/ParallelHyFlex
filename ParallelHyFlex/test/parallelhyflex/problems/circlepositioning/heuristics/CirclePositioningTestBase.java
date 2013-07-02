/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;

/**
 *
 * @author kommusoft
 */
class CirclePositioningTestBase {

    protected CirclePositioningSolutionGenerator tsg;
    protected CirclePositioningProblem tsp;
    protected CirclePositioningProblemGenerator tspg;
    protected CirclePositioningSolution tss;

    protected void renewProblem() {
        tsp = tspg.generateProblem();
    }

    protected void renewProblemGenerator() {
        tspg = new CirclePositioningProblemGenerator();
    }

    protected void renewSolution() {
        tss = tsg.generateSolution();
    }

    protected void renewSolutionGenerator() {
        tsg = tsp.getSolutionGenerator();
    }
}
